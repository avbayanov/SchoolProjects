package me.bayanov.temperature.Converter;

public class CelsiusConverter implements Converter {
    @Override
    public double convertFromCelsius(double fromNumber) {
        return fromNumber;
    }

    @Override
    public double convertToCelsius(double fromNumber) {
        return fromNumber;
    }
}
