package me.bayanov.temperatureMain;

import me.bayanov.temperature.Controller;
import me.bayanov.temperature.Model;
import me.bayanov.temperature.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new Model(), new View());
    }
}
