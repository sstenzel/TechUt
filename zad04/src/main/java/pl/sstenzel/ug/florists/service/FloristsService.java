package pl.sstenzel.ug.florists.service;

import pl.sstenzel.ug.florists.domain.*;

import java.sql.Date;
import java.util.List;

public interface FloristsService {

    public void addFlower(Flower flower);
    public Flower getFlower(long id);
    public List<Flower> getAllFlowers();
    public void deleteFlower(long id);
    public Flower updateFlower(Flower flower);
    public void addFlowers(Flower[] flowers);

    public void addDescription(Description description);
    public void addType(Type type);
    public void addPerson(Person person);

    public void setCareDescription(Flower flower, Description description);
    public void removeCareDescription(Flower flower);

    public void addWaterman(Flower flower, Person person);
    public void removeWaterman(Flower flower, Person person);

    public void setType(Flower flower, Type type);
    public void removeType(Flower flower);

    public List<Flower> findFlowersByDate (Date from, Date to);
    public List<Flower> findByCareDescription (String description);
    public List<Person> getPersonByFlowerAmout (long amount);

}
