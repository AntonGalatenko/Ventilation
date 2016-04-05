package com.toxa.ventilation.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyToolBar extends JToolBar{
    private MyMainPanel myMainPanel;
    private SettingsPanel settingsPanel;

    private JPanel mainPanel;
    private JButton fileButton;
    private JButton settingsButton;
    private JButton aboutProgramButton;

    public MyToolBar(final MyMainPanel myMainPanel){
        this.myMainPanel = myMainPanel;

        add(mainPanel);

        setFloatable(false);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsPanel = new SettingsPanel(myMainPanel);
//                settingsPanel.setMyMainPanel(myMainPanel);
            }
        });
    }

}
