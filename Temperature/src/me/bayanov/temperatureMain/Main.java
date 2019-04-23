package me.bayanov.temperatureMain;

import me.bayanov.temperature.Controller;
import me.bayanov.temperature.Converter;
import me.bayanov.temperature.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new Converter(), new View());
    }
}
