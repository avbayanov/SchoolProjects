package me.bayanov.listMain;

import me.bayanov.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new List<>();
        numbers.insertBeforeAll(4);
        numbers.insertBeforeAll(3);
        numbers.insertBeforeAll(2);
        numbers.insertBeforeAll(1);

        System.out.println(numbers.set(0, 5));
        System.out.println(numbers.remove(3));
        numbers.insertTo(numbers.getSize() - 1, 500);
        System.out.println(numbers.removeByData(5));
        System.out.println(numbers.removeFirst());

        System.out.println();
        for (int i = 0; i < numbers.getSize(); i++) {
            System.out.println(numbers.get(i));
        }
        System.out.println();

        List<Integer> reversedNumbers = numbers.getCopy();
        reversedNumbers.reverse();

        System.out.println("Non-reversed numbers:");
        for (int i = 0; i < numbers.getSize(); i++) {
            System.out.println(numbers.get(i));
        }

        System.out.println("Reversed numbers:");
        for (int i = 0; i < reversedNumbers.getSize(); i++) {
            System.out.println(reversedNumbers.get(i));
        }
    }
}
