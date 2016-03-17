package com.toxa.ventilation;


import com.toxa.ventilation.gui.MainPanel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        setNimbusLookAndFeel();

        new MainPanel();
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
}
