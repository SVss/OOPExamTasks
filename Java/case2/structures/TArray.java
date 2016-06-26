package structures;

import java.util.Comparator;

public class TArray<T extends Comparable> {
    private enum ActionType{
        AT_ADD,
        AT_ACCESS
    }

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
        return array != null ? array.length : 0;
    }

    public void add(T element){
        add(element, getSize());
    }

    public void add(T element, int index){
        if (!checkIndex(index, ActionType.AT_ADD)){
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
        if (array != null) {
            System.arraycopy(array, 0, newArray, 0, getSize());
        }
        array = newArray;
    }

    @SuppressWarnings("unchecked")
    public T get(int index){
        if (!checkIndex(index, ActionType.AT_ACCESS)){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return (T)array[index];
    }

    public int indexOf(T element){
        for (int i = 0; i < getSize(); i++){
            if (array[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T element){
        return indexOf(element) != -1;
    }

    public void removeAt(int index){
        if (!checkIndex(index, ActionType.AT_ACCESS)){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (index < getSize() - 1) {
            System.arraycopy(array, index + 1, array, index, getSize() - index);
        }
        used--;

    }

    private boolean checkIndex(int index, ActionType actionType){
        boolean result = index >= 0;
        if (actionType == ActionType.AT_ADD){
            result = result && (index <= getSize());
        } else {
            result = result && (index < getSize());
        }

        return result;
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
    public void sort(){
        int leadIndex;
        T temp;
        for (int i = 0; i < getSize() - 1; i++){
            leadIndex = i;
            for (int j = i + 1; j < getSize(); j++){
                if (get(leadIndex).compareTo(get(j)) > 0){
                    leadIndex = j;
                }
            }
            temp = (T)array[i];
            array[i] = array[leadIndex];
            array[leadIndex] = temp;
        }
    }


}
