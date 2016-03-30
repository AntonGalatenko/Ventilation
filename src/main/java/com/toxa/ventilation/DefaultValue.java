package com.toxa.ventilation;

import java.util.HashMap;

public class DefaultValue {

    private HashMap<String, Integer> fan50Capacity = new HashMap<>();
    private HashMap<String, Integer> fan36Capacity = new HashMap<>();
    private HashMap<String, Integer> fan26Capacity = new HashMap<>();
    private HashMap<String, Integer> fanRoofCapacity = new HashMap<>();

    private HashMap<String, String> fan50Description = new HashMap<>();


//    private HashMap<String, Integer> airInletOnWallCapacity = new HashMap<>();
//    private HashMap<String, Integer> airInletOnRoofCapacity = new HashMap<>();
//    private HashMap<String, Integer> airInletForPadCoolCapacity = new HashMap<>();
//    private HashMap<String, Integer> shutterCapacity = new HashMap<>();
//
//    private HashMap<String, Integer> padCoolNames = new HashMap<>();



    public DefaultValue (){
        putFan50Capacity();
        putFan36Capacity();
        putFan26Capacity();
        putFanRoofCapacity();

        putFan50Description();
    }

    public void putFan50Capacity(){
        fan50Capacity.put("TBO12,7-2-1", 40000);
        fan50Capacity.put("DB1380", 40000);
        fan50Capacity.put("EOS53/1,5", 40000);
        fan50Capacity.put("EOC53/1,5", 43000);
    }

    public void putFan50Description(){
        fan50Description.put("TBO12,7-2-1", "Вентилятор осевой с жалюзи 40000м3/ч");
        fan50Description.put("DB1380", "Вентилятор осевой с клапаном 40000м3/ч");
        fan50Description.put("EOS53/1,5", "Вентилятор осевой с жалюзи 40000м3/ч");
        fan50Description.put("EOC53/1,5", "Вентилятор осевой с жалюзи 43000м3/ч");
    }

    public void putFan36Capacity(){
        fan36Capacity.put("EOS42/1,1", 20000);
        fan36Capacity.put("DM1000", 20000);
    }

    public void putFan26Capacity(){
        fan26Capacity.put("TBO6,6-2-1", 10000);
    }

    public void putFanRoofCapacity(){
        fanRoofCapacity.put("P6D82", 21000);
        fanRoofCapacity.put("P6D63", 10500);
    }

    public HashMap<String, Integer> getFan50Capacity() {
        return fan50Capacity;
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

    public HashMap<String, String> getFan50Description() {
        return fan50Description;
    }

}
