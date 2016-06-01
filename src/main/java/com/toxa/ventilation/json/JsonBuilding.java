package com.toxa.ventilation.json;

public class JsonBuilding {

    private String length;
    private String width;
    private String heightMin;
    private String heightMax;

    public JsonBuilding() {
    }

    public JsonBuilding(String length, String width, String heightMin, String heightMax) {
        this.length = length;
        this.width = width;
        this.heightMin = heightMin;
        this.heightMax = heightMax;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeightMin() {
        return heightMin;
    }

    public void setHeightMin(String heightMin) {
        this.heightMin = heightMin;
    }

    public String getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(String heightMax) {
        this.heightMax = heightMax;
    }
}
