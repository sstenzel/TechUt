package pl.sstenzel.ug.javaee.florists.hibernate.domain;


import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    private Card careDescription;
    private List<Fertilization> fertilizations = new ArrayList<Fertilization>();
    private List<Person> watermen = new ArrayList<Person>();

    public Flower() {
    }
    public Flower(String name, String dateOfPlant, Boolean dogToxic) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        this.dateOfPlant = new Date(f.parse(dateOfPlant).getTime());
        this.name = name;
        this.dogToxic = dogToxic;
    }

    public Flower(String name, Date dateOfPlant, Boolean dogToxic) {
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

    @Temporal(TemporalType.DATE)
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Card getCareDescription() {
        return careDescription;
    }
    public void setCareDescription(Card careDescription) {
        this.careDescription = careDescription;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Fertilization> getFertilizations() {
        return fertilizations;
    }
    public void setFertilizations(List<Fertilization> fertilizations) {
        this.fertilizations = fertilizations;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Person> getWatermen() {
        return watermen;
    }
    public void setWaterman(List<Person> watermen) {
        this.watermen = watermen;
    }

    public void addWaterman(Person waterman){
        this.watermen.add(waterman);
    }
    public void removeWaterman (Person waterman) {
        this.watermen.remove(waterman);
    }

}
