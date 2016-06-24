package structures;

public class TStack<T> {
    private class TStackElement {
        T value;
        TStackElement next;

        TStackElement(T value) {
            this.value = value;
            next = null;
        }
    }

    TStackElement head;
    int size;

    public void push(T value) {
        TStackElement element = new TStackElement(value);
        element.next = head;
        head = element;

        size++;
    }

    public T pop() {
        T result = getTop();
        head = head.next;
        size--;
        return result;
    }

    public T getTop() {
        if (!isEmpty()) {
            return head.value;
        } else {
            throw new EmptyCollectionException();
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    public void clear(){
        while (!isEmpty()){
            pop();
        }
    }

}
