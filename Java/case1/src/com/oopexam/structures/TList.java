package com.oopexam.structures;

public class TList<T> {

    public class TListItem {
        T data;
        TListItem next;

        TListItem(T obj) {
            this.data = obj;
            this.next = null;
        }
    }

    private TListItem head;
    private int count;

    public TList() {
        this.head = null;
        this.count = 0;
    }

    private TList(TListItem itm) {
        this.head = itm;
        this.count = 1;
    }

    public T getFirst() {
        return head.data;
    }

    // cache
    private TListItem last = null;
    private int lastPos = 0;

    private void clearCache() {
        last = head;
        lastPos = 0;
    }

    public T getAt(int pos) throws IndexOutOfBoundsException {
        if (!checkIndex(pos)) {
            throw new IndexOutOfBoundsException("Bad index to delete.");
        }

        TListItem curr = head;
        int i = pos;

        if ( (pos > lastPos) && (last != null) ) {
            curr = last;
            i = pos - lastPos;
        }

        while(i > 0){
            curr = curr.next;
            --i;
        }

        last = curr;
        lastPos = pos;

        return curr.data;
    }

    public int getCount() {
        return this.count;
    }

    private void insertList(int pos, TList<T> srcList) {    // srcList is cleared
        if (this.head == null) {
            this.head = srcList.head;
        } else {

            TListItem lastItm = srcList.head;
            while (lastItm.next != null) {
                lastItm = lastItm.next;
            }

            if (pos <= 0) {
                lastItm.next = head;
                head = srcList.head;
            } else {
                if (pos > count) {
                    pos = count;
                }

                TListItem curr = head;

                for (int i = pos-1; i > 0; --i) {
                    curr = curr.next;
                }

                lastItm.next = curr.next;
                curr.next = srcList.head;
            }
        }
        count += srcList.count;

        srcList.clear();

        clearCache();
    }

    public void insert(int pos, T data) {
        this.insertList(pos, new TList<>(new TListItem(data) ) );
    }

    public void delete(int pos) throws IndexOutOfBoundsException {
        if (!checkIndex(pos)) {
            throw new IndexOutOfBoundsException("Bad index to delete.");
        }

        if (pos == 0) {
            head = head.next;
        } else {
            TListItem curr = head;

            for (int i = pos - 1; i > 0; --i) {
                curr = curr.next;
            }

            curr.next = curr.next.next;
        }
        --count;

        clearCache();
    }

    public void moveTo(TList<T> destList, int pos) {
        destList.insertList(pos, this);
        clearCache();
    }

    public void moveTo(TList<T> destList) {
        moveTo(destList, destList.count);
    }

    public void clear() {
        this.head = null;
        this.count = 0;
        clearCache();
    }

    public boolean checkIndex(int pos) {
        return !((pos < 0) || (pos >= count));
    }
}
