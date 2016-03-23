package com.toxa.ventilation.gui;

import com.toxa.ventilation.Count;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ResultsPanel extends JPanel{
    private Count count;
    private MyMainPanel2 myMainPanel;

    private JPanel mainPanel;
    private JPanel bigFanPanel;
    private JRadioButton fan50RadioButton;
    public JSpinner fan50Spinner;
    private JRadioButton fan36RadioButton;
    private JSpinner fan36spinner;

    public ResultsPanel(/*Count count*/){
//        this.count = count;
//        this.myMainPanel = myMainPanel;
        count = Count.getInstance();
        count.setResultsPanel(this);

        add(mainPanel);

        fan50Spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("fan50Spinner " + fan50Spinner.getValue());
            }
        });
    }

    public void setMyMainPanel(MyMainPanel2 myMainPanel) {
        this.myMainPanel = myMainPanel;
    }

    //    public void setCount(Count count) {
//        this.count = count;
//        System.out.println(count + " " + this.count);
//        this.count.setResultsPanel(this);
//    }

    public void updateResults(){
        fan50Spinner.setValue(/*new Integer(count.getFan50Count())*/12);
        System.out.println("!! " + fan50Spinner.getValue());
//        mainPanel.revalidate();
//        mainPanel.repaint();

//        remove(mainPanel);
//        add(mainPanel);
//        revalidate();
//        repaint();

        myMainPanel.update(this);
    }

    public int getFan50spinner() {
        return (int)(fan50Spinner.getValue());
    }
}
