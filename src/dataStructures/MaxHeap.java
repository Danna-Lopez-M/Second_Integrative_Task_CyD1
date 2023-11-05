package dataStructures;
import dataStructures.IDataStructures.IHeap;

import java.util.ArrayList;
public class MaxHeap<T extends Comparable<T>> implements IHeap<T> {

        private ArrayList<T> heap;

        public MaxHeap(ArrayList<T> list) {
            this.heap = list;
            buildMaxHeap();
        }

        @Override
        public int parent(int index) {
            return (index - 1) / 2;
        }

        @Override
        public int left(int index) {
            return 2 * index + 1;
        }

        @Override
        public int right(int index) {
            return 2 * index + 2;
        }

        @Override
        public void swap(int i, int j) {
            T temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        @Override
        public void insert(T value) {
            this.heap.add(value);
            heapIncreaseKey(this.heap.size() - 1);
        }

        @Override
        public T extractMax() {
            if (this.heap.isEmpty()) {
                System.out.println("The heap is empty");
                return null;
            }

            if (this.heap.size() == 1) {
                return this.heap.remove(0);
            }

            T maxValue = this.heap.get(0);
            this.heap.set(0, this.heap.remove(this.heap.size() - 1));
            maxHeapify(0);

            return maxValue;
        }

        @Override
        public void maxHeapify(int currentIndex) {
            int leftIndex = left(currentIndex);
            int rightIndex = right(currentIndex);
            int largest = currentIndex;

            if (leftIndex < this.heap.size() && this.heap.get(leftIndex).compareTo(this.heap.get(largest)) > 0) {
                largest = leftIndex;
            }

            if (rightIndex < this.heap.size() && this.heap.get(rightIndex).compareTo(this.heap.get(largest)) > 0) {
                largest = rightIndex;
            }

            if (currentIndex != largest) {
                swap(currentIndex, largest);
                maxHeapify(largest); // Llamada recursiva con el nuevo índice
            }
        }

        @Override
        public void buildMaxHeap() {
            for (int i = this.heap.size() / 2 - 1; i >= 0; i--) {
                maxHeapify(i);
            }
        }

        @Override
        public T heapMaximum() {
            return this.heap.get(0);
        }

        @Override
        public void heapIncreaseKey(int index) {
            while (index > 0 && this.heap.get(parent(index)).compareTo(this.heap.get(index)) < 0) {
                swap(index, parent(index));
                index = parent(index);
            }
        }

        public boolean isEmpty() {
            return this.heap.isEmpty();
        }

        public int size() {
            return this.heap.size();
        }

        public boolean delete(T value) {
            int index = this.heap.indexOf(value);
            if (index == -1) {
                return false;
            }
            this.heap.set(index, this.heap.remove(this.heap.size() - 1));
            maxHeapify(index);
            return true;
        }

}
