using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OOPexam
{
    class TCollectionItem
    {
        public TCollection Owner;
        private object value;
        public object Value { get { return value; }
            set { this.value = value; }
        }
    }

    class TCollection
    {
        TCollectionItem[] PCollection = new TCollectionItem[0];

        
        //индексатор по нашему массиву, нужно это что бы сам массив у нас приватный был
        //+ здесь проверка что бы не падал если к индексам левым обращаемся
        public object this[int index]
        {
            get
            {
                if (IsInside(index))
                {
                    return PCollection[index];
                }
                Length = 0;

                return null;
            }
            private set
            {
                if (IsInside(index))
                    PCollection[index].Value = (TCollectionItem)value;
            }
        }
        //свойство кол-во жэлементов в массиве
        public int Length { get; private set; }

        //метод увеличивает размер массива на 1
        private void IncreaseLength()
        {
            TCollectionItem[] tmp = new TCollectionItem[PCollection.Length + 1];
            Array.Copy(PCollection, 0, tmp, 0, PCollection.Length);
            PCollection = tmp;
            Length = PCollection.Length - 1;
        }


        //вставка по индексу
        public void Add(TCollectionItem elem, int index)
        {
            IncreaseLength();
            if (!IsInside(index))
                index = PCollection.Length;
            if (PCollection.Length > 1)
            {
                for (int i = PCollection.Length - 2; i > index + 1; i--)
                {
                    PCollection[i + 1].Value = PCollection[i].Value;
                }
                PCollection[index + 1].Value = elem;
            }
            else
            {
                
                PCollection[index].Value = elem;
            }
        }

        //копирование в другой массив
        public void Copy(TCollection array1,ref TCollection array2, int startPos = 0)
        {
            array2 = array1;
        }


        //очистка)
        public void Clear()
        {
            PCollection = new TCollectionItem[0];
        }

        //удаление по индексу
        public void Delete(int index)
        {
            TCollectionItem[] tmp = new TCollectionItem[PCollection.Length - 1];
            int j = 0;
            for (int i = 0; i < PCollection.Length; i++)
            {
                if (i != index)
                {
                    tmp[j].Value = PCollection[i].Value;
                    j++;
                }
            }
            PCollection = tmp;
            Length--;
        }

        //поиск индекса элемента по значению элемента
        public int Search(TCollectionItem elem)
        {
            for (int i = 0; i < PCollection.Length; i++)
            {
                if (PCollection[i].Equals(elem))
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
            object temp;

            for (int i = 0; i < PCollection.Length - 1; i++)
            {
                for (int j = 0; j < PCollection.Length - 1 - 1; j++)
                {
                    if (((IComparable)PCollection[j].Value).CompareTo(PCollection[j + 1].Value) > 0)
                    {
                        temp = PCollection[j].Value;
                        PCollection[j].Value = PCollection[j + 1].Value;
                        PCollection[j + 1].Value = temp;
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
