package pl.sstenzel.ug.javaee.florists.hibernate.domain;


import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
@NamedQueries({
        @NamedQuery(name = "flower.all", query = "Select f from Flower f"),
        @NamedQuery(name = "person.byPin", query = "Select p from Person p where p.pin = :pin")
})
public class Flower {

    private long id;
    private String name;
    private Date dateOfPlant;
    private Boolean dogToxic;


    public Flower() {
    }
    public Flower(String name, String dateOfPlant, Boolean dogToxic, int petalAmount) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        this.dateOfPlant = new Date(f.parse(dateOfPlant).getTime());
        this.name = name;
        this.dogToxic = dogToxic;
    }

    public Flower(String name, Date dateOfPlant, Boolean dogToxic, int petalAmount) {
        this.name = name;
        this.dateOfPlant = dateOfPlant;
        this.dogToxic = dogToxic;

    }

    @Id
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

    public Date getPickDate() {
        return dateOfPlant;
    }

    public void setPickDate(Date dateOfPlant) {
        this.dateOfPlant = dateOfPlant;
    }

    public Boolean getDogToxic() {
        return dogToxic;
    }

    public void setDogToxic(Boolean dogToxic) {
        this.dogToxic = dogToxic;
    }

    public String toString() {
        return "Id: " + getId() +", Name: "+ getName() + ", PickDate: " + getPickDate() +
                ", Toxic for dogs: " + getDogToxic();
    };
}
