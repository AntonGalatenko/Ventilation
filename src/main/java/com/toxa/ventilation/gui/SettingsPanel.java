package com.toxa.ventilation.gui;

import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.Data.DataOfEquipment;
import com.toxa.ventilation.Data.Storage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SettingsPanel extends JDialog{

//    private MyToolBar myToolBar;
    private MyMainPanel myMainPanel;
    private DataOfEquipment dataOfEquipment;
    private boolean fan50Change, fan36Change, fan26Change, fanRoofChange, airInletOfWallChange, airInletOfRoofChange,
            airInletOfPadCoolChange, shutterChange, humidityChange, heaterChange, fanCirculationChange, automaticChange;

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

    public SettingsPanel(final MyMainPanel myMainPanel){
        this.myMainPanel = myMainPanel;
        setLocationForThisFrame();

        setTitle("Настройки");
        add(mainPanel);



        setVisible(true);
        pack();

        setDefaultValue();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveActualValue();
                dispose();
                myMainPanel.getResultPanel().setModelsToComboBox();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        fan50TextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fan50Change = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fan50Change = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fan50Change = true;
            }
        });

        fan36TextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fan36Change = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fan36Change = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fan36Change = true;
            }
        });

        fan26TextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fan26Change = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fan26Change = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fan26Change = true;
            }
        });

        fanRoofTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fanRoofChange = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fanRoofChange = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fanRoofChange = true;
            }
        });

        airInletOnWallTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                airInletOfWallChange = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                airInletOfWallChange = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                airInletOfWallChange = true;
            }
        });

        airInletOnRoofTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                airInletOfRoofChange = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                airInletOfRoofChange = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                airInletOfRoofChange = true;
            }
        });

        airInletForPadCoolTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                airInletOfPadCoolChange = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                airInletOfPadCoolChange = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                airInletOfPadCoolChange = true;
            }
        });

        shutterTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                shutterChange = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                shutterChange = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                shutterChange = true;
            }
        });

        heaterTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                heaterChange = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                heaterChange = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                heaterChange = true;
            }
        });

        fanCirculationTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fanCirculationChange = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fanCirculationChange = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fanCirculationChange = true;
            }
        });

        automaticTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                automaticChange = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                automaticChange = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                automaticChange = true;
            }
        });

    }

    public void setDefaultValue(){
        dataOfEquipment = new ActualValues().loadActualValue();

        setFan50Names(parseEquipmentValue(dataOfEquipment.getFan50()));
        setFan36Names(parseEquipmentValue(dataOfEquipment.getFan36()));
        setFan26Names(parseEquipmentValue(dataOfEquipment.getFan26()));
        setFanRoofNames(parseEquipmentValue(dataOfEquipment.getFanRoof()));
        setAirInletOnWallNames(parseEquipmentValue(dataOfEquipment.getAirInletOfWall()));
        setAirInletOnRoofNames(parseEquipmentValue(dataOfEquipment.getAirInletOfRoof()));
        setAirInletOfPadCoolNames(parseEquipmentValue(dataOfEquipment.getAirInletOfPadCool()));
        setShutterNames(parseEquipmentValue(dataOfEquipment.getShutter()));
        setHeaterNames(parseEquipmentValue(dataOfEquipment.getHeater()));
        setFanCirculationNames(parseEquipmentValue(dataOfEquipment.getFanCirculation()));
        setAutomaticNames(parseEquipmentValue(dataOfEquipment.getAutomatic()));

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
        if(fan50Change)
            dataOfEquipment.updateFan50(getFan50Names());
        if(fan36Change)
            dataOfEquipment.updateFan36(getFan36Names());
        if(fan26Change)
            dataOfEquipment.updateFan26(getFan26Names());
        if(fanRoofChange)
            dataOfEquipment.updateFanRoof(getFanRoofNames());
        if(airInletOfWallChange)
            dataOfEquipment.updateAirInletOfWall(getAirInletOnWallNames());
        if(airInletOfRoofChange)
            dataOfEquipment.updateAirInletOfRoof(getAirInletOnRoofNames());
        if(airInletOfPadCoolChange)
            dataOfEquipment.updateAirInletOfPadCool(getAirInletForPadCoolNames());
        if(shutterChange)
            dataOfEquipment.updateShutter(getShutterNames());
        if(heaterChange)
            dataOfEquipment.updateHeater(getHeaterNames());
        if(fanCirculationChange)
            dataOfEquipment.updateFanCirculation(getFanCirculationNames());
        if(automaticChange)
            dataOfEquipment.updateAutomatic(getAutomaticNames());

    }

    public StringBuilder parseEquipmentValue(HashMap<String, Storage> map){
        StringBuilder result = new StringBuilder();

        for(String key : map.keySet())
            result.append(key + " : " + map.get(key).getCapacity() + " : " + map.get(key).getDescription() + "\n");

        return result;
    }

    public void setLocationForThisFrame(){
        Point point = myMainPanel.getLocation();
        setLocation((int)point.getX() + 10, (int)point.getY() + 30);
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
        fan36TextArea.setText(text.toString());
    }

    public StringBuilder getFan26Names() {
        return new StringBuilder().append(fan26TextArea.getText());
    }

    public void setFan26Names(StringBuilder text) {
        fan26TextArea.setText(text.toString());
    }

    public StringBuilder getAirInletOnWallNames() {
        return new StringBuilder().append(airInletOnWallTextArea.getText());
    }

    public void setAirInletOnWallNames(StringBuilder text) {
        airInletOnWallTextArea.setText(text.toString());
    }

    public StringBuilder getAirInletOnRoofNames() {
        return new StringBuilder().append(airInletOnRoofTextArea.getText());
    }

    public void setAirInletOnRoofNames(StringBuilder text) {
        airInletOnRoofTextArea.setText(text.toString());
    }

    public StringBuilder getAirInletForPadCoolNames() {
        return new StringBuilder().append(airInletForPadCoolTextArea.getText());
    }

    public void setAirInletOfPadCoolNames(StringBuilder text) {
        airInletForPadCoolTextArea.setText(text.toString());
    }

    public StringBuilder getShutterNames() {
        return new StringBuilder().append(shutterTextArea.getText());
    }

    public void setShutterNames(StringBuilder text) {
        shutterTextArea.setText(text.toString());
    }

    public StringBuilder getHeaterNames() {
        return new StringBuilder().append(heaterTextArea.getText());
    }

    public void setHeaterNames(StringBuilder text) {
        heaterTextArea.setText(text.toString());
    }

    public StringBuilder getFanCirculationNames() {
        return new StringBuilder().append(fanCirculationTextArea.getText());
    }

    public void setFanCirculationNames(StringBuilder text) {
        fanCirculationTextArea.setText(text.toString());
    }

    public StringBuilder getAutomaticNames() {
        return new StringBuilder().append(automaticTextArea.getText());
    }

    public void setAutomaticNames(StringBuilder text) {
        automaticTextArea.setText(text.toString());
    }

    public StringBuilder getServomotorNames() {
        return new StringBuilder().append(servomotorTextArea.getText());
    }

    public void setServomotorNames(StringBuilder text) {
        servomotorTextArea.setText(text.toString());
    }

    public StringBuilder getEmergencyNames() {
        return new StringBuilder().append(emergencyTextArea.getText());
    }

    public void setEmergencyNames(StringBuilder text) {
        emergencyTextArea.setText(text.toString());
    }

    public StringBuilder getFanRoofNames() {
        return new StringBuilder().append(fanRoofTextArea.getText());
    }

    public void setFanRoofNames(StringBuilder text) {
        fanRoofTextArea.setText(text.toString());
    }
}
