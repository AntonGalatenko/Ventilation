package com.toxa.ventilation.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class JsonBuilding {

    @JsonProperty("L, м")
    private String length;

    @JsonProperty("W, м")
    private String width;

    @JsonProperty("Hmin, м")
    private String heightMin;

    @JsonProperty("Hmax, м")
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
