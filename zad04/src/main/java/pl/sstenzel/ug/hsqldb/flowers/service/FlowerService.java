package pl.sstenzel.ug.hsqldb.flowers.service;

import pl.sstenzel.ug.hsqldb.flowers.domain.Flower;

import java.sql.SQLException;
import java.util.List;

public interface FlowerService {

    boolean add(Flower flower);
    Flower get(long id);
    List<Flower> getAll();

    void deleteAll() throws SQLException;

    boolean delete(long id) throws SQLException;

    /* transactional */
    boolean addMany(List<Flower> flowers);

    List<Flower> getByDogToxic (boolean toxic);
//    List<Flower> getOlderThan (String date) throws ParseException;
}
