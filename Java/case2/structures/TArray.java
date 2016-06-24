package structures;

import java.util.Comparator;

public class TArray<T> {
    private Object[] array;
    private int used = 0;

    public TArray(){}

    public TArray(int size){
        array = new Object[size];
    }

    public int getSize(){
        return used;
    }

    private int getReserved(){
        return array.length;
    }

    public void add(T element){
        add(element, getSize());
    }

    public void add(T element, int index){
        if (!checkIndex(index) && index > getSize()){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (getReserved() == getSize()){
            expand();
        }

        System.arraycopy(array, index, array, index + 1, getSize() - index);

        array[index] = element;
        used++;
    }

    private void expand(){
        int size = getSize();
        Object[] newArray = new Object[(size == 0 ? 1 : size) * 2];
        System.arraycopy(array, 0, newArray, 0, getSize());
        array = newArray;
    }

    @SuppressWarnings("unchecked")
    public T get(int index){
        if (!checkIndex(index)){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return (T)array[index];
    }

    public boolean contains(T element){
        for (int i = 0; i < getSize(); i++){
            if (array[i].equals(element)){
                return true;
            }
        }
        return false;
    }

    public void removeAt(int index){
        if (!checkIndex(index)){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        System.arraycopy(array, index + 1, array, index, getSize() - index);
        used--;

    }

    private boolean checkIndex(int index){
        return index >= 0 && index < getSize();
    }

    public void copyTo(TArray<T> dest, int startIndex, int length){
        for (int i = 0; i < length; i++){
            dest.add(get(i), startIndex + i);
        }
    }

    public void copyTo(TArray<T> dest){
        copyTo(dest, dest.getSize(), getSize());
    }

    public void copyTo(TArray<T> dest, int startIndex){
        copyTo(dest, startIndex, getSize());
    }

    @SuppressWarnings("unchecked")
    public void sort(Comparator<T> comparator){
        int leadIndex;
        T temp;
        for (int i = 0; i < getSize() - 1; i++){
            leadIndex = i;
            for (int j = i + 1; j < getSize(); j++){
                if (comparator.compare(get(leadIndex), get(j)) > 0){
                    leadIndex = j;
                }
            }
            temp = (T)array[i];
            array[i] = array[leadIndex];
            array[leadIndex] = temp;
        }
    }


}
