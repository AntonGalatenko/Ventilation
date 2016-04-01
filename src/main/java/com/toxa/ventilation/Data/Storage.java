package com.toxa.ventilation.Data;

import java.io.Serializable;

public class Storage implements Serializable{
    private int capacity;
    private String description;

    public Storage(int capacity, String description) {
        this.capacity = capacity;
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
