package com.toxa.ventilation.gui;

import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends JFrame{
    private JPanel mainPanel;
    private JProgressBar progressBar;
    private JLabel loadingLabel;

    public LoadingPanel() {
        add(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setPreferredSize(new Dimension(200, 100));
        pack();
        progressBar.setIndeterminate(true);
    }
}
