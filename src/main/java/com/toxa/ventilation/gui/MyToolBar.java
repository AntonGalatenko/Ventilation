package com.toxa.ventilation.gui;

import javax.swing.*;

public class MyToolBar extends JToolBar{
    private JPanel mainPanel;
    private JButton fileButton;
    private JButton settingsButton;
    private JButton aboutProgramButton;

    public MyToolBar(){
        add(mainPanel);
        setFloatable(false);
    }
}
