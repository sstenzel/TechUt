package pl.sstenzel.ug.florists.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.sstenzel.ug.florists.domain.Description;
import pl.sstenzel.ug.florists.domain.Flower;
import pl.sstenzel.ug.florists.domain.Person;
import pl.sstenzel.ug.florists.domain.Type;

import java.sql.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class FloristsServiceTest {

    private final String fName1 = "Jukka";
    private final String fName2 = "Dracena";
    private final String fName3 = "Hoja";
    private final String fName4 = "Orchidea";

    private final String pName1 = "Mietek";
    private final String pName2 = "Anka";
    private final String pName3 = "Zdzisiu";

    private final String desc1 = "Podlewac dwa razy w tygodniu";
    private final String desc2 = "Nie trzymać na słońcu";

    private final String tName1 = "Palmiaste";
    private final String tName2 = "Inne";

    private final Date date1 =  new Date(2002-1900,9, 11);
    private final Date date2 =  new Date(2017-1900, 10, 1);


    @Autowired
    FloristsService fs;


// ===========================================================================


    @Test
    public void addFlowersCheck() {
        int size = fs.getAllFlowers().size();
        Flower[] flowers = {
                new Flower(fName1, date1, true),
                new Flower(fName2, date2, false)
        };
        fs.addFlowers(flowers);
        assertEquals(size+2, fs.getAllFlowers().size());
    }

    @Test
    public void addFlowerCheck(){
        Flower flower = new Flower(fName4, date1, true);
        fs.addFlower(flower);
        Flower retrieved = fs.getFlower(flower.getId());
        assertEquals(fName4, retrieved.getName());
    }

    @Test
    public void updateFlowerCheck(){
        Flower flower = new Flower(fName4, date1, false);
        fs.addFlower(flower);
        long id = flower.getId();
        flower.setName(fName3);
        fs.updateFlower(flower);
        Flower retrieved = fs.getFlower(id);
        assertEquals(fName3, retrieved.getName());
    }

    @Test
    public void deleteFlowerCheck() {
        Flower flower = new Flower(fName4, date1, false);
        fs.addFlower(flower);
        long id = flower.getId();
        fs.deleteFlower(id);
        assertNull( fs.getFlower(id));
    }


// ===========================================================================


    @Test
    public void careDescription(){
        Flower flower = new Flower(fName4, date1, true);
        fs.addFlower(flower);
        long id = flower.getId();

        Description description1 = new Description(desc1, true, true);
        fs.addDescription(description1);
        fs.setCareDescription(flower,description1);

        Flower retrieved = fs.getFlower(id);
        assertEquals(desc1, retrieved.getCareDescription().getDescription());

        fs.removeCareDescription(retrieved);
        assertNull(retrieved.getCareDescription());
    }


    @Test
    public void watermanCheck() {
        Person person1 = new Person(pName1);
        Person person2 = new Person(pName2);
        fs.addPerson(person1);
        fs.addPerson(person2);

        Flower flower = new Flower(fName4, date1, true);
        fs.addFlower(flower);

        fs.addWaterman(flower, person1);
        fs.addWaterman(flower, person2);

        assertEquals(2, fs.getFlower(flower.getId()).getPersons().size());

        fs.removeWaterman(flower, person1);
        assertEquals(1, fs.getFlower(flower.getId()).getPersons().size());
    }



    @Test
    public void typeCheck() {
        Type type1 = new Type(tName1);
        fs.addType(type1);

        Flower flower = new Flower(fName4, date1, true);
        fs.addFlower(flower);
        fs.setType(flower,type1);

        Flower retrieved = fs.getFlower(flower.getId());
        assertEquals(tName1, retrieved.getType().getName());

        fs.removeType(flower);
        retrieved = fs.getFlower(flower.getId());
        assertNull(retrieved.getType());
    }


    @Test
    public void findFlowersByDateCheck() {
        Date superDate1 =  new Date(1000, 11, 5);
        Date superDate2 =  new Date(1001, 18, 7);
        Date from =  new Date(999, 11, 5);
        Date to =  new Date(1002, 18, 7);

       int size = fs.findFlowersByDate(from, to).size();

        Flower flower1 = new Flower(fName4, date1, true);
        Flower flower2 = new Flower(fName4, superDate1, true);
        Flower flower3 = new Flower(fName4, superDate1, true);
        Flower flower4 = new Flower(fName4, superDate2, true);
        fs.addFlower(flower1);
        fs.addFlower(flower2);
        fs.addFlower(flower3);
        fs.addFlower(flower4);

        List<Flower> flowers = fs.findFlowersByDate(from, to);
        assertEquals(size+3, flowers.size());

    }

    @Test
    public void findByCareDescriptionCheck() {
        Flower flower1 = new Flower(fName4, date1, true);
        Flower flower2 = new Flower(fName4, date2, true);
        Flower flower3 = new Flower(fName4, date2, true);
        fs.addFlower(flower1);
        fs.addFlower(flower2);
        fs.addFlower(flower3);

        String descr1 = "Jakis opis";
        String descr2 = "Zupelnie inny opis";

        int size = fs.findByCareDescription("opis").size();

        Description description1 = new Description(desc2, true, true);
        Description description2 = new Description(descr1, true, true);
        Description description3 = new Description(descr2, true, true);
        fs.addDescription(description1);
        fs.addDescription(description2);
        fs.addDescription(description3);

        fs.setCareDescription(flower1 ,description1);
        fs.setCareDescription(flower2 ,description2);
        fs.setCareDescription(flower3 ,description3);

        List<Flower> flowers = fs.findByCareDescription("opis");
        assertEquals(size+2, flowers.size());
    }

    @Test
    public void getPersonByFlowerAmoutCheck() {
        Person person1 = new Person(pName1);
        Person person2 = new Person(pName2);
        Person person3 = new Person(pName3);
        fs.addPerson(person1);
        fs.addPerson(person2);
        fs.addPerson(person3);

        int size = fs.getPersonByFlowerAmout(2).size();

        Flower flower1 = new Flower(fName4, date1, true);
        Flower flower2 = new Flower(fName4, date2, true);
        Flower flower3 = new Flower(fName4, date2, true);
        fs.addFlower(flower1);
        fs.addFlower(flower2);
        fs.addFlower(flower3);

        fs.addWaterman(flower1, person1);
        fs.addWaterman(flower2, person1);
        fs.addWaterman(flower2, person1);
        fs.addWaterman(flower2, person2);
        fs.addWaterman(flower1, person2);
        fs.addWaterman(flower3, person3);

        flower1 = new Flower(fName4, date1, true);
        flower2 = new Flower(fName4, date2, true);
        flower3 = new Flower(fName4, date2, true);
        fs.addFlower(flower1);
        fs.addFlower(flower2);
        fs.addFlower(flower3);
        fs.addWaterman(flower1, person1);
        fs.addWaterman(flower2, person2);
        fs.addWaterman(flower3, person3);

        List<Person> people = fs.getPersonByFlowerAmout(2);

        assertEquals(size+2,people.size());
        assertEquals(pName1,people.get(0).getName());

    }


}