package me.bayanov.arrayListMain;

import me.bayanov.arrayList.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(15);

        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        Integer[] integers = new Integer[arrayList.size()];
        integers = arrayList.toArray(integers);

        arrayList.trimToSize();
        arrayList.add(1, 5);
        arrayList.remove((Object) 0);
        arrayList.add(4, 1);
        arrayList.add(8);

        arrayList.ensureCapacity(20);
        arrayList.trimToSize();

        System.out.println();

        for (Integer i : arrayList) {
            System.out.println(i);
        }

        System.out.println();

        ArrayList<Integer> arrayList1 = new ArrayList<>();
        for (Integer i : integers) {
            System.out.println(i);
            arrayList1.add(i);
        }

        System.out.println(arrayList.addAll(arrayList1));

        System.out.println();

        for (Integer i : arrayList) {
            System.out.println(i);
        }

    }
}
