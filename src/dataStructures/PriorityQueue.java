package dataStructures;

import java.util.ArrayList;

public class PriorityQueue<K extends Comparable<K>> {
    private MaxHeap<K> maxHeap;

    public PriorityQueue() {
        ArrayList<K> list = new ArrayList<>();
        maxHeap = new MaxHeap<>(list);
    }

    public void insert(K value) {
        maxHeap.insert(value);
    }

    public K peek() {
        return maxHeap.heapMaximum();
    }

    public K poll() {
        return maxHeap.extractMax();
    }

    public int size() {
        return maxHeap.size();
    }

    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    public boolean delete(K value) {
        return maxHeap.delete(value);
    }
}
