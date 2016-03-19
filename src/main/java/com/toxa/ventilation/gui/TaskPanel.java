package com.toxa.ventilation.gui;

import com.toxa.ventilation.CageInfo;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TaskPanel extends JPanel{

    private CageInfo cageInfo = CageInfo.getInstance();

    private JPanel mainPanel;
    private JTextField lengthTextField;
    private JLabel lengthLabel;
    private JPanel BuildingParametersPanel;
    private JTextField companyNameTextField;
    private JLabel companyNameLabel;
    private JLabel countryLabel;
    private JTextField countryTextField;
    private JPanel GeneralInfoPanel;
    private JLabel widthLabel;
    private JTextField widthTextField;
    private JTextField heightMinTextField;
    private JLabel heightMinLabel;
    private JTextField heightMaxTextField;
    private JLabel heightMaxLabel;
    private JLabel headsNumberLabel;
    private JTextField headsNumberTextField;
    private JPanel CageInfoPanel;
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
    private JPanel ClimatInfoPanel;
    private JLabel airQuantityLabel;
    private JLabel airSummerLabel;
    private JSpinner airSummerSpinner;
    private JLabel airWinterLabel;
    private JSpinner airWinterSpinner;

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

        setDefaultValues();

    }

    public void setDefaultValues() {
        cageNumberComboBox1.setSelectedIndex(1);
//        cageNameComboBox.setSelectedIndex(0);
        airSummerSpinner.setValue(12);
        airWinterSpinner.setValue(0);

        updateCageTiersComboBox();
    }

    public String getCageName(){
        return cageNameComboBox.getSelectedItem().toString();
    }

    public String getVentilationType(){
        return ventilationTypeComboBox.getSelectedItem().toString();
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
        cageTiersLabel1.setEnabled(true);
        cageTiersLabel2.setEnabled(true);
        cageNumberLabel1.setEnabled(true);
        cageNumberLabel2.setEnabled(true);

        cageTiersComboBox1.setEnabled(true);
        cageTiersComboBox2.setEnabled(true);
        cageNumberComboBox1.setEnabled(true);
        cageNumberComboBox2.setEnabled(true);
    }

    public void setDisablesCageTiredAndCageNumberComboBox() {
        cageTiersLabel1.setEnabled(false);
        cageTiersLabel2.setEnabled(false);
        cageNumberLabel1.setEnabled(false);
        cageNumberLabel2.setEnabled(false);

        cageTiersComboBox1.setEnabled(false);
        cageTiersComboBox2.setEnabled(false);
        cageNumberComboBox1.setEnabled(false);
        cageNumberComboBox2.setEnabled(false);
    }

}
