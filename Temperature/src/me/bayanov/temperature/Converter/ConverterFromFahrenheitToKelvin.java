package me.bayanov.temperature.Converter;

public class ConverterFromFahrenheitToKelvin implements Converter {
    private static final double fromFahrenheitMultiplier = 5F / 9;
    private static final double kelvinFahrenheitDifference = 459.67;

    @Override
    public double convert(double fromNumber) {
        return (fromNumber + kelvinFahrenheitDifference) * fromFahrenheitMultiplier;
    }
}
