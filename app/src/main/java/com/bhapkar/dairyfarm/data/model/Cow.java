package com.bhapkar.dairyfarm.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cow_table")
public class Cow {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String cowId;
    private String name;
    private String description;
    private String imageUrl;

    public Cow(String cowId, String name) {
        this.cowId = cowId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCowId() {
        return cowId;
    }

    public void setCowId(String cowId) {
        this.cowId = cowId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUri() {
        return imageUrl;

    }
}
