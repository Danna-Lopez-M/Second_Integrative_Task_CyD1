package dataStructures.IDataStructures;

public interface IHeap<T> {

        int parent(int index);

        int left(int index);

        int right(int index);

        void swap(int i, int j);

        void insert(T value);

        T extractMax();

        void maxHeapify(int currentIndex);

        void buildMaxHeap();
        T heapMaximum();
        void heapIncreaseKey(int index);

}
