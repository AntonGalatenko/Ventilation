package com.toxa.ventilation.gui;

import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.Data.DataOfEquipment;
import com.toxa.ventilation.Main;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
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
    private JCheckBox checkBox2017;
    private Map<String, Boolean> yearsMap;
    private DataOfEquipment dataOfEquipment = new ActualValues().loadActualValue();

    public FactoryInfo(final TableModel model){
        this.model = model;
        table1.setModel(model);
        setColumnSize();

        add(mainPanel);

        yearsPanel.setVisible(false);
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

        checkBox2017.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    updateYears(checkBox2017, true);
                else
                    updateYears(checkBox2017, false);

                setTableSorter();
            }
        });

        checkBox2016.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    updateYears(checkBox2016, true);
                else
                    updateYears(checkBox2016, false);

                setTableSorter();
            }
        });

        checkBox2015.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    updateYears(checkBox2015, true);
                else
                    updateYears(checkBox2015, false);

                setTableSorter();
            }
        });

        checkBox2014.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    updateYears(checkBox2014, true);
                else
                    updateYears(checkBox2014, false);

                setTableSorter();
            }
        });

        checkBox2013.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    updateYears(checkBox2013, true);
                else
                    updateYears(checkBox2013, false);

                setTableSorter();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveActualValue();
            }
        });

        addEscapeListener(this);
    }

    public static void addEscapeListener(final JDialog dialog){
        ActionListener escListener = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                dialog.dispose();
            }
        };

        dialog.getRootPane().registerKeyboardAction(escListener, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    public void setLocationForThisFrame(){
        Point point = Main.getMainPanelLocation();
        setLocation((int)point.getX() + 200, (int)point.getY() + 120);
    }

    public int getHeight(int size){
        int a = 67;

        if(yearsPanel.isVisible())
            a = 95;

        if(size > 30)
            return 570;

        return a + size * 16;
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

    private void addCheckBoxToMap(){
        yearsMap.put("2013", false);
        yearsMap.put("2014", false);
        yearsMap.put("2015", false);
        yearsMap.put("2016", false);
        yearsMap.put("2017", false);

        checkBox2017.setSelected(true);

    }

    public void doSort(){
        yearsMap = BaseInfo.getInstance().getYearsToView();
        if(yearsMap.size() == 0){
            addCheckBoxToMap();
            System.out.println("true");
        } else{
            checkBox2013.setSelected(yearsMap.get("2013"));
            checkBox2014.setSelected(yearsMap.get("2014"));
            checkBox2015.setSelected(yearsMap.get("2015"));
            checkBox2016.setSelected(yearsMap.get("2016"));
            checkBox2017.setSelected(yearsMap.get("2017"));
        }

        yearsPanel.setVisible(true);
        setTableSorter();
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

        final Set<String> years = yearsMap.keySet();
        Iterator<String> iterator = years.iterator();

        final List<Integer> l = new ArrayList<>();

        while (iterator.hasNext()){
            String ye = iterator.next();
            if(yearsMap.get(ye))
                l.add(Integer.parseInt(ye));
        }

        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
            @Override
            public boolean include(Entry<?, ?> entry) {
            Integer i = (Integer) entry.getValue(0);
            return (l.indexOf(i.intValue()) >= 0);
            }
        };

        sorter.setRowFilter(filter);
        sorter.setSortsOnUpdates(true);
        table1.setRowSorter(sorter);

        setPreferredSize(new Dimension(700, getHeight(table1.getRowCount()) + 5));
        pack();
    }

    private void updateYears(JCheckBox checkBox, boolean value){
        yearsMap.replace(checkBox.getText(), value);

        dataOfEquipment.updateYearsToView(yearsMap);
    }

//    private List<Integer> parseYearsMap(){
//        List<Integer> result = new ArrayList<>();
//        for(JCheckBox checkBox : yearsMap.keySet())
//            if(yearsMap.get(checkBox))
//                result.add(Integer.parseInt(checkBox.getText()));
//
//        return  result;
//    }


    public void saveActualValue(){
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("save_ventilation");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(dataOfEquipment);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
