using System;

namespace OOPexam
{
    //    На языке C# разработать шаблон «словарь» — TDictionary, который представляет собой набор ключей и их значений, и который:
    //- содержит ключи типа string и значения заданного в шаблоне типа данных,
    //- увеличивает свой размер при добавлении в него элементов,
    //- предоставляет типовые свойства (количество ключей и значений, признак сортированного «словаря»),
    //- предоставляет типовые процедуры (вставка ключа и значения, удаление значения по ключу, поиск значения по ключу, очистка, сортировка),
    //- предоставляет типовые события, происходящие при работе со «словарем» (вставка, удаление, очистка).

    class TDictionary<T>
    {
        class Item<T>
        {
            public string Key;
            public T Value;
        }

        internal delegate void AddDictionaryEventHandler(string key, T elem);

        internal delegate void DeleteDictionaryEventHandler(string key);

        public event DeleteDictionaryEventHandler DeleteEvent;
        public event AddDictionaryEventHandler AddEvent;

        public void OnDeleteEvent(string key)
        {
            if (DeleteEvent != null)
            {
                DeleteEvent(key);
            }
        }

        public void OnAddEvent(string key, T elem)
        {
            if (AddEvent != null)
            {
                AddEvent(key, elem);
            }
        }

        private Item<T>[] items;
        public int Count { get { return items.Length - 1 ; } }
        public bool IsSorted { get; private set; }
        public TDictionary()
        {
            items = new Item<T>[0];
            IsSorted = false;
        }

        public void IncSize(int count)
        {
            Item<T>[] tmp = new Item<T>[items.Length + count];
            Array.Copy(items, tmp, items.Length);
            items = tmp;
        }

        public void Clear()
        {
            items = new Item<T>[0];
        }

        public void Add(string key, T value)
        {
            IncSize(1);
            items[Count].Key = key;
            items[Count].Value = value;
        }

        public void Remove(string Key)
        {
            for (int i = 0; i < Count; ++i)
            {
                if (String.Compare(Key, items[i].Key) == 0)
                {
                    for (int j = i; j < Count - 1; ++j)
                        items[j] = items[j + 1];
                    
                }
            }
            IncSize(-1);
        }

        public T Search(string key)
        {
            for (int i = 0; i < Count; ++i)
            {
                if (String.Compare(key, items[i].Key) == 0)
                {
                    return items[i].Value;
                }
            }
            throw new Exception("Не найдено");
        }

        public void Sort()
        {
            for (int i = 0; i < Count; ++i)
                for (int j = 0; j < Count; ++j)
                {
                    if (string.Compare(items[i].Key, items[j].Key) > 0)
                    {
                        Item<T> tmp = items[i];
                        items[i] = items[j];
                        items[j] = tmp;
                    }
                }
            IsSorted = true;
        }        

    }
}
