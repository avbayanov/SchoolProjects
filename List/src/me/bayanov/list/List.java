package me.bayanov.list;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.Objects;

public class List<T> {
    private Node<T> head;
    private int size;

    public List() {
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("No first element. List does not contain anything");
        }

        return head.getData();
    }

    private Node<T> getNodeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and list size (" + size + ")");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and list size (" + size + ")");
        }

        return getNodeByIndex(index).getData();
    }

    public T set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and list size (" + size + ")");
        }

        Node<T> current = getNodeByIndex(index);
        T dataBeforeChange = current.getData();

        current.setData(data);

        return dataBeforeChange;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and list size (" + size + ")");
        }

        T dataBeforeRemove;

        if (index != 0) {
            Node<T> previous = getNodeByIndex(index - 1);

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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and list size (" + size + ")");
        }

        if (index != 0) {
            Node<T> previous = getNodeByIndex(index - 1);

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

            if (Objects.equals(current.getData(), data)) {
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
            throw new NoSuchElementException("No first element. List does not contain anything");
        }

        T data = head.getData();
        head = head.getNext();

        size--;
        return data;
    }

    public void reverse() {
        Node<T> current = head;

        for (Node<T> next, previous = null; ; previous = current, current = next) {
            next = current.getNext();

            current.setNext(previous);

            if (next == null) {
                break;
            }
        }

        head = current;
    }

    public List<T> getCopy() {
        List<T> result = new List<>();
        result.size = size;

        if (size == 0) {
            return result;
        }

        result.head = new Node<>(head.getData(), null);

        for (Node<T> current = head.getNext(), next, resultCurrent, resultPrevious = result.head; current != null;
             current = next, resultPrevious = resultCurrent) {

            next = current.getNext();

            resultCurrent = new Node<>(current.getData(), null);
            resultPrevious.setNext(resultCurrent);
        }

        return result;
    }
}
