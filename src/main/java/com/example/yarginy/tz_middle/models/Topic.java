package com.example.yarginy.tz_middle.models;

public class Topic {
    private int id;
    private String name;
    private String description;

    public Topic() {
    }

    public Topic(String name, String description) {
        this(0, name, description);
    }

    public Topic(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Topic{id=" + id + ", name='" + name + "'}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
