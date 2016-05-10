package com.toxa.ventilation.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyToolBar extends JToolBar{
    private MyMainPanel myMainPanel;
//    private SettingsPanel settingsPanel;

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
                createSettingsPanel();
//                settingsPanel.setMyMainPanel(myMainPanel);
            }
        });
    }

    public void createSettingsPanel(){
        /*SettingsPanel settingsPanel = */new SettingsPanel(this);
    }

//    public Point getLocationOfMyMainPanel(){
//        return myMainPanel.getLocation();
//    }


    public MyMainPanel getMyMainPanel() {
        return myMainPanel;
    }
}
