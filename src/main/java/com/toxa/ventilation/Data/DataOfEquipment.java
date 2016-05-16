package com.toxa.ventilation.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class DataOfEquipment implements Serializable {

    private LinkedHashMap<String, Storage> fan50 = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> fan36 = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> fan26 = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> fanRoof = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> shaft = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> airInletOfWall = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> airInletOfRoof = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> airInletOfPadCool = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> shutter = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> humidity = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> heater = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> fanCirculation = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> automatic = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> cageArea = new LinkedHashMap<>();

    public DataOfEquipment (){
        setFan50DefaultValue();
        setFan36DefaultValue();
        setFan26DefaultValue();
        setFanRoofDefaultValue();

        setShaftDefaultValue();
        setAirInletOfWallDefaultValue();
        setAirInletOfRoofDefaultValue();
        setAirInletOfPadCoolDefaultValue();
        setShutterDefaultValue();

        setHumidityDefaultValue();

        setHeaterDefaultValue();
        setFanCirculationDefaultValue();

        setAutomaticDefaultValue();

        setCageAreaDefaultValue();

    }

    public void setFan50DefaultValue(){
        fan50.put("TBO12,7-2-1", new Storage(40000, "Вентилятор осевой с жалюзи 40000м3/ч при 20Па ~3 1,1кВт"));
        fan50.put("DB1380", new Storage(40000, "Вентилятор осевой с конусом и клапаном 43000м3/ч при 20Па ~3 1,1кВт"));
        fan50.put("EOS53/1,5", new Storage(40000, "Вентилятор осевой с жалюзи 40000м3/ч при 20Па ~3 1,1кВт"));
        fan50.put("EOC53/1,5", new Storage(43000, "Вентилятор осевой с конусом и жалюзи 43000м3/ч при 20Па ~3 1,1кВт"));
    }

    public void setFan36DefaultValue(){
        fan36.put("EOS42/1,1", new Storage(20000, "Вентилятор осевой с жалюзи 20000м³/ч при 20Па ~3 0,76кВт"));
        fan36.put("DM1000/1,1", new Storage(20000, "Вентилятор осевой с жалюзи 20000м³/ч при 20Па ~3 0,4кВт"));
    }

    public void setFan26DefaultValue(){
        fan26.put("TBO6,6-2-1", new Storage(10000, "Вентилятор осевой с жалюзи 10000м³/ч при 20Па ~3 0,37кВт"));
    }

    public void setFanRoofDefaultValue(){
        fanRoof.put("P6D82", new Storage(21000, "Вентилятор осевой канальный 21000 м³/ч при 30Па ~3 0,87кВт"));
        fanRoof.put("P6D63", new Storage(10500, "Вентилятор осевой канальный 10500 м³/ч при 30Па ~3"));
    }

    public void setAirInletOfWallDefaultValue(){
        airInletOfWall.put("ZWN3000", new Storage(4100, "Стеновой клапан"));
        airInletOfWall.put("ZWN1500", new Storage(2050, "Стеновой клапан"));
    }

    public void setAirInletOfRoofDefaultValue(){
        airInletOfRoof.put("PVH-D-600/600-100PU", new Storage(0 , "Клапан потолочный"));
        airInletOfRoof.put("PVH-D-1120/600-100PU", new Storage(0 , "Клапан потолочный"));
    }

    public void setAirInletOfPadCoolDefaultValue(){
        airInletOfPadCool.put("ВПК5", new Storage(0, "Блок клапанов стеновой, 2500х900мм"));
    }

    public void setShutterDefaultValue(){
        shutter.put("SOB53(SA2S)", new Storage(33000, "Туннельные жалюзи 1380х1380мм"));
        shutter.put("SOB53(DMS1,1)", new Storage(33000, "Туннельные жалюзи 1380х1380мм"));
        shutter.put("VJ130", new Storage(33000, "Туннельные жалюзи 1380х1380мм"));
    }

    public void setShaftDefaultValue(){
        shaft.put("ВПК2В-04.000", new Storage(15000, "Шахта приточная 1000х1000мм"));
    }

    public void setHeaterDefaultValue(){
        heater.put("GA/N 70C", new Storage(70, "Теплогенератор газовый"));
        heater.put("GA/N 95C", new Storage(95, "Теплогенератор газовый"));
        heater.put("Guardian AD250", new Storage(73, "Теплогенератор газовый"));
        heater.put("Phoen/S", new Storage(110, "Теплогенератор дизельный"));
    }

    public void setFanCirculationDefaultValue(){
        fanCirculation.put("ACF-18", new Storage(0, "Вентилятор разгонный/циркуляционный 3720м3/ч"));
        fanCirculation.put("ACF-22", new Storage(0, "Вентилятор разгонный/циркуляционный"));
        fanCirculation.put("ACF-25", new Storage(0, "Вентилятор разгонный/циркуляционный"));
        fanCirculation.put("ACF-30", new Storage(0, "Вентилятор разгонный/циркуляционный"));
    }

    public void setAutomaticDefaultValue(){
        automatic.put("Rotem Junior XL", new Storage(0, "Климатконтроллер"));
        automatic.put("Fancom Lumina 37", new Storage(0, "Климатконтроллер"));
        automatic.put("ОЩУМ+СЩУМ", new Storage(0, "Щит микроклимата"));
        automatic.put("ОЩУМ", new Storage(0, "Щит микроклимата"));
    }

    public void setHumidityDefaultValue(){
        humidity.put("ПУН", new Storage(6.0, "6.0x2")); humidity.put("ПУН-01", new Storage(6.6, "6.6x2")); humidity.put("ПУН-02", new Storage(7.2, "7.2x2"));
        humidity.put("ПУН-17", new Storage(7.8, "7.8x2"));
        humidity.put("ПУН-03", new Storage(8.4, "8.4x2")); humidity.put("ПУН-04", new Storage(9.0, "9.0x2")); humidity.put("ПУН-05", new Storage(9.6, "9.6x2"));
        humidity.put("ПУН-06", new Storage(10.0, "10.2x2"));
        humidity.put("ПУН-18", new Storage(10.8, "10.8x2")); humidity.put("ПУН-07", new Storage(11.4, "11.4x2")); humidity.put("ПУН-08", new Storage(12.0, "12.0x2"));
        humidity.put("ПУН-09", new Storage(12.6, "12.6x2"));
        humidity.put("ПУН-10", new Storage(13.2, "13.2x2")); humidity.put("ПУН-22", new Storage(13.8, "13.8x2")); humidity.put("ПУН-11", new Storage(14.4, "14.4x2"));
        humidity.put("ПУН-12", new Storage(15.0, "15.0x2"));
        humidity.put("ПУН-13", new Storage(15.6, "15.6x2")); humidity.put("ПУН-14", new Storage(16.2, "16.2x2")); humidity.put("ПУН-20", new Storage(16.8, "16.8x2"));
        humidity.put("ПУН-15", new Storage(17.4, "17.4x2"));
        humidity.put("ПУН-16", new Storage(18.0, "18.0x2")); humidity.put("ПУН-23", new Storage(19.2, "19.2x2")); humidity.put("ПУН-25", new Storage(19.8, "19.8x2"));
        humidity.put("ПУН-24", new Storage(21.0, "21.0x2"));

    }

    public void setCageAreaDefaultValue(){
        cageArea.put("ТБК3", new Storage(2.1, "")); cageArea.put("ТБК4", new Storage(2.6, "")); cageArea.put("ТБК5", new Storage(3.1, ""));
        cageArea.put("ТБК6", new Storage(3.7, "")); cageArea.put("ТБК12", new Storage(5.2, ""));
        cageArea.put("ТБЦ3", new Storage(2.4, "")); cageArea.put("ТБЦ4", new Storage(3.1, "")); cageArea.put("ТБЦ5", new Storage(3.7, ""));
        cageArea.put("ТБЦ6", new Storage(4.2, ""));
        cageArea.put("ТБЦ(бр)3", new Storage(2.4, "")); cageArea.put("ТБЦ(бр)4", new Storage(3.1, "")); cageArea.put("ТБЦ(бр)5", new Storage(3.7, ""));
        cageArea.put("ТБЦ(бр)6", new Storage(4.2, ""));
        cageArea.put("ТББ3", new Storage(3.2, "")); cageArea.put("ТББ4", new Storage(4.1, "")); cageArea.put("ТББ5", new Storage(5.0, ""));
        cageArea.put("ТБР2", new Storage(1.9, "")); cageArea.put("ТБР3", new Storage(2.6, ""));
    }

    public LinkedHashMap<String, Storage> getFan50() {
        return fan50;
    }

    public LinkedHashMap<String, Storage> getFan36() {
        return fan36;
    }

    public LinkedHashMap<String, Storage> getFan26() {
        return fan26;
    }

    public LinkedHashMap<String, Storage> getFanRoof() {
        return fanRoof;
    }

    public LinkedHashMap<String, Storage> getShaft() {
        return shaft;
    }

    public LinkedHashMap<String, Storage> getAirInletOfWall() {
        return airInletOfWall;
    }

    public LinkedHashMap<String, Storage> getAirInletOfRoof() {
        return airInletOfRoof;
    }

    public LinkedHashMap<String, Storage> getAirInletForPadCool() {
        return airInletOfPadCool;
    }

    public LinkedHashMap<String, Storage> getShutter() {
        return shutter;
    }

    public LinkedHashMap<String, Storage> getHumidity() {
        return humidity;
    }

    public LinkedHashMap<String, Storage> getHeater() {
        return heater;
    }

    public LinkedHashMap<String, Storage> getFanCirculation() {
        return fanCirculation;
    }

    public LinkedHashMap<String, Storage> getAutomatic() {
        return automatic;
    }

    public LinkedHashMap<String, Storage> getCageArea() {
        return cageArea;
    }

    public void updateFan50(StringBuilder value){
        updateHashMap(fan50, value);
    }

    public void updateFan36(StringBuilder value){
        updateHashMap(fan36, value);
    }

    public void updateFan26(StringBuilder value){
        updateHashMap(fan26, value);
    }

    public void updateFanRoof(StringBuilder value){
        updateHashMap(fanRoof, value);
    }

    public void updateAirInletOfWall(StringBuilder value){
        updateHashMap(airInletOfWall, value);
    }

    public void updateAirInletOfRoof(StringBuilder value){
        updateHashMap(airInletOfRoof, value);
    }

    public void updateAirInletOfPadCool(StringBuilder value){
        updateHashMap(airInletOfPadCool, value);
    }

    public void updateShaft(StringBuilder value){
        updateHashMap(shaft, value);
    }

    public void updateShutter(StringBuilder value){
        updateHashMap(shutter, value);
    }

    public void updateHumidity(StringBuilder value){
        updateHashMap(humidity, value);
    }

    public void updateHeater(StringBuilder value){
        updateHashMap(heater, value);
    }

    public void updateFanCirculation(StringBuilder value){
        updateHashMap(fanCirculation, value);
    }

    public void updateAutomatic(StringBuilder value){
        updateHashMap(automatic, value);
    }

    public void updateCageArea(StringBuilder value){
        updateHashMap(cageArea, value);
    }

    public void updateHashMap(LinkedHashMap<String, Storage> map, StringBuilder value){
        String[] line;
        map.clear();
        Scanner scan = new Scanner(value.toString());
        while(scan.hasNextLine()){
            line = parseStringLine(scan.nextLine());
            map.put(line[0], new Storage(Double.parseDouble(line[1]), line[2]));
        }
    }

    public String[] parseStringLine(String line){
        String[] result = line.split(" : ");
        return  result;
    }

}