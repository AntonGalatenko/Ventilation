package com.toxa.ventilation.gui;

import javax.swing.*;

public class FactoryInfo extends JDialog{
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane mainScrollPane;

    public FactoryInfo(String[][] data){
        String[] columnName = {"Название", "Кол-во п/м"};
        table1 = new JTable(data, columnName);

        add(mainPanel);

        setVisible(true);

        pack();
    }
}
