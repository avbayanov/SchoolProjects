package me.bayanov.temperature.Converter;

public class ConverterFromFahrenheitToCelsius implements Converter {
    private static final double celsiusFahrenheitDifference = 32;
    private static final double fromFahrenheitMultiplier = 5F / 9;

    @Override
    public double convert(double fromNumber) {
        return (fromNumber - celsiusFahrenheitDifference) * fromFahrenheitMultiplier;
    }
}
