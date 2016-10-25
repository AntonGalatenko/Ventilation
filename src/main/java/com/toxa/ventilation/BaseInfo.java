package com.toxa.ventilation;

import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.Data.DataOfEquipment;
import com.toxa.ventilation.gui.ResultsPanel;
import com.toxa.ventilation.gui.TaskPanel;
import com.toxa.ventilation.json.CreateJson;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BaseInfo {

    private static BaseInfo instance;

    private TaskPanel taskPanel;
    private ResultsPanel resultsPanel;
    private List<Integer> cageTiers = Arrays.asList(3, 4, 5, 6);
    private int airForAirInletForTunnelTypeOfVentilation = 3;
    private double airSpeedForPadCool = 1.5;
    private int firstGroup;
    private String dataBaseStatus;

    private BaseInfo() {
    }

    public static BaseInfo getInstance() {
        if(instance == null)
            instance = new BaseInfo();
        return instance;
    }

    public void setTaskPanel(TaskPanel taskPanel){
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

        taskPanel.setCageTierCurrentValue();
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

        firstGroup = 0;

        cageTiers = Arrays.asList(3, 4, 5, 6);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
        resultsPanel.setElementOnPanelDisableForHeating();
        resultsPanel.setAutomaticSensorCO2Spinner(0);
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
        firstGroup = 1;

        cageTiers = Arrays.asList(3, 4, 5);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
        resultsPanel.setElementOnPanelEnableForHeating();
        resultsPanel.setAutomaticSensorCO2Spinner(0);
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

        firstGroup = 1;

        cageTiers = Arrays.asList(3, 4, 5);

        taskPanel.setEnableCageTiredAndCageNumberComboBox();
        resultsPanel.setElementOnPanelEnableForHeating();
        resultsPanel.setAutomaticSensorCO2Spinner(0);
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

        firstGroup = 0;

        cageTiers = Arrays.asList(3, 4);

        resultsPanel.setAutomaticSensorCO2Spinner(0);

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

        firstGroup = 0;

        resultsPanel.enableElementsInPanel(resultsPanel.getHeaterRadioButton());
        resultsPanel.disableElementsInPanel(resultsPanel.getFanCirculationRadioButton());
        resultsPanel.setAutomaticSensorCO2Spinner(1);

        taskPanel.setDisablesCageTiredAndCageNumberComboBox();
    }

    public String getCompanyName() {
        return taskPanel.getCompanyName().trim();
    }

    public String getPoultryHouseNumber() {
        return taskPanel.getPoultryHouseNumber();
    }

    public String getSecondFolder(){
        if(taskPanel.getCageName().equals("Напольник"))
            return taskPanel.getCageName();
        else
            return "ПЧ";
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

    public String getBuildingLengthString() {
        double length = taskPanel.getBuildingLength();

        if(length % 1 == 0)
            return String.valueOf((int)length);
        else
            return String.valueOf(length);
    }
    
    public double getBuildingWidth() {
        return taskPanel.getBuildingWidth();
    }

    public String getBuildingWidthString() {
        double width = taskPanel.getBuildingWidth();

        if(width % 1 == 0)
            return String.valueOf((int)width);
        else
            return String.valueOf(width);
    }

    public double getBuildingHeightMin() {
        return taskPanel.getBuildingHeightMin();
    }

    public String getBuildingHeightMinString() {
        double heightMin = taskPanel.getBuildingHeightMin();

        if(heightMin % 1 == 0)
            return String.valueOf((int)heightMin);
        else
            return String.valueOf(heightMin);
    }

    public double getBuildingHeightMax() {
        return taskPanel.getBuildingHeightMax();
    }

    public String getBuildingHeightMaxString() {
        double heightMax = taskPanel.getBuildingHeightMax();
        if(heightMax % 1 == 0)
            return String.valueOf((int)heightMax);
        else
            return String.valueOf(heightMax);
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

    public LinkedHashMap<String, Integer[]> getGroups(){
        return resultsPanel.getGroups();
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

    public String getHumidityWaterCirculation(){
        return resultsPanel.getHumidityWaterCirculation();
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

    public LinkedHashMap<String, Integer> getSelectedComponents(){
        return resultsPanel.getSelectedComponents();
    }

    public void getJson(){
        new CreateJson(this);
    }

    public int getFirstGroup() {
        return firstGroup;
    }

    public int[] padCoolWaterCirculation(){
        return resultsPanel.padCoolWaterCirculation();
    }

    public String getFilePathName(){
        return getPathFile() + "\\" +
                getCompanyName() + " " +
                getBuildingLengthString() + "x" +
                getBuildingWidthString() + "x" +
                getBuildingHeightMinString() + " " +
                getCageName() + " " +
                getHeadsNumber();

    }

    public String getPathFile(){
        if(isDistributeByCountry()){

            String dir = new ActualValues().loadActualValue().getFilePath() + "\\" + getSecondFolder();
            if(! new File(dir).exists())
                new File(dir).mkdir();

            if (isDirectoryExist(getCountry()))
                return new ActualValues().loadActualValue().getFilePath() + "\\" + getSecondFolder() + "\\" + getCountry().trim();
            else{
                if(! isDirectoryExist("Temp"))
                    new File(new ActualValues().loadActualValue().getFilePath() + "\\" + getSecondFolder() + "\\Temp").mkdir();
                return new ActualValues().loadActualValue().getFilePath() + "\\" + getSecondFolder() + "\\Temp";
                }
            }
        else
            return new ActualValues().loadActualValue().getFilePath();
    }

    public boolean isDistributeByCountry(){
        return new ActualValues().loadActualValue().isDistributeByCountry();
    }

    public boolean isDirectoryExist(String value){
        String dir = new ActualValues().loadActualValue().getFilePath() + "\\" + getSecondFolder() + "\\" + value;
        return new File(dir).exists();
    }

    public String getFilePathText(){
        return new ActualValues().loadActualValue().getFilePath();
    }

    public void setModelsToComboBoxInResultsPanel(){
        resultsPanel.setModelsToComboBox();
    }

    public boolean isResultsPanelVisible(){
        return resultsPanel.isVisible();
    }

    public void setResultPanelVisible(boolean value){
        resultsPanel.setVisible(value);
    }

    public String getCompose(){
        return new ActualValues().loadActualValue().getComposeChecked()[0];
    }

    public String getChecked(){
        return new ActualValues().loadActualValue().getComposeChecked()[1];
    }

    public Map<String, Boolean> getYearsToView(){
        return new ActualValues().loadActualValue().getYearsToView();
    }

    public void setDataBaseStatus(String text){
        dataBaseStatus = text;
    }

    public String getDataBaseStatusText(){
        return dataBaseStatus;
    }

    public long getFileSize(){
        return new ActualValues().loadActualValue().getFilesSize();
    }

    public void setFilesSize(int value){
        new ActualValues().loadActualValue().updateFileSize(value);
    }

    public void saveActualValue(DataOfEquipment dataOfEquipment){

        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("save_ventilation");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(dataOfEquipment);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
