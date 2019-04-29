package me.bayanov.temperature.Converter;

public class ConverterFromKelvinToFahrenheit implements Converter {
    private static final double toFahrenheitMultiplier = 9F / 5;
    private static final double kelvinFahrenheitDifference = 459.67;

    @Override
    public double convert(double fromNumber) {
        return fromNumber * toFahrenheitMultiplier - kelvinFahrenheitDifference;
    }
}
