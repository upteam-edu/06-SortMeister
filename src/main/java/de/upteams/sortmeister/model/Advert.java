package de.upteams.sortmeister.model;

public class Advert {

    private Long id;
    private String title;
    private String photo;
    private String description;

    public Advert() {
    }

    public Advert(String title, String photo, String description) {
        this.title = title;
        this.photo = photo;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
