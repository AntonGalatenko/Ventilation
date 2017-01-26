package com.toxa.ventilation.Data;

import java.io.Serializable;
import java.util.*;

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
    private LinkedHashMap<String, Storage> humidity2 = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> humidity15 = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> humidity1 = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> heater = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> fanCirculation = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> automatic = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> servomotor = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> emergency = new LinkedHashMap<>();
    private LinkedHashMap<String, Storage> cageArea = new LinkedHashMap<>();
    private String lightTrap50;
    private String lightTrap36;
    private String lightTrap26;
    private String lightTrapAirInletOfWall;
    private String lightTrapShutter;

    private String humidityWaterCirculation;
    private String[] composeChecked = new String[2];
    private String filePath;
    private boolean distributeByCountry;
    private Map<String, Boolean> yearsToView = new HashMap<>();
    private long filesSize;


    public void setDefaultValues(){
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
        setHumidityWaterCirculationDefaultValue();

        setHeaterDefaultValue();
        setFanCirculationDefaultValue();

        setAutomaticDefaultValue();
        setServomotorDefaultValue();
        setEmergencyDefaultValue();

        setCageAreaDefaultValue();

        setComposeCheckedDefaultValue();

        setFilePathDefaultValue();

        setLightTrapDefaultValue();
    }

    public void setFilePathDefaultValue(){
        filePath = "C:/";
    }

    public void setFan50DefaultValue(){
        fan50.put("TBO12,7-2-1", new Storage(40000, "Вентилятор осевой с жалюзи 40000м3/ч при 20Па ~3 1,1кВт"));
        fan50.put("DB1380", new Storage(43000, "Вентилятор осевой с конусом и клапаном 43000м3/ч при 20Па ~3 1,1кВт"));
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
        airInletOfWall.put("ZWN3000", new Storage(3000, "Стеновой клапан"));
        airInletOfWall.put("ZWN1500", new Storage(1500, "Стеновой клапан"));
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
        automatic.put("Rotem Junior XL", new Storage(0, "Климатконтроллер в комплекте"));
        automatic.put("Fancom Lumina 37", new Storage(0, "Климатконтроллер в комплекте"));
        automatic.put("ОЩУМ", new Storage(0, "Щит микроклимата"));
    }

    public void setServomotorDefaultValue(){
        servomotor.put("TW150", new Storage(0, "Привод управления тросовкой"));
        servomotor.put("RW45E", new Storage(0, "Привод управления тросовкой"));
        servomotor.put("EGM-100A", new Storage(0, "Привод управления тросовкой"));
        servomotor.put("RDL200", new Storage(0, "Привод управления тросовкой"));
    }

    public void setEmergencyDefaultValue(){
        emergency.put("САОП1", new Storage(0, "Система аварийного открытия приточной вентиляции"));
        emergency.put("САОП2", new Storage(0, "Система аварийного открытия приточной вентиляции"));
        emergency.put("ESE24/2", new Storage(0, "Система аварийного открытия приточной вентиляции"));
        emergency.put("ESE24/4", new Storage(0, "Система аварийного открытия приточной вентиляции"));
    }

    public void setHumidityDefaultValue(){
        humidity2.put("6.0x2.0", new Storage(6.0, "ПУН")); humidity2.put("6.6x2.0", new Storage(6.6, "ПУН-01")); humidity2.put("7.2x2.0", new Storage(7.2, "ПУН-02.0"));
        humidity2.put("7.8x2.0", new Storage(7.8, "ПУН-17"));
        humidity2.put("8.4x2.0", new Storage(8.4, "ПУН-03")); humidity2.put("9.0x2.0", new Storage(9.0, "ПУН-04")); humidity2.put("9.6x2.0", new Storage(9.6, "ПУН-05"));
        humidity2.put("10.2x2.0", new Storage(10.0, "ПУН-06"));
        humidity2.put("10.8x2.0", new Storage(10.8, "ПУН-18")); humidity2.put("11.4x2.0", new Storage(11.4, "ПУН-07")); humidity2.put("12.0x2.0", new Storage(12.0, "ПУН-08"));
        humidity2.put("12.6x2.0", new Storage(12.6, "ПУН-09"));
        humidity2.put("13.2x2.0", new Storage(13.2, "ПУН-10")); humidity2.put("13.8x2.0", new Storage(13.8, "ПУН-22"));humidity2.put("13.8x2.0", new Storage(13.8, "ПУН-22"));
        humidity2.put("14.4x2.0", new Storage(14.4, "ПУН-11")); humidity2.put("15.0x2.0", new Storage(15.0, "ПУН-12"));
        humidity2.put("15.6x2.0", new Storage(15.6, "ПУН-13")); humidity2.put("16.2x2.0", new Storage(16.2, "ПУН-14")); humidity2.put("16.8x2.0", new Storage(16.8, "ПУН-20"));
        humidity2.put("17.4x2.0", new Storage(17.4, "ПУН-15"));
        humidity2.put("18.0x2.0", new Storage(18.0, "ПУН-16")); humidity2.put("18.6x2.0", new Storage(18.6, "ПУН-26")); humidity2.put("19.2x2.0", new Storage(19.2, "ПУН-23"));
        humidity2.put("19.8x2.0", new Storage(19.8, "ПУН-25")); humidity2.put("20.4x2.0", new Storage(20.4, "ПУН-27")); humidity2.put("21.0x2.0", new Storage(21.0, "ПУН-24"));

        humidity15.put("6.0x1.5", new Storage(6.0, "ПУН23")); humidity15.put("6.6x1.5", new Storage(6.6, "ПУН23-01")); humidity15.put("7.2x1.5", new Storage(7.2, "ПУН23-02"));
        humidity15.put("7.8x1.5", new Storage(7.8, "ПУН23-03")); humidity15.put("8.4x1.5", new Storage(8.4, "ПУН23-04")); humidity15.put("9.0x1.5", new Storage(9.0, "ПУН23-05"));
        humidity15.put("9.6x1.5", new Storage(9.6, "ПУН23-06")); humidity15.put("10.2x1.5", new Storage(10.2, "ПУН23-07")); humidity15.put("10.8x1.5", new Storage(10.8, "ПУН23-08"));
        humidity15.put("11.4x1.5", new Storage(11.4, "ПУН23-09")); humidity15.put("12.0x1.5", new Storage(12.0, "ПУН23-10")); humidity15.put("12.6x1.5", new Storage(12.6, "ПУН23-11"));
        humidity15.put("13.2x1.5", new Storage(13.2, "ПУН23-12")); humidity15.put("13.8x1.5", new Storage(13.8, "ПУН23-13")); humidity15.put("14.4x1.5", new Storage(14.4, "ПУН23-14"));
        humidity15.put("15.0x1.5", new Storage(15.0, "ПУН23-15")); humidity15.put("15.6x1.5", new Storage(15.6, "ПУН23-16")); humidity15.put("16.2x1.5", new Storage(16.2, "ПУН23-17"));
        humidity15.put("16.8x1.5", new Storage(16.8, "ПУН23-18")); humidity15.put("17.4x1.5", new Storage(17.4, "ПУН23-19")); humidity15.put("18.0x1.5", new Storage(18.0, "ПУН23-20"));
        humidity15.put("19.2x1.5", new Storage(19.2, "ПУН23-27")); humidity15.put("21.0x1.5", new Storage(21.0, "ПУН23-28"));

        humidity1.put("6.0x1.0", new Storage(6.0, "ПУН21")); humidity1.put("6.6x1.0", new Storage(6.6, "ПУН21-01")); humidity1.put("7.2x1.0", new Storage(7.2, "ПУН21-02"));
        humidity1.put("7.8x1.0", new Storage(7.8, "ПУН21-03")); humidity1.put("8.4x1.0", new Storage(8.4, "ПУН21-04")); humidity1.put("9.0x1.0", new Storage(9.0, "ПУН21-05"));
        humidity1.put("9.6x1.0", new Storage(9.6, "ПУН21-06")); humidity1.put("10.2x1.0", new Storage(10.2, "ПУН21-07")); humidity1.put("10.8x1.0", new Storage(10.8, "ПУН21-08"));
        humidity1.put("11.4x1.0", new Storage(11.4, "ПУН21-09")); humidity1.put("12.0x1.0", new Storage(12.0, "ПУН21-10")); humidity1.put("12.6x1.0", new Storage(12.6, "ПУН21-11"));
        humidity1.put("13.2x1.0", new Storage(13.2, "ПУН21-12")); humidity1.put("13.8x1.0", new Storage(13.8, "ПУН21-13")); humidity1.put("14.4x1.0", new Storage(14.4, "ПУН21-14"));
        humidity1.put("15.0x1.0", new Storage(15.0, "ПУН21-15")); humidity1.put("15.6x1.0", new Storage(15.6, "ПУН21-16")); humidity1.put("16.2x1.0", new Storage(16.2, "ПУН21-17"));
        humidity1.put("16.8x1.0", new Storage(16.8, "ПУН21-18")); humidity1.put("17.4x1.0", new Storage(17.4, "ПУН21-19")); humidity1.put("18.0x1.0", new Storage(18.0, "ПУН21-20"));
        humidity1.put("18.6x1.0", new Storage(18.6, "ПУН21-21")); humidity1.put("19.2x1.0", new Storage(19.2, "ПУН21-22")); humidity1.put("19.8x1.0", new Storage(19.8, "ПУН21-23"));
        humidity1.put("20.4x1.0", new Storage(20.4, "ПУН21-24")); humidity1.put("21.0x1.0", new Storage(21.0, "ПУН21-25"));
    }

    private void setHumidityWaterCirculationDefaultValue(){
        humidityWaterCirculation = "ТСУ3-01.000";
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

    public void setComposeCheckedDefaultValue(){
        composeChecked[0] = "Составляющий";
        composeChecked[1] = "Проверяющий";
    }

    private void setLightTrapDefaultValue(){
        lightTrap50 = "LTP53";
        lightTrap36 = "LTP42";
        lightTrap26 = "LTP26";
        lightTrapAirInletOfWall = "OS2";
        lightTrapShutter = "LTP53";
    }

    public String getFilePath(){
        return filePath;
    }

    public boolean isDistributeByCountry(){
        return distributeByCountry;
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
        LinkedHashMap<String, Storage> result = new LinkedHashMap<>();
        result.putAll(humidity2);
        result.putAll(humidity15);
        result.putAll(humidity1);
        return result;
    }

    public String getHumidityWaterCirculation() {
        return humidityWaterCirculation;
    }

    public LinkedHashMap<String, Storage> getHumidity2() {
         return humidity2;
    }

    public LinkedHashMap<String, Storage> getHumidity15() {
        return humidity15;
    }

    public LinkedHashMap<String, Storage> getHumidity1() {
        return humidity1;
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

    public LinkedHashMap<String, Storage> getServomotor() {
        return servomotor;
    }

    public LinkedHashMap<String, Storage> getEmergency() {
        return emergency;
    }

    public LinkedHashMap<String, Storage> getCageArea() {
        return cageArea;
    }

    public String getLightTrap50(){
        return lightTrap50;
    }

    public String getLightTrap36(){
        return lightTrap36;
    }

    public String getLightTrap26(){
        return lightTrap26;
    }

    public String getLightTrapAirInletOfWall(){
        return lightTrapAirInletOfWall;
    }

    public String getLightTrapShutter(){
        return lightTrapShutter;
    }

    public String[] getComposeChecked(){
        return composeChecked;
    }

    public Map<String, Boolean> getYearsToView() {
        return yearsToView;
    }

    public long getFilesSize(){
        return filesSize;
    }



    public void updateYearsToView(Map<String, Boolean> yearsToView) {
        this.yearsToView = yearsToView;
    }

    public void updateFilePath(String filePath){
        this.filePath = filePath;
    }

    public void updateDistributeByCountry(boolean value){
        distributeByCountry = value;
    }

    public void updateFan50(StringBuilder value){
        updateHashMap(fan50, value);
    }

    public void updateFan36(StringBuilder value) {
        updateHashMap(fan36, value);
    }

    public void updateFan26(StringBuilder value) {
        updateHashMap(fan26, value);
    }

    public void updateFanRoof(StringBuilder value){
        updateHashMap(fanRoof, value);
    }

    public void updateAirInletOfWall(StringBuilder value) {
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

    public void updateShutter(StringBuilder value) {
        updateHashMap(shutter, value);
    }

    public void updateHumidity2(StringBuilder value){
        updateHashMap(humidity2, value);
    }

    public void updateHumidity15(StringBuilder value){
        updateHashMap(humidity15, value);
    }

    public void updateHumidity1(StringBuilder value){
        updateHashMap(humidity1, value);
    }

    public void updateHumidityWaterCirculation(String value){
        humidityWaterCirculation = value;
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

    public void updateServomotor(StringBuilder value){
        updateHashMap(servomotor, value);
    }

    public void updateEmergency(StringBuilder value){
        updateHashMap(emergency, value);
    }

    public void updateCageArea(StringBuilder value){
        updateHashMap(cageArea, value);
    }

    public void updateLightTrap50(String value){
        lightTrap50 = value;
    }

    public void updateLightTrap36(String value){
        lightTrap36 = value;
    }

    public void updateLightTrap26(String value){
        lightTrap26 = value;
    }

    public void updateLightTrapAirInletOfWall(String value){
        lightTrapAirInletOfWall = value;
    }

    public void updateLightTrapShutter(String value){
        lightTrapShutter = value;
    }

    public void updateComposeChecked(String[] value){
        composeChecked = value;
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

    public void updateFileSize(long value){
        filesSize = value;
    }

}
