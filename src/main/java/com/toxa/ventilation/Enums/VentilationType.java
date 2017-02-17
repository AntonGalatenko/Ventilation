package com.toxa.ventilation.Enums;

public enum VentilationType {
    TUNNEL("Тунель"),
    EURO("Евро"),
    TEXHA("Техна");

    private String type;

    VentilationType(String type){
        this.type = type;
    }

    public String getName(){
        return type;
    }

    public static Enum getType(String ventilationType) {
        for(VentilationType v : VentilationType.values())
            if(v.getName() == ventilationType)
                return v;
        return null;
    }
}
