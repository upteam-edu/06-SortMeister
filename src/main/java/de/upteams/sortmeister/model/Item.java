package de.upteams.sortmeister.model;

public class Item {

    private Long id;
    private String name;
    private Container container;

    public Item() {
    }

    public Item(String name, Container container) {
        this.name = name;
        this.container = container;
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


    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }




}
