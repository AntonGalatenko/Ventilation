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
    private JTextField CompanyNameTextField;
    private JLabel CompanyNameLabel;
    private JLabel CountryLabel;
    private JTextField CountryTextField;
    private JPanel GeneralInfoPanel;
    private JLabel WidthLabel;
    private JTextField WidthTextField;
    private JTextField HeightMinTextField;
    private JLabel HeightMinLabel;
    private JTextField HeightMaxTextField;
    private JLabel HeightMaxLabel;
    private JLabel HeadsNumberLabel;
    private JTextField HeadsNumberTextField;
    private JPanel CageInfoPanel;
    private JLabel CageNameLabel;
    private JComboBox CageNameComboBox;
    private JComboBox CageTiersComboBox1;
    private JComboBox CageNumberComboBox1;
    private JLabel CageTiersLabel1;
    private JLabel CageNumberLabel1;
    private JLabel CageTiersLabel2;
    private JComboBox CageTiersComboBox2;
    private JLabel CageNumberLabel2;
    private JComboBox CageNumberComboBox2;
    private JLabel VentilationTypeLabel;
    private JComboBox VentilationTypeComboBox;
    private JPanel ClimatInfoPanel;
    private JLabel AirQuantityLabel;
    private JLabel AirSummerLabel;
    private JSpinner AirSummerSpinner;
    private JLabel AirWinterLabel;
    private JSpinner AirWinterSpinner;

    public TaskPanel(){
        add(mainPanel);

        CageNumberComboBox1.setSelectedIndex(1);

        CageNameComboBox.setSelectedIndex(0);
        CageNameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1)
                    cageInfo.setCageName(e.getItem().toString());
            }
        });

        VentilationTypeComboBox.setSelectedIndex(0);
        VentilationTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("click");
                updateCageTiers();
            }
        });

        updateCageTiers();

    }

    public String getCageName(){
        return CageNameComboBox.getSelectedItem().toString();
    }

    public String getVentilationType(){
        return VentilationTypeComboBox.getSelectedItem().toString();
    }

    private DefaultComboBoxModel getCageTiersForComboBoxModel(){
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        System.out.println(cageInfo.getCageTiers());
        for(Integer i : cageInfo.getCageTiers())
            defaultComboBoxModel.addElement(i);

        return defaultComboBoxModel;
    }

    public void updateCageTiers(){
        CageTiersComboBox1.setModel(getCageTiersForComboBoxModel());
        CageTiersComboBox2.setModel(getCageTiersForComboBoxModel());
    }


}
