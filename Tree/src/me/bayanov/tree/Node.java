package me.bayanov.tree;

class Node<E> {
    private Node<E> left;
    private Node<E> right;
    private E data;

    Node(Node<E> left, Node<E> right, E data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    Node(E data) {
        this.data = data;
    }

    boolean hasLeft() {
        return left != null;
    }

    boolean hasRight() {
        return right != null;
    }

    boolean hasOnlyLeft() {
        return left != null && right == null;
    }

    boolean hasOnlyRight() {
        return right != null && left == null;
    }

    boolean isLeaf() {
        return left == null && right == null;
    }

    void setLeft(Node<E> left) {
        this.left = left;
    }

    void setRight(Node<E> right) {
        this.right = right;
    }

    Node<E> getLeft() {
        return left;
    }

    Node<E> getRight() {
        return right;
    }

    E getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{ " + data + " : left = " + (left != null ? left.data : "null")
                + ", right = " + (right != null ? right.data : "null") + " }";
    }
}
