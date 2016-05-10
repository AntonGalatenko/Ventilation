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
        System.out.println("!!!! " + baseInfo.getFan50Capacity());
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




//    public int getFan50Count() {
//        return fan50;
//    }
}
