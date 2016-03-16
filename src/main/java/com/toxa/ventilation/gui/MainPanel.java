package com.toxa.ventilation.gui;

import javax.swing.*;

public class MainPanel extends JFrame{

    TaskPanel vp;

    public MainPanel(){

        vp = new TaskPanel();

        setContentPane(vp);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
