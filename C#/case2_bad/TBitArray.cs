using System;

namespace OOPexam
{
    //    разработать контейнер «битовый массив» — TBitArray, который
    //- содержит элементы типа Boolean так, что на каждый отводится лишь 1 бит,
    //- увеличивает свой размер при добавлении в него элементов,
    //- предоставляет типовые свойства (текущее количество элементов, количество зарезервированных элементов),
    //- предоставляет типовые процедуры, применяемые при работе с массивом (чтение и запись бита по индексу, расширение массива),
    //- при обращении к биту в массиве проверяет, что индекс находится в допустимых пределах.

    class TBitArray
    {
        private bool[] bitArray;

        public int CountReserved { get { return bitArray.Length; } }

        public int CurrentCount { get; private set; }

        public TBitArray(int size)
        {
            bitArray = new bool[size];
            CurrentCount = 0;
        }

        public void IncSize(int count)
        {
            bool[] tmp = new bool[bitArray.Length + count];
            Array.Copy(bitArray,tmp,bitArray.Length);
            bitArray = tmp;
        }

        public bool this[int index]
        {
            get
            {
                if (IsInside(index))
                {
                    CurrentCount++;
                    return bitArray[index];
                }
                throw new Exception("За пределами");
            }
            set
            {
                if (IsInside(index))
                {
                    bitArray[index] = value;
                }
                else
                {
                    throw new Exception("За пределами");
                }
            }
        }

        private bool IsInside(int index)
        {
            if (index < CountReserved)
            {
                return true;
            }
            return false;
        }

        
    }
}
