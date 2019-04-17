package me.bayanov.hashTable;

import java.util.*;

public class HashTable<K, V> implements Collection {
    private List[] data;
    private int size;
    private int modCount;

    public HashTable() {
        data = new LinkedList[100];
    }

    public HashTable(int length) {
        data = new LinkedList[length];
    }

    public V get(K key) {
        int address = getAddress(key);

        if (data[address] == null) {
            return null;
        }

        for (Object object : data[address]) {
            Pair<K, V> pair = (Pair<K, V>) object;
            if (pair.getKey() == key) {
                return pair.getValue();
            }
        }

        return null;
    }

    private int getAddress(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    private ListIterator getListIteratorByKey(K key) {
        int address = getAddress(key);

        if (data[address] == null) {
            return null;
        }

        for (ListIterator i = data[address].listIterator(); i.hasNext(); ) {
            Pair<K, V> pair = (Pair<K, V>) i.next();
            if (pair.getKey() == key) {
                return i;
            }
        }

        return null;
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
        return getListIteratorByKey((K) o) != null;
    }

    private class HashTableIterator implements Iterator {
        private int currentAddress;
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
        public Object next() {
            if (modCountBefore != modCount) {
                throw new ConcurrentModificationException("HashTable was modified");
            }

            if (currentListIterator == null) {
                nextListIterator();
            }

            if (currentListIterator.hasNext()) {
                return currentListIterator.next();
            }

            nextListIterator();
            return currentListIterator.next();
        }
    }

    @Override
    public Iterator iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];

        int resultCount = 0;
        for (List list : data) {
            if (list != null) {
                for (Object pair : list) {
                    result[resultCount] = new Pair<>((Pair<K, V>) pair);
                    resultCount++;
                }
            }
        }

        return result;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a == null) {
            throw new NullPointerException("Array must be not null");
        }

        if (a.length >= size) {
            int resultCount = 0;
            for (List list : data) {
                if (list != null) {
                    for (Object pair : list) {
                        a[resultCount] = new Pair<>((Pair<K, V>) pair);
                        resultCount++;
                    }
                }
            }

            if (a.length > size) {
                a[size] = null;
            }

            return a;
        }

        return toArray();
    }

    @Override
    public boolean add(Object o) {
        if (o == null) {
            throw new NullPointerException("Pair must be not null");
        }

        Pair<K, V> pair = (Pair<K, V>) o;
        int address = getAddress(pair.getKey());

        if (data[address] == null) {
            data[address] = new LinkedList<Pair<K, V>>();
        } else {
            ListIterator existedPair = getListIteratorByKey(pair.getKey());
            if (existedPair != null) {
                existedPair.set(pair);

                modCount++;
                return true;
            }
        }

        data[address].add(pair);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        ListIterator existedPair = getListIteratorByKey((K) o);

        if (existedPair != null) {
            existedPair.remove();

            if (!existedPair.hasNext()) {
                data[getAddress((K) o)] = null;
            }

            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object object : c) {
            add(object);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        int modCountBefore = modCount;

        for (Object object : c) {
            while (contains(object)) {
                remove(object);
            }
        }

        return modCount != modCountBefore;
    }

    @Override
    public boolean retainAll(Collection c) {
        int modCountBefore = modCount;

        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {

                for (ListIterator iterator = data[i].listIterator(); iterator.hasNext(); ) {
                    Pair<K, V> currentPair = (Pair<K, V>) iterator.next();
                    if (!c.contains(currentPair.getKey())) {
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
