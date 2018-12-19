package pl.sstenzel.ug.javaee.florists.hibernate.service;

import pl.sstenzel.ug.javaee.florists.hibernate.domain.Flower;
import pl.sstenzel.ug.javaee.florists.hibernate.domain.Person;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface FloristsService {

    public void addFlower(Flower flower);
    public Flower getFlower(long id);
    public List<Flower> getAllFlowers();
    public void clear();
    public void deleteFlower(long id);
    public Flower updateFlower(long id, Flower flower);

    /* transactional */
    public void addFlowers(List<Flower> flowers);

    public void addWaterman(long flowerId, Person waterman);
    public void removeWaterman(long flowerId, long personId);

    public Person getPerson(long id);

    public void deletePerson(long id);


//    List<Flower> getFlowersByDogToxic (boolean toxic);
//    List<Flower> getFlowersOlderThan (String date) throws ParseException;
}
