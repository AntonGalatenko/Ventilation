package com.toxa.ventilation.gui;

import javax.swing.*;

public class SettingsPanel extends JFrame{
    private JTabbedPane tabbedPane;
    private JPanel mainPanel;
    private JPanel generalPanel;
    private JLabel themeGeneralSettingsLabel;
    private JComboBox pathGeneralSettingsComboBox;
    private JPanel exhaustAirPanel;
    private JLabel fan50Label;
    private JLabel fan36Label;
    private JLabel fan26Label;
    private JScrollPane fanRoofScrollPane;
    private JLabel fanRoofLabel;
    private JScrollPane fan26ScrollPane;
    private JScrollPane fan36ScrollPane;
    private JScrollPane fan50ScrollPane;
    private JLabel pathGeneralSettingsLabel;
    private JTextArea fan26TextArea;
    private JTextArea FanRoofTextArea;
    private JTextArea fan36TextArea;
    private JTextArea fan50TextArea;
    private JPanel intelAirPanel;
    private JTextArea airInletOnWallTextArea;
    private JScrollPane airInletOnWallScrollPane;
    private JLabel airInletOnWallLabel;
    private JLabel airInletOnRoofLabel;
    private JTextArea airInletOnRoofTextArea;
    private JScrollPane airInletOnRoofScrollPane;
    private JLabel airInletForPadCoolLabel;
    private JTextArea airInletForPadCoolTextArea;
    private JScrollPane airInletForPadCoolScrollPane;
    private JLabel shutterLabel;
    private JTextArea shutterTextArea;
    private JScrollPane shutterScrollPane;
    private JPanel heaterPanel;
    private JLabel heaterLabel;
    private JScrollPane heaterScrollPane;
    private JTextArea heaterTextArea;
    private JLabel fanCirculationLabel;
    private JScrollPane fanCirculationScrollPane;
    private JTextArea fanCirculationTextArea;
    private JPanel automaticPanel;
    private JLabel automaticLabel;
    private JScrollPane automaticScrollPane;
    private JTextArea automaticTextArea;
    private JLabel servomotorLabel;
    private JScrollPane servomotorScrollPane;
    private JTextArea servomotorTextArea;
    private JLabel emergencyLabel;
    private JScrollPane emergencyScrollPane;
    private JTextArea emergencyTextArea;

    public SettingsPanel(){
        add(mainPanel);

        setVisible(true);
        pack();


    }
}
