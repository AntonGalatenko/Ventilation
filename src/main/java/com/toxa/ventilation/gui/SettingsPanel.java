package com.toxa.ventilation.gui;

import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.Data.DataOfEquipment;
import com.toxa.ventilation.Data.Storage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SettingsPanel extends JFrame{

    private DataOfEquipment dataOfEquipment;
    private boolean fan50;

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
    private JButton saveButton;
    private JButton cancelButton;

    public SettingsPanel(){
        add(mainPanel);

        dataOfEquipment = new ActualValues().loadActualValue();

        setVisible(true);
        pack();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveActualValue();
                dispose();
            }
        });

        setDefaultValue();
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        fan50TextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fan50 = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fan50 = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fan50 = true;
            }
        });


    }

    public void setDefaultValue(){
        setFan50Names(parseEquipmentValue(dataOfEquipment.getFan50()));
    }

    public void saveActualValue(){
        updateValues();

        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("save_ventilation");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(dataOfEquipment);
            oos.flush();
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateValues(){
        if(fan50)
            dataOfEquipment.updateFan50(getFan50Names());

    }

    public StringBuilder parseEquipmentValue(HashMap<String, Storage> map){
        StringBuilder result = new StringBuilder();

        for(String key : map.keySet())
            result.append(key + " : " + map.get(key).getCapacity() + " : " + map.get(key).getDescription() + "\n");

        return result;
    }

    public StringBuilder getFan50Names() {
        return new StringBuilder().append(fan50TextArea.getText());
    }

    public void setFan50Names(StringBuilder text) {
        fan50TextArea.setText(text.toString());
    }

    public StringBuilder getFan36Names() {
        return new StringBuilder().append(fan36TextArea.getText());
    }

    public void setFan36Names(StringBuilder text) {
        this.fan36TextArea = fan36TextArea;
    }

    public StringBuilder getFan26Names() {
        return new StringBuilder().append(fan26TextArea.getText());
    }

    public void setFan26Names(StringBuilder text) {
        this.fan26TextArea = fan26TextArea;
    }

    public StringBuilder getAirInletOnWallNames() {
        return new StringBuilder().append(airInletOnWallTextArea.getText());
    }

    public void setAirInletOnWallNames(StringBuilder text) {
        this.airInletOnWallTextArea = airInletOnWallTextArea;
    }

    public StringBuilder getAirInletOnRoofNames() {
        return new StringBuilder().append(airInletOnRoofTextArea.getText());
    }

    public void setAirInletOnRoofNames(StringBuilder text) {
        this.airInletOnRoofTextArea = airInletOnRoofTextArea;
    }

    public StringBuilder getAirInletForPadCoolNames() {
        return new StringBuilder().append(airInletForPadCoolTextArea.getText());
    }

    public void setAirInletForPadCoolNames(StringBuilder text) {
        this.airInletForPadCoolTextArea = airInletForPadCoolTextArea;
    }

    public StringBuilder getShutterNames() {
        return new StringBuilder().append(shutterTextArea.getText());
    }

    public void setShutterNames(StringBuilder text) {
        this.shutterTextArea = shutterTextArea;
    }

    public StringBuilder getHeaterNames() {
        return new StringBuilder().append(heaterTextArea.getText());
    }

    public void setHeaterNames(StringBuilder text) {
        this.heaterTextArea = heaterTextArea;
    }

    public StringBuilder getFanCirculationNames() {
        return new StringBuilder().append(fanCirculationTextArea.getText());
    }

    public void setFanCirculationNames(StringBuilder text) {
        this.fanCirculationTextArea = fanCirculationTextArea;
    }

    public StringBuilder getAutomaticNames() {
        return new StringBuilder().append(automaticTextArea.getText());
    }

    public void setAutomaticNames(StringBuilder text) {
        this.automaticTextArea = automaticTextArea;
    }

    public StringBuilder getServomotorNames() {
        return new StringBuilder().append(servomotorTextArea.getText());
    }

    public void setServomotorNames(StringBuilder text) {
        this.servomotorTextArea = servomotorTextArea;
    }

    public StringBuilder getEmergencyNames() {
        return new StringBuilder().append(emergencyTextArea.getText());
    }

    public void setEmergencyNames(StringBuilder text) {
        this.emergencyTextArea = emergencyTextArea;
    }

    public StringBuilder getFanRoofNames() {
        return new StringBuilder().append(fanRoofTextArea.getText());
    }

    public void setFanRoofNames(StringBuilder text) {
        this.fanRoofTextArea = fanRoofTextArea;
    }
}
