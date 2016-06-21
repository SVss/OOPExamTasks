using System;

namespace OOPexam
{
    class TArray<T>
    {
        //наш массив
        private  T[] array = new T[0];

        //индексатор по нашему массиву, нужно это что бы сам массив у нас приватный был
        //+ здесь проверка что бы не падал если к индексам левым обращаемся
        public object this[int index]
        {
            get
            {
                if (IsInside(index))
                {
                    return array[index];
                }
                Length = 0;
                
                return null;
            }
            private set { if (IsInside(index))
                array[index] = (T) value; 
            }
        }
        //свойство кол-во жэлементов в массиве
        public int Length  { get; private set; }
        
        //метод увеличивает размер массива на 1
        private void IncreaseLength()
        {
            T[] tmp = new T[array.Length + 1];
            Array.Copy(array, 0, tmp, 0, array.Length);
            array = tmp;
            Length = array.Length -1;
        }


        //вставка по индексу
        public void Add(T elem, int index)
        {
            IncreaseLength();
            if (!IsInside(index))
                index = array.Length;
            
                for (int i = Length - 1; i > index ; i--)
                {
                    array[i + 1] = array[i];
                }
                array[index ] = elem;
            
            
        }

        //копирование в другой массив
        public void Copy(TArray<T> array1, TArray<T> array2, int startPos = 0)
        {
            for (int i = startPos; i < array1.Length; i++)
            {
                array2[i] = array1[i];
            }
        }


        //очистка)
        public void Clear()
        {
            array = null;
        }

        //удаление по индексу
        public void Delete(int index)
        {
            T[] tmp = new T[array.Length - 1];
            int j = 0;
            for (int i = 0; i < array.Length; i++)
            {
                if (i != index)
                {
                    tmp[j] = array[i];
                    j++;
                }   
            }
            array = tmp;
            Length--;
        }

        //поиск индекса элемента по значению элемента
        public int Search(T elem)
        {
            for (int i = 0; i < array.Length; i++)
            {
                if (array[i].Equals(elem))
                {
                    return i;
                }
            }
            Console.WriteLine("Элемент не найден :(");
            return -1;
        }

        //сортировка пузырьком
        public void BubbleSort()
        {
            T temp;

            for (int i = 0; i < array.Length - 1; i++)
            {
                for (int j = 0; j < array.Length - 1 - 1; j++)
                {
                    if (((IComparable)array[j]).CompareTo(array[j + 1]) > 0)
                    {
                        temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }

        //находится ли индекс в допустимых пределах
        private bool IsInside(int index)
        {
            if (index >= 0 || index < Length)
            {
                return true;
            }
            return false;
        }
        
    }
}
