using System;

namespace OOPexam
{
    /// <summary>
    /// Задача 131.
    /// На языке C# разработать шаблон стека — TStack, который:
    /// - содержит элементы заданного типа данных,
    /// - предоставляет типовые свойства (указатель на вершину стека, указатель на дно стека),
    /// - предоставляет типовые процедуры, применяемые при работе со стеком (загрузка элемента на стек,
    /// выталкивание элемента из стека, очистка стека),
    /// - предоставляет типовые события, происходящие при работе со стеком (загрузка в стек, выталкивание из стека).
    /// </summary>
    /// <typeparam name="T"></typeparam>
    class TStack<T>
    {
        /// <summary>
        /// Массив, содержащий стек.
        /// </summary>
        private readonly T[] stack;

        internal delegate void StackPushEventHandler(T elem);
        internal delegate T StackPopEventHandler();

        public event StackPopEventHandler PopEvent;
        public event StackPushEventHandler PushEvent;

        /// <summary>
        /// Ссылка на дно стека.
        /// </summary>
        public int Tos { get; private set; }
    
        public TStack(int size)
        { 
            stack = new T[size]; 
            Tos = 0; 
        }

        /// <summary>
        /// Помещает елемент в стек.
        /// </summary>
        /// <param name="elem"></param>
        public void Push(T elem) {
            if (Tos == stack.Length)
            {
                throw new Exception("Stack is full.");
                return;
            }
            
            stack[Tos] = elem;
            Tos++;
            Console.WriteLine(elem +"\r\n");
        }

        /// <summary>
        /// Вызывается обработчик событий с помощью делегата PushEvent. 
        /// </summary>
        /// <param name="elem"></param>
        public void OnPushEvent(T elem)
        {
            if (PushEvent != null)
            {
                PushEvent(elem);
            }
        }

        /// <summary>
        /// Вызывается обработчик событий с помощью делегата PopEvent. 
        /// </summary>
        public void OnPopEvent()
        {
            if (PopEvent != null)
            {
                PopEvent();
            }
        }

        /// <summary>
        /// Извлекает элемент из  стека.
        /// </summary>
        /// <returns></returns>
        public T Pop()
        {
            if (Tos != 0)
            {
                Tos--;
                return stack[Tos];
            }
            
            throw new Exception("Stack is empty.");
        }

        /// <summary>
        /// Очистка стека.
        /// </summary>
        public void Clear()
        {
            for (int i = 0; i < stack.Length; i++)
            {
                Pop();
            }
        }

    }
}
