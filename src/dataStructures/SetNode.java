package dataStructures;

public class SetNode<T extends Comparable<T>> {
    private T item;
    private SetNode<T> next;

    public SetNode(T item){
        this.item = item;
        this.next = null;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public SetNode<T> getNext() {
        return next;
    }

    public void setNext(SetNode<T> next) {
        this.next = next;
    }
}
