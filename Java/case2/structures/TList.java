package structures;

public class TList<T> {
    private class TListItem {
        private T data;
        private TListItem next;

        private TListItem(T element) {
            data = element;
            next = null;
        }
    }

    private TListItem head;
    private int size;

    public TList() {
        size = 0;
        head = null;
    }

    private TListItem getReferenceAt(int index) {
        if (!checkIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        TListItem currentElement = head;
        for (int i = 0; i < index; i++) {
            currentElement = currentElement.next;
        }

        return currentElement;
    }

    public int getSize() {
        return size;
    }

    public void add(T element) {
        add(size, element);
    }

    public void add(int index, T element) {
        TListItem newItem = new TListItem(element);

        if (index == 0) {
            newItem.next = head;
            head = newItem;
        } else {
            TListItem previousItem = getReferenceAt(index - 1);
            newItem.next = previousItem.next;
            previousItem.next = newItem;
        }

        size++;
    }

    public T get(int index) {
        return getReferenceAt(index).data;
    }

    public void remove(int index) {
        if (!checkIndex(index)){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index == 0) {
            head = head.next;
        } else {
            TListItem previous = getReferenceAt(index - 1);
            TListItem current = previous.next;
            previous.next = current.next;
        }

        size--;
    }

    public void setElementAt(int index, T element) {
        getReferenceAt(index).data = element;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void moveTo(TList<T> outList) {
        moveTo(outList.getSize(), outList);
    }

    public void moveTo(int index, TList<T> outList) {
        for (int i = 0; i < getSize(); i++) {
            outList.add(index + i, get(i));
        }
        clear();
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < size;
    }
}