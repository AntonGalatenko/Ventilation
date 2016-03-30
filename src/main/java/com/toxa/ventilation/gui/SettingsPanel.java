package com.toxa.ventilation.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JFrame{
    private JPanel mainPanel;
    private JPanel selectionPanel;
    private JPanel cardLayoutPanel;
    private JPanel generalSettingsPanel;
    private JPanel equipmentSettingsPanel;
    private JButton generalSettingsButton;
    private JButton equipmentSettingsButton;
    private JLabel themeGeneralSettingsLabel;
    private JComboBox pathGeneralSettingsComboBox;
    private JLabel pathGeneralSettingsLabel;
    private JComboBox themeGeneralSettingsComboBox;
    private JLabel fan50EquipmentSettingsLabel;
    private JTextPane fan50EquipmentSettingsTextPane;
    private JLabel fan36EquipmentSettingsLabel;
    private JTextPane fan36EquipmentSettingsTextPane;

    public SettingsPanel(){
        add(mainPanel);

        final CardLayout cardLayout = new CardLayout();
        cardLayoutPanel.setLayout(cardLayout);

        cardLayoutPanel.add(generalSettingsPanel, "card1");
        cardLayoutPanel.add(equipmentSettingsPanel, "card2");

        setVisible(true);
        pack();

        generalSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardLayoutPanel, "card1");
            }
        });


        equipmentSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardLayoutPanel, "card2");
            }
        });
    }
}
