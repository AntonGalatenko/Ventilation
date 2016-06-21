package com.toxa.ventilation.gui;

import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.Count;

import javax.swing.*;
import java.awt.*;

public class MyMainPanel extends JFrame{
    private JPanel mainPanel;
    private TaskPanel taskPanel;
    private ResultsPanel resultPanel;
    private MyToolBar toolBar;
    private final double versionNumber = 1.21;

    public MyMainPanel(){
        setTitle("Вентиляция ver" + versionNumber);

        BaseInfo baseInfo = BaseInfo.getInstance();
        Count count = new Count();
        count.setBaseInfo(baseInfo);
        baseInfo.setCount(count);


        toolBar = new MyToolBar(this);

        taskPanel = new TaskPanel();
        taskPanel.setCount(count);

        resultPanel = new ResultsPanel(count);
        count.setResultsPanel(resultPanel);
        baseInfo.setResultsPanel(resultPanel);
        resultPanel.setTunnel();

        taskPanel.setResultsPanel(resultPanel);

        mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(toolBar, BorderLayout.NORTH);
        mainPanel.add(taskPanel, BorderLayout.WEST);
        mainPanel.add(resultPanel, BorderLayout.EAST);

        add(mainPanel);

        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public void update(ResultsPanel resultsPanel){
//        mainPanel.remove(resultPanel);
//        mainPanel.add(resultsPanel);
//        repaint();
//    }

    public ResultsPanel getResultPanel (){
        return resultPanel;
    }

//    public void setVisibleAndInvisibleSettingsPanel(){
//        if(settingsPanel.isVisible())
//            settingsPanel.setVisible(false);
//        else
//            settingsPanel.setVisible(true);
//        System.out.println("123");
//        repaint();
//        revalidate();
//    }
}
