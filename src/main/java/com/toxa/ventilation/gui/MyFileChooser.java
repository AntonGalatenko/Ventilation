package com.toxa.ventilation.gui;

import com.toxa.ventilation.BaseInfo;

import javax.swing.*;
import java.io.File;

public class MyFileChooser extends JFrame {

    private String path;
    BaseInfo baseInfo;

    public MyFileChooser(){
        baseInfo = BaseInfo.getInstance();
        JFileChooser chooser = new JFileChooser();

//        if(baseInfo.getPathFile() != null)
        chooser.setCurrentDirectory(new File(baseInfo.getFilePathText()));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(this);

        if(chooser.getSelectedFile().getPath() != null)
            path = chooser.getSelectedFile().getPath();

//        System.out.println("MyFileChooser " + path);

//        setPreferredSize(new Dimension(500, 200));
//        pack();
//        setVisible(true);
    }

    public String getPath(){
        return path;
    }
}
