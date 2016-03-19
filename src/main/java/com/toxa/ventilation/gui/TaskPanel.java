package com.toxa.ventilation.gui;

import com.toxa.ventilation.CageInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TaskPanel extends JPanel{

    private CageInfo cageInfo = CageInfo.getInstance();

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
        cageInfo.setTaskPanel(this);


        cageNameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1)
                    cageInfo.setInfo();



            }
        });

        ventilationTypeComboBox.setSelectedIndex(0);
        ventilationTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1)
                    cageInfo.setInfo();


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
                System.out.println(getCageTiersComboBox1());
                System.out.println(getCageNumberComboBox1());
                System.out.println(getCageTiersComboBox2());
                System.out.println(getCageNumberComboBox2());
                System.out.println(getVentilationType());
                System.out.println(getAirSummerSpinner());
                System.out.println(getAirWinterSpinner());
            }
        });

        setDefaultValues();
    }

    public void setDefaultValues() {
        airSummerSpinner.setModel(new SpinnerNumberModel(new Double(12), new Double(0), null, new Double(0.5)));
        airWinterSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.5)));
        cageNumberComboBox1.setSelectedIndex(1);
//        cageNameComboBox.setSelectedIndex(0);

        updateCageTiersComboBox();
    }

    public String getCompanyName() {
        return companyNameTextField.getText();
    }

    public String getCountry(){
        return countryTextField.getText();
    }

    public int getHeadsNumber() {
        return Integer.parseInt(headsNumberTextField.getText());
    }

    public int getBuildingLength() {
        return Integer.parseInt(lengthTextField.getText());
    }

    public int getBuildingWidth() {
        return Integer.parseInt(widthTextField.getText());
    }

    public int getBuildingHeightMin() {
        return Integer.parseInt(heightMinTextField.getText());
    }

    public int getBuildingHeightMax() {
        return Integer.parseInt(heightMaxTextField.getText());
    }

    public String getCageName(){
        return cageNameComboBox.getSelectedItem().toString();
    }

    public int getCageTiersComboBox1() {
        return Integer.parseInt(cageTiersComboBox1.getSelectedItem().toString());
    }

    public int getCageNumberComboBox1() {
        return Integer.parseInt(cageNumberComboBox1.getSelectedItem().toString());
    }

    public int getCageTiersComboBox2() {
        return Integer.parseInt(cageTiersComboBox2.getSelectedItem().toString());
    }

    public int getCageNumberComboBox2() {
//        return Integer.parseInt(cageNumberComboBox2.getSelectedItem().toString());
        return Integer.parseInt(cageNumberComboBox2.getSelectedItem().toString());
    }

    public String getVentilationType(){
        return ventilationTypeComboBox.getSelectedItem().toString();
    }

    public double getAirWinterSpinner() {
        return (double) airWinterSpinner.getValue();
    }

    public double getAirSummerSpinner() {
        return (double) airSummerSpinner.getValue();
    }

    private DefaultComboBoxModel getCageTiersForComboBoxModel(){
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        for(Integer i : cageInfo.getCageTiers())
            defaultComboBoxModel.addElement(i);

        return defaultComboBoxModel;
    }

    public void updateCageTiersComboBox() {
        cageTiersComboBox1.setModel(getCageTiersForComboBoxModel());
        cageTiersComboBox2.setModel(getCageTiersForComboBoxModel());
    }

    public void updateAirSpinner() {
        airSummerSpinner.setValue(cageInfo.getAirSummer());
        airWinterSpinner.setValue(cageInfo.getAirWinter());
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
