package com.toxa.ventilation.gui;

import com.toxa.ventilation.Main;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FactoryInfo extends JDialog{
    private JPanel mainPanel;
    private JTable table1;
    private TableModel model;
    private JScrollPane mainScrollPane;
    private JPanel yearsPanel;
    private JCheckBox checkBox2016;
    private JCheckBox checkBox2013;
    private JCheckBox checkBox2014;
    private JCheckBox checkBox2015;
    private List<Integer> years = new ArrayList<>();

    public FactoryInfo(final TableModel model){
        this.model = model;
        table1.setModel(model);
        setColumnSize();

        add(mainPanel);



        setPreferredSize(new Dimension(700, getHeight(table1.getRowCount())));
        setVisible(true);
        setLocationForThisFrame();

        pack();

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable)e.getSource();
                String path = (String)table.getValueAt(table.getSelectedRow(), 8);
                openExcel(path);
            }
        });

        checkBox2016.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    years.add(2016);
                else
                    years.remove(years.indexOf(2016));

                setTableSorter();
            }
        });

        checkBox2015.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    years.add(2015);
                else
                    years.remove(years.indexOf(2015));

                setTableSorter();
            }
        });

        checkBox2014.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    years.add(2014);
                else
                    years.remove(years.indexOf(2014));

                setTableSorter();
            }
        });

        checkBox2013.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    years.add(2013);
                else
                    years.remove(years.indexOf(2013));

                setTableSorter();
            }
        });

        yearsPanel.setVisible(false);

    }

    public void doSort(){
        checkBox2016.setSelected(true);
        years.add(2016);

        setTableSorter();

        yearsPanel.setVisible(true);
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

    private void setTableSorter(){
        TableRowSorter sorter = new TableRowSorter(model);

        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
            @Override
            public boolean include(Entry<?, ?> entry) {
                Integer i = (Integer) entry.getValue(0);
                return (years.indexOf(i.intValue()) >= 0);
            }
        };

        sorter.setRowFilter(filter);
        sorter.setSortsOnUpdates(true);
        table1.setRowSorter(sorter);
    }
}
