package pl.sstenzel.ug.javaee.florists.hibernate.domain;

import java.sql.Date;

public class Fertilization {
    private long id;
    private Date date;
    private String fertilizer;

    public Fertilization(long id, Date date, String fertilizer) {
        this.id = id;
        this.date = date;
        this.fertilizer = fertilizer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}
