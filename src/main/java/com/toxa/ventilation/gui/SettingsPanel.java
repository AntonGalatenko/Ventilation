package com.toxa.ventilation.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextArea fanRoofTextArea;
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
    private JButton button1;

    public SettingsPanel(){
        add(mainPanel);

        setVisible(true);
        pack();


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(getFan50TextArea());
            }
        });
    }

    public StringBuilder getFan50Names() {
        return new StringBuilder().append(fan50TextArea.getText());
    }

    public void setFan50TextArea(JTextArea fan50TextArea) {
        this.fan50TextArea = fan50TextArea;
    }

    public StringBuilder getFan36Names() {
        return new StringBuilder().append(fan36TextArea.getText());
    }

    public void setFan36TextArea(JTextArea fan36TextArea) {
        this.fan36TextArea = fan36TextArea;
    }

    public StringBuilder getFan26Names() {
        return new StringBuilder().append(fan26TextArea.getText());
    }

    public void setFan26TextArea(JTextArea fan26TextArea) {
        this.fan26TextArea = fan26TextArea;
    }

    public StringBuilder getAirInletOnWallNames() {
        return new StringBuilder().append(airInletOnWallTextArea.getText());
    }

    public void setAirInletOnWallTextArea(JTextArea airInletOnWallTextArea) {
        this.airInletOnWallTextArea = airInletOnWallTextArea;
    }

    public StringBuilder getAirInletOnRoofNames() {
        return new StringBuilder().append(airInletOnRoofTextArea.getText());
    }

    public void setAirInletOnRoofTextArea(JTextArea airInletOnRoofTextArea) {
        this.airInletOnRoofTextArea = airInletOnRoofTextArea;
    }

    public StringBuilder getAirInletForPadCoolNames() {
        return new StringBuilder().append(airInletForPadCoolTextArea.getText());
    }

    public void setAirInletForPadCoolTextArea(JTextArea airInletForPadCoolTextArea) {
        this.airInletForPadCoolTextArea = airInletForPadCoolTextArea;
    }

    public StringBuilder getShutterNames() {
        return new StringBuilder().append(shutterTextArea.getText());
    }

    public void setShutterTextArea(JTextArea shutterTextArea) {
        this.shutterTextArea = shutterTextArea;
    }

    public StringBuilder getHeaterNames() {
        return new StringBuilder().append(heaterTextArea.getText());
    }

    public void setHeaterTextArea(JTextArea heaterTextArea) {
        this.heaterTextArea = heaterTextArea;
    }

    public StringBuilder getFanCirculationNames() {
        return new StringBuilder().append(fanCirculationTextArea.getText());
    }

    public void setFanCirculationTextArea(JTextArea fanCirculationTextArea) {
        this.fanCirculationTextArea = fanCirculationTextArea;
    }

    public StringBuilder getAutomaticNames() {
        return new StringBuilder().append(automaticTextArea.getText());
    }

    public void setAutomaticTextArea(JTextArea automaticTextArea) {
        this.automaticTextArea = automaticTextArea;
    }

    public StringBuilder getServomotorNames() {
        return new StringBuilder().append(servomotorTextArea.getText());
    }

    public void setServomotorTextArea(JTextArea servomotorTextArea) {
        this.servomotorTextArea = servomotorTextArea;
    }

    public StringBuilder getEmergencyNames() {
        return new StringBuilder().append(emergencyTextArea.getText());
    }

    public void setEmergencyTextArea(JTextArea emergencyTextArea) {
        this.emergencyTextArea = emergencyTextArea;
    }

    public StringBuilder getFanRoofNames() {
        return new StringBuilder().append(fanRoofTextArea.getText());
    }

    public void setFanRoofTextArea(JTextArea fanRoofTextArea) {
        this.fanRoofTextArea = fanRoofTextArea;
    }
}
