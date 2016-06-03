package com.toxa.ventilation.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class JsonEquipment {

    @JsonProperty("Описание")
    private String description;

    @JsonProperty("Тип")
    private String name;

    @JsonProperty("Количество")
    private int number;

    public JsonEquipment() {
    }

    public JsonEquipment(String name, String description, int number) {
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
        return String.valueOf(number);
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
