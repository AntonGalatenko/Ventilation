package com.toxa.ventilation.gui;

import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.Count;
import com.toxa.ventilation.ExcelApachePOI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TaskPanel extends JPanel{

    private BaseInfo baseInfo;
    private Count count;
    private ExcelApachePOI excelApachePOI;
    private MyMainPanel myMainPanel;

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
    private JLabel poultryHouseNamberLabel;
    private JTextField poultryHouseNumberTextField;
    private JLabel airSummerCurrentLabel;
    private JLabel airWinterCurrentLabel;
    private JLabel airTotalCurrentLabel;
    private JButton saveExcelButton;
    private JButton saveOpenExcelButton;
    private JButton hideShowResPanelButton;

    public TaskPanel(){

        add(mainPanel);

//        baseInfo = new BaseInfo(this);
        baseInfo = BaseInfo.getInstance();
        baseInfo.setTaskPanel(this);
//        count = new Count();
//        count.setBaseInfo(baseInfo);


        cageNameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1){
                    baseInfo.setInfo();
                }
            }
        });

        ventilationTypeComboBox.setSelectedIndex(0);
        ventilationTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1)
                    baseInfo.setInfo();
            }
        });

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count.startCount();

                baseInfo.setResultPanelVisible(true);
                myMainPanel.pack();
            }
        });

        saveOpenExcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseInfo.getJson();
                excelApachePOI = new ExcelApachePOI();
                excelApachePOI.setOpenExcel(true);
                excelApachePOI.saveThis();
            }
        });

        hideShowResPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(baseInfo.isResultsPanelVisible())
                    baseInfo.setResultPanelVisible(false);
                else
                    baseInfo.setResultPanelVisible(true);
                myMainPanel.pack();
            }
        });

        setDefaultValues();
    }

    public void setCount(Count count){
        this.count = count;
    }

    public void setMyMainPanel(MyMainPanel myMainPanel){
        this.myMainPanel = myMainPanel;
    }

    public void setDefaultValues() {
        airSummerSpinner.setModel(new SpinnerNumberModel(new Double(12), new Double(0), null, new Double(0.5)));
        airWinterSpinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.5)));
        cageNumberComboBox1.setSelectedIndex(1);

        updateCageTiersComboBox();
    }

    public void updateCageTiersComboBox() {
        cageTiersComboBox1.setModel(getCageTiersForComboBoxModel());
        cageTiersComboBox2.setModel(getCageTiersForComboBoxModel());
    }

    public String getCompanyName() {
        return companyNameTextField.getText();
    }

    public String getCountry(){
        return countryTextField.getText();
    }

    public int getHeadsNumber() {
        try{
            headsNumberTextField.setBackground(Color.WHITE);
            return Integer.parseInt(headsNumberTextField.getText());
        } catch (NumberFormatException e){
            headsNumberTextField.setBackground(Color.YELLOW);
            e.printStackTrace();
        }
        return Integer.parseInt(headsNumberTextField.getText());
    }

    public double getBuildingLength() {
        try{
            lengthTextField.setBackground(Color.WHITE);
            return Integer.parseInt(lengthTextField.getText());
        } catch (NumberFormatException e){
            lengthTextField.setBackground(Color.YELLOW);
            e.printStackTrace();
        }
        return Double.parseDouble(lengthTextField.getText().replace(",", "."));
    }

    public double getBuildingWidth() {
        try{
            widthTextField.setBackground(Color.WHITE);
            return Integer.parseInt(widthTextField.getText());
        } catch (NumberFormatException e){
            widthTextField.setBackground(Color.YELLOW);
            e.printStackTrace();
        }
        return Double.parseDouble(widthTextField.getText().replace(",", "."));
    }

    public double getBuildingHeightMin() {
        try{
            heightMinTextField.setBackground(Color.WHITE);
            return Integer.parseInt(heightMinTextField.getText());
        } catch (NumberFormatException e){
            heightMinTextField.setBackground(Color.YELLOW);
            e.printStackTrace();
        }
        return Double.parseDouble(heightMinTextField.getText().replace(",", "."));
    }

    public double getBuildingHeightMax() {
        if(heightMaxTextField.getText().length() == 0)
            return 0;
        return Double.parseDouble(heightMaxTextField.getText().replace(",", "."));
    }

    public String getPoultryHouseNumber() {
        return poultryHouseNumberTextField.getText();
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
        for(Integer i : baseInfo.getCageTiers())
            defaultComboBoxModel.addElement(i);

        return defaultComboBoxModel;
    }

    public void updateAirSpinner() {
        airSummerSpinner.setValue(getAirSummer());
        airWinterSpinner.setValue(getAirWinter());
    }

    public void setEnableCageTiredAndCageNumberComboBox() {
        for(Component c : cageTiredAndCageNumberInfoPanel.getComponents())
            c.setEnabled(true);
    }

    public void setDisablesCageTiredAndCageNumberComboBox() {
        for(Component c : cageTiredAndCageNumberInfoPanel.getComponents())
            c.setEnabled(false);
    }

    public void setAirWinterCurrent(double value) {
        if(value == 0)
            airWinterCurrentLabel.setText("");
        else
            airWinterCurrentLabel.setText(String.format("%.2f", value) + "м3/час");
    }

    public void setAirSummerCurrent(double value) {
        if(value == 0)
            airSummerCurrentLabel.setText("");
        else
            airSummerCurrentLabel.setText(String.format("%.2f", value) + "м3/час");
    }

    public void setAirTotalCurrent(double value) {
        if(value == 0)
            airTotalCurrentLabel.setText("");
        else
            airTotalCurrentLabel.setText(String.format("%.2f", value) + "м3/час");
    }

}
