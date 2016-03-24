package com.toxa.ventilation;

import com.toxa.ventilation.gui.TaskPanel;

import java.util.Arrays;
import java.util.List;

public class BaseInfo {

    private TaskPanel taskPanel;

    private List<Integer> cageTiers = Arrays.asList(3, 4, 5, 6);
    public static final int FAN_50_CAPACITY = 40000;
    public static final int FAN_36_CAPACITY = 20000;
    public static final int FAN_26_CAPACITY = 10000;
    public static final int FAN_Roof820_CAPACITY = 21000;
    public static final int FAN_Roof630_CAPACITY = 10500;


    public BaseInfo(TaskPanel taskPanel) {
        this.taskPanel = taskPanel;
    }

    public List<Integer> getCageTiers() {
        return cageTiers;
    }

    public void setInfo() {
        switch (taskPanel.getCageName()) {
            case "ТБК":
                setTBKInfo();
                break;
            case "ТБЦ":
                setTBCInfo();
                break;
            case "ТБЦ(бр)":
                setTBBInfo();
                break;
            case "ТББ":
                setTBBInfo();
                break;
            case "ТБР":
                setTBRInfo();
                break;
            case "Напольник":
                setNapolnikInfo();
                break;
        }

        taskPanel.updateCageTiersComboBox();
        taskPanel.updateAirSpinner();
    }

    private void setTBKInfo(){
        if(taskPanel.getVentilationType().equals("Тунель")){
            taskPanel.setAirSummer(12);
            taskPanel.setAirWinter(0);
        } else {
            taskPanel.setAirSummer(9);
            taskPanel.setAirWinter(3);
        }
        cageTiers = Arrays.asList(3, 4, 5, 6);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
    }

    private void setTBCInfo(){
        if(taskPanel.getVentilationType().equals("Тунель")){
            taskPanel.setAirSummer(9);
            taskPanel.setAirWinter(0);
        } else {
            taskPanel.setAirSummer(6);
            taskPanel.setAirWinter(3);
        }
        cageTiers = Arrays.asList(3, 4, 5);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
    }

    private void setTBBInfo() {
        if (taskPanel.getVentilationType().equals("Тунель")) {
            taskPanel.setAirSummer(14);
            taskPanel.setAirWinter(0);
        } else {
            taskPanel.setAirSummer(11);
            taskPanel.setAirWinter(3);
        }
        cageTiers = Arrays.asList(3, 4, 5);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
    }

    private void setTBRInfo() {
        if (taskPanel.getVentilationType().equals("Тунель")) {
            taskPanel.setAirSummer(12);
            taskPanel.setAirWinter(0);
        } else {
            taskPanel.setAirSummer(9);
            taskPanel.setAirWinter(3);
        }
        cageTiers = Arrays.asList(3, 4);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
    }

    private void setNapolnikInfo() {
        taskPanel.setAirSummer(12);
        taskPanel.setAirWinter(0);

        taskPanel.setDisablesCageTiredAndCageNumberComboBox();
    }

    public String getCompanyName() {
        return taskPanel.getCompanyName();
    }

    public String getCountry(){
        return taskPanel.getCountry();
    }

    public int getHeadsNumber() {
        return taskPanel.getHeadsNumber();
    }

    public int getBuildingLength() {
        return taskPanel.getBuildingLength();
    }

    public int getBuildingWidth() {
        return taskPanel.getBuildingWidth();
    }

    public int getBuildingHeightMin() {
        return taskPanel.getBuildingHeightMin();
    }

    public int getBuildingHeightMax() {
        return taskPanel.getBuildingHeightMax();
    }

    public String getCageName(){
        return taskPanel.getCageName();
    }

    public int getCageTiers1() {
        return taskPanel.getCageTiers1();
    }

    public int getCageNumber1() {
        return taskPanel.getCageNumber1();
    }

    public int getCageTiers2() {
        return taskPanel.getCageTiers2();
    }

    public int getCageNumber2() {
        return taskPanel.getCageNumber2();
    }

    public String getVentilationType(){
        return taskPanel.getVentilationType();
    }

    public double getAirWinter() {
        return taskPanel.getAirWinter();
    }

    public double getAirSummer() {
        return taskPanel.getAirSummer();
    }

}
