package com.toxa.ventilation.json;

import java.util.ArrayList;

public class JsonEnt {

    private String name;
    private ArrayList<JsonEquipment> equipments;

    public JsonEnt() {

    }

    public JsonEnt(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<JsonEquipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(ArrayList<JsonEquipment> equipments) {
        this.equipments = equipments;
    }

    public void addEquipment(JsonEquipment equipment){
        if(equipments == null)
            equipments = new ArrayList<>();

        equipments.add(equipment);
    }

    @Override
    public String toString(){
        String result = name;

        for(JsonEquipment e : equipments){
            result += " " + e.toString() + " ";
        }

        return  result;
    }
}
