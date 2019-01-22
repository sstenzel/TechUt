package pl.sstenzel.ug.florists.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "flower.dateBetween",
                query = "SELECT f FROM Flower f WHERE f.dateOfPlant >= :from AND f.dateOfPlant <= :to"),
        @NamedQuery(name = "flower.findByCareDescription",
                query = "SELECT f FROM Flower f JOIN f.careDescription d " +
                        "WHERE LOWER(d.description) LIKE LOWER(CONCAT('%', :description, '%'))"),
})
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date dateOfPlant;
    private Boolean dogToxic;

    @OneToOne(cascade = {CascadeType.ALL})
    private Description careDescription = null;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Type type;


//    @OneToMany(mappedBy = "flower")
//    private Set<Fertilization> fertilizations = new HashSet<Fertilization>();


    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Person_Flower",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "flower_id") } )
    private List<Person> persons = new ArrayList<Person>();

    public Flower() {
    }


    public Flower(String name, Date dateOfPlant, Boolean dogToxic) {
        this.name = name;
        this.dateOfPlant = dateOfPlant;
        this.dogToxic = dogToxic;
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


    public Date getDateOfPlant() {
        return dateOfPlant;
    }
    public void setDateOfPlant(Date dateOfPlant) {
        this.dateOfPlant = dateOfPlant;
    }


    public Boolean getDogToxic() {
        return dogToxic;
    }
    public void setDogToxic(Boolean dogToxic) {
        this.dogToxic = dogToxic;
    }

    public Description getCareDescription() {
        return careDescription;
    }
    public void setCareDescription(Description careDescription) {
        this.careDescription = careDescription;
    }

//    public Set<Fertilization> getFertilizations() {
//        return fertilizations;
//    }
//    public void setFertilizations(Set<Fertilization> fertilizations) {
//        this.fertilizations = fertilizations;
//    }
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
