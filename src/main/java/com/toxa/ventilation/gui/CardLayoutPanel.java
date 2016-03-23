package com.toxa.ventilation.gui;

import javax.swing.*;
import java.awt.*;

public class CardLayoutPanel extends JPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;
//    private JButton button;
//    private JFrame jFrame;

    public CardLayoutPanel(){
//        jFrame = new JFrame();

        mainPanel = new JPanel();
        cardLayout = new CardLayout();

        mainPanel.setLayout(cardLayout);

        mainPanel.add(new ResultsPanel(), "0");

        cardLayout.show(mainPanel, "0");

//        mainPanel.setSize(3000, 3000);

//        button = new JButton("Click");
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addTest();
//            }
//        });

        add(mainPanel);
//        add(button);

//        jFrame.add(this);
//        jFrame.pack();
//        jFrame.setVisible(true);
//
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        revalidate();
//        repaint();
    }

    public void addPanel(ResultsPanel resultsPanel){
        mainPanel.add(resultsPanel, "1");
        cardLayout.show(mainPanel, "1");
        System.out.println("ok!");
        mainPanel.repaint();
        repaint();
    }

    public void addTest(){
        mainPanel.add(new Label("Hello world"), "1");
        cardLayout.show(mainPanel, "1");
        mainPanel.revalidate();
        mainPanel.repaint();
        revalidate();
        repaint();
    }
}
