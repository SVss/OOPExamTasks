package structures.Collection;


public class TCollectionItem<T extends Comparable<T>> implements Comparable<TCollectionItem<T>> {
    private T value;
    private TCollection<T> owner;

    public TCollectionItem(T value, TCollection<T> owner, int index){
        this.value = value;
        owner.add(this, index);
    }

    @Override
    public int compareTo(TCollectionItem<T> o) {
        return getValue().compareTo(o.getValue());
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
