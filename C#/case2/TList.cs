using System;

namespace OOPexam
{
    /// <summary>
    /// Задача 132.
    /// На языке C# разработать шаблон списка — TList, который:
    /// - содержит элементы заданного типа данных,
    /// - предоставляет типовые свойства (первый элемент, следующий элемент),
    /// - предоставляет типовые процедуры, применяемые при работе со списком 
    /// (вставка элемента, удаление элемента, очистка списка, замена элемента, перемещение списка в другой список),
    /// - предоставляет типовые события, происходящие при работе со списком (вставка, удаление, очистка).
    /// </summary>
    /// <typeparam name="T"></typeparam>
    class TList<T>
    {
        
        public Node Head { get; private set; }

        internal delegate void AddListEventHandler(T elem);

        internal delegate void DeleteListEventHandler(T elem);

        internal delegate void ClearListEventHandler();

        public event AddListEventHandler AddEvent;
        public event DeleteListEventHandler DeleteEvent;
        public event ClearListEventHandler ClearEvent;



        public void OnAddEvent(T elem)
        {
            if (AddEvent != null)
            {
                AddEvent(elem);
            }
        }

        public void OnDeleteEvent(T elem)
        {
            if (DeleteEvent != null)
            {
                DeleteEvent(elem);
            }
        }

        public void OnClearEvent()
        {
            if (ClearEvent != null)
            {
                ClearEvent();
            }
        }

        public class Node
        {
            public T Data;
            public Node Next;
        } 

        public void Add(T data)
        {
            Node newNode = new Node();
            
            newNode.Data = data;
            if (Head != null)
            {
                Node tempNode = Head;
                while (tempNode.Next != null)
                {
                    tempNode = tempNode.Next;
                }
                tempNode.Next = newNode;
            }
            else
            {
                Head = newNode;
            } 
        }

        public void Clear()
        {
            Head = null;
        }
        public void Show()
        {
            Node tempNode = Head;
            while (tempNode != null)
            {
                Console.WriteLine(tempNode.Data);
                tempNode = tempNode.Next;
            }
        }

        public void Remove(T item)
        {
            if (Head.Next == null && ((IComparable)Head.Data).CompareTo(item) == 0)
            {
                Head = null;
                return;
            }
            Node Tmp = Head;
            Node Prev = new Node();
            while (Tmp.Next != null && ((IComparable)Tmp.Data).CompareTo(item) != 0)
            {
                Prev = Tmp;
                Tmp = Tmp.Next;
            }
            if (((IComparable)Tmp.Data).CompareTo(item) == 0)
                Prev.Next = Tmp.Next;
        }

        public void CopyTo(TList<T> X)
        {
         
            Node tempNode = Head;
            while (tempNode != null)
            {
                X.Add(tempNode.Data);
                tempNode = tempNode.Next;
            }
        }

    }
}
