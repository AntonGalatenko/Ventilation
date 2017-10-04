package com.toxa.ventilation.gui;

import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.Count;
import com.toxa.ventilation.Data.DataOfEquipment;
import com.toxa.ventilation.CompareDBandFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MyMainPanel extends JFrame{
    private JPanel mainPanel;
    private TaskPanel taskPanel;
    private ResultsPanel resultPanel;
    private MyToolBar toolBar;

    public MyMainPanel(double  versionNumber){
        new CompareDBandFiles().start();

        setTitle("Вентиляция ver" + versionNumber);

        BaseInfo baseInfo = BaseInfo.getInstance();
        Count count = new Count();

        toolBar = new MyToolBar();

        taskPanel = new TaskPanel();
        taskPanel.setCount(count);
        taskPanel.setMyMainPanel(this);

        resultPanel = new ResultsPanel(count);
        count.setResultsPanel(resultPanel);
        baseInfo.setResultsPanel(resultPanel);
        resultPanel.setTunnel();
        resultPanel.setVisible(false);

        mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(toolBar, BorderLayout.NORTH);
        mainPanel.add(taskPanel, BorderLayout.WEST);
        mainPanel.add(resultPanel, BorderLayout.EAST);

        add(mainPanel);

        setResizable(false);
        setLocation(5, 5);

        setVisible(true);
        pack();

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                DataOfEquipment dataOfEquipment = taskPanel.getDataOfEquipment();

                FileOutputStream fos;
                ObjectOutputStream oos;
                try {
                    fos = new FileOutputStream("save_ventilation");
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(dataOfEquipment);
                    oos.flush();
                    oos.close();
                } catch (FileNotFoundException er) {
                    er.printStackTrace();
                } catch (IOException er) {
                    er.printStackTrace();
                }

                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

    }

}
