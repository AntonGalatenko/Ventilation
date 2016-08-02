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
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Название фабрики";
            case 1:
                return "Страна";
            case 2:
                return "Тип";
            case 3:
                return "П/м";
            case 4:
                return "Длина";
            case 5:
                return "Ширина";
            case 6:
                return "Высота";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
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
                return factory.getName();
            case 1:
                return factory.getCountry();
            case 2:
                return factory.getCage();
            case 3:
                return factory.getNumberOfHeads();
            case 4:
                return parseToString(factory.getLength());
            case 5:
                return parseToString(factory.getWidth());
            case 6:
                return parseToString(factory.getHeightMin());
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

    public List<Factory> getFactoryList(){
        return factoryList;
    }

    public String parseToString(double value){
        if(value % 1 == 0)
            return String.valueOf((int)value);
        else
            return String.valueOf(value);
    }

}
