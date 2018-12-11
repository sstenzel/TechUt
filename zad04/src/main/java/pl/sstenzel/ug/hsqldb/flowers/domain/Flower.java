package pl.sstenzel.ug.hsqldb.flowers.domain;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Flower {

    private long id;
    private String name;
    private Date pickDate;
    private Boolean dogToxic;
    private int petalAmount;

    public Flower() {
    }
    public Flower(String name, String pickDate, Boolean dogToxic, int petalAmount) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        this.pickDate = new Date(f.parse(pickDate).getTime());
        this.name = name;
        this.dogToxic = dogToxic;
        this.petalAmount = petalAmount;
    }

    public Flower(String name, Date pickDate, Boolean dogToxic, int petalAmount) {
        this.name = name;
        this.pickDate = pickDate;
        this.dogToxic = dogToxic;
        this.petalAmount = petalAmount;
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

    public Date getPickDate() {
        return pickDate;
    }

    public void setPickDate(Date pickDate) {
        this.pickDate = pickDate;
    }

    public Boolean getDogToxic() {
        return dogToxic;
    }

    public void setDogToxic(Boolean dogToxic) {
        this.dogToxic = dogToxic;
    }

    public int getPetalAmount() {
        return petalAmount;
    }

    public void setPetalAmount(int petalAmount) {
        this.petalAmount = petalAmount;
    }

    public String toString() {
        return "Id: " + getId() +", Name: "+ getName() + ", PickDate: " + getPickDate() +
                ", Toxic for dogs: " + getDogToxic() + ", Amount of petals: "+ getPetalAmount();
    };
}
