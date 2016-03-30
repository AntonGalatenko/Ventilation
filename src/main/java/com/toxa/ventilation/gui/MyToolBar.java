package com.toxa.ventilation.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyToolBar extends JToolBar{
    private MyMainPanel2 myMainPanel2;

    private JPanel mainPanel;
    private JButton fileButton;
    private JButton settingsButton;
    private JButton aboutProgramButton;

    public MyToolBar(final MyMainPanel2 myMainPanel2){
        this.myMainPanel2 = myMainPanel2;

        add(mainPanel);

        setFloatable(false);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsPanel();
            }
        });
    }
}
