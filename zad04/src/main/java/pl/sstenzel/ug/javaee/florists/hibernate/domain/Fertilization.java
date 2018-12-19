package pl.sstenzel.ug.javaee.florists.hibernate.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;

public class Fertilization {
    private long id;
    private Date date;
    private String fertilizer;
    private Flower flower;

    public Fertilization(long id, Date date, String fertilizer) {
        this.id = id;
        this.date = date;
        this.fertilizer = fertilizer;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }
}
