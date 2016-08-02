package com.toxa.ventilation.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "factory")
public class Factory {

    public Factory() {
    }

    public Factory(String name, String country, String cage, int numberOfHeads, double length, double width,
                   double heightMin, double heightMax) {
        this.name = name;
        this.country = country;
        this.cage = cage;
        this.numberOfHeads = numberOfHeads;
        this.length = length;
        this.width = width;
        this.heightMin = heightMin;
        this.heightMax = heightMax;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;

        if(this.getClass() != obj.getClass())
            return false;

        Factory other = (Factory) obj;

        return (this.name.equals(other.name)) && (this.country.equals(other.country)) && (this.cage.equals(other.cage)) &&
                (this.numberOfHeads == other.numberOfHeads) && (this.length == other.length) &&
                (this.width == other.width) && (this.heightMin == other.heightMin) && (this.heightMax == other.heightMax);

    }

    @Override
    public int hashCode(){
        int hash = name.hashCode() + country.hashCode() + cage.hashCode();
        int hash1 = (int)(numberOfHeads * length * width * heightMin + heightMax);

        return hash + hash1;
    }

    public boolean isSimilar(Factory factory){
        if(! this.cage.equals(factory.cage))
            return false;
        if(! (this.numberOfHeads >= factory.numberOfHeads * 0.95) && (this.numberOfHeads <= factory.numberOfHeads * 1.05))
            return false;

        return true;
    }

    public boolean isNameEquals(Factory factory){
        if(this.name.equals(factory.name))
            return true;

        return false;
    }
}
