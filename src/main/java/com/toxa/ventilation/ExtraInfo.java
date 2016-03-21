package com.toxa.ventilation;

import com.toxa.ventilation.gui.TaskPanel;

import java.util.Arrays;
import java.util.List;

public class ExtraInfo {

    private TaskPanel taskPanel;/* = TaskPanel.getInstance();*/

    private List<Integer> cageTiers = Arrays.asList(3, 4, 5, 6);
    public static final int FAN_50_CAPACITY = 40000;

    public ExtraInfo(TaskPanel taskPanel) {
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

}
