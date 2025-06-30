package de.upteams.sortmeister.model;

public class Container {

    private Long id;
    private String name;
    private String color;
    private String description;

    public Container() {
    }

    public Container(String name, String color, String description) {
        this.name = name;
        this.color = color;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

