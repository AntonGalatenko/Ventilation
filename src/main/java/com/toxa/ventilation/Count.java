package com.toxa.ventilation;

import com.toxa.ventilation.gui.ResultsPanel;

public class Count {

    private static Count instance;
    private BaseInfo baseInfo;
    private ResultsPanel resultsPanel;

    private int fan50;
    private int fan36;


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
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public void setResultsPanel(ResultsPanel resultsPanel) {
        this.resultsPanel = resultsPanel;
    }

    public int countFan50(){
        fan50 = (int) (Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirSummer() / baseInfo.FAN_50_CAPACITY));
        return fan50;
    }



    public int getFan50Count() {
        return fan50;
    }
}
