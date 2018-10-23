package pl.sstenzel.ug.hsqldb.flowers.domain;


import java.sql.Date;

public class Flower {

    private long id;
    private String name;
    private Date pickDate;
    private Boolean dogToxic;
    private int petalAmount;

    public Flower() {
    }

    public Flower(String name, Date pickDate, Boolean dogToxic, int petalAmount) {
        super();
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
}
