package me.bayanov.temperature;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JComboBox fromList;
    private JComboBox toList;
    private JTextField fromField;
    private JTextField toField;
    private JButton convertButton;
    private JLabel fromLabel;
    private JLabel toLabel;

    public View() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not set native look",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }

        frame = new JFrame("Temperature converter");
        frame.setSize(235, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout layout = new GridLayout(3, 1);
        frame.setLayout(layout);

        drawTopPanel();
        drawCenterPanel();

        JPanel bottomPanel = new JPanel();
        convertButton = new JButton("Convert");
        bottomPanel.add(convertButton);

        frame.add(topPanel);
        frame.add(centerPanel);
        frame.add(bottomPanel);

        frame.setVisible(true);
    }

    private void drawTopPanel() {
        topPanel = new JPanel(new GridLayout(0, 2));

        JPanel fromPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JLabel fromLabel = new JLabel("from:");
        fromList = new JComboBox();

        fromPanel.add(fromLabel);
        fromPanel.add(fromList);

        JPanel toPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        JLabel toLabel = new JLabel("to:");
        toList = new JComboBox();

        toPanel.add(toLabel);
        toPanel.add(toList);

        topPanel.add(fromPanel);
        topPanel.add(toPanel);
    }

    private void drawCenterPanel() {
        centerPanel = new JPanel();

        fromField = new JTextField(5);
        fromLabel = new JLabel();

        JLabel equals = new JLabel("=");

        toField = new JTextField(5);
        toLabel = new JLabel();

        centerPanel.add(fromField);
        centerPanel.add(fromLabel);
        centerPanel.add(equals);
        centerPanel.add(toField);
        centerPanel.add(toLabel);
    }

    JComboBox getFromList() {
        return fromList;
    }

    JComboBox getToList() {
        return toList;
    }

    JTextField getFromField() {
        return fromField;
    }

    JTextField getToField() {
        return toField;
    }

    JButton getConvertButton() {
        return convertButton;
    }

    JLabel getFromLabel() {
        return fromLabel;
    }

    JLabel getToLabel() {
        return toLabel;
    }
}
