package com.toxa.ventilation;

import com.toxa.ventilation.gui.ResultsPanel;

import java.util.*;

public class Count {

    private BaseInfo baseInfo;
    private ResultsPanel resultsPanel;

    final double INDEX = 0.7;

    public void startCount(){
        baseInfo = BaseInfo.getInstance();

        countFan50();
        countFan36();
        countFan26();
        countFanRoof();

        countShaft();
        countAirInletWallAndAirOneHeadAndDistance();
        countShutter();

        countPadCoolAndAirInlet();

        countHeaterAndFanCirculation();

        countFan50AirSpeed();
        countAirTotalCurrent();

        countEmergency();
    }

    public void setResultsPanel(ResultsPanel resultsPanel) {
        this.resultsPanel = resultsPanel;
    }

    public int countFan50(){
        int result = (int) (Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirSummer() / baseInfo.getFan50Capacity()));

        if(baseInfo.isFan50TwoSide() && result %2 != 0)
            result++;

        resultsPanel.setFan50Count(result);
        return result;
    }

    public int countFan36(){
        int result = (int)(Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirWinter()) / baseInfo.getFan36Capacity());

        resultsPanel.setFan36Count(result);
        return result;
    }

    public int countFan26(){
        int result = 1;

        if(baseInfo.getVentilationType().equals("Техна")){
            result = (int)(Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirWinter()) / baseInfo.getFan26Capacity());

            if(result % 2 != 0)
                result++;
        }

        resultsPanel.setFan26Count(result);
        return result;
    }

    public int countFanRoof(){
        int result = (int)(Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirWinter() / baseInfo.getFanRoofCapacity()));

        resultsPanel.setFanRoofCount(result);
        return result;
    }

    public int countShaft(){
        int result = 0;

        if(resultsPanel.getFan26RadioButton().isSelected() && resultsPanel.getFan26Count() > 1)
            result = (int)(Math.ceil(resultsPanel.getFan26Count() * baseInfo.getFan26Capacity() / baseInfo.getShaftCapacity()));

        if(resultsPanel.getFan36RadioButton().isSelected() /*|| (resultsPanel.getFan26Count() == 1)*/)
            result += (int)(Math.ceil(resultsPanel.getFan36Count() * baseInfo.getFan36Capacity() / baseInfo.getShaftCapacity()));

        if(resultsPanel.getFan26Count() == 1 && ! resultsPanel.getFan36RadioButton().isSelected())
            result = (int)(Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirForAirInletForTunnelTypeOfVentilation() / baseInfo.getShaftCapacity()));

        countServomotor();
        resultsPanel.setShaftCount(result);
        return result;
    }

    public int countAirInletWallAndAirOneHeadAndDistance(){
        int result = (int)(Math.ceil(resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity() / getAirInletWallCapacity()));

        if(result == 0)
            result = (int)(Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirForAirInletForTunnelTypeOfVentilation() / getAirInletWallCapacity()));

        if(result % 2 != 0)
            result++;

        resultsPanel.setAirInletOnWallCount(result);

        countAirInletWallAirOneHead();
        countAirInletWallDistance();
        countServomotor();

        return result;
    }

    private int getAirInletWallCapacity(){
        double k = 1;

        if(baseInfo.getBuildingWidth() > 10)
            k = (baseInfo.getBuildingWidth() - 10) * 0.37 / 10 + 1;

        return (int)(baseInfo.getAirInletOnWallCapacity() * k);
    }

    public int countShutter(){
        int result = (int)(Math.ceil(resultsPanel.getFan50Count() * baseInfo.getFan50Capacity() / baseInfo.getShutterCapacity()));

        resultsPanel.setShutterCount(result);
        return result;
    }

    public void countPadCoolAndAirInlet(){
        resultsPanel.setHumidityLength1(padCoolFaceSideLength()[0]);
        resultsPanel.setHumidityCount1((int) padCoolFaceSideLength()[1]);

        resultsPanel.setHumidityLength2(padCoolOneSideLength()[0]);
        resultsPanel.setHumidityCount2((int) padCoolOneSideLength()[1]);

//        countShutterGroup();
        countAirInletPadCool();
    }

    public double[] padCoolOneSideLength(){
        double faceSideSquare = padCoolFaceSideLength()[0] * baseInfo.getHumidityHeight1() * baseInfo.getHumidityCount1();
        double oneSideSquare = (padCoolTotalSquare() - faceSideSquare) / 2;
        double oneSideLength = oneSideSquare / baseInfo.getHumidityHeight2();

        int count = 1;

        while (oneSideLength / count > 21)
            count++;

        oneSideLength = padCoolCurrentLength(oneSideLength / count, false);
        count *= 2;

        if(oneSideLength < 6)
            oneSideLength = 6;

        return new double[]{oneSideLength, count};
    }

    public double[] padCoolFaceSideLength(){
        if(padCoolTotalSquare() < 36)
            return new double[]{0, 0};

        double length = 0;
        int count = 0;

        if (baseInfo.getBuildingWidth() <= 22 && ! baseInfo.isFan50TwoSide()){
            length = padCoolCurrentLength(baseInfo.getBuildingWidth(), true);
            count = 1;
        }
        else if(! baseInfo.isFan50TwoSide()){
            length = padCoolCurrentLength((baseInfo.getBuildingWidth() - 1)/ 2 ,true);
            count = 2;
        }

        double faceSideSquare = length * baseInfo.getHumidityHeight1() * baseInfo.getHumidityCount1();

        if((padCoolTotalSquare() - faceSideSquare) < 24)
            length = padCoolCurrentLength((padCoolTotalSquare() - 24) / baseInfo.getHumidityHeight1(), false);

        return new double[]{length, count};
    }

    public double padCoolCurrentLength(double value, boolean isFaceSide) {
        double x = value % 0.6;
        double result = value;

        if(x > 0.01 && isFaceSide)
            result = value - x;
        else if(x > 0.01)
            result = value - x + 0.6;

        result = Math.round(result * 10) / 10.0;
        return result;
    }

    public double padCoolTotalSquare(){
        int fansCapacity = resultsPanel.getFan50Count() * baseInfo.getFan50Capacity();

        if(baseInfo.isHumidityPlus()){
            if(baseInfo.isFanRoofSelected())
                fansCapacity += resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity();
            if(resultsPanel.getFan36RadioButton().isSelected())
                fansCapacity += resultsPanel.getFan36Count() * baseInfo.getFan36Capacity();
            if(resultsPanel.getFan26RadioButton().isSelected() && resultsPanel.getFan26Count() > 1)
                fansCapacity += resultsPanel.getFan26Count() * baseInfo.getFan26Capacity();
        }

        double result = fansCapacity / 3600 / baseInfo.getAirSpeedForPadCool();
        return result;
    }

    public double padCoolAirSpeedCurrent(){
        double padCoolSquareCurrent = baseInfo.getHumidityLength1() * baseInfo.getHumidityHeight1() * baseInfo.getHumidityCount1()  +
                baseInfo.getHumidityLength2() * baseInfo.getHumidityHeight2() * baseInfo.getHumidityCount2();

        int fansCapacity = resultsPanel.getFan50Count() * baseInfo.getFan50Capacity();

        if(baseInfo.isHumidityPlus()){
            if(baseInfo.isFanRoofSelected())
                fansCapacity += resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity();
            if(resultsPanel.getFan36RadioButton().isSelected())
                fansCapacity += resultsPanel.getFan36Count() * baseInfo.getFan36Capacity();
            if(resultsPanel.getFan26RadioButton().isSelected() && resultsPanel.getFan26Count() > 1)
                fansCapacity += resultsPanel.getFan26Count() * baseInfo.getFan26Capacity();
        }

        double result = fansCapacity / 3600 / padCoolSquareCurrent;

        resultsPanel.setHumidityAirSpeed(result);
        return result;
    }

    public int[] padCoolWaterCirculation(){
        int[] result = new int[4];

        if(resultsPanel.getHumidityCount1() > 0){
            if(resultsPanel.getHumidityLength1() > 14.9)
                result[0] = 3;
            else
                result[0] = 2;
            result[1] = resultsPanel.getHumidityCount1();
        }

        if(resultsPanel.getHumidityCount2() > 0){
            if(resultsPanel.getHumidityLength2() > 14.9)
                result[2] = 3;
            else
                result[2] = 2;
            result[3] = resultsPanel.getHumidityCount2();
        }

        if(result[0] == result[2]){
            result[1] += result[3];
            result[2] = 0;
            result[3] = 0;
        }

        if(result[1] == 0 && result[3] != 0){
            result[0] = result[2];
            result[1] = result[3];
            result[2] = 0;
            result[3] = 0;
        }
        return result;
    }

    public int countAirInletPadCool(){
        int resultFaceSide = (int) ((baseInfo.getHumidityLength1() + 0.6) / 3) * baseInfo.getHumidityCount1();
        int resultOneSide = (int)((baseInfo.getHumidityLength2() + 0.6) / 3) * baseInfo.getHumidityCount2();
        int result = resultFaceSide + resultOneSide;

        resultsPanel.setAirInletForPadCoolCount(result);
        countServomotor();

        return result;
    }

    public int countHeaterAndFanCirculation(){

        double [] airCapMin = {0.074, 0.125, 0.21, 0.285, 0.353, 0.417, 0.479/*, 0.537, 0.594*/};
        double [] weight = {0.05, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6/*, 0.7, 0.8*/};
        int [] tempCurrent = {32, 32, 30, 30, 28, 28, 28/*, 0.7, 0.8*/};

        double sideSquare = baseInfo.getBuildingLength() * baseInfo.getBuildingHeightMin() * 2;
        double faceSquare = baseInfo.getBuildingWidth() * baseInfo.getBuildingHeightMin();
        double roofSquare = baseInfo.getBuildingWidth() * baseInfo.getBuildingLength();
        double totalSideSquare = sideSquare + faceSquare;

//        int temp = 18;
//        if(baseInfo.getCageName().equals("ТБЦ") || baseInfo.getCageName().equals("ТББ") || baseInfo.getCageName().equals("ТБЦ(бр)")
//                || baseInfo.getCageName().equals("Напольник"))
        int deltaTemp;

        double qSide, qRoof;

        double airCapacity, m, q, qExist, needPowerTemp;
        double needPower = 0;

        for(int i = 0; i < airCapMin.length; i++){
            deltaTemp = tempCurrent[i] - baseInfo.getOutsideWinterTemp();
            qSide = totalSideSquare * deltaTemp / 2.08 / 1000;
            qRoof = roofSquare * deltaTemp / 3.13 / 1000;

            airCapacity = baseInfo.getHeadsNumber() * airCapMin[i];
            m = airCapacity * 1.395;
            q = m * deltaTemp * 1.005 / 3600;

            qExist = baseInfo.getHeadsNumber() * weight[i] * 11.6 / 3600;

            needPowerTemp = qSide + qRoof + q - qExist;

            if(needPowerTemp > needPower)
                needPower = needPowerTemp;
        }

        int result = (int) Math.ceil(needPower / baseInfo.getHeaterCapacity());
        if(result % 2 != 0)
            result ++;

        resultsPanel.setHeaterCount(result);
        resultsPanel.setFanCirculationCount(result);
        resultsPanel.setHeaterNeedPower(needPower);

        return result;
    }

//    public int countHeaterAndFanCirculationOLD(){
//        double heightAverage = baseInfo.getBuildingHeightMin();
//        if(baseInfo.getBuildingHeightMax() != 0)
//            heightAverage = (heightAverage + baseInfo.getBuildingHeightMax()) / 2;
//
//        double needPower = baseInfo.getBuildingWidth() * baseInfo.getBuildingLength() * heightAverage;
//
//        int temp = 18;
//        if(baseInfo.getCageName().equals("ТБЦ") || baseInfo.getCageName().equals("ТББ") || baseInfo.getCageName().equals("ТБЦ(бр)"))
//            temp = 32;
//        int yTemp = temp - baseInfo.getOutsideWinterTemp();
//
//        needPower *= yTemp;
//        needPower *= 1.9;
//        needPower /= 860.61;
//
//        int currPower = 0;
//        if(baseInfo.getCageName().equals("ТБР") || baseInfo.getCageName().equals("ТБК"))
//            currPower = baseInfo.getHeadsNumber() * 10 / 1000;
//
//        int result = (int) Math.ceil((needPower - currPower) * 0.9 / baseInfo.getHeaterCapacity());
//
//        if(result % 2 != 0)
//            result ++;
//
//
//        resultsPanel.setHeaterCount(result);
//        resultsPanel.setFanCirculationCount(result);
//        resultsPanel.setHeaterNeedPower(needPower);
//
//        return  result;
//    }

    public double countAirSummerCurrent(){
        double airSummerCount = resultsPanel.getFan50Count() * baseInfo.getFan50Capacity();
        double airSummer = airSummerCount / baseInfo.getHeadsNumber();

        baseInfo.setAirSummerCurrent(airSummer);
        return  airSummer;
    }

    public double countAirWinterCurrent(){
        double airWinterCount = 0;

        if(resultsPanel.getFan36RadioButton().isSelected())
            airWinterCount += resultsPanel.getFan36Count() * baseInfo.getFan36Capacity();
        if(resultsPanel.getFan26RadioButton().isSelected() && resultsPanel.getFan26Count() != 1)
            airWinterCount += resultsPanel.getFan26Count() * baseInfo.getFan26Capacity();
        if(resultsPanel.getFanRoofRadioButton().isSelected())
            airWinterCount += resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity();

        double airWinter = airWinterCount / baseInfo.getHeadsNumber();
        baseInfo.setAirWinterCurrent(airWinter);

        return airWinter;
    }

    public void countAirTotalCurrent(){
        double result = countAirSummerCurrent() + countAirWinterCurrent();

        if(countAirSummerCurrent() == 0 || countAirWinterCurrent() == 0)
            baseInfo.setAirTotalCurrent(0);
        else
            baseInfo.setAirTotalCurrent(result);
    }

    public double countFan50AirSpeed(){
        double heightAverage = baseInfo.getBuildingHeightMin();
        if(baseInfo.getBuildingHeightMax() != 0)
            heightAverage = (heightAverage + baseInfo.getBuildingHeightMax()) / 2;

        double buildSquare = baseInfo.getBuildingWidth() * heightAverage;

        double cageSquare = 0;
        if(! baseInfo.getCageName().equals("Напольник")){
            cageSquare = (baseInfo.getCageArea(baseInfo.getCageName() + baseInfo.getCageTiers1())) * baseInfo.getCageNumber1();

            if(baseInfo.getCageNumber2() != 0)
                cageSquare += (baseInfo.getCageArea(baseInfo.getCageName() + baseInfo.getCageTiers2())) * baseInfo.getCageNumber2();
        }

        double totalSquare = buildSquare - cageSquare;
        double result = resultsPanel.getFan50Count() * baseInfo.getFan50Capacity() / totalSquare / 3600;

        if(baseInfo.isFan50TwoSide())
            result /= 2;

        resultsPanel.setFan50AirSpeed(result);
        return result;
    }

    public double countAirInletWallAirOneHead(){
        double airCapacity = resultsPanel.getAirInletOnWallCount() * getAirInletWallCapacity();
        double result = airCapacity / baseInfo.getHeadsNumber();

        resultsPanel.setAirInletWallAirOneHead(result);
        return result;
    }

    public double countAirInletWallDistance(){
        double buildingLength = baseInfo.getBuildingLength();
        buildingLength -= 12;

        double airInletNumberForOneSide = resultsPanel.getAirInletOnWallCount() / 2 - 1;
        double result = buildingLength / airInletNumberForOneSide;

        resultsPanel.setAirInletWallDistance(result);
        return result;
    }

    public int countServomotor(){
        int result = 0;

        if(resultsPanel.getAirInletWallRadioButton().isSelected())
            result += 2;
        if(resultsPanel.getShaftRadioButton().isSelected())
            result += 1;
        if(resultsPanel.getAirInletForPadCoolRadioButton().isSelected())
            result += 1;
        if(resultsPanel.getAirInletForPadCoolRadioButton().isSelected() && resultsPanel.getHumidityCount1() > 0)
            result += 1;
        if(resultsPanel.getAirInletForPadCoolRadioButton().isSelected() && resultsPanel.getAirInletForPadCoolCount() > 15)
            result += 1;

        resultsPanel.setServomotorCount(result);
        return result;
    }

    public int countEmergency(){
        int result = 0;
        if(resultsPanel.getAirInletWallRadioButton().isSelected() || resultsPanel.getShaftRadioButton().isSelected())
            result = 1;

        resultsPanel.setEmergencyCount(result);
        return result;
    }

    public ArrayList<Integer> countShutterGroup() {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> fan50GroupList = countFan50Group();

        for(int fans : fan50GroupList)
            result.add((int)Math.ceil(fans * baseInfo.getFan50Capacity() / baseInfo.getShutterCapacity()));

        if(groupCountAllList(result) != resultsPanel.getShutterCount())
            result.set(result.size() - 1, result.get(result.size() - 1) - (groupCountAllList(result) - resultsPanel.getShutterCount()));

        Collections.sort(result);
        return result;
    }

    public ArrayList<Integer> countFanRoofGroup() {
        ArrayList<Integer> result = new ArrayList<>();

        int fans = (int)(baseInfo.getHeadsNumber() * 0.9 / baseInfo.getFanRoofCapacity());

        if(fans == 0)
            fans++;

        result.add(fans);

        while (groupCountAllList(result) < resultsPanel.getFanRoofCount()) {
            fans = (int)Math.ceil(fans * 1.55);

            if(groupCountAllList(result) + fans > resultsPanel.getFanRoofCount())
                result.add(resultsPanel.getFanRoofCount() - groupCountAllList(result));
            else
                result.add(fans);
        }

        Collections.sort(result);
        return result;
    }

    public ArrayList<Integer> countFan26Group(){
        ArrayList<Integer> result = new ArrayList<>();

        int fans = (int)(baseInfo.getHeadsNumber() * INDEX / baseInfo.getFan26Capacity());

        if(fans == 0)
            fans++;

        if(fans % 2 != 0)
            fans ++;
        result.add(fans);
        result.add(fans);

        while (groupCountAllList(result) < resultsPanel.getFan26Count()) {
            fans = (int)Math.ceil(fans * 1.55);

            if(groupCountAllList(result) + fans > resultsPanel.getFan26Count())
                result.add(resultsPanel.getFan26Count() - groupCountAllList(result));
            else{
                if(fans % 2 != 0)
                    result.add(++fans);
            }
        }

        Collections.sort(result);
        return result;
    }

    public ArrayList<Integer> countFan36Group(){
        ArrayList<Integer> result = new ArrayList<>();

        int fans = (int)Math.ceil(baseInfo.getHeadsNumber() * INDEX / baseInfo.getFan36Capacity());

        if(fans == 0)
            fans++;

        result.add(fans);

        while (groupCountAllList(result) < resultsPanel.getFan36Count()) {
            fans = (int)Math.ceil(fans * 1.55);

            if(groupCountAllList(result) + fans > resultsPanel.getFan36Count())
                result.add(resultsPanel.getFan36Count() - groupCountAllList(result));
            else
                result.add(fans);
        }

        Collections.sort(result);
        return result;
    }

    public ArrayList<Integer> countFan50Group(){
        ArrayList<Integer> result = new ArrayList<>();

        int fans = (int)Math.ceil(baseInfo.getHeadsNumber() * INDEX / baseInfo.getFan50Capacity());

        int fanCount = resultsPanel.getFan50Count();
        if(resultsPanel.isFan50TwoSide()){
            fanCount /= 2;
            fans /= 2;
        }

        if(fans == 0)
            fans++;

        result.add(fans);

        while (groupCountAllList(result) < fanCount) {
            fans = (int)Math.ceil(fans * 1.55);

            if(groupCountAllList(result) + fans > fanCount)
                result.add(fanCount - groupCountAllList(result));
            else
                result.add(fans);
        }

        Collections.sort(result);

        if(resultsPanel.isFan50TwoSide())
            result.add(-1);

        return result;
    }


     private int groupCountAllList(ArrayList<Integer> list){
        int result = 0;
        for(int i : list)
            result += i;

        return result;
    }

    public LinkedHashMap<String, Integer[]> getGroups(){
        LinkedHashMap<String, Integer[]> result = new LinkedHashMap<String, Integer[]>();

        result.put("first_group", new Integer[]{baseInfo.getFirstGroup()});

        if(resultsPanel.getFan26RadioButton().isSelected() && resultsPanel.getFan26Count() > 1){
            Integer[] x = countFan26Group().toArray(new Integer[0]);
            result.put(resultsPanel.getFan26Name(), x);
        }

        if(resultsPanel.getFanRoofRadioButton().isSelected()){
            Integer[] x = countFanRoofGroup().toArray(new Integer[0]);
            result.put(resultsPanel.getFanRoofName(), x);
        }

        if(resultsPanel.getFan36RadioButton().isSelected()){
            Integer[] x = countFan36Group().toArray(new Integer[0]);
            result.put(resultsPanel.getFan36Name(), x);
        }

        if(resultsPanel.getFan50RadioButton().isSelected()){
            Integer[] x = countFan50Group().toArray(new Integer[0]);
            result.put(resultsPanel.getFan50Name(), x);
        }

        if(resultsPanel.getShutterRadioButton().isSelected()){
            Integer[] x = countShutterGroup().toArray(new Integer[0]);
            result.put(resultsPanel.getShutterName(), x);
        }

        return result;
    }

}
