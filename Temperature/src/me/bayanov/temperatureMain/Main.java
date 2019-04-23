package me.bayanov.temperatureMain;

import me.bayanov.temperature.Controller;
import me.bayanov.temperature.Converter;
import me.bayanov.temperature.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not set native look",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }

        Controller controller = new Controller(new Converter(), new View());
    }
}
