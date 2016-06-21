using System;
using System.Collections.Generic;
using System.Text;

namespace OOPExam
{
    class TestProgram
    {
        static void Main(string[] args)
        {
            //TestStack();

            //TestArray();



            Console.ReadKey();
        }

        static void TestStack()
        {
            Console.WriteLine("*** TStack test ***");

            TStack<string> strStack = new TStack<string>();

            strStack.Push("hello");
            strStack.Push("12");
            strStack.Push("34");
            strStack.Push("56");

            while (!strStack.IsEmpty())
            {
                Console.WriteLine(strStack.Pop());
            }

            strStack.onPush += ( () => Console.WriteLine("Pushed!") );
            strStack.Push("123");

            strStack.onPop += ( () => Console.WriteLine("Popped!"));
            Console.WriteLine(strStack.Pop());

            //strStack.Pop();   // <- Exception


            TStack<int> intStack = new TStack<int>(3);

            intStack.Push(5);
            intStack.Push(10);

            intStack.onPush += ( () => Console.WriteLine("Pushed into intStack") );
            intStack.Push(0);
            //intStack.Push(90);    // <- exception

            Console.WriteLine(intStack.Pop());

            intStack.Clear();
            //Console.WriteLine(intStack.Pop());    // <- exception

        }

        static void printTArray<T>(TArray<T> arr)
        {
            Console.WriteLine("Count: {0}\nReserved: {1}", arr.Count, arr.Reserved);
            for (int i = 0; i < arr.Count; ++i)
            {
                Console.WriteLine("[{0}] {1}", i, arr[i]);
            }
            Console.WriteLine();
        }

        static void findInTArray<T>(TArray<T> arr, T x)
        {
            int index = arr.Find(x);
            if (index != -1)
            {
                Console.WriteLine("{0} has index {1}.", x, index);
            }
            else
            {
                Console.WriteLine("{0} not found.");
            }
        }

        static void TestArray()
        {
            Console.WriteLine("*** TArray test ***");

            TArray<int> intArr = new TArray<int>(3);
            intArr[1] = 10;
            intArr[0] = -10;
            //intArr[90] = 0;   // <- exception

            printTArray(intArr);
            intArr.Insert(-50, 1);

            printTArray(intArr);
            findInTArray(intArr, 10);
            Console.WriteLine();

            intArr.Sort( (int x, int y) => (x > y) );
            Console.WriteLine("Sorted intArr:");
            printTArray(intArr);

            intArr.Clear();
            Console.WriteLine("Cleared strArr:");
            printTArray(intArr);

            
            //TArray<string> strArr = new TArray<string>(-4); // <- exception

            TArray<string> strArr = new TArray<string>(1);
            strArr[0] = "ohoho";
            strArr.Insert("hello", 0);
            strArr.Insert("greg", 0);
            strArr.Insert("lol", 0);
            strArr.Insert("hello2", 0);
            printTArray(strArr);

            strArr.Sort((string x, string y) => {
                bool xGy = x.Length > y.Length;
                int lng = (xGy) ? y.Length : x.Length;

                int i = 0;
                for (i = 0; (i < lng) && (x[i] == y[i]); ++i) { }

                if (i != lng)
                {
                    return (x[i] > y[i]);
                }
                else
                {
                    return xGy;
                }
            });

            Console.WriteLine("Sorted strArr:");
            printTArray(strArr);

        }
    

    }
}
