package com.toxa.ventilation;

import com.toxa.ventilation.gui.ResultsPanel;
import com.toxa.ventilation.gui.TaskPanel;

public class Count {

    private TaskPanel taskPanel;
    private ExtraInfo extraInfo;
    private ResultsPanel resultsPanel;

    private int fan50Count;

    public Count(TaskPanel taskPanel, ExtraInfo extraInfo){
        this.taskPanel = taskPanel;
        this.extraInfo = extraInfo;

        getFan50Count();
    }

//    public void setTaskPanel(TaskPanel taskPanel) {
//        this.taskPanel = taskPanel;
//    }
//
//    public void setExtraInfo(ExtraInfo extraInfo) {
//        this.extraInfo = extraInfo;
//    }


    public void setResultsPanel(ResultsPanel resultsPanel) {
        this.resultsPanel = resultsPanel;
    }

    public int getFan50Count(){
        fan50Count = (int) (Math.ceil(taskPanel.getHeadsNumber() * taskPanel.getAirSummer() / extraInfo.FAN_50_CAPACITY));

        resultsPanel.updateResults();

        System.out.println(fan50Count);
        return fan50Count;

    }


}
