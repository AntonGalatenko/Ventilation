package com.toxa.ventilation.json;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

public class JsonObject {

    @JsonProperty("Базовая информация")
    private JsonBaseInfo baseInfo;

    @JsonProperty("Параметры здания")
    private JsonBuilding building;

    @JsonProperty("Оборудование")
    private ArrayList<JsonEnt> equ;

    public JsonObject() {
    }

    public JsonObject(JsonBaseInfo baseInfo, JsonBuilding building) {
        this.baseInfo = baseInfo;
        this.building = building;
    }

    public JsonBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(JsonBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public JsonBuilding getBuilding() {
        return building;
    }

    public void setBuilding(JsonBuilding building) {
        this.building = building;
    }

    public ArrayList<JsonEnt> getEqu() {
        return equ;
    }

    public void setEqu(ArrayList<JsonEnt> equipment) {
        equ = equipment;
    }

    public void addEqu(JsonEnt equipment){
        if(equ == null)
            equ = new ArrayList<>();

        equ.add(equipment);
    }

}
