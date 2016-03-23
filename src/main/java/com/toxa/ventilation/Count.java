package com.toxa.ventilation;

import com.toxa.ventilation.gui.ResultsPanel;

public class Count {

    private static Count instance;

//    private TaskPanel taskPanel;
    private BaseInfo baseInfo;
    private ResultsPanel resultsPanel;

    private int fan50Count;

    private Count(/*TaskPanel taskPanel,*/ /*BaseInfo baseInfo*/){
//        this.taskPanel = taskPanel;
//        this.baseInfo = baseInfo;

//        resultsPanel = new ResultsPanel(this);
//        resultsPanel.setCount(this);


    }

    public static Count getInstance() {
        if(instance == null)
            instance = new Count();
        return instance;
    }

//        public void setTaskPanel(TaskPanel taskPanel) {
//        this.taskPanel = taskPanel;
//    }

    public void startCount(){
        countFan50Count();
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public void setResultsPanel(ResultsPanel resultsPanel) {
        this.resultsPanel = resultsPanel;
    }

    public int countFan50Count(){
        fan50Count = (int) (Math.ceil(baseInfo.getHeadsNumber() * baseInfo.getAirSummer() / baseInfo.FAN_50_CAPACITY));

        resultsPanel.updateResults();

        System.out.println(fan50Count);

        return fan50Count;
    }
    /*
    public void finishCount(){
        ResultsPanel resultsPanel = new
    }*/

    public int getFan50Count() {
        return fan50Count;
    }
}
