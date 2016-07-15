package com.toxa.ventilation.gui;

import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.Count;
import com.toxa.ventilation.ExcelApachePOI;
import com.toxa.ventilation.model.entity.Factory;
import com.toxa.ventilation.model.repository.Repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

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

//    @Autowired
    private Repository repository = new Repository();

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

//                checkFactoryToSimilar();
            }
        });

        saveOpenExcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseInfo.getJson();
                excelApachePOI = new ExcelApachePOI();
                excelApachePOI.setOpenExcel(true);
                excelApachePOI.saveThis();

//                addFactoryToDataBase();
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

        companyNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                System.out.println("name " + repository.getNameEquals(companyNameTextField.getText()));
            }
        });

        setDefaultValues();

    }

    private void addFactoryToDataBase(){
        repository.addItem(getFactory());
    }

    private void checkFactoryToSimilar(){
        List<Factory> result = repository.getSimilar(getFactory());
        System.out.println(result.size());
    }

    private Factory getFactory(){
        return new Factory(baseInfo.getCompanyName(), baseInfo.getCountry(), baseInfo.getCageName(), baseInfo.getHeadsNumber(),
                baseInfo.getBuildingLength(), baseInfo.getBuildingWidth(), baseInfo.getBuildingHeightMin(), baseInfo.getBuildingHeightMax());
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

    private void updateCageTiersComboBox() {
        cageTiersComboBox1.setModel(getCageTiersForComboBoxModel());
        cageTiersComboBox2.setModel(getCageTiersForComboBoxModel());
    }

    public void setCageTierCurrentValue(){
        int cageTier1 = 0;
        int cageTier2 = 0;
        if(cageTiersComboBox1.getSelectedItem() != null)
            cageTier1 = getCageTiers1();
        if(cageTiersComboBox2.getSelectedItem() != null)
            cageTier2 = getCageTiers2();

        updateCageTiersComboBox();

        cageTiersComboBox1.setSelectedItem(cageTier1);
        cageTiersComboBox2.setSelectedItem(cageTier2);
    }

    public String getCompanyName() {
        if(companyNameTextField.getText().length() == 0)
            companyNameTextField.setText("Название фабирики");

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

        checkNumberField(headsNumberTextField);
        return Integer.parseInt(headsNumberTextField.getText());
    }

    public double getBuildingLength() {
        if(lengthTextField.getText().length() == 0)
            lengthTextField.setText("96");

        checkNumberField(lengthTextField);
        return Double.parseDouble(lengthTextField.getText().replace(",", "."));
    }

    public double getBuildingWidth() {
        if(widthTextField.getText().length() == 0)
            widthTextField.setText("18");

        checkNumberField(widthTextField);
        return Double.parseDouble(widthTextField.getText().replace(",", "."));
    }

    public double getBuildingHeightMin() {
        if(heightMinTextField.getText().length() == 0)
            heightMinTextField.setText("4");

        checkNumberField(heightMinTextField);
        return Double.parseDouble(heightMinTextField.getText().replace(",", "."));
    }

    private void checkNumberField(JTextField field){
        try{
            field.setBackground(Color.WHITE);
            Double.parseDouble(field.getText().replace(",", "."));
        } catch (NumberFormatException e){
            field.setBackground(Color.YELLOW);
            e.printStackTrace();
        }
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
