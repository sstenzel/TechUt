package pl.sstenzel.ug.florists.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Description {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private Boolean onPaper;
    private Boolean byEmail;

    public Description() {
        super();
    }

    public Description(String description, Boolean onPaper, Boolean byEmail) {
        this.description = description;
        this.onPaper = onPaper;
        this.byEmail = byEmail;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOnPaper() {
        return onPaper;
    }

    public void setOnPaper(Boolean onPaper) {
        this.onPaper = onPaper;
    }

    public Boolean getByEmail() {
        return byEmail;
    }

    public void setByEmail(Boolean byEmail) {
        this.byEmail = byEmail;
    }
}
