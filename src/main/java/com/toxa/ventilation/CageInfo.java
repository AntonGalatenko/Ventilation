package com.toxa.ventilation;

import com.toxa.ventilation.gui.TaskPanel;

import java.util.Arrays;
import java.util.List;

public class CageInfo {

    private static CageInfo instance;/* = new CageInfo();*/

    private TaskPanel taskPanel;
    //    private String cageName = "ТБК";
    private double airSummer;
    private double airWinter;
    private List<Integer> cageTiers = Arrays.asList(3, 4, 5, 6);

    private CageInfo() {

    }

    public static CageInfo getInstance(){
        if(instance == null)
            instance = new CageInfo();
        return instance;
    }

    public void setTaskPanel(TaskPanel taskPanel) {
        this.taskPanel = taskPanel;
    }

//    public String getCageName() {
//        return cageName;
//    }
//
//    public void setCageName(String cageName) {
//        this.cageName = cageName;
//
//        setInfo();
//    }

    public double getAirSummer() {
        return airSummer;
    }

    public double getAirWinter() {
        return airWinter;
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
            airSummer = 12;
            airWinter = 0;
        } else {
            airSummer = 9;
            airWinter = 3;
        }
        cageTiers = Arrays.asList(3, 4, 5, 6);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
    }

    private void setTBCInfo(){
        if(taskPanel.getVentilationType().equals("Тунель")){
            airSummer = 9;
            airWinter = 0;
        } else {
            airSummer = 6;
            airWinter = 3;
        }
        cageTiers = Arrays.asList(3, 4, 5);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
    }

    private void setTBBInfo() {
        if (taskPanel.getVentilationType().equals("Тунель")) {
            airSummer = 12;
            airWinter = 0;
        } else {
            airSummer = 9;
            airWinter = 3;
        }
        cageTiers = Arrays.asList(3, 4, 5);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
    }

    private void setTBRInfo() {
        if (taskPanel.getVentilationType().equals("Тунель")) {
            airSummer = 12;
            airWinter = 0;
        } else {
            airSummer = 9;
            airWinter = 3;
        }
        cageTiers = Arrays.asList(3, 4);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
    }

    private void setNapolnikInfo() {
        airSummer = 12;
        airWinter = 0;

        taskPanel.setDisablesCageTiredAndCageNumberComboBox();


    }

}
