package me.bayanov.hashTableMain;

import me.bayanov.hashTable.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();
        hashTable.add(null);
        hashTable.add(2);
        hashTable.add(3);
        hashTable.add(4);

        hashTable.remove(1);
        System.out.println(hashTable.containsAll(Arrays.asList(1, 3)));
        hashTable.addAll(Arrays.asList(4, 5));
        hashTable.removeAll(Arrays.asList(5, 6));
//        hashTable.retainAll(Arrays.asList(1, 2, 3));

        Object[] objects = hashTable.toArray(new Object[10]);

        for (Object object : hashTable) {
            System.out.println(object);
        }
    }
}
