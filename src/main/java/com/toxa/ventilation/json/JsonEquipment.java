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
        System.out.println("setName " + name);
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        System.out.println("setDescription " + description);
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        System.out.println("setNumber " + number);
        this.number = number;
    }
}
