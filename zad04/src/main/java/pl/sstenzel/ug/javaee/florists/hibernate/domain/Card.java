package pl.sstenzel.ug.javaee.florists.hibernate.domain;

public class Card {

    private long id;
    private boolean onPaper;
    private boolean electronicVersion;
    private String description;

    public Card(long id, boolean onPaper, boolean electronicVersion, String description) {
        this.id = id;
        this.onPaper = onPaper;
        this.electronicVersion = electronicVersion;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isOnPaper() {
        return onPaper;
    }

    public void setOnPaper(boolean onPaper) {
        this.onPaper = onPaper;
    }

    public boolean isElectronicVersion() {
        return electronicVersion;
    }

    public void setElectronicVersion(boolean electronicVersion) {
        this.electronicVersion = electronicVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
