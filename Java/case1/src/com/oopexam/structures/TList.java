package com.oopexam.structures;

public class TList<T> {

    private class TListItem {
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

    public T getFirst() {
        return head.data;
    }

    public int getCount() {
        return this.count;
    }

    public void insert(int pos, T data) {
        TListItem itm = new TListItem(data);

        if (head == null) {
            head = itm;
        } else {
            if (pos <= 0) {
                itm.next = head;
                head = itm;
            } else {
                if (pos > count) {
                    pos = count;
                }

                TListItem curr = head;

                for (int i = pos-1; i > 0; --i) {
                    curr = curr.next;
                }

                itm.next = curr.next;
                curr.next = itm;
            }
        }
        ++count;
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
    }

    public boolean checkIndex(int pos) {
        return !((pos < 0) || (pos >= count));
    }
}
