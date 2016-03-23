/*
package com.toxa.ventilation.gui;

import javax.swing.*;

public class MyMainPanel extends JFrame{

    private JPanel mainPanel;
    private TaskPanel taskPanel;
    private CardLayoutPanel cardLayoutPanel1;
//    private CardLayoutPanel resultCardLayoutPane;
    private ResultsPanel resultPanel;

    public MyMainPanel(){

        cardLayoutPanel1 = new CardLayoutPanel();
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

    public void update(ResultsPanel resultsPanel){
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

//        cardLayoutPanel1.addPanel(resultsPanel);
        cardLayoutPanel1.addTest();
//        revalidate();
        cardLayoutPanel1.revalidate();
        cardLayoutPanel1.repaint();
        mainPanel.revalidate();
        mainPanel.repaint();
        revalidate();
        repaint();

        System.out.println("DDDDD " + resultPanel.getFan50spinner());
    }



}
*/
