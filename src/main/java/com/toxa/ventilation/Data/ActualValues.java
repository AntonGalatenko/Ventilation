package com.toxa.ventilation.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ActualValues extends DataOfEquipment{

    private static DataOfEquipment actualValues;

    public DataOfEquipment loadActualValue(){
        if(actualValues != null)
            return actualValues;
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream("save_ventilation");
            ois = new ObjectInputStream(fis);
            actualValues = (DataOfEquipment)ois.readObject();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            System.err.println("Не удаётся найти файл с сохранёнными данными!!!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(actualValues == null){
            actualValues = new DataOfEquipment();
            actualValues.setDefaultValues();
        }
        return actualValues;
    }


}
