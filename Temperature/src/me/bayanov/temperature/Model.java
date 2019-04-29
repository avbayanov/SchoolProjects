package me.bayanov.temperature;

import me.bayanov.temperature.Converter.*;

import java.util.HashMap;

public class Model {
    public enum Systems {
        CELSIUS, KELVIN, FAHRENHEIT;

        public static final Systems[] values = values();

        public static Systems getByIndex(int index) {
            return values[index];
        }
    }

    private double fromNumber, toNumber;
    private Systems fromSystem, toSystem;

    private final HashMap<Systems, HashMap<Systems, Converter>> converters = new HashMap<>();

    public Model() {
        converters.put(Systems.CELSIUS, new HashMap<>());
        converters.get(Systems.CELSIUS).put(Systems.CELSIUS, fromNumber -> fromNumber);
        converters.get(Systems.CELSIUS).put(Systems.KELVIN, new ConverterFromCelsiusToKelvin());
        converters.get(Systems.CELSIUS).put(Systems.FAHRENHEIT, new ConverterFromCelsiusToFahrenheit());

        converters.put(Systems.KELVIN, new HashMap<>());
        converters.get(Systems.KELVIN).put(Systems.CELSIUS, new ConverterFromKelvinToCelsius());
        converters.get(Systems.KELVIN).put(Systems.KELVIN, fromNumber -> fromNumber);
        converters.get(Systems.KELVIN).put(Systems.FAHRENHEIT, new ConverterFromKelvinToFahrenheit());

        converters.put(Systems.FAHRENHEIT, new HashMap<>());
        converters.get(Systems.FAHRENHEIT).put(Systems.CELSIUS, new ConverterFromFahrenheitToCelsius());
        converters.get(Systems.FAHRENHEIT).put(Systems.KELVIN, new ConverterFromFahrenheitToKelvin());
        converters.get(Systems.FAHRENHEIT).put(Systems.FAHRENHEIT, fromNumber -> fromNumber);
    }

    void setFromNumber(double fromNumber) {
        this.fromNumber = fromNumber;
    }

    void setFromSystem(Systems fromSystem) {
        this.fromSystem = fromSystem;
    }

    void setToSystem(Systems toSystem) {
        this.toSystem = toSystem;
    }

    double getToNumber() {
        return toNumber;
    }

    void update() {
        convert();
    }

    private void convert() {
        if (!converters.containsKey(fromSystem) || !converters.get(fromSystem).containsKey(toSystem)) {
            throw new IllegalArgumentException("No such converter: from " + fromSystem + " to: " + toSystem);
        }
        toNumber = converters.get(fromSystem).get(toSystem).convert(fromNumber);
    }
}
