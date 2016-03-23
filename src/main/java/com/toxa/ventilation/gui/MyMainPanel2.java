package com.toxa.ventilation.gui;

import javax.swing.*;

public class MyMainPanel2 extends JFrame{
    private TaskPanel taskPanel;
    private ResultsPanel resultPanel;
    private JPanel mainPanel;

    public MyMainPanel2(){

        taskPanel = new TaskPanel();
        resultPanel = new ResultsPanel();

        taskPanel.setResultsPanel(resultPanel);
        resultPanel.setMyMainPanel(this);

        mainPanel = new JPanel();

        mainPanel.add(taskPanel);
        mainPanel.add(resultPanel);

        add(mainPanel);

        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update(ResultsPanel resultsPanel){
        mainPanel.remove(resultPanel);
        mainPanel.add(resultsPanel);
        repaint();
    }
}
