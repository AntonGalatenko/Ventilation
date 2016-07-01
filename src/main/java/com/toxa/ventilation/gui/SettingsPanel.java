package com.toxa.ventilation.gui;

import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.Data.DataOfEquipment;
import com.toxa.ventilation.Data.Storage;
import com.toxa.ventilation.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SettingsPanel extends JDialog{

//    private MyToolBar myToolBar;
//    private MyMainPanel myMainPanel;
    private DataOfEquipment dataOfEquipment;
    private MyFileChooser fileChooser;

    private JTabbedPane tabbedPane;
    private JPanel mainPanel;
    private JPanel generalPanel;
    private JLabel directoryLabel;
    private JPanel exhaustAirPanel;
    private JLabel fan50Label;
    private JLabel fan36Label;
    private JLabel fan26Label;
    private JScrollPane fanRoofScrollPane;
    private JLabel fanRoofLabel;
    private JScrollPane fan26ScrollPane;
    private JScrollPane fan36ScrollPane;
    private JScrollPane fan50ScrollPane;
    private JLabel pathGeneralSettingsLabel;
    private JTextArea fan26TextArea;
    private JTextArea fanRoofTextArea;
    private JTextArea fan36TextArea;
    private JTextArea fan50TextArea;
    private JPanel intelAirPanel;
    private JTextArea airInletOnWallTextArea;
    private JScrollPane airInletOnWallScrollPane;
    private JLabel airInletOnWallLabel;
    private JLabel airInletOnRoofLabel;
    private JTextArea airInletOnRoofTextArea;
    private JScrollPane airInletOnRoofScrollPane;
    private JLabel airInletForPadCoolLabel;
    private JTextArea airInletForPadCoolTextArea;
    private JScrollPane airInletForPadCoolScrollPane;
    private JLabel shutterLabel;
    private JTextArea shutterTextArea;
    private JScrollPane shutterScrollPane;
    private JPanel heaterPanel;
    private JLabel heaterLabel;
    private JScrollPane heaterScrollPane;
    private JTextArea heaterTextArea;
    private JLabel fanCirculationLabel;
    private JScrollPane fanCirculationScrollPane;
    private JTextArea fanCirculationTextArea;
    private JPanel automaticPanel;
    private JLabel automaticLabel;
    private JScrollPane automaticScrollPane;
    private JTextArea automaticTextArea;
    private JLabel servomotorLabel;
    private JScrollPane servomotorScrollPane;
    private JTextArea servomotorTextArea;
    private JLabel emergencyLabel;
    private JScrollPane emergencyScrollPane;
    private JTextArea emergencyTextArea;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel shaftLabel;
    private JScrollPane shaftScrollPane;
    private JTextArea shaftTextArea;
    private JPanel humidityPanel;
    private JLabel humidity2mLabel;
    private JScrollPane humidity2mScrollPane;
    private JTextArea humidity2mTextArea;
    private JScrollPane humidity15mScrollPane;
    private JLabel humidity15mLabel;
    private JTextArea humidity15mTextArea;
    private JLabel humidity1mLabel;
    private JScrollPane humidity1mScrollPane;
    private JTextArea humidity1mTextArea;
    private JButton FileChooserButton;
    private JCheckBox distributeByCountryCheckBox;
    private JLabel fileChooserLabel;
    private JLabel humidityWaterCirculationLabel;
    private JTextArea humidityWaterCirculationTextArea;
    private JScrollPane humidityWaterCirculationScrollPane;
    private JLabel composeLabel;
    private JTextArea composeTextArea;
    private JLabel checkedLabel;
    private JTextArea chackedTextArea;

    public SettingsPanel(/*final MyToolBar myToolBar*/){
//        this.myToolBar = myToolBar;
        setLocationForThisFrame();

        setTitle("Настройки");
        add(mainPanel);

        setVisible(true);
        pack();

        setDefaultNamesDescriptionsCapacity();

//        BaseInfo.getInstance().setSettingsPanel(this);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveActualValue();
                dispose();
//                myToolBar.getMyMainPanel().getResultPanel().setModelsToComboBox();
                BaseInfo.getInstance().setModelsToComboBoxInResultsPanel();
                new ActualValues().loadActualValue();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        FileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new MyFileChooser();
                fileChooserLabel.setText(fileChooser.getPath());
            }
        });
    }

    public void setDefaultNamesDescriptionsCapacity(){
        dataOfEquipment = new ActualValues().loadActualValue();

        setFan50NamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getFan50()));
        setFan36NamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getFan36()));
        setFan26NamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getFan26()));
        setFanRoofNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getFanRoof()));
        setShaftNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getShaft()));
        setAirInletOnWallNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getAirInletOfWall()));
        setAirInletOnRoofNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getAirInletOfRoof()));
        setAirInletOfPadCoolNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getAirInletForPadCool()));
        setShutterNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getShutter()));
        setHumidity2NamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getHumidity2()));
        setHumidity15NamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getHumidity15()));
        setHumidity1NamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getHumidity1()));
        setHeaterNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getHeater()));
        setFanCirculationNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getFanCirculation()));
        setAutomaticNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getAutomatic()));
        setServomotorNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getServomotor()));
        setEmergencyNamesDescriptionsCapacity(parseEquipmentValue(dataOfEquipment.getEmergency()));
        setFileChooserText(dataOfEquipment.getFilePath());
        setDistributeByCountry(dataOfEquipment.isDistributeByCountry());
        setHumidityWaterCirculation(dataOfEquipment.getHumidityWaterCirculation());
        setComposeChecked(dataOfEquipment.getComposeChecked());
    }

    public void saveActualValue(){
        updateNamesOfEquipmentInDataOfEquipmentClass();

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

    public void updateNamesOfEquipmentInDataOfEquipmentClass(){
        dataOfEquipment.updateFan50(getFan50NamesDescriptionsCapacity());
        dataOfEquipment.updateFan36(getFan36NamesDescriptionsCapacity());
        dataOfEquipment.updateFan26(getFan26NamesDescriptionsCapacity());
        dataOfEquipment.updateFanRoof(getFanRoofNamesDescriptionsCapacity());
        dataOfEquipment.updateShaft(getShaftNamesDescriptionsCapacity());
        dataOfEquipment.updateAirInletOfWall(getAirInletOnWallNamesDescriptionsCapacity());
        dataOfEquipment.updateAirInletOfRoof(getAirInletOnRoofNamesDescriptionsCapacity());
        dataOfEquipment.updateAirInletOfPadCool(getAirInletForPadCoolNamesDescriptionsCapacity());
        dataOfEquipment.updateShutter(getShutterNamesDescriptionsCapacity());
        dataOfEquipment.updateHumidity2(getHumidity2mNamesDescriptionsCapacity());
        dataOfEquipment.updateHumidity15(getHumidity15mNamesDescriptionsCapacity());
        dataOfEquipment.updateHumidity1(getHumidity1mNamesDescriptionsCapacity());
        dataOfEquipment.updateHumidityWaterCirculation(getHumidityWaterCirculation());
        dataOfEquipment.updateHeater(getHeaterNamesDescriptionsCapacity());
        dataOfEquipment.updateFanCirculation(getFanCirculationNamesDescriptionsCapacity());
        dataOfEquipment.updateAutomatic(getAutomaticNamesDescriptionsCapacity());
        dataOfEquipment.updateServomotor(getServomotorNamesDescriptionsCapacity());
        dataOfEquipment.updateEmergency(getEmergencyNamesDescriptionsCapacity());
        dataOfEquipment.updateFilePath(getFilePath());
        dataOfEquipment.updateDistributeByCountry(isDistributeByCountry());
        dataOfEquipment.updateComposeChecked(getComposeChecked());
    }

    public StringBuilder parseEquipmentValue(HashMap<String, Storage> map){
        StringBuilder result = new StringBuilder();
        for(String key : map.keySet())
            result.append(key + " : " + map.get(key).getCapacity() + " : " + map.get(key).getDescription() + "\n");
        return result;
    }

    public void setLocationForThisFrame(){
        Point point = Main.getMainPanelLocation();
        setLocation((int)point.getX() + 10, (int)point.getY() + 30);
    }

    public String getFilePath(){
        return fileChooserLabel.getText();
    }

    public void setFileChooserText(String text){
        fileChooserLabel.setText(text);
    }

    public boolean isDistributeByCountry(){
        return distributeByCountryCheckBox.isSelected();
    }

    public void setDistributeByCountry(boolean value){
        distributeByCountryCheckBox.setSelected(value);
    }

    public StringBuilder getFan50NamesDescriptionsCapacity() {
        return new StringBuilder().append(fan50TextArea.getText());
    }

    public void setFan50NamesDescriptionsCapacity(StringBuilder text) {
        fan50TextArea.setText(text.toString());
    }

    public StringBuilder getFan36NamesDescriptionsCapacity() {
        return new StringBuilder().append(fan36TextArea.getText());
    }

    public void setFan36NamesDescriptionsCapacity(StringBuilder text) {
        fan36TextArea.setText(text.toString());
    }

    public StringBuilder getFan26NamesDescriptionsCapacity() {
        return new StringBuilder().append(fan26TextArea.getText());
    }

    public void setFan26NamesDescriptionsCapacity(StringBuilder text) {
        fan26TextArea.setText(text.toString());
    }

    public StringBuilder getShaftNamesDescriptionsCapacity() {
        return new StringBuilder().append(shaftTextArea.getText());
    }

    public void setShaftNamesDescriptionsCapacity(StringBuilder text) {
        shaftTextArea.setText(text.toString());
    }


    public StringBuilder getAirInletOnWallNamesDescriptionsCapacity() {
        return new StringBuilder().append(airInletOnWallTextArea.getText());
    }

    public void setAirInletOnWallNamesDescriptionsCapacity(StringBuilder text) {
        airInletOnWallTextArea.setText(text.toString());
    }

    public StringBuilder getAirInletOnRoofNamesDescriptionsCapacity() {
        return new StringBuilder().append(airInletOnRoofTextArea.getText());
    }

    public String[] getComposeChecked(){
        return new String[]{composeTextArea.getText(), chackedTextArea.getText()};
    }

    public void setAirInletOnRoofNamesDescriptionsCapacity(StringBuilder text) {
        airInletOnRoofTextArea.setText(text.toString());
    }

    public StringBuilder getAirInletForPadCoolNamesDescriptionsCapacity() {
        return new StringBuilder().append(airInletForPadCoolTextArea.getText());
    }

    public void setAirInletOfPadCoolNamesDescriptionsCapacity(StringBuilder text) {
        airInletForPadCoolTextArea.setText(text.toString());
    }

    public StringBuilder getShutterNamesDescriptionsCapacity() {
        return new StringBuilder().append(shutterTextArea.getText());
    }

    public void setShutterNamesDescriptionsCapacity(StringBuilder text) {
        shutterTextArea.setText(text.toString());
    }

    public void setComposeChecked(String[] text){
        composeTextArea.setText(text[0]);
        chackedTextArea.setText(text[1]);
    }

//    public void setChecked(String text){
//        chackedTextArea.setText(text);
//    }

    public String getCompose(){
        return composeTextArea.getText();
    }

    public String getChecked(){
        return chackedTextArea.getText();
    }

    public StringBuilder getHumidity2mNamesDescriptionsCapacity() {
        return new StringBuilder().append(humidity2mTextArea.getText());
    }

    public StringBuilder getHumidity15mNamesDescriptionsCapacity() {
        return new StringBuilder().append(humidity15mTextArea.getText());
    }

    public StringBuilder getHumidity1mNamesDescriptionsCapacity() {
        return new StringBuilder().append(humidity1mTextArea.getText());
    }

    public String getHumidityWaterCirculation(){
        return humidityWaterCirculationTextArea.getText();
    }

    public void setHumidity2NamesDescriptionsCapacity(StringBuilder text) {
        humidity2mTextArea.setText(text.toString());
    }

    public void setHumidity15NamesDescriptionsCapacity(StringBuilder text) {
        humidity15mTextArea.setText(text.toString());
    }

    public void setHumidity1NamesDescriptionsCapacity(StringBuilder text) {
        humidity1mTextArea.setText(text.toString());
    }

    public void setHumidityWaterCirculation(String text){
        humidityWaterCirculationTextArea.setText(text);
    }

    public StringBuilder getHeaterNamesDescriptionsCapacity() {
        return new StringBuilder().append(heaterTextArea.getText());
    }

    public void setHeaterNamesDescriptionsCapacity(StringBuilder text) {
        heaterTextArea.setText(text.toString());
    }

    public StringBuilder getFanCirculationNamesDescriptionsCapacity() {
        return new StringBuilder().append(fanCirculationTextArea.getText());
    }

    public void setFanCirculationNamesDescriptionsCapacity(StringBuilder text) {
        fanCirculationTextArea.setText(text.toString());
    }

    public StringBuilder getAutomaticNamesDescriptionsCapacity() {
        return new StringBuilder().append(automaticTextArea.getText());
    }

    public void setAutomaticNamesDescriptionsCapacity(StringBuilder text) {
        automaticTextArea.setText(text.toString());
    }

    public StringBuilder getServomotorNamesDescriptionsCapacity() {
        return new StringBuilder().append(servomotorTextArea.getText());
    }

    public void setServomotorNamesDescriptionsCapacity(StringBuilder text) {
        servomotorTextArea.setText(text.toString());
    }

    public StringBuilder getEmergencyNamesDescriptionsCapacity() {
        return new StringBuilder().append(emergencyTextArea.getText());
    }

    public void setEmergencyNamesDescriptionsCapacity(StringBuilder text) {
        emergencyTextArea.setText(text.toString());
    }

    public StringBuilder getFanRoofNamesDescriptionsCapacity() {
        return new StringBuilder().append(fanRoofTextArea.getText());
    }

    public void setFanRoofNamesDescriptionsCapacity(StringBuilder text) {
        fanRoofTextArea.setText(text.toString());
    }

}
