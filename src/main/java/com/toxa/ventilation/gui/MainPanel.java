package com.toxa.ventilation.gui;

import javax.swing.*;

public class MainPanel extends JFrame{

    private TaskPanel taskPanel;

    public MainPanel(){

        taskPanel = new TaskPanel();

        setContentPane(taskPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
