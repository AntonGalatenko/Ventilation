package com.toxa.ventilation;

import com.toxa.ventilation.gui.ResultsPanel;

public class Count {

    private static Count instance;
    private BaseInfo baseInfo;
    private ResultsPanel resultsPanel;

//    private int fan50;
//    private int fan36;
//    private int fan26;


    private Count(){
    }

    public static Count getInstance() {
        if(instance == null)
            instance = new Count();
        return instance;
    }

    public void countFinish(){
        resultsPanel.updateResults();

    }

    public void startCount(){
        countFan50();
        countFan26();
        countFanRoof();

        countFinish();
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public void setResultsPanel(ResultsPanel resultsPanel) {
        this.resultsPanel = resultsPanel;
    }

    public int countFan50(){
        int result = (int) (Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirSummer() / baseInfo.FAN_50_CAPACITY));
        resultsPanel.setFan50Count(result);
        return result;
    }

    public int countFan26(){
        int result = (int)(Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirWinter()) / baseInfo.FAN_26_CAPACITY);
        resultsPanel.setFan26Count(result);
        return result;
    }

    public int countFanRoof(){
        int fanCapacity;
        if(resultsPanel.getFanRoofName().equals("P6D82"))
            fanCapacity = baseInfo.FAN_Roof820_CAPACITY;
        else
            fanCapacity = baseInfo.FAN_Roof630_CAPACITY;

        int result = (int)(Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirWinter()) / fanCapacity);
        resultsPanel.setFanRoofCount(result);
        return result;
    }




//    public int getFan50Count() {
//        return fan50;
//    }
}
