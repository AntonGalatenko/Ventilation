package com.toxa.ventilation;

import com.toxa.ventilation.gui.TaskPanel;

import java.util.Arrays;
import java.util.List;

public class CageInfo {

    private static CageInfo instance;/* = new CageInfo();*/

    private TaskPanel taskPanel;/* = new TaskPanel();*/
    private String cageName;/* = taskPanel.getCageName();*/
    private double airSummer;
    private double airWinter;
    private List<Integer> cageTiers = Arrays.asList(3, 4, 5);

    private CageInfo() {

    }

    public static CageInfo getInstance(){
        if(instance == null)
            instance = new CageInfo();
        return instance;
    }

    public String getCageName() {
        return cageName;
    }

    public void setCageName(String cageName) {
        this.cageName = cageName;

        setInfo();
    }

    public double getAirSummer() {
        return airSummer;
    }

    public double getAirWinter() {
        return airWinter;
    }

    public List<Integer> getCageTiers() {
        return cageTiers;
    }

    private void setInfo(){
        taskPanel = new TaskPanel();

        switch (cageName){
            case "ТБК":
                setTBKInfo();
                break;
            case "ТБЦ":
                setTBCInfo();
                break;
        }

    }

    private void setTBKInfo(){
        if(taskPanel.getVentilationType().equals("Тунель")){
            airSummer = 12;
            airWinter = 0;
        } else {
            airSummer = 9;
            airSummer = 3;
        }
        cageTiers = Arrays.asList(3, 4, 5);
    }

    private void setTBCInfo(){
        System.out.println("TBC");
        if(taskPanel.getVentilationType().equals("Тунель")){
            airSummer = 9;
            airWinter = 0;
        } else {
            airSummer = 6;
            airSummer = 3;
        }
        cageTiers = Arrays.asList(3, 4, 5, 6);
    }

}
