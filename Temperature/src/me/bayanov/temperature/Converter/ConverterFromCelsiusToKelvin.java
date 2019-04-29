package me.bayanov.temperature.Converter;

public class ConverterFromCelsiusToKelvin implements Converter {
    private static final double celsiusKelvinDifference = 273.15;

    @Override
    public double convert(double fromNumber) {
        return fromNumber + celsiusKelvinDifference;
    }
}
