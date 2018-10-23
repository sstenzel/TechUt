package pl.sstenzel.ug.hsqldb.flowers.service;

import pl.sstenzel.ug.hsqldb.flowers.domain.Flower;

import java.sql.SQLException;
import java.util.List;

public interface FlowerService {

    boolean addFlower(Flower flower);
//    Flower getFlower(long id);
//    List<Flower> getAllFlowers();
    void deleteAllFlowers() throws SQLException;
//    Boolean deleteFlower(long id);
//    void addFlowers(List<Flower> flowers);

}
