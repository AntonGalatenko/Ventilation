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

        countPadCool();

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
        if(resultsPanel.getFan26Count() > 1)
            result = (int)(Math.ceil(resultsPanel.getFan26Count() * baseInfo.getFan26Capacity() / baseInfo.getShaftCapacity()));
        else if(resultsPanel.getFan36Count() > 1)
            result = (int)(Math.ceil(resultsPanel.getFan36Count() * baseInfo.getFan36Capacity() / baseInfo.getShaftCapacity()));
        resultsPanel.setShaftCount(result);
        return result;
    }

    public int countAirInletOnWall(){
        int result = (int)(Math.ceil(resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity() / baseInfo.getAirInletOnWallCapacity()));
        if(result == 0)
            result = (int)(Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirForAirInletForTunnelTypeOfVentilation() / baseInfo.getAirInletOnWallCapacity()));

        if(result % 2 != 0)
            result += 1;

        resultsPanel.setAirInletOnWallCount(result);
        return result;
    }

    public int countShutter(){
        int result = (int)(Math.ceil(resultsPanel.getFan50Count() * baseInfo.getFan50Capacity() / baseInfo.getShutterCapacity()));
        resultsPanel.setShutterCount(result);
        return result;
    }

    public void countPadCool(){
        resultsPanel.setHumidityLength1(padCoolFaceSideLength()[0]);
        resultsPanel.setHumidityCount1((int) padCoolFaceSideLength()[1]);

        resultsPanel.setHumidityLength2(padCoolOneSideLength()[0]);
        resultsPanel.setHumidityCount2((int) padCoolOneSideLength()[1]);

        resultsPanel.setHumidityAirSpeed(padCoolAirSpeedCurrent());
    }

    public double[] padCoolOneSideLength(){
        double faceSideSquare = padCoolFaceSideLength()[0] * baseInfo.getHumidityHeight1() * baseInfo.getHumidityCount1();
        double oneSideSquare = (padCoolTotalSquare() - faceSideSquare) / 2;
        double oneSideLength = oneSideSquare / baseInfo.getHumidityHeight2();

        int count = 1;

        while (oneSideLength / count > 21)
            count++;

        oneSideLength = padCoolCurrentLength(oneSideLength / count);
        count *= 2;

        return new double[]{oneSideLength, count};
    }

    public double[] padCoolFaceSideLength(){
        double result = 0;
        int count = 0;

        if (baseInfo.getBuildingWidth() <= 22 && ! baseInfo.isFan50TwoSideCheckBox()){
            result = padCoolCurrentLength(baseInfo.getBuildingWidth());
            count = 1;
        } else if(! baseInfo.isFan50TwoSideCheckBox()){
            result = padCoolCurrentLength((baseInfo.getBuildingWidth() - 1)/ 2);
            count = 2;
        }

        return new double[]{result, count};
    }

    public double padCoolCurrentLength(double value) {
        double x = value % 0.6;
        double result = value;

        if(x > 0.01)
            result = value - x + 0.6;

        result = Math.round(result * 10) / 10.0;
        if(result > 21)
            result = 21;
        return result;
    }

    public double padCoolTotalSquare(){
        int fansCapacity = resultsPanel.getFan50Count() * baseInfo.getFan50Capacity();
        if(baseInfo.isHumidityPlus())
            fansCapacity += resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity();
        double result = fansCapacity / 3600 / baseInfo.getAirSpeedForPadCool();
        return result;
    }

    public double padCoolAirSpeedCurrent(){
        double padCoolSquareCurrent = padCoolFaceSideLength()[0] * padCoolFaceSideLength()[1] * baseInfo.getHumidityHeight1() +
                padCoolOneSideLength()[0] * padCoolOneSideLength()[1] * baseInfo.getHumidityHeight2();
        int fansCapacity = resultsPanel.getFan50Count() * baseInfo.getFan50Capacity();
        if(baseInfo.isHumidityPlus())
            fansCapacity += resultsPanel.getFanRoofCount() * baseInfo.getFanRoofCapacity();
        double result = fansCapacity / 3600 / padCoolSquareCurrent;
        return result;
    }


//    public int getFan50Count() {
//        return fan50;
//    }
}
