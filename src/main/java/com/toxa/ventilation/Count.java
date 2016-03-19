package com.toxa.ventilation;

import com.toxa.ventilation.gui.TaskPanel;

public class Count {

    private ExtraInfo extraInfo = ExtraInfo.getInstance();
    private TaskPanel taskPanel;

    private int bigFansCount;

    public Count(){
        extraInfo.setCount(this);


    }

    public void setTaskPanel(TaskPanel taskPanel) {
        this.taskPanel = taskPanel;
    }

    public int getBigFansCount(){
        taskPanel.getHeadsNumber() * taskPanel.getAirSummer() / 40
    }


}
