using System;


namespace OOPexam
{
    public struct XmlAttribute
    {
        public string Key { get; set; }
        public string Value { get; set; }
        public XmlAttribute(string k, string v)
            : this()
        {
            Key = k;
            Value = v;
        }
    }
    public class XMLTree
    {
        public delegate void EventHandler(object sender, EventArgs e);    
        private XmlAttribute[] attributes;
        private XMLTree[] SubTags;                      // вложенные теги
        public int SubTagsCount { get; private set; }  // кол-во дочерних тегов  
        public int AttrCount { get; private set; }     // количество ключей-значений (атрибутов)   
        public event EventHandler OnAdd;
        public event EventHandler OnRemove;
        public event EventHandler OnClear;
        public XMLTree(int count = 100, params XmlAttribute[] attrs)
        {
            SubTags = new XMLTree[count];
            attributes = attrs;
            AttrCount = attributes.Length;
            SubTagsCount = 0;
        }

       


        public void Add(XMLTree T) // добавление подтега
        {
            if (SubTagsCount == SubTags.Length)
            {
                var nTags = new XMLTree[SubTagsCount + 100];
                for (int i = 0; i < SubTagsCount; ++i)
                    nTags[i] = SubTags[i];
                SubTags = nTags;
            }
            SubTags[SubTagsCount] = T;
            SubTagsCount++;
            if (OnAdd != null)
                OnAdd(this, new EventArgs());
        }
        public int IndexOf(XMLTree T)
        {
            for (int i = 0; i < SubTagsCount; ++i)
                if (Object.Equals(T, SubTags[i]))
                    return i;
            return -1;
        }
        public void Remove(XMLTree T)
        {
            int x = IndexOf(T);
            if (x != -1)
            {
                for (int i = x; i < SubTagsCount - 1; ++i)
                    SubTags[i] = SubTags[i + 1];
                SubTagsCount--;
                if (OnRemove != null)
                    OnRemove(this, new EventArgs());
            }
        }
        public string GetValue(string Key)
        {
            for (int i = 0; i < AttrCount; ++i)
                if (attributes[i].Key == Key) return attributes[i].Value;
            return default(string);
        }
        public void Clear()
        {
            //
            if (OnClear != null)
                OnClear(this, new EventArgs());
        }
    }

}
