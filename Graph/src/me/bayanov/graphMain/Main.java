package me.bayanov.graphMain;

import me.bayanov.graph.Graph;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] data = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7})
                .boxed()
                .toArray(Integer[]::new);

        boolean[][] connections = new boolean[][]{
                {false, true, false, false, false, false, false},
                {true, false, true, true, true, true, false},
                {false, true, false, false, false, false, true},
                {false, true, false, false, false, false, false},
                {false, true, false, false, false, true, false},
                {false, true, false, false, true, false, true},
                {false, false, true, false, false, true, false}
        };

        Graph<Integer> graph = new Graph<>(data, connections);
        graph.breadthTraversal(System.out::println);
        graph.depthTraversalWithStack(System.out::println);
        graph.depthTraversalWithRecursion(System.out::println);
    }
}
