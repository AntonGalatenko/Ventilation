package com.toxa.ventilation.gui;

import com.toxa.ventilation.Count;
import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.Data.DataOfEquipment;
import com.toxa.ventilation.Data.Storage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ResultsPanel extends JPanel{
    private Count count;
//    private MyMainPanel myMainPanel;
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
    private JSpinner humidityHeightSpinner1;
    private JCheckBox humidityPlusCheckBox;
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
    private JRadioButton shaftRadioButton;
    private JPanel shaftPanel;
    private JSpinner shaftSpinner;
    private JComboBox shaftComboBox;
    private JSpinner humidityHeightSpinner2;
    private JLabel humidityAirSpeedLabel;
    private JLabel heaterLabel;
    private JLabel fan50AirSpeedLabel;

    public ResultsPanel(){
        count = Count.getInstance();
        count.setResultsPanel(this);

        add(mainPanel);

        setDefaultValues();
        setModelsToComboBox();

        fan50Spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                count.countShutter();
                count.countPadCoolAndAirInlet();
                count.countAirSummerCurrent();
                count.countFan50AirSpeed();
            }
        });

        fan36Spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                count.countShaft();
                count.countAirWinterCurrent();
            }
        });

        fan26Spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                count.countShaft();
                count.countAirWinterCurrent();
            }
        });

        fanRoofSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                count.countAirInletOnWall();
                count.countAirWinterCurrent();
            }
        });

        shaftSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        airInletOnWallSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        airInletOfRoofSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        airInletForPadCoolSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        shutterSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        humidityCountSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                count.countAirInletPadCool();
                count.padCoolAirSpeedCurrent();
            }
        });

        humidityCountSpinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                count.countAirInletPadCool();
                count.padCoolAirSpeedCurrent();
            }
        });

        heaterSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        fanCirculationSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        humidityLengthSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                count.countAirInletPadCool();
                count.padCoolAirSpeedCurrent();
            }
        });

        humidityLengthSpinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                count.countAirInletPadCool();
                count.padCoolAirSpeedCurrent();
            }
        });

        humidityHeightSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                count.countAirInletPadCool();
                count.padCoolAirSpeedCurrent();
            }
        });

        humidityHeightSpinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                count.countAirInletPadCool();
                count.padCoolAirSpeedCurrent();
            }
        });

        automaticComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1)
                    changeVisibleOSHUMAndSSHUMTextField();
            }
        });



        fan50RadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(fan50Panel);
                else
                    disableElementsInPanel(fan50Panel);
            }
        });

        fan36RadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(fan36Panel);
                else
                    disableElementsInPanel(fan36Panel);

                if(getFan36Count() != 0)
                    count.countAirWinterCurrent();
            }
        });

        fan26RadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(fan26Panel);
                else
                    disableElementsInPanel(fan26Panel);

                if(getFan26Count() != 0)
                    count.countAirWinterCurrent();
            }
        });

        fanRoofRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(fanRoofPanel);
                else
                    disableElementsInPanel(fanRoofPanel);

                if(getFanRoofCount() != 0)
                    count.countAirWinterCurrent();
            }
        });

        shaftRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(shaftPanel);
                else
                    disableElementsInPanel(shaftPanel);
            }
        });

        airInletOnWallRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(airInletOnWallPanel);
                else
                    disableElementsInPanel(airInletOnWallPanel);
            }
        });

        airInletOfRoofRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(airInletOfRoofPanel);
                else
                    disableElementsInPanel(airInletOfRoofPanel);
            }
        });

        airInletForPadCoolRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(airInletForPadCoolPanel);
                else
                    disableElementsInPanel(airInletForPadCoolPanel);
            }
        });

        shutterRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(shutterPanel);
                else
                    disableElementsInPanel(shutterPanel);
            }
        });

        humidityRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    enableElementsInPanel(humidityPanel);
                    if(! getAirInletForPadCoolRadioButton().isSelected())
                        airInletForPadCoolRadioButton.doClick();
                    if(getShutterRadioButton().isSelected())
                        shutterRadioButton.doClick();
                }
                else{
                    disableElementsInPanel(humidityPanel);
                    if(getAirInletForPadCoolRadioButton().isSelected())
                        airInletForPadCoolRadioButton.doClick();
                    if(! getShutterRadioButton().isSelected())
                        shutterRadioButton.doClick();
                }
            }
        });

        heaterRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(heaterPanel);
                else
                    disableElementsInPanel(heaterPanel);
            }
        });

        fanCirculationRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(fanCirculationPanel);
                else
                    disableElementsInPanel(fanCirculationPanel);
            }
        });

        automaticRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    enableElementsInPanel(automaticPanel);
                else
                    disableElementsInPanel(automaticPanel);
            }
        });


//        fan50RadioButton.addItemListener(new MyItemListener(fan50Panel));
//        fan36RadioButton.addItemListener(new MyItemListener(fan36Panel));
//        fan26RadioButton.addItemListener(new MyItemListener(fan26Panel));
//        fanRoofRadioButton.addItemListener(new MyItemListener(fanRoofPanel));
//        shaftRadioButton.addItemListener(new MyItemListener(shaftPanel));
//        airInletOnWallRadioButton.addItemListener(new MyItemListener(airInletOnWallPanel));
//        airInletOfRoofRadioButton.addItemListener(new MyItemListener(airInletOfRoofPanel));
//        airInletForPadCoolRadioButton.addItemListener(new MyItemListener(airInletForPadCoolPanel));
//        shutterRadioButton.addItemListener(new MyItemListener(shutterPanel));
//        humidityRadioButton.addItemListener(new MyItemListener(humidityPanel));
//        heaterRadioButton.addItemListener(new MyItemListener(heaterPanel));
//        fanCirculationRadioButton.addItemListener(new MyItemListener(fanCirculationPanel));
//        automaticRadioButton.addItemListener(new MyItemListener(automaticPanel));

        fan50TwoSideCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count.countFan50();
            }
        });

        humidityPlusCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count.countPadCoolAndAirInlet();
            }
        });

        setElementsOnPanelForTunnelVentilationType();
        setElementOnPanelDisableForHeating();

    }

    public class MyItemListener implements ItemListener{
        private JPanel panel;

        public MyItemListener(JPanel panel){
            this.panel = panel;
        }
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED)
                enableElementsInPanel(panel);
            else
                disableElementsInPanel(panel);
        }
    }

    public void setDefaultValues(){
        setAllSpinnerMoreZeroValue();

        automaticSpinner.setValue(1);

        humidityHeightSpinner1.setModel(new SpinnerNumberModel(new Double(2), new Double(1), new Double(2), new Double(0.5)));
        humidityHeightSpinner2.setModel(new SpinnerNumberModel(new Double(2), new Double(1), new Double(2), new Double(0.5)));
        humidityLengthSpinner1.setModel(new SpinnerNumberModel(new Double(6),new Double(6), new Double(24), new Double(0.6)));
        humidityLengthSpinner2.setModel(new SpinnerNumberModel(new Double(6),new Double(6), new Double(24), new Double(0.6)));

        automaticSensorTemperatureSpinner.setModel(new SpinnerNumberModel(new Double(4), new Double(0), null, new Double(1)));
        automaticSensorHumiditySpinner.setModel(new SpinnerNumberModel(new Double(1), new Double(0), null, new Double(1)));
        automaticSensorPressureSpinner.setModel(new SpinnerNumberModel(new Double(1), new Double(0), null, new Double(1)));
        automaticSensorCO2Spinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
    }

    public void setAllSpinnerMoreZeroValue(){
        ArrayList<JSpinner> list = getNeededComponent(new JSpinner());
        for(JSpinner spinner : list)
            spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
    }

    public ArrayList getNeededComponent(Component neededComponent){
        ArrayList result = new ArrayList();
        for(Component c : mainPanel.getComponents()){
            JPanel panel = (JPanel)c;
            for(Component component : panel.getComponents())
                if(component.getClass().equals(neededComponent.getClass()))
                    result.add(component);
        }
        return result;
    }

    public void setModelsToComboBox(){
        dataOfEquipment = new ActualValues().loadActualValue();

        fan50ComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getFan50())));
        fan36ComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getFan36())));
        fan26ComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getFan26())));
        fanRoofComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getFanRoof())));
        shaftComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getShaft())));
        airInletOnWallComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getAirInletOfWall())));
        airInletOfRoofComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getAirInletOfRoof())));
        airInletForPadCoolComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getAirInletForPadCool())));
        shutterComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getShutter())));
        heaterComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getHeater())));
        fanCirculationComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getFanCirculation())));
        automaticComboBox.setModel(new DefaultComboBoxModel(parseHashMapForComboBox(dataOfEquipment.getAutomatic())));
    }

    public String[] parseHashMapForComboBox(LinkedHashMap<String, Storage> map){
        String[] result = new String[map.size()];
        int i = 0;
        for(String key : map.keySet()){
            result[i] = key;
            i++;
        }
        return result;
    }

    public void disableElementsInPanel(JRadioButton radioButton){
        if(radioButton.isSelected())
            radioButton.doClick();
    }

    public void enableElementsInPanel(JRadioButton radioButton){
        if(!radioButton.isSelected())
            radioButton.doClick();
    }

    public void disableElementsInPanel(JPanel panel){
        for(Component component : panel.getComponents()){
            if(component.getClass().equals(JPanel.class))
                disableElementsInPanel((JPanel)component);
            if(!component.getClass().equals(JRadioButton.class))
                component.setEnabled(false);
        }
    }

    public void enableElementsInPanel(JPanel panel){
        for(Component component : panel.getComponents()){
            if(component.getClass().equals(JPanel.class))
                enableElementsInPanel((JPanel)component);
            if(!component.getClass().equals(JRadioButton.class))
                component.setEnabled(true);
        }

    }

//    public void setEnableOrDisableFan50(boolean value){
//        for(Component component : fan50Panel.getComponents()){
//             if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableFan36(boolean value){
//        for(Component component : fan36Panel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableFan26(boolean value){
//        for(Component component : fan26Panel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableFanRoof(boolean value){
//        for(Component component : fanRoofPanel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableShaft(boolean value){
//        for(Component component : shaftPanel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableAirInletWall(boolean value){
//        for(Component component : airInletOnWallPanel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableAirInletRoof(boolean value){
//        for(Component component : airInletOfRoofPanel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableAirInletPadCool(boolean value){
//        for(Component component : airInletForPadCoolPanel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableShutter(boolean value){
//        for(Component component : shutterPanel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableHumidity(boolean value){
//        for(Component component : humidityPanel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableHeater(boolean value){
//        for(Component component : heaterPanel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableFanCirculation(boolean value){
//        for(Component component : fanCirculationPanel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }
//
//    public void setEnableOrDisableAutomatic(boolean value){
//        for(Component component : automaticPanel.getComponents()){
//            if(!component.getClass().equals(JRadioButton.class))
//                component.setEnabled(value);
//        }
//    }





//    public void setMyMainPanel(MyMainPanel myMainPanel) {
//        this.myMainPanel = myMainPanel;
//    }

//    public void updateResults(){
//        fan50Spinner.setValue(new Integer(count.getFan50Count()));
//        myMainPanel.update(this);
//    }

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

    public int getShaftCount() {
        return (int) shaftSpinner.getValue();
    }

    public void setShaftCount(int shaftCount) {
        shaftSpinner.setValue(shaftCount);
    }

    public String getShaftName() {
        return shaftComboBox.getSelectedItem().toString();
    }

    public int getAirInletForPadCoolCount() {
        return (int) airInletForPadCoolSpinner.getValue();
    }

    public void setAirInletForPadCoolCount(int airInletBigCount) {
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

    public int getAirInletOnWallCount() {
        return (int) airInletOnWallSpinner.getValue();
    }

    public void setAirInletOnWallCount(int airInletSmallCount) {
        airInletOnWallSpinner.setValue(airInletSmallCount);
    }

    public String getAirInletOnWallName() {
        return airInletOnWallComboBox.getSelectedItem().toString();
    }

    public String getAirInletOfRoofName() {
        return airInletOfRoofComboBox.getSelectedItem().toString();
    }

    public int getAirInletOfRoofCount() {
        return (int) airInletOfRoofSpinner.getValue();
    }

    public void setAirInletOfRoofCount(int airInletOfRoofCount) {
        airInletOfRoofSpinner.setValue(airInletOfRoofCount);
    }

    public String getAirInletForPadCoolName() {
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

    public double getHumidityHeight1() {
        return (double) humidityHeightSpinner1.getValue();
    }

    public double getHumidityHeight2() {
        return (double) humidityHeightSpinner2.getValue();
    }

    public void setHumidityHeight(double humidityHeight) {
        humidityHeightSpinner1.setValue(humidityHeight);
    }

    public boolean isHumidityPlus() {                                                           //изменить название
        return humidityPlusCheckBox.isSelected();
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

    public JRadioButton getFan50RadioButton() {
        return fan50RadioButton;
    }

    public JRadioButton getFan36RadioButton() {
        return fan36RadioButton;
    }

    public JRadioButton getFan26RadioButton() {
        return fan26RadioButton;
    }

    public JRadioButton getFanRoofRadioButton() {
        return fanRoofRadioButton;
    }

    public JRadioButton getShaftRadioButton() {
        return shaftRadioButton;
    }

    public JRadioButton getAirInletOnWallRadioButton() {
        return airInletOnWallRadioButton;
    }

    public JRadioButton getAirInletForPadCoolRadioButton() {
        return airInletForPadCoolRadioButton;
    }

    public JRadioButton getShutterRadioButton() {
        return shutterRadioButton;
    }

    public JRadioButton getHumidityRadioButton() {
        return humidityRadioButton;
    }

    public JRadioButton getHeaterRadioButton() {
        return heaterRadioButton;
    }

    public JRadioButton getFanCirculationRadioButton() {
        return fanCirculationRadioButton;
    }

    public JRadioButton getAutomaticRadioButton() {
        return automaticRadioButton;
    }

    public JRadioButton getAirInletOfRoofRadioButton() {
        return airInletOfRoofRadioButton;
    }

    public void setHumidityAirSpeed(double value) {
        humidityAirSpeedLabel.setText(String.format("%.2f", value) + "м/c");
    }

    public boolean isFan50LightTrap() {
        return fan50LightTrapCheckBox.isSelected();
    }

    public boolean isFan36LightTrap() {
        return fan36LightTrapCheckBox.isSelected();
    }

    public boolean isFan26LightTrap() {
        return fan26LightTrapCheckBox.isSelected();
    }

    public boolean isAirInletOnWallLightTrap() {
        return airInletOnWallLightTrapCheckBox.isSelected();
    }

    public boolean isShutterLightTrap() {
        return shutterLightTrapCheckBox.isSelected();
    }

    public void setHeaterNeedPower(double value) {
        heaterLabel.setText(String.format("%d", (int)value) + "кВт");
    }

    public void setFan50AirSpeed(double value) {
        fan50AirSpeedLabel.setText(String.format("%.2f", value) + "м/с");
    }

    public void setElementsOnPanelForTunnelVentilationType(){
        enableElementsInPanel(getFan50RadioButton());
        enableElementsInPanel(getAirInletOnWallRadioButton());
        enableElementsInPanel(getAirInletForPadCoolRadioButton());
        enableElementsInPanel(getHumidityRadioButton());

        disableElementsInPanel(getFan36RadioButton());
        disableElementsInPanel(getFan26RadioButton());
        disableElementsInPanel(getFanRoofRadioButton());
        disableElementsInPanel(getAirInletOfRoofRadioButton());
        disableElementsInPanel(getShaftRadioButton());
        disableElementsInPanel(getShutterRadioButton());
    }

    public void setElementsOnPanelForEuroVentilationType(){
        enableElementsInPanel(getFan50RadioButton());
        enableElementsInPanel(getFan26RadioButton());
        enableElementsInPanel(getFanRoofRadioButton());
        enableElementsInPanel(getAirInletForPadCoolRadioButton());
        enableElementsInPanel(getHumidityRadioButton());
        enableElementsInPanel(getAirInletOnWallRadioButton());

        disableElementsInPanel(getShaftRadioButton());
        disableElementsInPanel(getFan36RadioButton());
        disableElementsInPanel(getAirInletOfRoofRadioButton());
        disableElementsInPanel(getShutterRadioButton());
    }

    public void setElementsOnPanelForTexhaVentilationType(){
        enableElementsInPanel(getFan50RadioButton());
        enableElementsInPanel(getFan26RadioButton());
        enableElementsInPanel(getShaftRadioButton());
        enableElementsInPanel(getShutterRadioButton());

        disableElementsInPanel(getFan36RadioButton());
        disableElementsInPanel(getFanRoofRadioButton());
        disableElementsInPanel(getAirInletOnWallRadioButton());
        disableElementsInPanel(getAirInletOfRoofRadioButton());
        disableElementsInPanel(getAirInletForPadCoolRadioButton());
        disableElementsInPanel(getHumidityRadioButton());
    }

    public void setElementOnPanelDisableForHeating(){
        disableElementsInPanel(getHeaterRadioButton());
        disableElementsInPanel(getFanCirculationRadioButton());
    }

    public void setElementOnPanelEnableForHeating(){
        enableElementsInPanel(getHeaterRadioButton());
        enableElementsInPanel(getFanCirculationRadioButton());
    }

}
