package com.toxa.ventilation.json;

public class JsonEquipment {

    private String name;
    private String description;
    private String number;

    public JsonEquipment() {
    }

    public JsonEquipment(String name, String description, String number) {
        this.name = name;
        this.description = description;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString(){
        return name + " : " + description + " : " + number;
    }
}
