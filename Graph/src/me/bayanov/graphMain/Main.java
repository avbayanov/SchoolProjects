package me.bayanov.graphMain;

import me.bayanov.graph.Graph;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] dataInt = new int[]{1, 2, 3, 4, 5, 6, 7};
        Integer[] data = Arrays.stream(dataInt)
                .boxed()
                .toArray(Integer[]::new);

        boolean[][] connections = new boolean[][]{
                {false, false, false, false, false, false, false},
                {false, false, true, true, false, true, false},
                {false, true, false, false, false, false, true},
                {false, true, false, false, false, false, false},
                {false, false, false, false, false, true, false},
                {false, true, false, false, true, false, true},
                {false, false, true, false, false, true, false}
        };

        Graph<Integer> graph = new Graph<>(data, connections);
        graph.breadthTraversal(System.out::println);
    }
}
