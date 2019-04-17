package me.bayanov.hashTable;

public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Pair(Pair<K, V> pair) {
        this.key = pair.getKey();
        this.value = pair.getValue();
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{ " + key + " : " + value + " }";
    }
}
