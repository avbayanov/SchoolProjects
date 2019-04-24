package me.bayanov.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Tree<E> {
    private Node<E> root;
    private int size;
    private Comparator<E> comparator;

    public Tree() {
    }

    public Tree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    private int compare(E first, E second) {
        if (comparator != null) {
            return comparator.compare(first, second);
        }

        if (first == null && second == null) {
            return 0;
        }
        if (second == null) {
            return 1;
        }
        if (first == null) {
            return -1;
        }

        //noinspection unchecked
        return ((Comparable<E>) first).compareTo(second);
    }

    public void add(E element) {
        if (root == null) {
            root = new Node<>(element);

            size++;
            return;
        }

        for (Node<E> current = root; ; ) {
            if (compare(element, current.getData()) < 0) {
                if (current.hasLeft()) {
                    current = current.getLeft();
                } else {
                    current.setLeft(new Node<>(element));
                    break;
                }
            } else {
                if (current.hasRight()) {
                    current = current.getRight();
                } else {
                    current.setRight(new Node<>(element));
                    break;
                }
            }
        }

        size++;
    }

    public boolean find(E element) {
        if (root == null) {
            return false;
        }

        for (Node<E> current = root; ; ) {
            if (compare(element, current.getData()) == 0) {
                return true;
            }

            if (compare(element, current.getData()) < 0) {
                if (current.hasLeft()) {
                    current = current.getLeft();
                } else {
                    return false;
                }
            } else {
                if (current.hasRight()) {
                    current = current.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    private boolean removeCurrentWithBothChildren(Node<E> current, Node<E> previous, boolean isLeftChild) {
        if (!current.getRight().hasLeft()) {
            if (current == root) {
                root = current.getRight();
                root.setLeft(current.getLeft());
                return true;
            }

            if (isLeftChild) {
                previous.setLeft(current.getRight());
            } else {
                previous.setRight(current.getRight());
            }

            current.getRight().setLeft(current.getLeft());

            return true;
        }

        Node<E> mostLeft = current.getRight();
        Node<E> mostLeftPrevious = current.getRight();
        //noinspection StatementWithEmptyBody
        for (; mostLeft.hasLeft(); mostLeftPrevious = mostLeft, mostLeft = mostLeft.getLeft()) {
        }

        mostLeftPrevious.setLeft(mostLeft.getRight());

        mostLeft.setLeft(current.getLeft());
        mostLeft.setRight(current.getRight());

        if (current == root) {
            root = mostLeft;
            return true;
        }

        if (isLeftChild) {
            previous.setLeft(mostLeft);
        } else {
            previous.setRight(mostLeft);
        }

        return true;
    }

    private boolean removeCurrent(Node<E> current, Node<E> previous, boolean isLeftChild) {
        if (current.isLeaf()) {
            if (current == root) {
                root = null;
                return true;
            }

            if (isLeftChild) {
                previous.setLeft(null);
            } else {
                previous.setRight(null);
            }

            return true;
        }

        if (current.hasOnlyLeft()) {
            if (current == root) {
                root = current.getLeft();
                return true;
            }

            if (isLeftChild) {
                previous.setLeft(current.getLeft());
            } else {
                previous.setRight(current.getLeft());
            }

            return true;
        }

        if (current.hasOnlyRight()) {
            if (current == root) {
                root = current.getRight();
                return true;
            }

            if (isLeftChild) {
                previous.setLeft(current.getRight());
            } else {
                previous.setRight(current.getRight());
            }

            return true;
        }

        return removeCurrentWithBothChildren(current, previous, isLeftChild);
    }

    public boolean remove(E element) {
        if (root == null) {
            return false;
        }

        boolean isLeftChild;
        for (Node<E> current = root, previous; ; ) {
            if (compare(element, current.getData()) < 0) {
                if (current.hasLeft()) {
                    previous = current;
                    current = current.getLeft();
                    isLeftChild = true;
                } else {
                    return false;
                }
            } else {
                if (current.hasRight()) {
                    previous = current;
                    current = current.getRight();
                    isLeftChild = false;
                } else {
                    return false;
                }
            }

            if (compare(element, current.getData()) == 0) {
                size--;
                return removeCurrent(current, previous, isLeftChild);
            }
        }
    }

    public void breadthTraversal(Consumer<E> consumer) {
        LinkedList<Node<E>> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node<E> current = nodes.remove();
            consumer.accept(current.getData());

            if (current.hasLeft()) {
                nodes.add(current.getLeft());
            }

            if (current.hasRight()) {
                nodes.add(current.getRight());
            }
        }
    }

    public void depthTraversalWithStack(Consumer<E> consumer) {
        LinkedList<Node<E>> nodes = new LinkedList<>();
        nodes.push(root);

        while (!nodes.isEmpty()) {
            Node<E> current = nodes.pop();
            consumer.accept(current.getData());

            if (current.hasRight()) {
                nodes.push(current.getRight());
            }

            if (current.hasLeft()) {
                nodes.push(current.getLeft());
            }
        }
    }

    private void depthTraversalWithRecursion(Consumer<E> consumer, Node<E> node) {
        consumer.accept(node.getData());

        if (node.hasLeft()) {
            depthTraversalWithRecursion(consumer, node.getLeft());
        }

        if (node.hasRight()) {
            depthTraversalWithRecursion(consumer, node.getRight());
        }
    }

    public void depthTraversalWithRecursion(Consumer<E> consumer) {
        depthTraversalWithRecursion(consumer, root);
    }
}
