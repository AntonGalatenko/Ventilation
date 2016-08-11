package com.toxa.ventilation.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "factory")
public class Factory {

    public Factory() {
    }

    public Factory(int year, String name, String country, String cage, int numberOfHeads, double length, double width,
                   double heightMin, double heightMax, String link) {
        this.year = year;
        this.name = name;
        this.country = country;
        this.cage = cage;
        this.numberOfHeads = numberOfHeads;
        this.length = length;
        this.width = width;
        this.heightMin = heightMin;
        this.heightMax = heightMax;
        this.link = link;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int year;
    private String name;
    private String country;
    private String cage;

    @Column(name = "number_of_heads")
    private int numberOfHeads;

    private double length;
    private double width;

    @Column(name = "height_min")
    private double heightMin;

    @Column(name = "height_max")
    private double heightMax;

    private String link;
    private boolean drawing;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCage() {
        return cage;
    }

    public void setCage(String cage) {
        this.cage = cage;
    }

    public int getNumberOfHeads() {
        return numberOfHeads;
    }

    public void setNumberOfHeads(int numberOfHeads) {
        this.numberOfHeads = numberOfHeads;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeightMin() {
        return heightMin;
    }

    public void setHeightMin(double heightMin) {
        this.heightMin = heightMin;
    }

    public double getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(double heightMax) {
        this.heightMax = heightMax;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isDrawing() {
        return drawing;
    }

    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public boolean isSimilar(int numberOfHeads, String cage){
        if(! this.cage.equals(cage))
            return false;
        if(! ((this.numberOfHeads >= numberOfHeads * 0.95) && (this.numberOfHeads <= numberOfHeads * 1.05)))
            return false;

        return true;
    }
}
