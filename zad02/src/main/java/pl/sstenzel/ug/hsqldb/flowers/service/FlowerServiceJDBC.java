package pl.sstenzel.ug.hsqldb.flowers.service;

import pl.sstenzel.ug.hsqldb.flowers.domain.Flower;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerServiceJDBC implements FlowerService{

    private Connection connection;
    private String url = "jdbc:hsqldb:hsql://localhost/workdb";

    private String createTableFlower = "CREATE TABLE IF NOT EXISTS Flower(id bigint GENERATED BY DEFAULT AS IDENTITY, name VARCHAR(20), pickDate DATE, dogToxic BOOLEAN, petalAmount integer)";

    private String addFlowerStmt = "INSERT INTO Flower (name, pickDate, dogToxic, petalAmount) VALUES (?, ?, ?, ?)";
    private String deleteFlowerStmt = "DELETE FROM Flower WHERE id=?";
    private String getFlowerStmt = "SELECT id, name, pickDate, dogToxic, petalAmount FROM Flower WHERE id=?";
    private String getAllFlowersStmt = "SELECT id, name, pickDate, dogToxic, petalAmount FROM Flower";
    private String deleteAllFlowersStmt = "DELETE FROM Flower";

    private PreparedStatement addFlowerPStmt;
    private PreparedStatement deleteFlowerPStmt;
    private PreparedStatement getFlowerPStmt;
    private PreparedStatement getAllFlowersPStmt;
    private PreparedStatement deleteAllFlowersPStmt;

    private Statement statement;

    public FlowerServiceJDBC() throws SQLException {
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement();

        ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
        boolean tableExists = false;
        while (rs.next()) {
            if ("Flowers".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                tableExists = true;
                break;
            }
        }
        if (!tableExists)
            statement.executeUpdate(createTableFlower);

        addFlowerPStmt = connection.prepareStatement(addFlowerStmt);
        deleteFlowerPStmt = connection.prepareStatement(deleteFlowerStmt);
        getFlowerPStmt = connection.prepareStatement(getFlowerStmt);
        getAllFlowersPStmt = connection.prepareStatement(getAllFlowersStmt);
        deleteAllFlowersPStmt = connection.prepareStatement(deleteAllFlowersStmt);
    }

    Connection getConnection() {
        return connection;
    }

    @Override
    public boolean addFlower(Flower flower){
        boolean result = false;
        try {
            addFlowerPStmt.setString(1, flower.getName());
            addFlowerPStmt.setDate(2, (Date) flower.getPickDate());
            addFlowerPStmt.setBoolean(3, flower.getDogToxic());
            addFlowerPStmt.setInt(4, flower.getPetalAmount());

            int noOfRecords = addFlowerPStmt.executeUpdate();

            if (noOfRecords == 1){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    };


    public Flower getFlower(long id){
        Flower flower = new Flower();
        try {
            getFlowerPStmt.setInt(1, (int)id);
            ResultSet rs = getFlowerPStmt.executeQuery();

            while (rs.next()) {
                flower.setId(rs.getInt("id"));
                flower.setName(rs.getString("name"));
                flower.setPickDate(rs.getDate("pickDate"));
                flower.setDogToxic(rs.getBoolean("dogToxic"));
                flower.setPetalAmount(rs.getInt("petalAmount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flower;
    };
    
    
    
    public boolean deleteFlower(long id) throws  SQLException{
        deleteFlowerPStmt.setInt(1,(int) id);
        if (deleteFlowerPStmt.executeUpdate() > 0)
            return true;
        return false;
    };

    public boolean addFlowers(List<Flower> flowers){
        try{
            for(Flower flower: flowers){
                addFlowerPStmt.setString(1, flower.getName());
                addFlowerPStmt.setDate(2, (Date) flower.getPickDate());
                addFlowerPStmt.setBoolean(3, flower.getDogToxic());
                addFlowerPStmt.setInt(4, flower.getPetalAmount());
                addFlowerPStmt.executeUpdate();
            }
        }catch(SQLException e){
            try {
                connection.rollback();
                return false;
            } catch (SQLException ee){
                e.printStackTrace();
                // tu sie należy martwić
            }
        }
        return true;
    };



    public List<Flower> getAllFlowers(){
        List<Flower> flowers = new ArrayList<Flower>();

        try {
            ResultSet rs = getAllFlowersPStmt.executeQuery();

            while (rs.next()) {
                Flower p = new Flower();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPickDate(rs.getDate("pickDate"));
                p.setDogToxic(rs.getBoolean("dogToxic"));
                p.setPetalAmount(rs.getInt("petalAmount"));
                flowers.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flowers;
    };


    public void deleteAllFlowers() throws SQLException{
        deleteAllFlowersPStmt.executeUpdate();
    };

    public void printFlowers (List<Flower> flowers){
        for(Flower flower: flowers){
            System.out.println(flower);
        }
    }

    public void printFlower (Flower flower) {
        System.out.println(flower);
    }


}