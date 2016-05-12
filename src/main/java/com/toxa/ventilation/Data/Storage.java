package com.toxa.ventilation.Data;

import java.io.Serializable;

public class Storage implements Serializable{
    private double capacity;
    private String description;

    public Storage(double capacity, String description) {
        this.capacity = capacity;
        this.description = description;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
