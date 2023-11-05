package dataStructures;

import dataStructures.IDataStructures.IHashTable;

public class HashTable<K, V> implements IHashTable<K, V> {
    private HashNode<K, V>[] table;

    public HashTable(int capacity) {
        table = new HashNode[capacity];
    }



    @Override
    public boolean add(K key, V value) {
        if(key == null) return false;
        int index = getIndex(key);

        if (table[index] == null) {
            table[index] = new HashNode<>(key, value);
            return true;
        } else {
            return table[index].colission(new HashNode<>(key, value));
        }
    }

    @Override
    public V search(K key) {
        int index = getIndex(key);
        HashNode<K, V> currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    @Override
    public V delete(K key) {
        int index = getIndex(key);
        HashNode<K, V> currentNode = table[index];
        HashNode<K, V> prevNode = null;

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                if (prevNode == null) {
                    table[index] = currentNode.getNext();
                } else {
                    prevNode.setNext(currentNode.getNext());
                }
                return currentNode.getValue();
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }

        return null;
    }


    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return (hashCode & 0x7FFFFFFF) % table.length;
    }


}

