using System;
using System.Collections.Generic;
using System.Text;

namespace OOPExam
{
    class TBitArray
    {
        const int DEFAULT_BLOCKS_COUNT = 1;

        long[] blocks;
        int count;

        TBitArray(int blocksCount = DEFAULT_BLOCKS_COUNT)
        {
            blocks = new long[blocksCount];
            count = sizeof(long) * blocksCount;
        }


    }
}
