package me.bayanov.temperature.Converter;

public class FahrenheitConverter implements Converter {
    private static final double multiplierTo = 9F / 5;
    private static final double multiplierFrom = 5F / 9;
    private static final double difference = 32;

    @Override
    public double convertFromCelsius(double fromNumber) {
        return fromNumber * multiplierTo + difference;
    }

    @Override
    public double convertToCelsius(double fromNumber) {
        return (fromNumber - difference) * multiplierFrom ;
    }
}
