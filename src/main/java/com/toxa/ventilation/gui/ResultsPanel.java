package com.toxa.ventilation.gui;

import com.toxa.ventilation.Count;

import javax.swing.*;

public class ResultsPanel extends JPanel{
    private Count count;

    private JPanel mainPanel;
    private JPanel bigFanPanel;
    private JRadioButton fan50RadioButton;
    private JSpinner fan50Spinner;
    private JRadioButton fan36RadioButton;
    private JSpinner fan36spinner;

    public ResultsPanel(){
        add(mainPanel);
    }

    public void setCount(Count count) {
        this.count = count;
        System.out.println(count + " " + this.count);

        this.count.setResultsPanel(this);
    }

    public void updateResults(){
        fan50Spinner.setValue(new Integer(count.getFan50Count()));
    }
}
