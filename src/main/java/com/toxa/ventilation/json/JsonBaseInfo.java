package com.toxa.ventilation.json;

public class JsonBaseInfo {
    private String name;
    private String country;
    private String numberHeads;
    private String numberHouse;
    private String cageName;

    public JsonBaseInfo() {
    }

    public JsonBaseInfo(String name, String country, String numberHeads, String numberHouse, String cageName) {
        this.name = name;
        this.country = country;
        this.numberHeads = numberHeads;
        this.numberHouse = numberHouse;
        this.cageName = cageName;
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

    public String getNumberHeads() {
        return numberHeads;
    }

    public void setNumberHeads(String numberHeads) {
        this.numberHeads = numberHeads;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getCageName() {
        return cageName;
    }

    public void setCageName(String cageName) {
        this.cageName = cageName;
    }
}
