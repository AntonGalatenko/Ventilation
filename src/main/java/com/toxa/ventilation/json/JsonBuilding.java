package com.toxa.ventilation.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class JsonBuilding {

    @JsonProperty("Длина")
    private double length;

    @JsonProperty("Ширина")
    private double width;

    @JsonProperty("Высота мин, м")
    private double heightMin;

    @JsonProperty("Высота макс, м")
    private double heightMax;

    public JsonBuilding() {
    }

    public JsonBuilding(double length, double width, double heightMin, double heightMax) {
        this.length = length;
        this.width = width;
        this.heightMin = heightMin;
        this.heightMax = heightMax;
    }

    public String getLength() {
        return String.valueOf(length);
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getWidth() {
        return String.valueOf(width);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getHeightMin() {
        return String.valueOf(heightMin);
    }

    public void setHeightMin(double heightMin) {
        this.heightMin = heightMin;
    }

    public String getHeightMax() {
        return String.valueOf(heightMax);
    }

    public void setHeightMax(double heightMax) {
        this.heightMax = heightMax;
    }

}
