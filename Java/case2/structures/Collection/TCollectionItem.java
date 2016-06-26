package structures.Collection;


public class TCollectionItem<T> {
    private T value;
    private TCollection<T> owner;

    public TCollectionItem(T value, TCollection<T> owner, int index){
        this.value = value;
        owner.add(this, index);
    }

    public TCollectionItem(T value, TCollection<T> owner){
        this(value, owner, owner.getSize());
    }

    public T getValue(){
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }

    public TCollection<T> getOwner(){
        return owner;
    }

    void setOwner(TCollection owner){
        this.owner = owner;
    }

}
