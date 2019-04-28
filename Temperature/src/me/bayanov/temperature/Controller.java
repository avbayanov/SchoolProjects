package me.bayanov.temperature;

import javax.swing.*;

public class Controller {
    private Converter model;
    private View view;

    public Controller(Converter model, View view) {
        this.model = model;
        this.view = view;
        initView();
    }

    private void initView() {
        for (Converter.Systems system : Converter.Systems.values()) {
            //noinspection unchecked
            view.getFromList().addItem("\u00B0" + system.name().charAt(0));
        }
        for (Converter.Systems system : Converter.Systems.values()) {
            //noinspection unchecked
            view.getToList().addItem("\u00B0" + system.name().charAt(0));
        }

        if (view.getToList().getItemCount() > 1) {
            view.getToList().setSelectedIndex(1);
        }

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
        view.getToField().setText(String.format("%.2f", model.getToNumber()));
    }

    private Converter.Systems getSystem(JComboBox list) {
        return Converter.Systems.getByIndex(list.getSelectedIndex());
    }
}
