package com.toxa.ventilation.gui;

import javax.swing.*;

public class MyMainPanel extends JFrame{

    private JPanel mainPanel;
    private TaskPanel taskPanel;
    private ResultsPanel resultPanel;

    public MyMainPanel(){

        taskPanel = new TaskPanel();
        resultPanel = new ResultsPanel();

        setCountInResultsPanel();

        add(mainPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setCountInResultsPanel(){
        taskPanel.setCountInResultsPanel(resultPanel);
    }

}
