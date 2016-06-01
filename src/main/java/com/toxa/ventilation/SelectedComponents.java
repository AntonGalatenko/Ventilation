package com.toxa.ventilation;

import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.gui.ResultsPanel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SelectedComponents {
    private ResultsPanel resultsPanel;

    public SelectedComponents(ResultsPanel resultsPanel) {
        this.resultsPanel = resultsPanel;
    }

    public StringBuilder getSelectedComponents(){
        StringBuilder selectedComponents = new StringBuilder();

        LinkedHashMap<String, Integer> c = resultsPanel.getSelectedComponents();

        System.out.println(c);////////////////////////////////////////////////////////////////////////////////////

        List<String> list = new ArrayList<>(c.keySet());
        for(int i = 0; i < list.size(); i++){
            String key = list.get(i);

            if(isThisComponentIsHumidity(key)){
                if(selectedComponents.indexOf("Увлажнение") < 0)
                    selectedComponents.append("Увлажнение\n");

                selectedComponents.append(getHumidityDescription(key)
                        + " : " + new ActualValues().loadActualValue().getHumidity().get(key).getDescription()
                        + " :" + c.get(key) + "\n");

                if(isNextComponentIsNotHumidity(list.get(i + 1)))
                    selectedComponentsAddHumidityWaterCirculation(selectedComponents);
            }
            else
                selectedComponents.append(selectedComponentsAddEquipmentTypeAndReturnEquipmentDescription(selectedComponents, getDescriptionEquipment(key))
                        + " : " + key
                        + " : " + c.get(key) + "\n");
        }

        System.out.println(selectedComponents);///////////////////////////////////////////////////////////////////////////////////////

        return selectedComponents;
    }

    private boolean isThisComponentIsHumidity(String key){
        return new ActualValues().loadActualValue().getHumidity().containsKey(key);
    }

    private boolean isNextComponentIsNotHumidity(String key){
        return ! new ActualValues().loadActualValue().getHumidity().containsKey(key);
    }

    private void selectedComponentsAddHumidityWaterCirculation(StringBuilder result){
        result.append("Система циркуляции воды : ТСУ3-01.000-0" + getPadCoolWaterCirculation()[0] + " : " + getPadCoolWaterCirculation()[1] + "\n");

        if(getPadCoolWaterCirculation()[3] != 0)
            result.append("Система циркуляции воды : ТСУ3-01.000-0" + getPadCoolWaterCirculation()[2] + " : " + getPadCoolWaterCirculation()[3] + "\n");
    }

    private String selectedComponentsAddEquipmentTypeAndReturnEquipmentDescription(StringBuilder selectedComponents, String value){
        if(value.indexOf("=") < 0)
            return value;

        String[] split = value.split("=");

        if(selectedComponents.indexOf(split[0]) < 0)
            selectedComponents.append(split[0] + "\n");

        return split[1];
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
}
