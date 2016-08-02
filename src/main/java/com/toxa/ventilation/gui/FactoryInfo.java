package com.toxa.ventilation.gui;

import com.toxa.ventilation.Main;
import com.toxa.ventilation.MyTableModel;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class FactoryInfo extends JDialog{
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane mainScrollPane;

    public FactoryInfo(final MyTableModel model){

        table1.setModel(model);
        setColumnSize();

        add(mainPanel);

        setPreferredSize(new Dimension(600, getHeight(model.getFactoryList().size())));
        setVisible(true);
        setLocationForThisFrame();

        pack();

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable)e.getSource();
                int row = table.getSelectedRow();

                String path = model.getFactoryList().get(row).getLink();
                openExcel(path);
            }
        });

    }

    public void setLocationForThisFrame(){
        Point point = Main.getMainPanelLocation();
        setLocation((int)point.getX() + 150, (int)point.getY() + 120);
    }

    public int getHeight(int size){
        if(size > 20)
            return 382;
        return 62 + size * 16;
    }

    private void setColumnSize(){
        TableColumnModel tcm = table1.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(250);
        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(2).setPreferredWidth(70);
        tcm.getColumn(3).setPreferredWidth(70);
        tcm.getColumn(4).setPreferredWidth(70);
        tcm.getColumn(5).setPreferredWidth(50);
        tcm.getColumn(6).setPreferredWidth(40);
    }

    public void openExcel(String path){
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(path + ".xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
