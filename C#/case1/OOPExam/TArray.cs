using System;
using System.Collections.Generic;
using System.Text;

namespace OOPExam
{
    class TArray<T>
    {
        public delegate bool CompDelegate(T x, T y);    // true - choose 1st, false - choose 2nd

        const int DEFAULT_ARRAY_SIZE = 128;

        private T[] items;
        private int count;

        public int Count
        {
            get
            {
                return count;
            }
        }

        public int Reserved
        {
            get
            {
                return items.Length;
            }
        }

        public TArray(int size = DEFAULT_ARRAY_SIZE)
        {
            if (size <= 0)
            {
                throw new Exception("Invalid size.");
            }

            count = size;
            this.items = new T[size];
        }

        public T this[int i]
        {
            get
            {
                if (IsValidIndex(i))
                {
                    return items[i];
                }
                else
                {
                    throw new Exception("Invalid index.");
                }
            }

            set
            {
                if (IsValidIndex(i))
                {
                    items[i] = value;
                }
                else
                {
                    throw new Exception("Invalid index.");
                }
            }
        }

        public bool IsValidIndex(int i)
        {
            return ( (i > -1) && (i < count) );
        }

        public void Insert(T value, int index = 0)
        {
            if (index > count)
            {
                index = count;
            }
            else if (index < 0)
            {
                index = 0;
            }

            if (count == items.Length)
            {
                Enlarge();
            }

            for (int i = count; i > index; --i)
            {
                items[i] = items[i - 1];
            }

            items[index] = value;
            ++count;
        }

        public void Delete(int index)
        {
            if (!IsValidIndex(index))
            {
                throw new Exception("Invalid index.");
            }
            else
            {
                for (int i = index; i < (count - 1); ++i)
                {
                    items[i] = items[i + 1];
                }
                --count;
            }
        }

        public int Find(T item)
        {
            for (int i = 0; i < count; ++i)
            {
                if (items[i].Equals(item) )
                {
                    return i;
                }
            }

            return -1;
        }

        private void Enlarge()
        {
            int newSize = items.Length * 2;
            var tmp = new T[newSize];

            for (int i = 0; i < count; ++i)
            {
                tmp[i] = items[i];
            }

            items = tmp;
        }

        public void Clear()
        {
            for (int i = 0; i < count; ++i)
            {
                items[i] = default(T);
            }    

            count = 0;  // faster
        }

        public void Sort(CompDelegate comp)
        {
            for (int i = (count - 1); i > 0; --i)
            {
                for (int j = 0; j < i; ++j)
                {
                    if (comp(items[j], items[j+1]) )
                    {
                        T tmp = items[j];
                        items[j] = items[j + 1];
                        items[j + 1] = tmp;
                    }
                }
            }
        }

    }
}
