package com.toxa.ventilation;

import com.toxa.ventilation.gui.ResultsPanel;

public class Count {

    private static Count instance;
    private BaseInfo baseInfo;
    private ResultsPanel resultsPanel;

    private Count(){
    }

    public static Count getInstance() {
        if(instance == null)
            instance = new Count();
        return instance;
    }

//    public void countFinish(){
//        resultsPanel.updateResults();
//    }

    public void startCount(){
        countFan50();
        countFan36();
        countFan26();
        countFanRoof();

        countShaft();
        countAirInletOnWall();
        countShutter();

        countPadCoolAndAirInlet();
//        countAirInletPadCool();

        countHeaterAndFanCirculation();

        countFan50AirSpeed();
        countAirSummerCurrent();
        countAirWinterCurrent();
//        countFinish();
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
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
        if(baseInfo.getVentilationType().equals("Техна"))
            result = (int)(Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirWinter()) / baseInfo.getFan26Capacity());
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
        if(resultsPanel.getFan26RadioButton().isSelected())
            result = (int)(Math.ceil(resultsPanel.getFan26Count() * baseInfo.getFan26Capacity() / baseInfo.getShaftCapacity()));
        if(resultsPanel.getFan36RadioButton().isSelected() || (resultsPanel.getFan26Count() == 1))
            result += (int)(Math.ceil(resultsPanel.getFan36Count() * baseInfo.getFan36Capacity() / baseInfo.getShaftCapacity()));
        resultsPanel.setShaftCount(result);
        return result;
    }

    public int countAirInletOnWall(){
        int result = (int)(Math.ceil(resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity() / baseInfo.getAirInletOnWallCapacity()));
        if(result == 0)
            result = (int)(Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirForAirInletForTunnelTypeOfVentilation() / baseInfo.getAirInletOnWallCapacity()));

        if(result % 2 != 0)
            result++;

        resultsPanel.setAirInletOnWallCount(result);
        return result;
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

        return new double[]{oneSideLength, count};
    }

    public double[] padCoolFaceSideLength(){
        double result = 0;
        int count = 0;

        if (baseInfo.getBuildingWidth() <= 22 && ! baseInfo.isFan50TwoSide()){
            result = padCoolCurrentLength(baseInfo.getBuildingWidth(), true);
            count = 1;
        } else if(! baseInfo.isFan50TwoSide()){
            result = padCoolCurrentLength((baseInfo.getBuildingWidth() - 1)/ 2 ,true);
            count = 2;
        }

        return new double[]{result, count};
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
        if(baseInfo.isHumidityPlus() && baseInfo.isFanRoofSelected())
            fansCapacity += resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity();
        double result = fansCapacity / 3600 / baseInfo.getAirSpeedForPadCool();
        return result;
    }

    public double padCoolAirSpeedCurrent(){
        double padCoolSquareCurrent = baseInfo.getHumidityLength1() * baseInfo.getHumidityHeight1() * baseInfo.getHumidityCount1()  +
                baseInfo.getHumidityLength2() * baseInfo.getHumidityHeight2() * baseInfo.getHumidityCount2();
        int fansCapacity = resultsPanel.getFan50Count() * baseInfo.getFan50Capacity();
        if(baseInfo.isHumidityPlus() && baseInfo.isFanRoofSelected())
            fansCapacity += resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity();
        double result = fansCapacity / 3600 / padCoolSquareCurrent;

        resultsPanel.setHumidityAirSpeed(result);
        return result;
    }

    public int countAirInletPadCool(){
        int resultFaceSide = (int) (baseInfo.getHumidityLength1() / 3) * baseInfo.getHumidityCount1();
        int resultOneSide = (int)(baseInfo.getHumidityLength2() / 3) * baseInfo.getHumidityCount2();
        int result = resultFaceSide + resultOneSide;

        resultsPanel.setAirInletForPadCoolCount(result);
        return result;
    }

    public int countHeaterAndFanCirculation(){
        double needPower = baseInfo.getBuildingWidth() * baseInfo.getBuildingLength() / 4;
        int result = (int) Math.ceil(needPower / baseInfo.getHeaterCapacity());
        if(result % 2 != 0)
            result += 1;
        resultsPanel.setHeaterCount(result);
        resultsPanel.setFanCirculationCount(result);
        resultsPanel.setHeaterNeedPower(needPower);
        return  result;
    }

    public void countAirSummerCurrent(){
        double airSummerCount = resultsPanel.getFan50Count() * baseInfo.getFan50Capacity();
        double airSummer = airSummerCount / baseInfo.getHeadsNumber();

        baseInfo.setAirSummerCurrent(airSummer);
    }

    public void countAirWinterCurrent(){
        double airWinterCount = 0;

        if(resultsPanel.getFan36RadioButton().isSelected())
            airWinterCount += resultsPanel.getFan36Count() * baseInfo.getFan36Capacity();
        if(resultsPanel.getFan26RadioButton().isSelected() && resultsPanel.getFan26Count() != 1)
            airWinterCount += resultsPanel.getFan26Count() * baseInfo.getFan26Capacity();
        if(resultsPanel.getFanRoofRadioButton().isSelected())
            airWinterCount += resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity();

        double airWinter = airWinterCount / baseInfo.getHeadsNumber();

        baseInfo.setAirWinterCurrent(airWinter);
    }
    public double countFan50AirSpeed(){
        double buildSquare = baseInfo.getBuildingWidth() * ((baseInfo.getBuildingHeightMin() + baseInfo.getBuildingHeightMax()) / 2);
        double cageSquare = (baseInfo.getCageArea(baseInfo.getCageName() + baseInfo.getCageTiers1())) * baseInfo.getCageNumber1();
        if(baseInfo.getCageNumber2() != 0)
            cageSquare += (baseInfo.getCageArea(baseInfo.getCageName() + baseInfo.getCageTiers2())) * baseInfo.getCageNumber1();

        double totalSquare = buildSquare;
        if(! baseInfo.getCageName().equals("Напольник"))
            totalSquare -= cageSquare;

        double result = resultsPanel.getFan50Count() * baseInfo.getFan50Capacity() / totalSquare / 3600;

        if(baseInfo.isFan50TwoSide())
            result /= 2;
        resultsPanel.setFan50AirSpeed(result);
        return result;
    }

}
