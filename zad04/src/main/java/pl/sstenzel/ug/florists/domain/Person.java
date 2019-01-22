package pl.sstenzel.ug.florists.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@NamedQueries({
        @NamedQuery(name = "person.findByFlowers",
                query = "SELECT p FROM Person p JOIN p.flowers f GROUP BY p.id HAVING COUNT(f) > :amount")
})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "persons", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Flower> flowers = new HashSet<Flower>();


    public Person() {super();}

    public Person(String name) {
        this.name = name;
    }


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

}
