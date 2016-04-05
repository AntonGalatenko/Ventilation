package com.toxa.ventilation.gui;

import com.toxa.ventilation.Count;
import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.Data.DataOfEquipment;
import com.toxa.ventilation.Data.Storage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

public class ResultsPanel extends JPanel{
    private Count count;
    private MyMainPanel myMainPanel;
    private DataOfEquipment dataOfEquipment;

    private JPanel mainPanel;
    private JPanel fan50Panel;
    private JRadioButton fan50RadioButton;
    private JSpinner fan50Spinner;
    private JComboBox fan50ComboBox;
    private JCheckBox fan50TwoSideCheckBox;
    private JRadioButton fan36RadioButton;
    private JSpinner fan36Spinner;
    private JPanel fan36Panel;
    private JComboBox fan36ComboBox;
    private JPanel fan26Panel;
    private JRadioButton fan26RadioButton;
    private JSpinner fan26Spinner;
    private JComboBox fan26ComboBox;
    private JPanel fanRoofPanel;
    private JRadioButton fanRoofRadioButton;
    private JSpinner fanRoofSpinner;
    private JComboBox fanRoofComboBox;
    private JRadioButton airInletOnWallRadioButton;
    private JPanel airInletOnWallPanel;
    private JSpinner airInletOnWallSpinner;
    private JComboBox airInletOnWallComboBox;
    private JPanel airInletForPadCoolPanel;
    private JRadioButton airInletForPadCoolRadioButton;
    private JSpinner airInletForPadCoolSpinner;
    private JComboBox airInletForPadCoolComboBox;
    private JPanel shutterPanel;
    private JRadioButton shutterRadioButton;
    private JSpinner shutterSpinner;
    private JComboBox shutterComboBox;
    private JPanel humidityPanel;
    private JRadioButton humidityRadioButton;
    private JSpinner humidityLengthSpinner1;
    private JSpinner humidityCountSpinner2;
    private JSpinner humidityCountSpinner1;
    private JSpinner humidityHeightSpinner;
    private JCheckBox humidityCheckBox;
    private JSpinner humidityLengthSpinner2;
    private JPanel heaterPanel;
    private JRadioButton heaterRadioButton;
    private JSpinner heaterSpinner;
    private JComboBox heaterComboBox;
    private JPanel fanCirculationPanel;
    private JRadioButton fanCirculationRadioButton;
    private JSpinner fanCirculationSpinner;
    private JComboBox fanCirculationComboBox;
    private JPanel automaticPanel;
    private JRadioButton automaticRadioButton;
    private JSpinner automaticSpinner;
    private JComboBox automaticComboBox;
    private JComboBox servomotorComboBox;
    private JSpinner servomotorSpinner;
    private JSpinner emergencySpinner;
    private JComboBox emergencyComboBox;
    private JCheckBox fan50LightTrapCheckBox;
    private JCheckBox fan36LightTrapCheckBox;
    private JCheckBox fan26LightTrapCheckBox;
    private JCheckBox airInletOnWallLightTrapCheckBox;
    private JCheckBox shutterLightTrapCheckBox;
    private JRadioButton airInletOfRoofRadioButton;
    private JSpinner airInletOfRoofSpinner;
    private JComboBox airInletOfRoofComboBox;
    private JButton automaticExtraPanelButton;
    private JPanel automaticExtraPanel;
    private JPanel airInletOfRoofPanel;
    private JTextField automaticOSHUMTextField;
    private JLabel automaticSensorTemperatureLabel;
    private JSpinner automaticSensorTemperatureSpinner;
    private JPanel automaticSensorPanel;
    private JLabel automaticSensorPressureLabel;
    private JSpinner automaticSensorPressureSpinner;
    private JLabel automaticSensorHumidityLabel;
    private JSpinner automaticSensorHumiditySpinner;
    private JLabel automaticSensorCO2Label;
    private JSpinner automaticSensorCO2Spinner;
    private JTextField automaticSSHUMTextField;

    public ResultsPanel(){
        count = Count.getInstance();
        count.setResultsPanel(this);

        add(mainPanel);

        dataOfEquipment = new ActualValues().loadActualValue();

        fan50Spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            }
        });


        automaticComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1)
                    changeVisibleOSHUMAndSSHUMTextField();
            }
        });

        setDefaultValues();
        setModelsToComboBox();
    }

    public void setDefaultValues(){
        setAllSpinnerMoreZeroValue();

        automaticSpinner.setValue(1);

        humidityHeightSpinner.setModel(new SpinnerNumberModel(new Double(2), new Double(1), new Double(2), new Double(0.5)));
        humidityLengthSpinner1.setModel(new SpinnerNumberModel(new Double(6),new Double(6), new Double(24), new Double(0.6)));
        humidityLengthSpinner2.setModel(new SpinnerNumberModel(new Double(6),new Double(6), new Double(24), new Double(0.6)));

        automaticSensorTemperatureSpinner.setModel(new SpinnerNumberModel(new Double(4), new Double(0), null, new Double(1)));
        automaticSensorHumiditySpinner.setModel(new SpinnerNumberModel(new Double(1), new Double(0), null, new Double(1)));
        automaticSensorPressureSpinner.setModel(new SpinnerNumberModel(new Double(1), new Double(0), null, new Double(1)));
        automaticSensorCO2Spinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
    }

    public void setAllSpinnerMoreZeroValue(){
        for(Component componentMainPanel : mainPanel.getComponents()){
            JPanel panel = (JPanel)componentMainPanel;
            for(Component component : panel.getComponents())
                if(component.getClass().equals(JSpinner.class)){
                    JSpinner spinner = (JSpinner)component;
                    spinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
                }
        }

    }

    public void setModelsToComboBox(){
        fan50ComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getFan50())));
        fan36ComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getFan36())));
        fan26ComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getFan26())));
        fanRoofComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getFanRoof())));
        airInletOnWallComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getAirInletOfWall())));
        airInletOfRoofComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getAirInletOfRoof())));
        airInletForPadCoolComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getAirInletOfPadCool())));
        shutterComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getShutter())));
        heaterComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getHeater())));
        fanCirculationComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getFanCirculation())));
        automaticComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getAutomatic())));
    }

    public String[] parseHashMapForComboBox(HashMap<String, Storage> map){
        String[] result = new String[map.size()];
        int i = 0;
        for(String key : map.keySet()){
            result[i] = key;
            i++;
        }
        return result;
    }

    public void hideElementsInPanel(JPanel panel){
        for(Component component : panel.getComponents())
            if(component.equals(JButton.class))
                System.out.println(component.getName());
    }

    public void setMyMainPanel(MyMainPanel myMainPanel) {
        this.myMainPanel = myMainPanel;
    }

    public void updateResults(){
//        fan50Spinner.setValue(new Integer(count.getFan50Count()));
        myMainPanel.update(this);
    }

    public void changeVisibleOSHUMAndSSHUMTextField(){
        if(automaticComboBox.getSelectedItem().toString().equals("ОЩУМ")){
            automaticOSHUMTextField.setEnabled(true);
            automaticSSHUMTextField.setEnabled(false);
        }
        else if(automaticComboBox.getSelectedItem().toString().equals("ОЩУМ + СЩУМ")){
            automaticOSHUMTextField.setEnabled(true);
            automaticSSHUMTextField.setEnabled(true);
        }
        else {
            automaticOSHUMTextField.setEnabled(false);
            automaticSSHUMTextField.setEnabled(false);

        }

    }



    public int getFan50Count() {
        return (int) fan50Spinner.getValue();
    }

    public void setFan50Count(int fan50Count) {
        fan50Spinner.setValue(fan50Count);
    }

    public String getFan50Name() {
        return fan50ComboBox.getSelectedItem().toString();
    }

    public boolean isFan50TwoSide() {
        return fan50TwoSideCheckBox.isSelected();
    }

    public int getAirInletBig() {
        return (int) airInletForPadCoolSpinner.getValue();
    }

    public void setAirInletBigCount(int airInletBigCount) {
        airInletForPadCoolSpinner.setValue(airInletBigCount);
    }

    public int getFan36Count() {
        return (int)fan36Spinner.getValue();
    }

    public void setFan36Count(int fan36Count) {
        fan36Spinner.setValue(fan36Count);
    }

    public String getFan36Name() {
        return fan36ComboBox.getSelectedItem().toString();
    }

    public int getFan26Count() {
        return (int)fan26Spinner.getValue();
    }

    public void setFan26Count(int fan26Count) {
        fan26Spinner.setValue(fan26Count);
    }

    public String getFan26Name() {
        return fan26ComboBox.getSelectedItem().toString();
    }

    public int getFanRoofCount() {
        return (int)fanRoofSpinner.getValue();
    }

    public void setFanRoofCount(int fanRoofCount) {
        fanRoofSpinner.setValue(fanRoofCount);
    }

    public String getFanRoofName() {
        return fanRoofComboBox.getSelectedItem().toString();
    }

    public int getAirInletSmallCount() {
        return (int) airInletOnWallSpinner.getValue();
    }

    public void setAirInletSmallCount(int airInletSmallCount) {
        airInletOnWallSpinner.setValue(airInletSmallCount);
    }

    public String getAirInletSmallName() {
        return airInletOnWallComboBox.getSelectedItem().toString();
    }

    public String getAirInletBigName() {
        return airInletForPadCoolComboBox.getSelectedItem().toString();
    }

    public int getShutterCount() {
        return (int)shutterSpinner.getValue();
    }

    public void setShutterCount(int shutterCount) {
        shutterSpinner.setValue(shutterCount);
    }

    public String getShutterName() {
        return shutterComboBox.getSelectedItem().toString();
    }

    public double getHumidityLength1() {
        return (double)humidityLengthSpinner1.getValue();
    }

    public void setHumidityLength1(double humidityLength1) {
        humidityLengthSpinner1.setValue(humidityLength1);
    }

    public int getHumidityCount2() {
        return (int)humidityCountSpinner2.getValue();
    }

    public void setHumidityCount2(int humidityCount2) {
        humidityCountSpinner2.setValue(humidityCount2);
    }

    public int getHumidityCount1() {
        return (int)humidityCountSpinner1.getValue();
    }

    public void setHumidityCount1(int humidityCount1) {
        humidityCountSpinner1.setValue(humidityCount1);
    }

    public double getHumidityHeight() {
        return (double)humidityHeightSpinner.getValue();
    }

    public void setHumidityHeight(double humidityHeight) {
        humidityHeightSpinner.setValue(humidityHeight);
    }

    public boolean isHumidityCheckBox() {                                                           //изменить название
        return humidityCheckBox.isSelected();
    }

    public double getHumidityLength2() {
        return (double)humidityLengthSpinner2.getValue();
    }

    public void setHumidityLength2(double humidityLength2) {
        humidityLengthSpinner2.setValue(humidityLength2);
    }

    public int getHeaterCount() {
        return (int)heaterSpinner.getValue();
    }

    public void setHeaterCount(int heaterCount) {
        heaterSpinner.setValue(heaterCount);
    }

    public String getHeaterName() {
        return heaterComboBox.getSelectedItem().toString();
    }

    public int getFanCirculationCount() {
        return (int)fanCirculationSpinner.getValue();
    }

    public void setFanCirculationCount(int fanCirculationCount) {
        fanCirculationSpinner.setValue(fanCirculationCount);
    }

    public String getFanCirculationName() {
        return fanCirculationComboBox.getSelectedItem().toString();
    }

    public int getAutomaticCount() {
        return (int)automaticSpinner.getValue();
    }

    public void setAutomaticCount(int automaticCount) {
        automaticSpinner.setValue(automaticCount);
    }

    public String getAutomaticName() {
        return automaticComboBox.getSelectedItem().toString();
    }

    public String getServomotorName() {
        return servomotorComboBox.getSelectedItem().toString();
    }

    public int getServomotorCount() {
        return (int)servomotorSpinner.getValue();
    }

    public void setServomotorCount(int servomotorCount) {
        servomotorSpinner.setValue(servomotorCount);
    }

    public int getEmergencyCount() {
        return (int)emergencySpinner.getValue();
    }

    public void setEmergencyCount(int emergencyCount) {
        emergencySpinner.setValue(emergencyCount);
    }

    public String getEmergencyName() {
        return emergencyComboBox.getSelectedItem().toString();
    }

    public int getAutomaticSensorTemperatureCount() {
        return (int)automaticSensorTemperatureSpinner.getValue();
    }

    public void setAutomaticSensorTemperatureCount(int automaticSensorTemperatureCount) {
        automaticSensorTemperatureSpinner.setValue(automaticSensorTemperatureCount);
    }

    public int getAutomaticSensorPressureCount() {
        return (int)automaticSensorPressureSpinner.getValue();
    }

    public void setAutomaticSensorPressureCount(int automaticSensorPressureCount) {
        automaticSensorPressureSpinner.setValue(automaticSensorPressureCount);
    }

    public int getAutomaticSensorHumidityCount() {
        return (int)automaticSensorHumiditySpinner.getValue();
    }

    public void setAutomaticSensorHumiditySpinner(int automaticSensorHumiditySpinner1) {
        automaticSensorHumiditySpinner.setValue(automaticSensorHumiditySpinner1);
    }

    public int getAutomaticSensorCO2Count() {
        return (int)automaticSensorCO2Spinner.getValue();
    }

    public void setAutomaticSensorCO2Spinner(int automaticSensorCO2Spinner1) {
        automaticSensorCO2Spinner.setValue(automaticSensorCO2Spinner1);
    }

    public String getAutomaticOSHUMName() {
        return automaticOSHUMTextField.getText();
    }

    public String getAutomaticSSHUMName() {
        return automaticSSHUMTextField.getText();
    }

}
