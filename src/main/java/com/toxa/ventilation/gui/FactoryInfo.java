package com.toxa.ventilation.gui;

import com.toxa.ventilation.Main;
import com.toxa.ventilation.model.entity.Factory;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FactoryInfo extends JDialog{
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane mainScrollPane;
    private JPanel yearsPanel;
    private JCheckBox checkBox2016;
    private JCheckBox checkBox2013;
    private JCheckBox checkBox2014;
    private JCheckBox checkBox2015;

    public FactoryInfo(final TableModel model, final List<Factory> factoryList){

        table1.setModel(model);
        setColumnSize();

        add(mainPanel);

        setTableSorter(model);

        setPreferredSize(new Dimension(700, getHeight(table1.getRowCount())));
        setVisible(true);
        setLocationForThisFrame();

        pack();

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable)e.getSource();
//                Component component = e.getComponent();
//                System.out.println("123 " + component.getClass());

//                int row = table.getSelectedRow();

                String path = (String)table.getValueAt(table.getSelectedRow(), 8);
//                System.out.println("123 " + link);

//                String path = factoryList.get(row).getLink();
                openExcel(path);
            }
        });

    }

    public void setLocationForThisFrame(){
        Point point = Main.getMainPanelLocation();
        setLocation((int)point.getX() + 200, (int)point.getY() + 120);
    }

    public int getHeight(int size){
        if(size > 30)
            return 542;
        return 62 + size * 16;
    }

    private void setColumnSize(){
        TableColumnModel tcm = table1.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(50);
        tcm.getColumn(1).setPreferredWidth(300);
        tcm.getColumn(2).setPreferredWidth(150);
        tcm.getColumn(3).setPreferredWidth(70);
        tcm.getColumn(4).setPreferredWidth(80);
        tcm.getColumn(5).setPreferredWidth(70);
        tcm.getColumn(6).setPreferredWidth(50);
        tcm.getColumn(7).setPreferredWidth(40);
        tcm.getColumn(8).setMinWidth(0);
        tcm.getColumn(8).setMaxWidth(0);
    }

    private void openExcel(String path){
        if(path == null)
            return;

        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTableSorter(TableModel model){
//        RowSorter<TableModel> sorter = new TableRowSorter<>(model);
//        sorter.setr
        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setRowFilter(RowFilter.regexFilter("2014", 0));
        table1.setRowSorter(sorter);
    }
}
