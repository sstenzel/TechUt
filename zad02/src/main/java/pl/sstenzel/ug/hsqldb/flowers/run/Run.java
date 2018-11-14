package pl.sstenzel.ug.hsqldb.flowers.run;

import pl.sstenzel.ug.hsqldb.flowers.domain.Flower;
import pl.sstenzel.ug.hsqldb.flowers.service.FlowerServiceJDBC;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Run {
    public static void main (String []args)  throws SQLException, ParseException {
        FlowerServiceJDBC flowerService = new FlowerServiceJDBC();

        System.out.println("Add flower:");
        flowerService.addFlower(new Flower("Fiolek", "2018-10-21", false, 5));
        flowerService.printFlowers(flowerService.getAllFlowers());

        System.out.println("\nAdd flowers");
        List<Flower> flowers = new ArrayList<>();
        flowers.add(new Flower("Gozdzik", "2018-09-11", true, 4));
        flowers.add(new Flower("Margaretka", "2018-11-13", true, 3));
        flowerService.addFlowers(flowers);
        flowerService.printFlowers(flowerService.getAllFlowers());

        System.out.println("\nGet flower id = 1");
        System.out.println(flowerService.getFlower(1));

        System.out.println("\nDelete flower id = 1");
        System.out.println(flowerService.deleteFlower(1));
        flowerService.printFlowers(flowerService.getAllFlowers());

        System.out.println("\nDelete all");
        flowerService.deleteAllFlowers();
        flowerService.printFlowers(flowerService.getAllFlowers());

    }
}
