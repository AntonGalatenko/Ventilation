package com.toxa.ventilation.gui;

import com.toxa.ventilation.Count;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ResultsPanel extends JPanel{
    private Count count;
    private MyMainPanel2 myMainPanel;

    private JPanel mainPanel;
    private JPanel fan50Panel;
    private JRadioButton fan50RadioButton;
    public JSpinner fan50Spinner;
    private JRadioButton fan36RadioButton;
    private JSpinner fan36spinner;
    private JPanel fan36Panel;
    private JPanel fan26Panel;
    private JRadioButton fan26RadioButton;
    private JSpinner fan26Spinner;
    private JPanel fanRoofPanel;
    private JComboBox fan50ComboBox;
    private JComboBox fan36ComboBox;
    private JComboBox fan26ComboBox;
    private JRadioButton fanFoorRadioButton;
    private JSpinner fanRoofSpinner;
    private JComboBox fanRoofComboBox;
    private JRadioButton airInletSmallRadioButton;
    private JPanel airInletSmallPanell;
    private JSpinner airInletSmallSpinner;
    private JComboBox airInletSmallComboBox;
    private JPanel airInletBigPanel;
    private JRadioButton airInletBigRadioButton;
    private JSpinner airInletBigSpinner;
    private JComboBox airInletBigComboBox;
    private JPanel shutterPanel;
    private JRadioButton shutterRadioButton;
    private JSpinner shutterSpinner;
    private JComboBox shutterComboBox;
    private JPanel humidityPanel;
    private JRadioButton humidityRadioButton;
    private JSpinner humidityLengthSpiner1;
    private JCheckBox fan50CheckBox;
    private JPanel heaterPanel;
    private JRadioButton heaterRadioButton;
    private JSpinner heaterSpinner;
    private JComboBox heaterComboBox;
    private JPanel fanCirculationPanel;
    private JRadioButton fanCirculationRadioButton;
    private JSpinner fanCirculationSpiner;
    private JComboBox fanCirculationComboBox;
    private JPanel automaticPanel;
    private JRadioButton automaticRadioButton;
    private JSpinner automaticSpinner;
    private JComboBox automaticComboBox;
    private JSpinner humidityCountSpiner2;
    private JSpinner humidityCountSpiner1;
    private JSpinner humidityHeightSpiner;
    private JCheckBox humidityCheckBox;
    private JSpinner humidityLengthSpiner2;
    private JComboBox servomotorComboBox;
    private JSpinner servomotorSpinner;
    private JSpinner emergencySpinner;
    private JComboBox emergencyComboBox;

    public ResultsPanel(){
        count = Count.getInstance();
        count.setResultsPanel(this);

        add(mainPanel);

        fan50Spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            }
        });
    }

    public void setMyMainPanel(MyMainPanel2 myMainPanel) {
        this.myMainPanel = myMainPanel;
    }

    public void updateResults(){
        fan50Spinner.setValue(new Integer(count.getFan50Count()));
        myMainPanel.update(this);
    }

    public int getFan50spinner() {
        return (int)(fan50Spinner.getValue());
    }
}
