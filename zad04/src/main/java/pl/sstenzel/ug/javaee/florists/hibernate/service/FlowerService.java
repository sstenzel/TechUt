package pl.sstenzel.ug.javaee.florists.hibernate.service;

import pl.sstenzel.ug.javaee.florists.hibernate.domain.Flower;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface FlowerService {

    void addFlower(Flower flower);
    Flower getFlower(long id);
    List<Flower> getAllFlowers();
    void deleteAllFlowers() throws SQLException;
    void deleteFlower(long id) throws SQLException;
    Flower updateFlower(long id) throws SQLException;

    /* transactional */
    void addFlowers(List<Flower> flowers);

//    List<Flower> getFlowersByDogToxic (boolean toxic);
//    List<Flower> getFlowersOlderThan (String date) throws ParseException;
}
