package com.toxa.ventilation;

import java.io.Serializable;
import java.util.HashMap;

public class ActualValues implements Serializable {

    private ActualValues a;

    private int version = 1223;

    private HashMap<String, Integer> fan50Capacity = new HashMap<>();
    private HashMap<String, Integer> fan36Capacity = new HashMap<>();
    private HashMap<String, Integer> fan26Capacity = new HashMap<>();
    private HashMap<String, Integer> fanRoofCapacity = new HashMap<>();

    private HashMap<String, String> fan50Description = new HashMap<>();


    public ActualValues(){
    }

    public ActualValues loadActualValue(){
        ActualValues actualValues = null;
//        FileInputStream fis;
//        ObjectInputStream ois;
//        try {
//            fis = new FileInputStream("save_ventilation");
//            ois = new ObjectInputStream(fis);
//            actualValues = (ActualValues)ois.readObject();
//            System.out.println(actualValues.getVersion());
//        } catch (FileNotFoundException e) {
////            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println("actualValues1 " + actualValues);
//        if(actualValues == null)
            actualValues = cloneDefaultValue();
        System.out.println("actualValues2 " + actualValues);

        return actualValues;
    }

    public ActualValues cloneDefaultValue(){
        DefaultValue defaultValue = new DefaultValue();
        ActualValues actualValues = new ActualValues();

        actualValues.setFan50Capacity(defaultValue.getFan50Capacity());
        actualValues.setFan36Capacity(defaultValue.getFan36Capacity());
        actualValues.setFan26Capacity(defaultValue.getFan26Capacity());
        actualValues.setFanRoofCapacity(defaultValue.getFanRoofCapacity());
        actualValues.setFan50Description(defaultValue.getFan50Description());
        return actualValues;
    }

    public int getVersion() {
        return version;
    }

    public HashMap<String, Integer> getFan50Capacity() {
        return fan50Capacity;
    }

    public StringBuilder getFan50() {
        a = loadActualValue();

        StringBuilder stringBuilder = new StringBuilder();

        for(String key : a.fan50Capacity.keySet())
            stringBuilder.append(key + " : " + a.fan50Capacity.get(key) + " : ");

        for(String key : a.fan50Description.keySet())
            stringBuilder.append(a.fan50Description.get(key) + "\n");

        System.out.println("stringBuilder: " + stringBuilder);
        return stringBuilder;
    }

    public HashMap<String, Integer> getFan36Capacity() {
        return fan36Capacity;
    }

    public HashMap<String, Integer> getFan26Capacity() {
        return fan26Capacity;
    }

    public HashMap<String, Integer> getFanRoofCapacity() {
        return fanRoofCapacity;
    }

    public void setFan50Capacity(HashMap<String, Integer> fan50Capacity) {
        this.fan50Capacity = fan50Capacity;
    }

    public void setFan36Capacity(HashMap<String, Integer> fan36Capacity) {
        this.fan36Capacity = fan36Capacity;
    }

    public void setFan26Capacity(HashMap<String, Integer> fan26Capacity) {
        this.fan26Capacity = fan26Capacity;
    }

    public HashMap<String, String> getFan50Description() {
        return fan50Description;
    }

    public void setFan50Description(HashMap<String, String> fan50Description) {
        this.fan50Description = fan50Description;
    }

    public void setFanRoofCapacity(HashMap<String, Integer> fanRoofCapacity) {
        this.fanRoofCapacity = fanRoofCapacity;


    }
}
