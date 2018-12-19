package pl.sstenzel.ug.javaee.florists.hibernate.domain;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "person.all", query = "Select p from Person p"),
        @NamedQuery(name = "person.byPin", query = "Select p from Person p where p.pin = :pin")
})
public class Person {
    private long id;
    private String name;
    private String surname;
    private List<Flower> flowers = new ArrayList<Flower>();

    public Person() {}

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Flower> getFlowers() {
        return flowers;
    }
    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }

    public void addFlower(Flower flower){
        this.flowers.add(flower);
    }
    public void removeFlower(Flower flower){
        this.flowers.remove(flower);
    }
}
