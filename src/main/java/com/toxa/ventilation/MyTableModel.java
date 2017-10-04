package com.toxa.ventilation;

import com.toxa.ventilation.model.entity.Factory;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyTableModel implements TableModel {

    private Set<TableModelListener> listeners = new HashSet<>();
    private List<Factory> factoryList;

    public MyTableModel(List<Factory> factoryList) {
        this.factoryList = factoryList;
    }

    @Override
    public int getRowCount() {
        return factoryList.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Год";
            case 1:
                return "Название фабрики";
            case 2:
                return "Страна";
            case 3:
                return "Тип";
            case 4:
                return "П/м";
            case 5:
                return "Длина";
            case 6:
                return "Ширина";
            case 7:
                return "Высота";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Integer.class;
            case 5:
                return Double.class;
            case 6:
                return Double.class;
            case 7:
                return Double.class;
            case 8:
                return String.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Factory factory = factoryList.get(rowIndex);

        switch (columnIndex){
            case 0:
                return factory.getYear();
            case 1:
                return factory.getName();
            case 2:
                return factory.getCountry();
            case 3:
                return factory.getCage();
            case 4:
                return factory.getNumberOfHeads();
            case 5:
                return factory.getLength();
            case 6:
                return factory.getWidth();
            case 7:
                return factory.getHeightMin();
            case 8:
                return factory.getLink();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

//    public String parseToString(double value){
//        if(value % 1 == 0)
//            return String.valueOf((int)value);
//        else
//            return String.valueOf(value);
//    }

}
