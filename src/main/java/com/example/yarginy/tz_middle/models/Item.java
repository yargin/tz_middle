package com.example.yarginy.tz_middle.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    private Integer id;
    private String name;
    private Integer order;
    @JsonBackReference
    private Topic topic;

    public Item() {
    }

    public Item(String name, Integer order, Topic topic) {
        this(0, name, order, topic);
    }

    public Item(Integer id, String name, Integer order, Topic topic) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.topic = topic;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(order, item.order) &&
                Objects.equals(topic, item.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, order, topic);
    }

    @Override
    public String toString() {
        return "Item{id=" + id + ", name='" + name + "'}";
    }
}
