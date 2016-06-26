package structures.Dictionary;

public class TPair<K, V> {
    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    void setValue(V value){
        this.value = value;
    }

    TPair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}