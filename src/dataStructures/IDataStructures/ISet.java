package dataStructures.IDataStructures;

import dataStructures.Set;

public interface ISet<T extends Comparable<T>> {
    boolean add(T e);
    boolean delete(T e);
    boolean union(Set<T> set);
    boolean intersection(Set<T> set);
    boolean difference(Set<T> set);
    boolean clear();
    boolean contains(T e);
    int size();
    boolean isEmpty();
}
