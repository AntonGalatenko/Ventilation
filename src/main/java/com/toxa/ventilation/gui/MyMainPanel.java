package com.toxa.ventilation.gui;

import javax.swing.*;

public class MyMainPanel extends JFrame{

    private JPanel mainPanel;
    private TaskPanel taskPanel;
    private ResultsPanel resultPanel;

    public MyMainPanel(){

        taskPanel = new TaskPanel();
        resultPanel = new ResultsPanel();

        taskPanel.setResultsPanel(resultPanel);
        resultPanel.setMyMainPanel(this);

//        setCountInResultsPanel();

        add(mainPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

//    public void setCountInResultsPanel(){
//        taskPanel.setCountInResultsPanel(resultPanel);
//    }

    public void update(){
//        resultPanel.revalidate();
//        resultPanel.repaint();
//        repaint();
//        revalidate();
//        mainPanel.revalidate();
//        remove(resultsPanel);
//        add(resultsPanel);

//        getContentPane().remove(resultPanel);
//        mainPanel.removeAll();
//        mainPanel.add(new TaskPanel());
//        mainPanel.add(new ResultsPanel());
//        getContentPane().removeAll();
//        getContentPane().add(mainPanel);


//        System.out.println("mainPanel.getComponent(0);" + mainPanel.getComponent(0));
//        System.out.println("mainPanel.getComponent(1);" + mainPanel.getComponent(1));


//        System.out.println("mainPanel.getComponent(2);" + mainPanel.getComponent(2));
//        System.out.println("mainPanel.getComponent(3);" + mainPanel.getComponent(3));
//        getContentPane().add(resultPanel);
//        remove(mainPanel);
//        add(mainPanel);

//        mainPanel.revalidate();
//        mainPanel.repaint();
        revalidate();
        repaint();

        System.out.println("DDDDD " + resultPanel.getFan50spinner());
    }



}
