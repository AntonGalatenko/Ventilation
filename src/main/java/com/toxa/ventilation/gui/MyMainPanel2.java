package com.toxa.ventilation.gui;

import javax.swing.*;
import java.awt.*;

public class MyMainPanel2 extends JFrame{
    private TaskPanel taskPanel;
    private ResultsPanel resultPanel;
    private JPanel mainPanel;
    private MyToolBar toolBar;

    public MyMainPanel2(){

        toolBar = new MyToolBar();
        taskPanel = new TaskPanel();
        resultPanel = new ResultsPanel();

        taskPanel.setResultsPanel(resultPanel);
        resultPanel.setMyMainPanel(this);

        mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(toolBar, BorderLayout.NORTH);
        mainPanel.add(taskPanel, BorderLayout.WEST);
        mainPanel.add(resultPanel, BorderLayout.EAST);

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
