package me.bayanov.arrayListHomeMain;

import me.bayanov.arrayListHome.NumbersRemovers;
import me.bayanov.arrayListHome.Reader;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> linesFromFile = Reader.linesFromFile("test.txt");
            System.out.println("linesFromFile: " + linesFromFile);
        } catch (FileNotFoundException exception) {
            System.out.println("File not found: " + exception);
        }

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 4, 5));
        System.out.println("numbers = " + numbers);

        NumbersRemovers.removeEvenNumbers(numbers);
        System.out.println("removed even in numbers = " + numbers);

        System.out.println("removed duplicates in numbers = " + NumbersRemovers.removeDuplicates(numbers));
    }
}
