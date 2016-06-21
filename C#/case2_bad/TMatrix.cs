using System;

namespace OOPexam
{
    class TMatrix
    {
        public int nSize { get; private set; }
        public int mSize{ get; private set; }
        public TMatrix(int nSize, int mSize)
        {
            this.nSize = nSize;
            this.mSize = mSize;
            matrix = new double[nSize,mSize];
        }

        private double[,] matrix;

        

        public double this[int index1, int index2]
        {
            get
            {
                if (IsInside(index1,index2))
                {
                    return matrix[index1, index2];
                }
                throw  new Exception("За гранью.");
            }
            set
            {
                if (IsInside(index1, index2))
                {
                    matrix[index1, index2] = value;
                    
                }
                else
                {
                    throw new Exception("За гранью.");
                }
            }
        }

        private bool IsInside(int index1, int index2)
        {
            if (index1 < nSize && index2 < mSize)
                return true;
            return false;
        }

        public TMatrix MulMatrix(TMatrix matrix1, TMatrix matrix2)
        {
            double tmp;
            TMatrix resMatrix = new TMatrix(matrix1.mSize,matrix2.nSize);

            for (int i = 0; i < matrix1.mSize; i++)
            {
                for (int j = 0; j < matrix2.nSize; j++)
                {
                    tmp = resMatrix[i, j];
                    for (int k = 0; k < matrix1.nSize; k++)
                    {
                        tmp += matrix1[i, k] * matrix2[k, j];
                    }
                    resMatrix[i, j] = tmp;
                }
            }
            return resMatrix;
        }

        public TMatrix TrasponirovanieMatrix(TMatrix matrix1)
        {
            double tmp;
            TMatrix resMatrix = matrix1;
            for (int i = 0; i < matrix1.nSize; i++)
            {
                for (int j = 0; j < i; j++)
                {
                    tmp = resMatrix[i, j];
                    resMatrix[i, j] = resMatrix[j, i];
                    resMatrix[j, i] = tmp;
                }
            }
            return resMatrix;
        }
    }
}
