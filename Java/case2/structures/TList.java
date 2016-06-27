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

    private TListItem head = null;
    private int lastVisitedIndex = 0;
    private TListItem lastVisited = null;

    public TList() {
        head = null;
    }

    private TListItem getReferenceAt(int index) {
        if (!checkIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        TListItem currentElement = head;
        int i = 0;

        if ((index >= lastVisitedIndex) && (lastVisited != null)) {
            currentElement = lastVisited;
            i = lastVisitedIndex;
        }

        while (i < index) {
            currentElement = currentElement.next;
            i++;
        }

        lastVisitedIndex = index;
        lastVisited = currentElement;

        return currentElement;
    }

    public int getSize() {
        int size = 0;
        TListItem current = head;

        while (current != null) {
            current = current.next;
            size++;
        }

        return size;
    }

    public void add(T element) {
        add(getSize(), element);
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

        if (index <= lastVisitedIndex) {
            clearCache();
        }
    }

    public T get(int index) {
        return getReferenceAt(index).data;
    }

    public void remove(int index) {
        if (!checkIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index == 0) {
            head = head.next;
        } else {
            TListItem previous = getReferenceAt(index - 1);
            TListItem current = previous.next;
            previous.next = current.next;
        }

        if (index <= lastVisitedIndex){
            clearCache();
        }
    }

    private void clearCache(){
        lastVisitedIndex = 0;
        lastVisited = null;
    }

    public void setElementAt(int index, T element) {
        getReferenceAt(index).data = element;
    }

    public void clear() {
        head = null;
        clearCache();
    }

    public void moveTo(TList<T> outList) {
        moveTo(outList.getSize(), outList);
    }

    public void moveTo(int index, TList<T> outList) {
        TListItem insertPoint = outList.getReferenceAt(index - 1);
        TListItem nextElement = insertPoint.next;
        insertPoint.next = this.head;
        getReferenceAt(getSize() - 1).next = nextElement;
        clear();
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < getSize();
    }
}