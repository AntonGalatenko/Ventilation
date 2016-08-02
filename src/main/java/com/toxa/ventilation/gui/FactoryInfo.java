package com.toxa.ventilation.gui;

import javax.swing.*;
import javax.swing.table.TableModel;

public class FactoryInfo extends JDialog{
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane mainScrollPane;

    public FactoryInfo(TableModel model){

        table1.setModel(model);

        add(mainPanel);

        setVisible(true);

        pack();
    }
}
