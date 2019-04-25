package me.bayanov.temperature;

import javax.swing.*;

public class Controller {
    private Converter model;
    private View view;

    private enum SystemLabels {
        CELSIUS ("\u00B0C", 0),
        KELVIN ("\u00B0K", 1),
        FAHRENHEIT ("\u00B0F", 2);

        private final String label;
        private final int index;

        SystemLabels(String label, int index) {
            this.label = label;
            this.index = index;
        }
    }

    public Controller(Converter model, View view) {
        this.model = model;
        this.view = view;
        initView();
    }

    private void initView() {
        for (SystemLabels system : SystemLabels.values()) {
            //noinspection unchecked
            view.getFromList().addItem(system.label);
        }
        for (SystemLabels system : SystemLabels.values()) {
            //noinspection unchecked
            view.getToList().addItem(system.label);
        }

        view.getToList().setSelectedIndex(SystemLabels.KELVIN.index);

        view.getFromField().setText("0");
        view.getToField().setEditable(false);

        updateView();

        view.getConvertButton().addActionListener(e -> updateView());
    }

    private void updateModel() {
        double fromNumber;
        try {
            fromNumber = Double.parseDouble(view.getFromField().getText());
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Not a number", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.setFromNumber(fromNumber);
        model.setFromSystem(getSystem(view.getFromList()));
        model.setToSystem(getSystem(view.getToList()));

        model.update();
    }

    private void updateView() {
        updateModel();
        view.getFromLabel().setText((String) view.getFromList().getSelectedItem());
        view.getToLabel().setText((String) view.getToList().getSelectedItem());
        view.getToField().setText(String.format ("%.2f", model.getToNumber()));
    }

    private Converter.System getSystem(JComboBox list) {
        switch (list.getSelectedIndex()) {
            case 0:
                return Converter.System.CELSIUS;
            case 1:
                return Converter.System.KELVIN;
            case 2:
                return Converter.System.FAHRENHEIT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
