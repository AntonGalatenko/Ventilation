package com.toxa.ventilation.gui;

import com.toxa.ventilation.BaseInfo;

import javax.swing.*;
import java.io.File;

public class MyFileChooser extends JFrame {

    private static String path;

    public MyFileChooser(){
        JFileChooser chooser = new JFileChooser();

        if(BaseInfo.getPathFile() != null)
            chooser.setCurrentDirectory(new File(BaseInfo.getPathFile()));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(this);

        if(chooser.getSelectedFile().getPath() != null)
            path = chooser.getSelectedFile().getPath();

//        setPreferredSize(new Dimension(500, 200));
//        pack();
//        setVisible(true);
    }

    public static String getPath(){
        return path;
    }
}
