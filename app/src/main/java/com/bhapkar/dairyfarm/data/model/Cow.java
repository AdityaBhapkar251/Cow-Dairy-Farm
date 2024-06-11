package com.bhapkar.dairyfarm.data.model;

public class Cow {
    private String id;
    private String name;
    private String weight;
    private String color;

    public Cow(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
