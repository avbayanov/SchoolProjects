package me.bayanov.temperature.Converter;

public class KelvinConverter implements Converter {
    private static final double difference = 273.15;

    @Override
    public double convertFromCelsius(double fromNumber) {
        return fromNumber + difference;
    }

    @Override
    public double convertToCelsius(double fromNumber) {
        return fromNumber - difference;
    }
}
