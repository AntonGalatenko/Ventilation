package com.toxa.ventilation.Data;

import java.io.*;

public class ActualValues extends DataOfEquipment{

    private DataOfEquipment actualValues;

    public ActualValues(){
    }

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
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(actualValues == null)
            actualValues = new DataOfEquipment();
        return actualValues;
    }

    public DataOfEquipment getActualValues() {
        return actualValues;
    }

    //    public ActualValues cloneDefaultValue(){
//        DefaultValue defaultValue = new DefaultValue();
//        ActualValues actualValues = new ActualValues();
//
//        actualValues.setFan50Capacity(defaultValue.getFan50Capacity());
//        actualValues.setFan36Capacity(defaultValue.getFan36Capacity());
//        actualValues.setFan26Capacity(defaultValue.getFan26Capacity());
//        actualValues.setFanRoofCapacity(defaultValue.getFanRoofCapacity());
//        actualValues.setFan50Description(defaultValue.getFan50Description());
//        return actualValues;
//    }


}
