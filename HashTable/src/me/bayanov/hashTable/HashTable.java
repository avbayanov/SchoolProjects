package me.bayanov.hashTable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private List<E>[] data;
    private int size;
    private int modCount;

    public HashTable() {
        data = new LinkedList[100];
    }

    public HashTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be > 0");
        }
        data = new LinkedList[length];
    }

    private int getAddress(E element) {
        if (element == null) {
            return 0;
        }
        return Math.abs(element.hashCode() % data.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int address = getAddress((E) o);

        if (data[address] == null) {
            return false;
        }

        for (Object object : data[address]) {
            if (Objects.equals(o, object)) {
                return true;
            }
        }

        return false;
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentAddress = -1;
        private ListIterator currentListIterator;
        private int modCountBefore = modCount;

        private int getNextAddress() {
            for (int i = currentAddress + 1; i < data.length; i++) {
                if (data[i] != null) {
                    return i;
                }
            }
            return -1;
        }

        private void nextListIterator() {
            currentAddress = getNextAddress();
            if (currentAddress == -1) {
                throw new NoSuchElementException("No next element. Iterator is on the end");
            }

            currentListIterator = data[currentAddress].listIterator();
        }

        @Override
        public boolean hasNext() {
            if (currentListIterator == null || !currentListIterator.hasNext()) {
                return getNextAddress() != -1;
            }

            return true;
        }

        @Override
        public E next() {
            if (modCountBefore != modCount) {
                throw new ConcurrentModificationException("HashTable was modified");
            }

            if (currentListIterator == null) {
                nextListIterator();
            }

            if (currentListIterator.hasNext()) {
                return (E) currentListIterator.next();
            }

            nextListIterator();
            return (E) currentListIterator.next();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];

        int resultCount = 0;
        for (List list : data) {
            if (list != null) {
                System.arraycopy(list.toArray(), 0, result, resultCount, list.size());
                resultCount += list.size();
            }
        }

        return result;
    }


    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Array must be not null");
        }

        if (a.length >= size) {
            int resultCount = 0;
            for (List list : data) {
                if (list != null) {
                    System.arraycopy(list.toArray(), 0, a, resultCount, list.size());
                    resultCount += list.size();
                }
            }

            if (a.length > size) {
                a[size] = null;
            }

            return a;
        }

        return (T[]) toArray();
    }

    @Override
    public boolean add(E e) {
        int address = getAddress(e);

        if (data[address] == null) {
            data[address] = new LinkedList<E>();
        }

        data[address].add(e);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int address = getAddress((E) o);

        if (data[address] == null) {
            return false;
        }

        boolean result = data[address].remove(o);

        if (result) {
            size--;
            modCount++;
        }

        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (Object object : c) {
            add((E) object);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int modCountBefore = modCount;

        for (Object object : c) {
            while (contains(object)) {
                remove(object);
            }
        }

        return modCount != modCountBefore;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int modCountBefore = modCount;

        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {

                for (ListIterator iterator = data[i].listIterator(); iterator.hasNext(); ) {
                    E currentElement = (E) iterator.next();
                    if (!c.contains(currentElement)) {
                        iterator.remove();

                        if (!iterator.hasNext()) {
                            data[i] = null;
                        }

                        size--;
                        modCount++;
                    }
                }
            }
        }

        return modCount != modCountBefore;
    }

    @Override
    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }

        size = 0;
        modCount++;
    }
}
