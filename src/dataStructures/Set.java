package dataStructures;

import dataStructures.IDataStructures.ISet;


public class Set<T extends Comparable<T>> implements ISet<T> {
    private SetLinkedList setLinkedList;

    public Set() {
        setLinkedList = new SetLinkedList<>();
    }
    // se debe asegurar que el elemento ya no exista en el conjunto
    @Override
    public boolean add(T e) {
        boolean result = false;
        if(!contains(e)){
            setLinkedList.add(e);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(T e) {
        return setLinkedList.delete(e);
    }

    @Override
    public boolean union(Set<T> set) {
        boolean result =  false;
        if(!set.isEmpty()) {
            // modifico la lista actual.
            result = setLinkedList.union(set);
        }
        return result;
    }

    @Override
    public boolean intersection(Set<T> set) {
        boolean result =  false;
        if(!set.isEmpty()) {
            SetLinkedList<T> interList = new SetLinkedList<>();
            interList = setLinkedList.intersection(set);
            // si efectivamente hay elementos comunes, entonces
            if(interList.getHead() != null) {
                // la referencia ahora apuntara a la nueva lista formada
                // por la interseccion.
                this.setLinkedList = interList;
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean difference(Set<T> set) {
        boolean result =  false;

        if(!set.isEmpty()) {
            // modifico la lista actual.
            setLinkedList.difference(set);
            result = true;
        }
        return result;
    }

    @Override
    public boolean clear() {
        return setLinkedList.clear();
    }

    @Override
    public boolean contains(T e) {
        return setLinkedList.contains(e);
    }

    @Override
    public int size() {
        return setLinkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return setLinkedList.isEmpty();
    }
    public SetNode<T> getFirstElement(){
        return setLinkedList.getHead();
    }
    public String print(){
        return setLinkedList.printList();
    }
}
