package me.bayanov.treeMain;

import me.bayanov.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        tree.add(8);
        tree.add(3);
        tree.add(10);
        tree.add(1);
        tree.add(6);
        tree.add(14);
        tree.add(4);
        tree.add(7);
        tree.add(13);

        System.out.println(tree.remove(6));
        System.out.println(tree.find(4));

        tree.breadthTraversal(System.out::println);
        tree.depthTraversalWithStack(System.out::println);
        tree.depthTraversalWithRecursion(System.out::println);
    }
}
