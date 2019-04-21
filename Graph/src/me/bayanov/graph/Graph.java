package me.bayanov.graph;

import java.util.LinkedList;
import java.util.function.Consumer;

public class Graph<E> {
    private E[] data;
    private boolean[][] connections;

    public Graph(E[] data, boolean[][] connections) {
        if (data == null) {
            throw new NullPointerException("Data must be not null");
        }
        if (connections == null) {
            throw new NullPointerException("Connections must be not null");
        }

        if (data.length == 0) {
            throw new IllegalArgumentException("Data length must be > 0");
        }
        if (connections.length == 0) {
            throw new IllegalArgumentException("Data length must be > 0");
        }

        if (data.length != connections.length) {
            throw new IllegalArgumentException("Data.length must be = connections.length");
        }

        for (boolean[] connectionsByNode : connections) {
            if (connections.length != connectionsByNode.length) {
                throw new IllegalArgumentException("Connections matrix must be square (Each subarray equals parent array)");
            }
        }

        this.data = data;
        this.connections = connections;
    }

    private boolean[] breadthTraversal(Consumer<E> consumer, int node, boolean[] usedNodes) {
        LinkedList<Integer> nodes = new LinkedList<>();
        nodes.add(node);

        while (!nodes.isEmpty()) {
            int current = nodes.remove();
            if (!usedNodes[current]) {
                consumer.accept(data[current]);
                usedNodes[current] = true;

                for (int i = 0; i < connections[current].length; i++) {
                    if (connections[current][i]) {
                        nodes.add(i);
                    }
                }
            }
        }

        return usedNodes;
    }

    public void breadthTraversal(Consumer<E> consumer) {
        boolean[] usedNodes = new boolean[data.length];

        for (int i = 0; i < usedNodes.length; i++) {
            if (!usedNodes[i]) {
                usedNodes = breadthTraversal(consumer, i, usedNodes);
            }
        }
    }
}