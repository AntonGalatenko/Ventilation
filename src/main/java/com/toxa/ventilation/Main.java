package com.toxa.ventilation;


import com.toxa.ventilation.gui.MyMainPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {
    private static MyMainPanel mainPanel;
    private static final double versionNumber = 1.881;

    public static void main(String[] args) {

        setNimbusLookAndFeel();

        setLogFile();

        mainPanel = new MyMainPanel(versionNumber);
    }

    public static Point getMainPanelLocation(){
        return mainPanel.getLocation();
    }

    private static void setLogFile(){
        try {
            System.setErr(new PrintStream(new File("log_err.txt")));
            System.setOut(new PrintStream(new File("log_out.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setNimbusLookAndFeel(){

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public double getVersionNumber() {
        return versionNumber;
    }
}
