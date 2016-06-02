package com.toxa.ventilation.json;

import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.Count;
import com.toxa.ventilation.Data.ActualValues;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.util.DefaultPrettyPrinter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CreateJson {

    private BaseInfo baseInfo;
    private JsonObject jsonObject;
    private ObjectMapper mapper;

    public CreateJson(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;

        jsonObject = new JsonObject();
        mapper = new ObjectMapper();

        createBaseInfo();
        createBuilding();
        createSelectedComponents();

        createJson();

    }

    private void createBaseInfo(){
        JsonBaseInfo b = new JsonBaseInfo(baseInfo.getCompanyName(), baseInfo.getCountry(), String.valueOf(baseInfo.getHeadsNumber()),
                baseInfo.getPoultryHouseNumber(), baseInfo.getCageName());
        jsonObject.setBaseInfo(b);
    }

    private void createBuilding(){
        JsonBuilding b = new JsonBuilding(baseInfo.getBuildingLength(), baseInfo.getBuildingWidth(), baseInfo.getBuildingHeightMin(), baseInfo.getBuildingHeightMax());
        jsonObject.setBuilding(b);
    }

    public void createSelectedComponents(){

        JsonEnt ent = new JsonEnt();
        JsonEquipment equipment;

        LinkedHashMap<String, Integer> c = baseInfo.getSelectedComponents();

        System.out.println(c);////////////////////////////////////////////////////////////////////////////////////

        List<String> list = new ArrayList<>(c.keySet());
        for(int i = 0; i < list.size(); i++){
            String key = list.get(i);

            if(ent.getName() == null)
                ent = new JsonEnt(parseValue(getDescriptionEquipment(key))[1]);

            if(isThisComponentIsHumidity(key)){
                if(! ent.getName().equals("Увлажнение")){
                    jsonObject.addEqu(ent);
                    ent = new JsonEnt("Увлажнение");
                }

                equipment = new JsonEquipment(getHumidityDescription(key), new ActualValues().loadActualValue().getHumidity().get(key).getDescription(), c.get(key));
                ent.addEquipment(equipment);

                if(isNextComponentIsNotHumidity(list.get(i + 1)))
                    selectedComponentsAddHumidityWaterCirculation(ent);

            }  else{
                if(parseValue(getDescriptionEquipment(key)).length > 1)
                    if(! ent.getName().equals(parseValue(getDescriptionEquipment(key))[1])){
                        jsonObject.addEqu(ent);
                        ent = new JsonEnt(parseValue(getDescriptionEquipment(key))[1]);
                    }

                equipment = new JsonEquipment(key, parseValue(getDescriptionEquipment(key))[0], c.get(key));
                ent.addEquipment(equipment);
            }
        }

        jsonObject.addEqu(ent);
    }

    private boolean isThisComponentIsHumidity(String key){
        return new ActualValues().loadActualValue().getHumidity().containsKey(key);
    }

    private boolean isNextComponentIsNotHumidity(String key){
        return ! new ActualValues().loadActualValue().getHumidity().containsKey(key);
    }

    private void selectedComponentsAddHumidityWaterCirculation(JsonEnt ent){
        JsonEquipment equipment = new JsonEquipment();
        equipment.setDescription("Система циркуляции воды : ТСУ3-01.000-0" + getPadCoolWaterCirculation()[0]);
        equipment.setNumber(getPadCoolWaterCirculation()[1]);
        ent.addEquipment(equipment);

        if(getPadCoolWaterCirculation()[3] != 0){
            equipment = new JsonEquipment();
            equipment.setDescription("Система циркуляции воды : ТСУ3-01.000-0" + getPadCoolWaterCirculation()[2]);
            equipment.setNumber(getPadCoolWaterCirculation()[3]);
            ent.addEquipment(equipment);
        }
    }

    private String[] parseValue(String value){
        if(! value.contains("="))
            return new String[]{value};

        String[] split = value.split("=");

        return new String[]{split[1], split[0]};
    }

    private int[] getPadCoolWaterCirculation(){
        return  Count.getInstance().padCoolWaterCirculation();
    }

    private String getDescriptionEquipment(String nameEquipment){
        String value = null;

        value = "Вытяжка=";

        if(new ActualValues().loadActualValue().getFan50().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getFan50().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getFan36().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getFan36().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getFan26().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getFan26().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getFanRoof().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getFanRoof().get(nameEquipment).getDescription();

        value = "Приток=";

        if(new ActualValues().loadActualValue().getShaft().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getShaft().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getAirInletOfWall().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getAirInletOfWall().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getAirInletOfRoof().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getAirInletOfRoof().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getAirInletForPadCool().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getAirInletForPadCool().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getShutter().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getShutter().get(nameEquipment).getDescription();

        value = "Отопление=";

        if(new ActualValues().loadActualValue().getHeater().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getHeater().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getFanCirculation().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getFanCirculation().get(nameEquipment).getDescription();

        value = "Автоматика=";

        if(new ActualValues().loadActualValue().getAutomatic().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getAutomatic().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getServomotor().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getServomotor().get(nameEquipment).getDescription();
        else if(new ActualValues().loadActualValue().getEmergency().containsKey(nameEquipment))
            return value + new ActualValues().loadActualValue().getEmergency().get(nameEquipment).getDescription();

        return nameEquipment;
    }

    private String getHumidityDescription(String value){
        int length = (int) (Double.parseDouble(value.substring(0, value.indexOf("x"))) * 1000);
        int height = (int) (Double.parseDouble(value.substring(value.indexOf("x") + 1)) * 1000);

        return "Испарительная панель " + length + "x" + height + "x150";
    }

    private void createJson(){
        try {
            mapper.writer(new DefaultPrettyPrinter()).writeValue(new File("base.json"), jsonObject);

            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject));//////////////////////////////////////////////////////////////
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
