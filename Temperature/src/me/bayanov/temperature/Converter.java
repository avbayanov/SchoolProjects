package me.bayanov.temperature;

public class Converter {
    public enum Systems {
        CELSIUS, KELVIN, FAHRENHEIT;

        public static Systems getByIndex(int index) {
            switch (index) {
                case 0:
                    return CELSIUS;
                case 1:
                    return KELVIN;
                case 2:
                    return FAHRENHEIT;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    private static double toFahrenheitMultiplier = 9F / 5;
    private static double celsiusKelvinDifference = 273.15;
    private static double celsiusFahrenheitDifference = 32;
    private static double kelvinFahrenheitDifference = 459.67;

    private double fromNumber, toNumber;
    private Systems fromSystem, toSystem;

    public Converter() {
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
        switch (fromSystem) {
            case CELSIUS:
                convertFromCelsius();
                return;
            case KELVIN:
                convertFromKelvin();
                return;
            case FAHRENHEIT:
                convertFromFahrenheit();
                return;
            default:
                throw new IllegalArgumentException("fromSystem must be not null");
        }
    }

    private void convertFromCelsius() {
        switch (toSystem) {
            case CELSIUS:
                toNumber = fromNumber;
                return;
            case KELVIN:
                toNumber = fromNumber + celsiusKelvinDifference;
                return;
            case FAHRENHEIT:
                toNumber = fromNumber * toFahrenheitMultiplier + celsiusFahrenheitDifference;
                return;
            default:
                throw new IllegalArgumentException("toSystem must be not null");
        }
    }

    private void convertFromKelvin() {
        switch (toSystem) {
            case CELSIUS:
                toNumber = fromNumber - celsiusKelvinDifference;
                return;
            case KELVIN:
                toNumber = fromNumber;
                return;
            case FAHRENHEIT:
                toNumber = fromNumber * toFahrenheitMultiplier - kelvinFahrenheitDifference;
                return;
            default:
                throw new IllegalArgumentException("toSystem must be not null");
        }
    }

    private void convertFromFahrenheit() {
        double fromFahrenheitMultiplier = 5F / 9;

        switch (toSystem) {
            case CELSIUS:
                toNumber = (fromNumber - celsiusFahrenheitDifference) * fromFahrenheitMultiplier;
                return;
            case KELVIN:
                toNumber = (fromNumber + kelvinFahrenheitDifference) * fromFahrenheitMultiplier;
                return;
            case FAHRENHEIT:
                toNumber = fromNumber;
                return;
            default:
                throw new IllegalArgumentException("toSystem must be not null");
        }
    }
}
