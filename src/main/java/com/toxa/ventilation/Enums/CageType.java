package com.toxa.ventilation.Enums;

public enum CageType {
    TBK ("ТБК"),
    TBC ("ТБЦ"),
    TBB ("ТББ"),
    TBCbr ("ТБЦ(бр)"),
    TBR ("ТБР"),
    NAPOLNIK ("Напольник");

    private String type;

    CageType(String type){
        this.type = type;
    }

    public String getName(){
        return type;
    }
}
