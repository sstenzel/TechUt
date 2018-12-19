import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.sstenzel.ug.javaee.florists.hibernate.domain.Flower;
import pl.sstenzel.ug.javaee.florists.hibernate.domain.Person;
import pl.sstenzel.ug.javaee.florists.hibernate.service.FloristsService;

import java.text.ParseException;
import java.util.List;
import static org.junit.Assert.assertEquals;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class FloristsServiceHibernateImplTest {

    private final String fName1 = "Jukka";
    private final String fName2 = "Dracena";
    private final String fName3 = "Hoja";
    private final String pName1 = "Mietek";
//    private final String pName2 = "Anka";

    @Autowired
    FloristsService fs;

    @After
    public void clear(){
        fs.clear();
    }

    @Test
    public void addFlowerCheck() throws ParseException {
        Flower f1 =  new Flower(fName1, "2018-09-11", true);
        fs.addFlower(f1);
        Flower f2 = new Flower(fName2, "2017-10-11", false);
        fs.addFlower(f2);

        Flower retrieved1 = fs.getFlower(1);
        assertEquals(fName1, retrieved1.getName());

        Flower retrieved2 = fs.getFlower(2);
        assertEquals(fName2, retrieved2.getName());
    }

    @Test
    public void getFlowersCheck() {
        List<Flower> flowers = fs.getAllFlowers();
        assertEquals(2, flowers.size());
    }

    @Test
    public void updateFlowerCheck() throws ParseException{
        Flower f3 =  new Flower(fName3, "2018-09-11", true);
        fs.updateFlower(2, f3);
        Flower retrieved = fs.getFlower(2);
        assertEquals(fName3, retrieved.getName());
    }

    @Test
    public void deleteFlowerCheck() {
        Flower toDelete = fs.getFlower(2);
        fs.deleteFlower(2);
        Flower deleted = fs.getFlower(2);
        assertEquals(null, deleted);
    }

    @Test
    public void addWatermanCheck() {
        Person p1 =  new Person(pName1, "Kropkowski");
        fs.addWaterman(1, p1);

        Flower flower = fs.getFlower(1);
        assertEquals(pName1, flower.getWatermen().get(0).getName());
    }

    @Test
    public void removeWaterman(){
        fs.removeWaterman(1,1);
        Flower flower = fs.getFlower(1);
        assertEquals(0, flower.getWatermen().size());
    }

    @Test
    public void deletePerson(){
        Person person = fs.getPerson(1);
        assertEquals(pName1, person.getName());

        fs.deletePerson(1);
        Person deleted = fs.getPerson(1);
        assertEquals(null, deleted);
    }
}