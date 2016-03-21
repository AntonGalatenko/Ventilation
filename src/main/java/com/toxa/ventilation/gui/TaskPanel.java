package com.toxa.ventilation.gui;

import com.toxa.ventilation.Count;
import com.toxa.ventilation.ExtraInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TaskPanel extends JPanel{

//    private static TaskPanel instance;
    private ExtraInfo extraInfo;
    private Count count;

    private JPanel mainPanel;
    private JTextField lengthTextField;
    private JLabel lengthLabel;
    private JPanel buildingParametersPanel;
    private JTextField companyNameTextField;
    private JLabel companyNameLabel;
    private JLabel countryLabel;
    private JTextField countryTextField;
    private JPanel generalInfoPanel;
    private JLabel widthLabel;
    private JTextField widthTextField;
    private JTextField heightMinTextField;
    private JLabel heightMinLabel;
    private JTextField heightMaxTextField;
    private JLabel heightMaxLabel;
    private JLabel headsNumberLabel;
    private JTextField headsNumberTextField;
    private JPanel cageInfoPanel;
    private JLabel cageNameLabel;
    private JComboBox cageNameComboBox;
    private JComboBox cageTiersComboBox1;
    private JComboBox cageNumberComboBox1;
    private JLabel cageTiersLabel1;
    private JLabel cageNumberLabel1;
    private JLabel cageTiersLabel2;
    private JComboBox cageTiersComboBox2;
    private JLabel cageNumberLabel2;
    private JComboBox cageNumberComboBox2;
    private JLabel ventilationTypeLabel;
    private JComboBox ventilationTypeComboBox;
    private JPanel climatInfoPanel;
    private JLabel airQuantityLabel;
    private JLabel airSummerLabel;
    private JSpinner airSummerSpinner;
    private JLabel airWinterLabel;
    private JSpinner airWinterSpinner;
    private JPanel cageTiredAndCageNumberInfoPanel;
    private JPanel cageNameInfoPanel;
    private JPanel performerPanel;
    private JButton countButton;

    public TaskPanel(){

        add(mainPanel);

        extraInfo = new ExtraInfo(this);

        cageNameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1)
                    extraInfo.setInfo();

            }
        });

        ventilationTypeComboBox.setSelectedIndex(0);
        ventilationTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1)
                    extraInfo.setInfo();

            }
        });

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getCompanyName());
                System.out.println(getCountry());
                System.out.println(getHeadsNumber());
                System.out.println(getBuildingLength());
                System.out.println(getBuildingWidth());
                System.out.println(getBuildingHeightMin());
                System.out.println(getBuildingHeightMax());
                System.out.println(getCageName());
                System.out.println(getCageTiers1());
                System.out.println(getCageNumber1());
                System.out.println(getCageTiers2());
                System.out.println(getCageNumber2());
                System.out.println(getVentilationType());
                System.out.println(getAirSummer());
                System.out.println(getAirWinter());

                createCountClass();
            }
        });

        setDefaultValues();
    }

//    public static TaskPanel getInstance(){
//        if(instance == null)
//            instance = new TaskPanel();
//        return instance;
//    }

    public void setDefaultValues() {
        airSummerSpinner.setModel(new SpinnerNumberModel(new Double(12), new Double(0), null, new Double(0.5)));
        airWinterSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.5)));
        cageNumberComboBox1.setSelectedIndex(1);
//        cageNameComboBox.setSelectedIndex(0);

        updateCageTiersComboBox();
    }

    public void createCountClass(){
        count = new Count(this, extraInfo);

//        count.setTaskPanel(this);
//        count.setExtraInfo(extraInfo);

    }

    public void setCountInResultsPanel(ResultsPanel resultsPanel){
        resultsPanel.setCount(count);
        count.setResultsPanel(resultsPanel);
    }

    public Count getCount() {
        return count;
    }

    public String getCompanyName() {
        if(companyNameTextField.getText().length() == 0)
            companyNameTextField.setText("Название фабрики");
        return companyNameTextField.getText();
    }

    public String getCountry(){
        if(countryTextField.getText().length() == 0)
            countryTextField.setText("Страна");
        return countryTextField.getText();
    }

    public int getHeadsNumber() {
        if(headsNumberTextField.getText().length() == 0)
            headsNumberTextField.setText("96360");
        return Integer.parseInt(headsNumberTextField.getText());
    }

    public int getBuildingLength() {
        if(lengthTextField.getText().length() == 0)
            lengthTextField.setText("96");
        return Integer.parseInt(lengthTextField.getText());
    }

    public int getBuildingWidth() {
        if(widthTextField.getText().length() == 0)
            widthTextField.setText("18");
        return Integer.parseInt(widthTextField.getText());
    }

    public int getBuildingHeightMin() {
        if(heightMinTextField.getText().length() == 0)
            heightMinTextField.setText("4");
        return Integer.parseInt(heightMinTextField.getText());
    }

    public int getBuildingHeightMax() {
        if(heightMaxTextField.getText().length() == 0)
            heightMaxTextField.setText("5");
        return Integer.parseInt(heightMaxTextField.getText());
    }

    public String getCageName(){
        return cageNameComboBox.getSelectedItem().toString();
    }

    public int getCageTiers1() {
        return Integer.parseInt(cageTiersComboBox1.getSelectedItem().toString());
    }

    public int getCageNumber1() {
        return Integer.parseInt(cageNumberComboBox1.getSelectedItem().toString());
    }

    public int getCageTiers2() {
        return Integer.parseInt(cageTiersComboBox2.getSelectedItem().toString());
    }

    public int getCageNumber2() {
        return Integer.parseInt(cageNumberComboBox2.getSelectedItem().toString());
    }

    public String getVentilationType(){
        return ventilationTypeComboBox.getSelectedItem().toString();
    }

    public double getAirWinter() {
        return (double) airWinterSpinner.getValue();
    }

    public double getAirSummer() {
        return (double) airSummerSpinner.getValue();
    }

    public void setAirSummer(double value) {
        airSummerSpinner.setValue(value);
    }

    public void setAirWinter(double value) {
        airWinterSpinner.setValue(value);
    }

    private DefaultComboBoxModel getCageTiersForComboBoxModel(){
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        for(Integer i : extraInfo.getCageTiers())
            defaultComboBoxModel.addElement(i);

        return defaultComboBoxModel;
    }

    public void updateCageTiersComboBox() {
        cageTiersComboBox1.setModel(getCageTiersForComboBoxModel());
        cageTiersComboBox2.setModel(getCageTiersForComboBoxModel());
    }

    public void updateAirSpinner() {
        airSummerSpinner.setValue(getAirSummer());
        airWinterSpinner.setValue(getAirWinter());
    }

    public void setEnableCageTiredAndCageNumberComboBox() {
        Component[] components = cageTiredAndCageNumberInfoPanel.getComponents();
        for(Component c : components)
            c.setEnabled(true);
    }

    public void setDisablesCageTiredAndCageNumberComboBox() {
        Component[] components = cageTiredAndCageNumberInfoPanel.getComponents();
        for(Component c : components)
            c.setEnabled(false);
    }

}
