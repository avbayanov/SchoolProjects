package me.bayanov.temperature;

import me.bayanov.temperature.Converter.*;

import java.util.LinkedHashMap;
import java.util.Set;

public class Model {
    private double fromNumber, toNumber;
    private String fromSystem, toSystem;

    private final LinkedHashMap<String, Converter> converters = new LinkedHashMap<>();

    public Model() {
        converters.put("\u00B0C", new CelsiusConverter());
        converters.put("\u00B0K", new KelvinConverter());
        converters.put("\u00B0F", new FahrenheitConverter());

    }

    void setFromNumber(double fromNumber) {
        this.fromNumber = fromNumber;
    }

    void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }

    void setToSystem(String toSystem) {
        this.toSystem = toSystem;
    }

    double getToNumber() {
        return toNumber;
    }

    Set<String> getConvertersNames() {
        return converters.keySet();
    }

    void update() {
        convert();
    }

    private void convert() {
        double fromInCelsius = converters.get(fromSystem).convertToCelsius(fromNumber);
        toNumber = converters.get(toSystem).convertFromCelsius(fromInCelsius);
    }
}
