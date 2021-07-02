package com.example.yarginy.tz_middle.models;

public class Item {
    private Integer id;
    private String name;
    private int order;
    private Topic topic;

    public Item() {
    }

    public Item(String name, int order, Topic topic) {
        this(0, name, order, topic);
    }

    public Item(Integer id, String name, int order, Topic topic) {
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Item{id=" + id + ", name='" + name + "'}";
    }
}
