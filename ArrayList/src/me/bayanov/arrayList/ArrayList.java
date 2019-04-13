package me.bayanov.arrayList;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] data;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        data = (E[]) new Object[10];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be >= 0");
        }

        //noinspection unchecked
        data = (E[]) new Object[capacity];
    }

    private void expand(int length) {
        data = Arrays.copyOf(data, length);
    }

    public void ensureCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be >= 0");
        }

        if (data.length < capacity) {
            expand(capacity);
        }
    }

    public void trimToSize() {
        if (data.length != size) {
            data = Arrays.copyOf(data, size);
        }
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
        return indexOf(o) != -1;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int modCountBefore = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (currentIndex + 1 >= size) {
                throw new NoSuchElementException("No next element. Iterator is on the end");
            }
            if (modCountBefore != modCount) {
                throw new ConcurrentModificationException("ArrayList was modified");
            }

            currentIndex++;
            return data[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Array must be not null");
        }

        if (a.length >= size) {
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(data, 0, a, 0, size);

            if (a.length > size) {
                a[size] = null;
            }

            return a;
        } else {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(data, size, a.getClass());
        }
    }

    @Override
    public boolean add(E e) {
        if (size + 1 > data.length) {
            expand(data.length * 2 + 1);
        }
        data[size] = e;

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);

            size--;
            modCount++;

            return true;
        }

        return false;
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
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and size");
        }

        if (c.size() != 0) {
            ensureCapacity(size + c.size());

            System.arraycopy(data, index, data, index + c.size(), size - index);

            int i = index;
            for (E object : c) {
                data[i] = object;
                i++;
            }

            modCount++;
            size += c.size();

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int modCountBefore = modCount;

        for (Object object : c) {
            remove(object);
        }

        return modCount != modCountBefore;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int modCountBefore = modCount;

        for (int i = 0; i < size; i++) {
            if (!c.contains(data[i])) {
                remove(i);
                i--;
            }
        }

        return modCount != modCountBefore;
    }

    @Override
    public void clear() {
        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and size - 1");
        }

        return data[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and size - 1");
        }

        E beforeSet = data[index];
        data[index] = element;

        return beforeSet;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and size");
        }

        if (size + 1 > data.length) {
            expand(data.length * 2 + 1);
        }

        if (index != size) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }

        data[index] = element;

        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and size - 1");
        }

        E beforeRemove = data[index];

        System.arraycopy(data, index + 1, data, index, size - index - 1);

        size--;
        modCount++;

        return beforeRemove;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, data[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, data[i])) {
                return i;
            }
        }

        return -1;
    }

    //    Not implemented methods:
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
