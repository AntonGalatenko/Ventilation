package com.toxa.ventilation;

import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.gui.ResultsPanel;
import com.toxa.ventilation.gui.TaskPanel;

import java.util.Arrays;
import java.util.LinkedHashMap;
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

    public BaseInfo() {
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
            taskPanel.setAirSummer(16);
            taskPanel.setAirWinter(0);
            resultsPanel.setElementsOnPanelForTunnelVentilationType();
        } else if(taskPanel.getVentilationType().equals("Евро")){
            taskPanel.setAirSummer(13);
            taskPanel.setAirWinter(3);
            resultsPanel.setElementsOnPanelForEuroVentilationType();
        } else{
            taskPanel.setAirSummer(13);
            taskPanel.setAirWinter(3);
            resultsPanel.setElementsOnPanelForTexhaVentilationType();
        }
        cageTiers = Arrays.asList(3, 4);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
    }

    private void setNapolnikInfo() {
        if (taskPanel.getVentilationType().equals("Тунель")) {
            taskPanel.setAirSummer(12);
            taskPanel.setAirWinter(0);
            resultsPanel.setElementsOnPanelForTunnelVentilationType();
        } else {
            taskPanel.setAirSummer(9);
            taskPanel.setAirWinter(3);
            resultsPanel.setElementsOnPanelForEuroVentilationType();
        }
        resultsPanel.enableElementsInPanel(resultsPanel.getHeaterRadioButton());
        resultsPanel.disableElementsInPanel(resultsPanel.getFanCirculationRadioButton());
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

    public double getBuildingLength() {
        return taskPanel.getBuildingLength();
    }

    public double getBuildingWidth() {
        return taskPanel.getBuildingWidth();
    }

    public double getBuildingHeightMin() {
        return taskPanel.getBuildingHeightMin();
    }

    public double getBuildingHeightMax() {
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

    public boolean isFan50TwoSide() {
        return resultsPanel.isFan50TwoSide();
    }

    public boolean isHumidityPlus(){
        return resultsPanel.isHumidityPlus();
    }

    public double getAirWinter() {
        return taskPanel.getAirWinter() * 0.99;
    }

    public double getAirSummer() {
        return taskPanel.getAirSummer() * 0.99;
    }

    public int getAirForAirInletForTunnelTypeOfVentilation() {
        return airForAirInletForTunnelTypeOfVentilation;
    }

    public int getFan50Capacity(){
        int result = (int) new ActualValues().loadActualValue().getFan50().get(resultsPanel.getFan50Name()).getCapacity();
        if(resultsPanel.isFan50LightTrap())
            result *= 0.8;
        return result;
    }

    public int getFan36Capacity(){
        int result = (int) new ActualValues().loadActualValue().getFan36().get(resultsPanel.getFan36Name()).getCapacity();
        if(resultsPanel.isFan36LightTrap())
            result *= 0.8;
        return result;
    }

    public int getFan26Capacity(){
        int result = (int) new ActualValues().loadActualValue().getFan26().get(resultsPanel.getFan26Name()).getCapacity();
        if(resultsPanel.isFan26LightTrap())
            result *= 0.8;
        return result;
    }

    public int getFanRoofCapacity(){
        return (int) new ActualValues().loadActualValue().getFanRoof().get(resultsPanel.getFanRoofName()).getCapacity();
    }

    public int getShaftCapacity(){
        return (int) new ActualValues().loadActualValue().getShaft().get(resultsPanel.getShaftName()).getCapacity();
    }

    public int getAirInletOnWallCapacity(){
        return (int) new ActualValues().loadActualValue().getAirInletOfWall().get(resultsPanel.getAirInletOnWallName()).getCapacity();
    }

    public int getAirInletOfRoofCapacity(){
        return (int) new ActualValues().loadActualValue().getAirInletOfRoof().get(resultsPanel.getAirInletOfRoofName()).getCapacity();
    }

    public double getAirInletForPadCoolCapacity(){
        return new ActualValues().loadActualValue().getAirInletForPadCool().get(resultsPanel.getAirInletForPadCoolName()).getCapacity();
    }

    public double getShutterCapacity(){
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

    public double getHumidityLength1(){
        return resultsPanel.getHumidityLength1();
    }

    public double getHumidityLength2(){
        return resultsPanel.getHumidityLength2();
    }

    public double getCageArea(String value){
        return new ActualValues().loadActualValue().getCageArea().get(value).getCapacity();
    }

    public double getAirSpeedForPadCool() {
        return airSpeedForPadCool * 1.02;
    }

    public int getHumidityCount1(){
        return resultsPanel.getHumidityCount1();
    }

    public int getHumidityCount2(){
        return resultsPanel.getHumidityCount2();
    }

    public boolean isFanRoofSelected(){
        return resultsPanel.getFanRoofRadioButton().isSelected();
    }

    public void setAirSummerCurrent(double value){
        taskPanel.setAirSummerCurrent(value);
    }

    public void setAirWinterCurrent(double value){
        taskPanel.setAirWinterCurrent(value);
    }

    public void setAirTotalCurrent(double value){
        taskPanel.setAirTotalCurrent(value);
    }

    public void getSelectedComponents(){
        LinkedHashMap<String, Integer> c = resultsPanel.getSelectedComponents();

        for(String key : c.keySet())
            System.out.println(getDescriptionEquipment(key) + " : " + key + " : " + c.get(key));

    }

    public String getDescriptionEquipment(String nameEquipment){
        String value = null;

        if(new ActualValues().loadActualValue().getFan50().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getFan50().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getFan36().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getFan36().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getFan26().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getFan26().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getFanRoof().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getFanRoof().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getShaft().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getShaft().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getAirInletOfWall().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getAirInletOfWall().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getAirInletOfRoof().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getAirInletOfRoof().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getAirInletForPadCool().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getAirInletForPadCool().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getShutter().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getShutter().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getHumidity().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getHumidity().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getHeater().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getHeater().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getFanCirculation().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getFanCirculation().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getAutomatic().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getAutomatic().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getServomotor().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getServomotor().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getEmergency().containsKey(nameEquipment))
            value = new ActualValues().loadActualValue().getEmergency().get(nameEquipment).getDescription();

        return value;
    }

    public String getHumidityDescription(String value){
        return "Испарительная панель " +
    }


}
