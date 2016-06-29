package com.toxa.ventilation.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class JsonBaseInfo {

    @JsonProperty("Название файла")
    private String filePathName;

    @JsonProperty("Предприятие")
    private String name;

    @JsonProperty("Страна")
    private String country;

    @JsonProperty("Номер птичника")
    private String numberHouse;

    @JsonProperty("Содержание")
    private String cageName;

    @JsonProperty("Поголовье")
    private String numberHeads;

    @JsonProperty("Составил")
    private String compose;

    @JsonProperty("Проверил")
    private String checked;

    public JsonBaseInfo() {
    }

    public JsonBaseInfo(String filePathName, String name, String country, String numberHeads, String numberHouse, String cageName, String compose, String checked) {
        this.filePathName = filePathName;
        this.name = name;
        this.country = country;
        this.numberHeads = numberHeads;
        this.numberHouse = numberHouse;
        this.compose = compose;
        this.checked = checked;
        setCageName(cageName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNumberHeads() {
        return numberHeads;
    }

    public void setNumberHeads(String numberHeads) {
        this.numberHeads = numberHeads;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getCageName() {
        return cageName;
    }

    public void setCageName(String cageName) {
        if(! cageName.equals("Напольник"))
            this.cageName = "Предусмотрена установка батарей " + cageName;
        else
            this.cageName = "Напольное";
    }

    public String getFilePathName() {
        return filePathName;
    }

    public void setFilePathName(String filePathName) {
        this.filePathName = filePathName;
    }

    public String getCompose() {
        return compose;
    }

    public String getChecked() {
        return checked;
    }
}
