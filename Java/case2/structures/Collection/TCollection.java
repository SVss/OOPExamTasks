package structures.Collection;

import structures.TArray;

public class TCollection<T> extends TArray<TCollectionItem<T>> {
    @Override
    public void add(TCollectionItem<T> element, int index) {
        super.add(element, index);
        element.setOwner(this);
    }

    @Override
    public void removeAt(int index) {
        TCollectionItem removingItem = get(index);
        removingItem.setOwner(null);
        super.removeAt(index);
    }

    public void remove(TCollectionItem<T> element){
        removeAt(indexOf(element));
    }

    @Override
    public boolean contains(TCollectionItem<T> element) {
        return element.getOwner() == this;
    }
}
