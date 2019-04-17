package me.bayanov.hashTableMain;

import me.bayanov.hashTable.HashTable;
import me.bayanov.hashTable.Pair;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer, String> hashTable = new HashTable<>();
        hashTable.add(new Pair<>(1, "First"));
        hashTable.add(new Pair<>(1, "2First"));
        hashTable.add(new Pair<>(2, "Second"));
        hashTable.add(new Pair<>(3, "Third"));

        hashTable.remove(1);
        System.out.println(hashTable.containsAll(Arrays.asList(1, 3)));
        hashTable.retainAll(Arrays.asList(1, 2));

        Object[] pairs = hashTable.toArray();

        for (Object pair : hashTable) {
            System.out.println(pair);
        }

        System.out.println(hashTable.get(2));
    }
}
