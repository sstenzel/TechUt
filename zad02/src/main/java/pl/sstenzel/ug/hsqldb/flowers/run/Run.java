package pl.sstenzel.ug.hsqldb.flowers.run;

import pl.sstenzel.ug.hsqldb.flowers.domain.Flower;
import pl.sstenzel.ug.hsqldb.flowers.service.FlowerServiceJDBC;

import java.sql.Date;
import java.sql.SQLException;

public class Run {
    public static void main (String []args)  throws SQLException {
        FlowerServiceJDBC flowerService = new FlowerServiceJDBC();
        Flower flower = new Flower("Fiolek",new Date(2018,10,23), false, 5);
        System.out.println( flowerService.addFlower(flower));
//        flowerService.getAllFlowers();
    }
}
