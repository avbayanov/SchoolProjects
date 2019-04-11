package me.bayanov.list;

import java.lang.reflect.Array;

public class List<T> {
    private Node<T> head;
    private int size;

    public List() {
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        return head.getData();
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and list size (" + size + ")");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    public T set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and list size (" + size + ")");
        }

        T dataBeforeChange;

        if (index != 0) {
            Node<T> previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.getNext();
            }

            Node<T> current = previous.getNext();
            dataBeforeChange = current.getData();

            Node<T> next = current.getNext();

            previous.setNext(new Node<>(data, next));
        } else {
            dataBeforeChange = head.getData();
            Node<T> next = head.getNext();

            head = new Node<>(data, next);
        }

        return dataBeforeChange;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and list size (" + size + ")");
        }

        T dataBeforeRemove;

        if (index != 0) {
            Node<T> previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.getNext();
            }

            Node<T> current = previous.getNext();
            dataBeforeRemove = current.getData();

            Node<T> next = current.getNext();

            previous.setNext(next);
        } else {
            dataBeforeRemove = head.getData();

            head = head.getNext();
        }

        size--;

        return dataBeforeRemove;
    }

    public void insertBeforeAll(T data) {
        Node<T> currentFirst = head;
        head = new Node<>(data, currentFirst);

        size++;
    }

    public void insertTo(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and list size (" + size + ")");
        }

        if (index != 0) {
            Node<T> previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.getNext();
            }

            Node<T> current = previous.getNext();

            previous.setNext(new Node<>(data, current));
        } else {
            head = new Node<>(data, head.getNext());
        }

        size++;
    }

    public boolean removeByData(T data) {
        for (Node<T> current = head, previous = null; current != null;
             previous = current, current = current.getNext()) {

            if (data.equals(current.getData())) {
                if (previous != null) {
                    previous.setNext(current.getNext());
                } else {
                    head = current.getNext();
                }

                size--;
                return true;
            }
        }

        return false;
    }

    public T removeFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Size is 0. No first element");
        }

        T data = head.getData();
        head = head.getNext();

        size--;
        return data;
    }

    public void reverse() {
        @SuppressWarnings("unchecked")
        Node<T>[] nodes = (Node<T>[]) Array.newInstance(head.getClass(), getSize());

        int i = 0;
        for (Node<T> current = head; current != null; current = current.getNext()) {
            nodes[i] = current;
            i++;
        }

        for (int j = getSize() - 1; j > 0; j--) {
            nodes[j].setNext(nodes[j - 1]);
        }

        nodes[0].setNext(null);

        head = nodes[getSize() - 1];
    }

    public List<T> getCopy() {
        List<T> result = new List<>();

        @SuppressWarnings("unchecked")
        Node<T>[] nodes = (Node<T>[]) Array.newInstance(head.getClass(), getSize());

        int i = 0;
        for (Node<T> current = head; current != null; current = current.getNext()) {
            nodes[i] = current;
            i++;
        }

        result.insertBeforeAll(nodes[getSize() - 1].getData());

        for (int j = getSize() - 2; j >= 0; j--) {
            result.insertBeforeAll(nodes[j].getData());
        }

        return result;
    }
}