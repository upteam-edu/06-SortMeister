package de.upteams.sortmeister.model;

public class Item {

    private Long id;
    private String name;
    private Long containerId;

    public Item() {
    }

    public Item( String name, Long containerId) {
        this.name = name;
        this.containerId = containerId;
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

    public Long getContainerId() {
        return containerId;
    }

    public void setContainerId(Long containerId) {
        this.containerId = containerId;
    }
}
