package me.bayanov.arrayListHome;

import java.util.ArrayList;

public class NumbersRemovers {
    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
    }

    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> numbers) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer number : numbers) {
            if (!result.contains(number)) {
                result.add(number);
            }
        }

        return result;
    }
}
