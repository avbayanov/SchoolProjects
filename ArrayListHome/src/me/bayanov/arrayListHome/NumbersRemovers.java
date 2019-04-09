package me.bayanov.arrayListHome;

import java.util.ArrayList;
import java.util.function.Predicate;

public class NumbersRemovers {
    private static Predicate<Integer> isEven() {
        return p -> p % 2 == 0;
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        numbers.removeIf(isEven());
    }

    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> numbers) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer number : numbers) {
            if (result.indexOf(number) == -1) {
                result.add(number);
            }
        }

        return result;
    }
}
