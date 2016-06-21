using System;


namespace OOPexam
{
    class TCircularBuffer
    {
        private byte[] bytes;

        public TCircularBuffer(int size)
        {
            bytes = new byte[size];
            WritePosition = 0;
            ReadPosition = 0;
            FreeItems = size;
        }

        public int WritePosition { get; private set; }
        public int ReadPosition { get; private set; }
        public int BufferSize { get { return bytes.Length; } }
        public int FreeItems { get; private set; }

        public byte[] Read(int count)
        {
            if (count > BufferSize) 
                throw new Exception("За гранью.");
            if (count > BufferSize - FreeItems) 
                throw new Exception("За гранью.");
            var result = new byte[count];
            int l = BufferSize;
            for (int i = 0; i < count; ++i)
            {
                result[i] = bytes[ReadPosition];
                if (ReadPosition == l - 1) ReadPosition = 0; else ++ReadPosition;
            }
            FreeItems += count;
            return result;
        }

        public void Write(byte[] writebytes)
        {
            if (writebytes == null) 
                throw new Exception("За гранью.");
            if (writebytes.Length > FreeItems) 
                throw new Exception("За гранью.");
            int w = WritePosition;
            int l = BufferSize;
            foreach (var b in writebytes)
            {
                bytes[w] = b;
                if (w == l - 1) w = 0; else ++w;
            }
            WritePosition = (w + writebytes.Length) % BufferSize;
            FreeItems -= writebytes.Length;
        }

        public void IncLength(int count)
        {
            var tmp = new byte[BufferSize + count];
            Array.Copy(bytes,tmp,BufferSize);
            bytes = tmp;

        }

    }
}
