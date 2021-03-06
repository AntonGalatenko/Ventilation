package com.toxa.ventilation.gui;

import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.Count;
import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.Data.DataOfEquipment;
import com.toxa.ventilation.Enums.VentilationType;
import com.toxa.ventilation.ExcelApachePOI;
import com.toxa.ventilation.MyTableModel;
import com.toxa.ventilation.model.entity.Factory;
import com.toxa.ventilation.model.repository.Repository;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.List;

public class TaskPanel extends JPanel{

    private BaseInfo baseInfo;
    private Count count;
    private ExcelApachePOI excelApachePOI;
    private MyMainPanel myMainPanel;
    DataOfEquipment dataOfEquipment;

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
    private JLabel dataBaseStatusLabel;
    private JSpinner outsideSummerTempSpinner;
    private JSpinner outsideWinterTempSpinner;
    private FactoryInfo factoryInfoNames;
    private FactoryInfo factoryInfoSimilar;
    FocusListener focusListenerSelectAll;
    String oldNameForModel;

    private Repository repository = new Repository();

    public TaskPanel(){
        add(mainPanel);

        baseInfo = BaseInfo.getInstance();
        baseInfo.setTaskPanel(this);
        dataOfEquipment = new ActualValues().loadActualValue();

        focusListenerSelectAll = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ((JTextField)e.getSource()).selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        };

        cageNameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1)
                    baseInfo.setInfo();
            }
        });

        cageNameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                checkFactoryToSimilar();
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
                if(isAllDataOk()){
                    count.startCount();
                    baseInfo.setResultPanelVisible(true);
                    myMainPanel.pack();
                }
            }
        });

        saveOpenExcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseInfo.getJson();
                excelApachePOI = new ExcelApachePOI();
                excelApachePOI.setOpenExcel(true);
                excelApachePOI.saveThis();

                addFactoryToDataBase();
            }
        });

//        saveExcelButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new AddAndCheckDataBase();
//            }
//        });

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
                checkFactoryNames();
            }
        });

        companyNameLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                List<Factory> list = repository.getNameEquals(companyNameTextField.getText());

                if(list != null && list.size() > 0)
                    new FactoryInfo(new MyTableModel(list));
            }
        });

        saveExcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        airSummerSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dataOfEquipment.updateAirSummer((double) airSummerSpinner.getValue(), getCageName(), getVentilationType());
            }
        });

        airWinterSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dataOfEquipment.updateAirWinter((double) airWinterSpinner.getValue(), getCageName(), getVentilationType());
            }
        });

        addTextFieldsToFocusListenerForSelectAllText();
        setDefaultValues();
    }

    private boolean isAllDataOk(){
        try{
            if(getBuildingLength() < 0)
                return false;

            if(getBuildingWidth() < 0)
                return false;

            if(getBuildingHeightMin() < 0)
                return false;

            if(getBuildingHeightMax() < 0)
                return false;

            if(getHeadsNumber() < 0)
                return false;

            if(getBuildingHeightMax() <= getBuildingHeightMin() && getBuildingHeightMax() != 0){
                heightMaxTextField.setBackground(Color.YELLOW);
                return false;
            }

        } catch(NumberFormatException e){
            System.err.println(e.toString());
        }

        return true;
    }

    private void addFactoryToDataBase(){
        repository.addItem(getFactory());
    }

    private void checkFactoryNames(){
        List<Factory> list = repository.getNameEquals(companyNameTextField.getText());

        if(list != null && list.size() > 0){
            if(! getCompanyName().equals(oldNameForModel)){
                new FactoryInfo(new MyTableModel(list));
                oldNameForModel = getCompanyName();
            }
        }
    }

    private void checkFactoryToSimilar(){
        List<Factory> list = repository.getSimilar(baseInfo.getHeadsNumber(), baseInfo.getCageName());

        if(list.size() > 0){
            MyTableModel model = new MyTableModel(list);
//            if(factoryInfoSimilar == null){
                factoryInfoSimilar = new FactoryInfo(model);
                factoryInfoSimilar.doSort();
//            }

        }
    }

    private Factory getFactory(){
        return new Factory(Calendar.getInstance().get(Calendar.YEAR), baseInfo.getCompanyName(), baseInfo.getCountry(), baseInfo.getCageName(), baseInfo.getHeadsNumber(),
                baseInfo.getBuildingLength(), baseInfo.getBuildingWidth(), baseInfo.getBuildingHeightMin(), baseInfo.getBuildingHeightMax(),
                baseInfo.getFilePathName());
    }

    public void setCount(Count count){
        this.count = count;
    }

    public void setMyMainPanel(MyMainPanel myMainPanel){
        this.myMainPanel = myMainPanel;
    }

    private void addTextFieldsToFocusListenerForSelectAllText(){
        companyNameTextField.addFocusListener(focusListenerSelectAll);
        countryTextField.addFocusListener(focusListenerSelectAll);
        poultryHouseNumberTextField.addFocusListener(focusListenerSelectAll);
        headsNumberTextField.addFocusListener(focusListenerSelectAll);
        lengthTextField.addFocusListener(focusListenerSelectAll);
        widthTextField.addFocusListener(focusListenerSelectAll);
        heightMinTextField.addFocusListener(focusListenerSelectAll);
        heightMaxTextField.addFocusListener(focusListenerSelectAll);
    }

    public void setDefaultValues() {
        airSummerSpinner.setModel(new SpinnerNumberModel(new Double(new ActualValues().loadActualValue().getAirSummerTBK(VentilationType.TUNNEL)), new Double(0), null, new Double(0.2)));
        airWinterSpinner.setModel(new SpinnerNumberModel(new Double(new ActualValues().loadActualValue().getAirWinterTBK(VentilationType.TUNNEL)), new Double(0), null, new Double(0.2)));
        outsideWinterTempSpinner.setModel(new SpinnerNumberModel(new Integer(-15), new Integer(-40), null, new Integer(1)));
        cageNumberComboBox1.setSelectedIndex(1);

        updateCageTiersComboBox();

        setBaseDataStatus(baseInfo.getDataBaseStatusText());
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
//        if(companyNameTextField.getText().length() == 0)
//            companyNameTextField.setText("Название фабирики");

        return companyNameTextField.getText();
    }

    public String getCountry(){
//        if(countryTextField.getText().length() == 0)
//            countryTextField.setText("Страна");

        return countryTextField.getText();
    }

    public int getHeadsNumber() {
//        if(headsNumberTextField.getText().length() == 0)
//            headsNumberTextField.setText("96360");

        return checkNumberField(headsNumberTextField).intValue();
    }

    public double getBuildingLength() {
//        if(lengthTextField.getText().length() == 0)
//            lengthTextField.setText("96");

        return checkNumberField(lengthTextField);
    }

    public double getBuildingWidth() {
//        if(widthTextField.getText().length() == 0)
//            widthTextField.setText("18");

        return checkNumberField(widthTextField);
    }

    public double getBuildingHeightMin() {
//        if(heightMinTextField.getText().length() == 0)
//            heightMinTextField.setText("4");

        return checkNumberField(heightMinTextField);
    }

    public double getBuildingHeightMax() {
        if(heightMaxTextField.getText().length() == 0){
            heightMaxTextField.setBackground(Color.WHITE);
            return 0;
        }

        return checkNumberField(heightMaxTextField);
    }

    private Double checkNumberField(JTextField field){
        try{
            field.setBackground(Color.WHITE);
            return Double.parseDouble(field.getText().replace(",", "."));
        } catch (NumberFormatException e){
            field.setBackground(Color.YELLOW);
            System.err.println(e.toString());
        }

        return new Double(-1);
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

    public int getOutsideSummerTemp() {
        return (int) outsideSummerTempSpinner.getValue();
    }

    public int getOutsideWinterTemp() {
        return (int) outsideWinterTempSpinner.getValue();
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

    public void setBaseDataStatus(String text){
        if(text == null)
            dataBaseStatusLabel.setVisible(false);

        dataBaseStatusLabel.setText(text);
        dataBaseStatusLabel.setForeground(Color.RED);
    }

    public DataOfEquipment getDataOfEquipment() {
        return dataOfEquipment;
    }
}
