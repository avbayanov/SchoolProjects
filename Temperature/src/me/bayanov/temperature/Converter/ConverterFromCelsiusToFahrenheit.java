package me.bayanov.temperature.Converter;

public class ConverterFromCelsiusToFahrenheit implements Converter {
    private static final double toFahrenheitMultiplier = 9F / 5;
    private static final double celsiusFahrenheitDifference = 32;

    @Override
    public double convert(double fromNumber) {
        return fromNumber * toFahrenheitMultiplier + celsiusFahrenheitDifference;
    }
}
