using System.IO;
using System.IO.MemoryMappedFiles;


namespace OOPexam
{
    abstract class TAbstractFile
    {
        public string fileName { get; set; }
        public abstract void Write(char elem);
        public abstract char Read();
        public abstract void Copy(TAbstractFile file1, TAbstractFile file2);
    }

    class TMemoryFile :TAbstractFile
    {
        MemoryMappedFile file;

        public TMemoryFile(string fileName)
        {
            this.fileName = fileName;
            file = MemoryMappedFile.CreateFromFile(fileName, FileMode.Open, "ImgA");

        }




    }

    class TOSFile: TAbstractFile
    {
        private FileInfo file;
        public TOSFile(string fileName)
        {
            file = new FileInfo(fileName);
            if (file.Exists == false) 
            {
                file.Create(); 
            }
            ReadPosition = 0;
            WritePosition = 0;
            this.fileName = fileName;
        }

        public int ReadPosition
        {
            get; 
            set;
        }

        public int WritePosition
        {
            get;
            set;
        }

        public override void Write(char elem)
        {
            StreamWriter writeText = file.AppendText();
            char[] buff = new char[1]{elem};
            writeText.Write(buff,WritePosition,1);
            writeText.Close();
            WritePosition++;
        }

        public override char Read()
        {
            StreamReader streamReader = file.OpenText();
            char[] buff = new char[1];
            streamReader.Read(buff, 1, ReadPosition);
            ReadPosition++;
            streamReader.Close();
            return buff[0];
        }

        public override void Copy(TAbstractFile file1, TAbstractFile file2)
        {
            string from = file1.fileName;
            string to = file2.fileName;
            File.Copy(from, to, true);
        }

    }

}
