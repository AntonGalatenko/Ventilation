package com.toxa.ventilation;

import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.gui.ResultsPanel;
import com.toxa.ventilation.gui.TaskPanel;

import java.util.Arrays;
import java.util.List;

public class BaseInfo {

    private TaskPanel taskPanel;
    private ResultsPanel resultsPanel;

    private List<Integer> cageTiers = Arrays.asList(3, 4, 5, 6);

    private int airForAirInletForTunnelTypeOfVentilation = 3;
    private double airSpeedForPadCool = 1.5;

    public BaseInfo(TaskPanel taskPanel) {
        this.taskPanel = taskPanel;

    }

    public void setResultsPanel(ResultsPanel resultsPanel) {
        this.resultsPanel = resultsPanel;
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
            resultsPanel.setElementsOnPanelForTunnelVentilationType();
        } else if(taskPanel.getVentilationType().equals("Евро")){
            taskPanel.setAirSummer(9);
            taskPanel.setAirWinter(3);
            resultsPanel.setElementsOnPanelForEuroVentilationType();
        } else{
            taskPanel.setAirSummer(9);
            taskPanel.setAirWinter(3);
            resultsPanel.setElementsOnPanelForTexhaVentilationType();
        }
        cageTiers = Arrays.asList(3, 4, 5, 6);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
        resultsPanel.setElementOnPanelDisableForHeating();
    }

    private void setTBCInfo(){
        if(taskPanel.getVentilationType().equals("Тунель")){
            taskPanel.setAirSummer(9);
            taskPanel.setAirWinter(0);
            resultsPanel.setElementsOnPanelForTunnelVentilationType();
        } else if(taskPanel.getVentilationType().equals("Евро")){
            taskPanel.setAirSummer(6);
            taskPanel.setAirWinter(3);
            resultsPanel.setElementsOnPanelForEuroVentilationType();
        } else{
            taskPanel.setAirSummer(6);
            taskPanel.setAirWinter(3);
            resultsPanel.setElementsOnPanelForTexhaVentilationType();
        }
        cageTiers = Arrays.asList(3, 4, 5);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
        resultsPanel.setElementOnPanelEnableForHeating();
    }

    private void setTBBInfo() {
        if(taskPanel.getVentilationType().equals("Тунель")){
            taskPanel.setAirSummer(14);
            taskPanel.setAirWinter(0);
            resultsPanel.setElementsOnPanelForTunnelVentilationType();
        } else if(taskPanel.getVentilationType().equals("Евро")){
            taskPanel.setAirSummer(11);
            taskPanel.setAirWinter(3);
            resultsPanel.setElementsOnPanelForEuroVentilationType();
        } else{
            taskPanel.setAirSummer(11);
            taskPanel.setAirWinter(3);
            resultsPanel.setElementsOnPanelForTexhaVentilationType();
        }
        cageTiers = Arrays.asList(3, 4, 5);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
        resultsPanel.setElementOnPanelEnableForHeating();
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

//    public void setElementsOnPanelForTunnelVentilationType(){
//        resultsPanel.enableElementsInPanel(resultsPanel.getFan50RadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getAirInletOnWallRadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getAirInletForPadCoolRadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getHumidityRadioButton());
//
//        resultsPanel.disableElementsInPanel(resultsPanel.getFan36RadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getFan26RadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getFanRoofRadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getAirInletOfRoofRadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getShaftRadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getShutterRadioButton());
//    }
//
//    public void setElementsOnPanelForEuroVentilationType(){
//        resultsPanel.enableElementsInPanel(resultsPanel.getFan50RadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getFan26RadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getFanRoofRadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getAirInletForPadCoolRadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getHumidityRadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getAirInletOnWallRadioButton());
//
//        resultsPanel.disableElementsInPanel(resultsPanel.getShaftRadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getFan36RadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getAirInletOfRoofRadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getShutterRadioButton());
//    }
//
//    public void setElementsOnPanelForTexhaVentilationType(){
//        resultsPanel.enableElementsInPanel(resultsPanel.getFan50RadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getFan26RadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getShaftRadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getShutterRadioButton());
//
//        resultsPanel.disableElementsInPanel(resultsPanel.getFan36RadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getFanRoofRadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getAirInletOnWallRadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getAirInletOfRoofRadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getAirInletForPadCoolRadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getHumidityRadioButton());
//    }
//
//    public void setElementOnPanelDisableForHeating(){
//        resultsPanel.disableElementsInPanel(resultsPanel.getHeaterRadioButton());
//        resultsPanel.disableElementsInPanel(resultsPanel.getFanCirculationRadioButton());
//    }
//
//    public void setElementOnPanelEnableForHeating(){
//        resultsPanel.enableElementsInPanel(resultsPanel.getHeaterRadioButton());
//        resultsPanel.enableElementsInPanel(resultsPanel.getFanCirculationRadioButton());
//    }

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

    public boolean isFan50TwoSideCheckBox() {
        return resultsPanel.isFan50TwoSide();
    }

    public boolean isHumidityPlus(){
        return resultsPanel.isHumidityPlus();
    }

    public double getAirWinter() {
        return taskPanel.getAirWinter();
    }

    public double getAirSummer() {
        return taskPanel.getAirSummer();
    }

    public int getAirForAirInletForTunnelTypeOfVentilation() {
        return airForAirInletForTunnelTypeOfVentilation;
    }

    public int getFan50Capacity(){
        return new ActualValues().loadActualValue().getFan50().get(resultsPanel.getFan50Name()).getCapacity();
    }

    public int getFan36Capacity(){
        return new ActualValues().loadActualValue().getFan36().get(resultsPanel.getFan36Name()).getCapacity();
    }

    public int getFan26Capacity(){
        return new ActualValues().loadActualValue().getFan26().get(resultsPanel.getFan26Name()).getCapacity();
    }

    public int getFanRoofCapacity(){
        return new ActualValues().loadActualValue().getFanRoof().get(resultsPanel.getFanRoofName()).getCapacity();
    }

    public int getShaftCapacity(){
        return new ActualValues().loadActualValue().getShaft().get(resultsPanel.getShaftName()).getCapacity();
    }

    public int getAirInletOnWallCapacity(){
        return new ActualValues().loadActualValue().getAirInletOfWall().get(resultsPanel.getAirInletOnWallName()).getCapacity();
    }

    public int getAirInletOfRoofCapacity(){
//        if(resultsPanel.getAirInletOfRoofRadioButton().isSelected())
//            return 0;
        return new ActualValues().loadActualValue().getAirInletOfRoof().get(resultsPanel.getAirInletOfRoofName()).getCapacity();
    }

    public double getAirInletForPadCoolCapacity(){
//        if(resultsPanel.getAirInletForPadCoolRadioButton().isSelected())
//            return 0;
        return new ActualValues().loadActualValue().getAirInletForPadCool().get(resultsPanel.getAirInletForPadCoolName()).getCapacity();
    }

    public double getShutterCapacity(){
//        if(resultsPanel.getShutterRadioButton().isSelected())
//            return 0;
        return new ActualValues().loadActualValue().getShutter().get(resultsPanel.getShutterName()).getCapacity();
    }

    public double getHeaterCapacity(){
        return new ActualValues().loadActualValue().getHeater().get(resultsPanel.getHeaterName()).getCapacity();
    }

    public double getFanCirculationCapacity(){
        return new ActualValues().loadActualValue().getFanCirculation().get(resultsPanel.getFanCirculationName()).getCapacity();
    }

    public double getHumidityHeight1(){
        return resultsPanel.getHumidityHeight1();
    }

    public double getHumidityHeight2(){
        return resultsPanel.getHumidityHeight2();
    }

    public double getAirSpeedForPadCool() {
        return airSpeedForPadCool;
    }

    public int getHumidityCount1(){
        return resultsPanel.getHumidityCount1();
    }

    public int getHumidityCount2(){
        return resultsPanel.getHumidityCount2();
    }
}
