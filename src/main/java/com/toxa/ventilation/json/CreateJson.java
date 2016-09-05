package com.toxa.ventilation.json;

import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.Data.ActualValues;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class CreateJson {

    private BaseInfo baseInfo;

    private JsonObject jsonObject;
    private ObjectMapper mapper;

    private static String json;

    public CreateJson(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;

        jsonObject = new JsonObject();
        mapper = new ObjectMapper();

        createBaseInfo();
        createBuilding();
        createSelectedComponents();
        createGroups();

        createJson();
    }

    public CreateJson() {
    }

    private void createBaseInfo(){
        JsonBaseInfo b = new JsonBaseInfo(baseInfo.getFilePathName(), baseInfo.getCompanyName(), baseInfo.getCountry(), String.valueOf(baseInfo.getHeadsNumber()),
                baseInfo.getPoultryHouseNumber(), baseInfo.getCageName(), baseInfo.getCompose(), baseInfo.getChecked());
        jsonObject.setBaseInfo(b);
    }

    private void createBuilding(){
        JsonBuilding b = new JsonBuilding(baseInfo.getBuildingLengthString(), baseInfo.getBuildingWidthString(), baseInfo.getBuildingHeightMinString(),
                baseInfo.getBuildingHeightMaxString());
        jsonObject.setBuilding(b);
    }

    private void createGroups(){
        jsonObject.setGroups(baseInfo.getGroups());
    }

    private void createSelectedComponents(){
        JsonEnt ent = new JsonEnt();
        JsonEquipment equipment;

        LinkedHashMap<String, Integer> c = baseInfo.getSelectedComponents();

        List<String> list = new ArrayList<>(c.keySet());
        for(int i = 0; i < list.size(); i++){
            String key = list.get(i);

//            System.out.println(key);

            if(ent.getName() == null)
                ent = new JsonEnt(parseValue(getDescriptionEquipment(key))[1]);

            if(isThisComponentIsHumidity(key)){
                if(! ent.getName().equals("Увлажнение")){
                    jsonObject.addEqu(ent);
                    ent = new JsonEnt("Увлажнение");
                }

                equipment = new JsonEquipment(new ActualValues().loadActualValue().getHumidity().get(key).getDescription(), getHumidityDescription(key), c.get(key));
                ent.addEquipment(equipment);

                if(isNextComponentIsNotHumidity(list.get(i + 1)))
                    selectedComponentsAddHumidityWaterCirculation(ent);

            }  else{
                if(parseValue(getDescriptionEquipment(key)).length > 1)
                    if(! ent.getName().equals(parseValue(getDescriptionEquipment(key))[1])){
                        jsonObject.addEqu(ent);
                        ent = new JsonEnt(parseValue(getDescriptionEquipment(key))[1]);
                    }

                if(key.contains("РЩУВ") || key.contains("СЩУМ"))
                    equipment = new JsonEquipment(key, "Щит вентиляции", c.get(key));
                else if(key.contains("ОЩУМ"))
                    equipment = new JsonEquipment(key, parseValue(getDescriptionEquipment(key))[0], c.get(key));
                else if(key.equals("- климатконтроллер"))
                    equipment = new JsonEquipment("Fancom Lumina 37", parseValue(getDescriptionEquipment(key))[0], c.get(key));
                else if(key.equals(parseValue(getDescriptionEquipment(key))[0]))
                    equipment = new JsonEquipment("", parseValue(getDescriptionEquipment(key))[0], c.get(key));
                else if(key.contains("="))
                    equipment = new JsonEquipment(getLightTrapName(key), parseValue(getDescriptionEquipment(key))[0], c.get(key));
                else
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
        equipment.setName(baseInfo.getHumidityWaterCirculation() + "-0" + getPadCoolWaterCirculation()[0]);
        equipment.setDescription("Система циркуляции воды");
        equipment.setNumber(getPadCoolWaterCirculation()[1]);
        ent.addEquipment(equipment);

        if(getPadCoolWaterCirculation()[3] != 0){
            equipment = new JsonEquipment();
            equipment.setName(baseInfo.getHumidityWaterCirculation() + "-0" + getPadCoolWaterCirculation()[2]);
            equipment.setDescription("Система циркуляции воды");
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
        return  baseInfo.padCoolWaterCirculation();
    }

    private String getDescriptionEquipment(String nameEquipment){
        String value = null;

        value = "Вытяжка=";
        if(! nameEquipment.contains("=")){
            if(new ActualValues().loadActualValue().getFan50().containsKey(nameEquipment))
                return value + new ActualValues().loadActualValue().getFan50().get(nameEquipment).getDescription();
            else if(new ActualValues().loadActualValue().getFan36().containsKey(nameEquipment))
                return value + new ActualValues().loadActualValue().getFan36().get(nameEquipment).getDescription();
            else if(new ActualValues().loadActualValue().getFan26().containsKey(nameEquipment))
                return value + new ActualValues().loadActualValue().getFan26().get(nameEquipment).getDescription();
            else if(new ActualValues().loadActualValue().getFanRoof().containsKey(nameEquipment))
                return value + new ActualValues().loadActualValue().getFanRoof().get(nameEquipment).getDescription();
        } else {
            String nameEquipment1 = nameEquipment.split("=")[1];
            if(new ActualValues().loadActualValue().getFan50().containsKey(nameEquipment1))
                return value + "Светофильтр для " + nameEquipment1;
            else if(new ActualValues().loadActualValue().getFan36().containsKey(nameEquipment1))
                return value + "Светофильтр для " + nameEquipment1;
            else if(new ActualValues().loadActualValue().getFan26().containsKey(nameEquipment1))
                return value + "Светофильтр для " + nameEquipment1;
        }

        value = "Приток=";
        if(! nameEquipment.contains("=")){
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
        } else{
            String nameEquipment1 = nameEquipment.split("=")[1];
            if(new ActualValues().loadActualValue().getAirInletOfWall().containsKey(nameEquipment1))
                return value + "Светофильтр для " + nameEquipment1;
            else if(new ActualValues().loadActualValue().getShutter().containsKey(nameEquipment1))
                return value + "Светофильтр для " + nameEquipment1;
        }

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
        else if(nameEquipment.contains("ОЩУМ"))
            return value + "Щит микроклимата в комплекте";

        return nameEquipment;
    }

    private String getHumidityDescription(String value){
        int length = (int) (Double.parseDouble(value.substring(0, value.indexOf("x"))) * 1000);
        int height = (int) (Double.parseDouble(value.substring(value.indexOf("x") + 1)) * 1000);

        return "Испарительная панель " + length + "x" + height + "x150";
    }

    private String getLightTrapName(String value){
        value = value.split("=")[1];

        if(new ActualValues().loadActualValue().getFan50().containsKey(value))
            return new ActualValues().loadActualValue().getLightTrap50();
        else if(new ActualValues().loadActualValue().getFan36().containsKey(value))
            return new ActualValues().loadActualValue().getLightTrap36();
        else if(new ActualValues().loadActualValue().getFan26().containsKey(value))
            return new ActualValues().loadActualValue().getLightTrap26();
        else if(new ActualValues().loadActualValue().getAirInletOfWall().containsKey(value))
            return new ActualValues().loadActualValue().getLightTrapAirInletOfWall();
        else if(new ActualValues().loadActualValue().getShutter().containsKey(value))
            return new ActualValues().loadActualValue().getLightTrapShutter();

        return null;
    }

    private void createJson(){
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getJson() {
        return json;
    }
}
