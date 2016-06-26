package structures.Dictionary;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class TDictionary<K extends Comparable<K>, V> implements Iterable<TPair<K, V>> {
    @Override
    public Iterator<TPair<K, V>> iterator() {
        return new Iterator<TPair<K, V>>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < used;
            }

            @Override
            public TPair<K, V> next() {
                TPair<K, V> result = values[current];
                current++;
                return result;
            }
        };
    }

    private TPair<K, V>[] values;
    private int used = 0;

    public int getSize() {
        return used;
    }

    private int getReserved() {
        return values.length;
    }

    public boolean isSorted() {
        for (int i = 0; i < getSize() - 1; i++){
            if (values[i].getKey().compareTo(values[i + 1].getKey()) > 0){
                return false;
            }
        }
        return true;
    }

    public V get(K key) {
        return values[indexOf(key)].getValue();
    }

    private int indexOf(K key) {
        for (int i = 0; i < getSize(); i++) {
            if (values[i].getKey().compareTo(key) == 0) {
                return i;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    private TPair<K, V> get(int index) {
        if (!validIndex(index)) {
            throw new NoSuchElementException("Element not found");
        }
        return values[index];
    }

    public void set(K key, V value) {
        int index = indexOf(key);
        values[index].setValue(value);
    }

    private boolean validIndex(int index) {
        return (index >= 0 && index < getSize());
    }

    public void remove(K key) {
        int removingIndex = indexOf(key);
        if (removingIndex < getSize() - 1) {
            System.arraycopy(values, removingIndex + 1, values, removingIndex, getSize() - removingIndex);
        }
        used--;
    }

    public void add(K key, V value) {
        if (getSize() == used) {
            expand();
        }
        values[used] = new TPair<>(key, value);
        used++;
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        TPair<K, V>[] newValues = new TPair[(getSize() == 0 ? 1 : getSize()) * 2];
        if (values != null) {
            System.arraycopy(values, 0, newValues, 0, values.length);
        }
        values = newValues;
    }

    public boolean contains(K key) {
        try {
            get(key);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void sort() {
        int leaderIndex;
        TPair<K, V> temp;
        for (int i = 0; i < getSize() - 1; i++) {
            leaderIndex = i;
            for (int j = i + 1; j < getSize(); j++) {
                if (values[leaderIndex].getKey().compareTo(values[j].getKey()) > 0) {
                    leaderIndex = j;
                }
            }
            temp = values[leaderIndex];
            values[leaderIndex] = values[i];
            values[i] = temp;
        }
    }

    public void clear() {
        used = 0;
    }
}
