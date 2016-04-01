//package com.toxa.ventilation.Data;
//
//import java.util.HashMap;
//
//public class DefaultValue extends DataOfEquipment{
//
//    private HashMap<String, Storage> fan50 = new HashMap<>();
//    private HashMap<String, Storage> fan36 = new HashMap<>();
//    private HashMap<String, Storage> fan26 = new HashMap<>();
//    private HashMap<String, Storage> fanRoof = new HashMap<>();
//    private HashMap<String, Storage> airInletOfWall = new HashMap<>();
//    private HashMap<String, Storage> airInletOfRoof = new HashMap<>();
//    private HashMap<String, Storage> airInletOfPadCool = new HashMap<>();
//    private HashMap<String, Storage> shutter = new HashMap<>();
//    private HashMap<String, String> humidity = new HashMap<>();
//    private HashMap<String, Storage> heater = new HashMap<>();
//    private HashMap<String, Storage> fanCirculation = new HashMap<>();
//    private HashMap<String, Storage> automatic = new HashMap<>();
//
//
//    public DefaultValue (){
//        setFan50();
//        setFan36();
//        setFan26();
//        setFanRoof();
//
//    }
//
//    public void setFan50(){
//        fan50.put("TBO12,7-2-1", new Storage(40000, "Вентилятор осевой с жалюзи 40000м3/ч при 20Па ~3 1,1кВт"));
//        fan50.put("DB1380", new Storage(40000, "Вентилятор осевой с конусом и клапаном 43000м3/ч при 20Па ~3 1,1кВт"));
//        fan50.put("EOS53/1,5", new Storage(40000, "Вентилятор осевой с жалюзи 40000м3/ч при 20Па ~3 1,1кВт"));
//        fan50.put("EOC53/1,5", new Storage(43000, "Вентилятор осевой с конусом и жалюзи 43000м3/ч при 20Па ~3 1,1кВт"));
//    }
//
//    public void setFan36(){
//        fan36.put("EOS42/1,1", new Storage(20000, "Вентилятор осевой с жалюзи 20000м³/ч при 20Па ~3 0,76кВт"));
//        fan36.put("DM1000/1,1", new Storage(20000, "Вентилятор осевой с жалюзи 20000м³/ч при 20Па ~3 0,4кВт"));
//    }
//
//    public void setFan26(){
//        fan26.put("TBO6,6-2-1", new Storage(10000, "Вентилятор осевой с жалюзи 10000м³/ч при 20Па ~3 0,37кВт"));
//    }
//
//    public void setFanRoof(){
//        fanRoof.put("P6D82", new Storage(21000, "Вентилятор осевой канальный 21000 м³/ч при 30Па ~3 0,87кВт"));
//        fanRoof.put("P6D63", new Storage(10500, "Вентилятор осевой канальный 10500 м³/ч при 30Па ~3"));
//    }
//
//    public HashMap<String, Storage> getFan50() {
//        return fan50;
//    }
//
//    public HashMap<String, Storage> getFan36() {
//        return fan36;
//    }
//
//    public HashMap<String, Storage> getFan26() {
//        return fan26;
//    }
//
//    public HashMap<String, Storage> getFanRoof() {
//        return fanRoof;
//    }
//
//    public HashMap<String, Storage> getAirInletOfWall() {
//        return airInletOfWall;
//    }
//
//    public HashMap<String, Storage> getAirInletOfRoof() {
//        return airInletOfRoof;
//    }
//
//    public HashMap<String, Storage> getAirInletOfPadCool() {
//        return airInletOfPadCool;
//    }
//
//    public HashMap<String, Storage> getShutter() {
//        return shutter;
//    }
//
//    public HashMap<String, String> getHumidity() {
//        return humidity;
//    }
//
//    public HashMap<String, Storage> getHeater() {
//        return heater;
//    }
//
//    public HashMap<String, Storage> getFanCirculation() {
//        return fanCirculation;
//    }
//
//    public HashMap<String, Storage> getAutomatic() {
//        return automatic;
//    }
//
//    public int getFan50Capacity(String key) {
//        return fan50.get(key).getCapacity();
//    }
//
//    public String getFan50Description(String key){
//        return fan50.get(key).getDescription();
//    }
//
//    public void setFan50Capacity(String key, int capacity) {
//        fan50.get(key).setCapacity(capacity);
//    }
//
//    public void setFan50Description(String key, String description){
//        fan50.get(key).setDescription(description);
//    }
//
//    public int getFan36Capacity(String key) {
//        return fan36.get(key).getCapacity();
//    }
//
//    public String getFan36Description(String key){
//        return fan36.get(key).getDescription();
//    }
//
//    public void setFan36Capacity(String key, int capacity) {
//        fan36.get(key).setCapacity(capacity);
//    }
//
//    public void setFan36Description(String key, String description){
//        fan36.get(key).setDescription(description);
//    }
//
//    public int getFan26Capacity(String key) {
//        return fan26.get(key).getCapacity();
//    }
//
//    public String getFan26Description(String key){
//        return fan26.get(key).getDescription();
//    }
//
//    public void setFan26Capacity(String key, int capacity) {
//        fan26.get(key).setCapacity(capacity);
//    }
//
//    public void setFan26Description(String key, String description){
//        fan26.get(key).setDescription(description);
//    }
//
//    public int getFanRoofCapacity(String key) {
//        return fanRoof.get(key).getCapacity();
//    }
//
//    public String getFanRoofDescription(String key){
//        return fanRoof.get(key).getDescription();
//    }
//
//    public void setFanRoofCapacity(String key, int capacity) {
//        fanRoof.get(key).setCapacity(capacity);
//    }
//
//    public void setFanRoofDescription(String key, String description){
//        fanRoof.get(key).setDescription(description);
//    }
//
//    public int getAirInletOfWallCapacity(String key) {
//        return airInletOfWall.get(key).getCapacity();
//    }
//
//    public String getAirInletOfWallDescription(String key){
//        return airInletOfWall.get(key).getDescription();
//    }
//
//    public void setAirInletOfWallCapacity(String key, int capacity) {
//        airInletOfWall.get(key).setCapacity(capacity);
//    }
//
//    public void setAirInletOfWallDescription(String key, String description){
//        airInletOfWall.get(key).setDescription(description);
//    }
//
//    public int getAirInletOfRoofCapacity(String key) {
//        return airInletOfRoof.get(key).getCapacity();
//    }
//
//    public String getAirInletOfRoofDescription(String key){
//        return airInletOfRoof.get(key).getDescription();
//    }
//
//    public void setAirInletOfRoofCapacity(String key, int capacity) {
//        airInletOfRoof.get(key).setCapacity(capacity);
//    }
//
//    public void setAirInletOfRoofDescription(String key, String description){
//        airInletOfRoof.get(key).setDescription(description);
//    }
//
//    public int getAirInletOfPadCoolCapacity(String key) {
//        return airInletOfPadCool.get(key).getCapacity();
//    }
//
//    public String getAirInletOfPadCoolDescription(String key){
//        return airInletOfPadCool.get(key).getDescription();
//    }
//
//    public void setAirInletOfPadCoolCapacity(String key, int capacity) {
//        airInletOfPadCool.get(key).setCapacity(capacity);
//    }
//
//    public void setAirInletOfPadCoolDescription(String key, String description){
//        airInletOfPadCool.get(key).setDescription(description);
//    }
//
//    public int getHeaterCapacity(String key) {
//        return heater.get(key).getCapacity();
//    }
//
//    public String getHeaterDescription(String key){
//        return heater.get(key).getDescription();
//    }
//
//    public void setHeaterCapacity(String key, int capacity) {
//        heater.get(key).setCapacity(capacity);
//    }
//
//    public void setHeaterDescription(String key, String description){
//        heater.get(key).setDescription(description);
//    }
//
//    public int getFanCirculationCapacity(String key) {
//        return fanCirculation.get(key).getCapacity();
//    }
//
//    public String getFanCirculationDescription(String key){
//        return fanCirculation.get(key).getDescription();
//    }
//
//    public void setFanCirculationCapacity(String key, int capacity) {
//        fanCirculation.get(key).setCapacity(capacity);
//    }
//
//    public void setFanCirculationDescription(String key, String description){
//        fanCirculation.get(key).setDescription(description);
//    }
//
//    public int getAutomaticCapacity(String key) {
//        return automatic.get(key).getCapacity();
//    }
//
//    public String getAutomaticDescription(String key){
//        return automatic.get(key).getDescription();
//    }
//
//    public void setAutomaticCapacity(String key, int capacity) {
//        automatic.get(key).setCapacity(capacity);
//    }
//
//    public void setAutomaticDescription(String key, String description){
//        automatic.get(key).setDescription(description);
//    }
//
//    public String getHumidityName(String key) {
//        return humidity.get(key);
//    }
//
//    public void setHumidityName(String key, String value) {
//        humidity.put(key, value);
//    }
//}
