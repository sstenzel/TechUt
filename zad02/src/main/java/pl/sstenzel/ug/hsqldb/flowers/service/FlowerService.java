package pl.sstenzel.ug.hsqldb.flowers.service;

import pl.sstenzel.ug.hsqldb.flowers.domain.Flower;

import java.sql.SQLException;
import java.util.List;

public interface FlowerService {

    boolean addFlower(Flower flower);
    Flower getFlower(long id);
    List<Flower> getAllFlowers();
    void deleteAllFlowers() throws SQLException;
    boolean deleteFlower(long id) throws SQLException;
    boolean addFlowers(List<Flower> flowers);

}
