using System;
using System.Collections.Generic;
using System.Text;

namespace OOPExam
{
    class TStack<T>
    {
        const int DEFAULT_STACK_SIZE = 128;

        public delegate void StackEvent();

        public event StackEvent onPop, onPush;

        protected void PopEvent()
        {
            StackEvent e = onPop;
            if (e != null)
            {
                e();
            }
        }

        protected void PushEvent()
        {
            StackEvent e = onPush;
            if (e != null)
            {
                e();
            }
        }

        private T[] items;
        private int count;

        public TStack(int size = DEFAULT_STACK_SIZE)
        {
            items = new T[size];
            count = 0;
        }

        public bool IsEmpty()
        {
            return (this.count == 0);
        }

        public void Push(T item)
        {
            if (this.count < items.Length)
            {
                items[count] = item;
                ++count;

                PushEvent();
            }
            else
            {
                throw new Exception("Stack limit exceeded.");
            }
        }

        public T Pop()
        {
            if (!this.IsEmpty() )
            {
                --count;
                PopEvent();

                return this.items[count];
            }
            else
            {
                throw new Exception("Empty stack pop forbidden.");
            }
        }

        public void Clear()
        {
            this.count = 0; // dont have to Pop each element, as  implemented on array
        }
    }
}
