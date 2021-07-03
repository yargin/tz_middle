package com.example.yarginy.tz_middle.models;

import java.util.Collection;
import java.util.Objects;

public class Topic {
    private Integer id;
    private String name;
    private String description;
    private Collection<Item> items;

    public Topic() {
    }

    public Topic(String name, String description) {
        this(0, name, description);
    }

    public Topic(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Topic{id=" + id + ", name='" + name + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id) && Objects.equals(name, topic.name) && Objects.equals(description,
                topic.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
