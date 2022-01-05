package section17;

public class Heap<T> {
    //one-dimensional array representation MAX HEAP
    private int[] heap;
    // number of items in the heap
    private int heapSize;

    public Heap() {
        heap = new int[Constants.CAPACITY];
    }

    public void insert(int data) {

        if (isFull()) {
            throw new RuntimeException("Heap is full ...");
        }
        heap[heapSize] = data;
        heapSize++;

        /* we have to check the heap properties - we start with the last item
        that has indexed heapSize -1
        we have to check the nodes (parent nodes) up to the root node*/
        fixUp(heapSize - 1);
    }

    //O(logN)
    private void fixUp(int index) {
        //index of the parent
        int parentIndex = (index - 1) / 2;
        if (index > 0 && heap[index] > heap[parentIndex]) {
            swap(index, parentIndex);
            fixUp(parentIndex);
        }
    }

    //heapsort is doing a poll() operation for N times O(NLogN)
    public void heapSort() {
        int n = heapSize;
        for (int i = 0; i < n; i++) {
            int max = poll();
            System.out.println(max);
        }
    }

    //the max item is the root node(index 0) in a max heap in O(1)
    public int getMax() {
        return heap[0];
    }

    //removes and returns the max item in O(logN)
    public int poll() {
        int max = getMax();
        swap(0, heapSize - 1);
        heapSize--;
        //fix the heap properties if needed
        fixDown(0);
        return max;
    }

    //O(logN)
    private void fixDown(int index) {
        //my way
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largestIndex = index;
        if (leftChildIndex < heapSize && rightChildIndex < heapSize && heap[index] < Math.max(heap[leftChildIndex], heap[rightChildIndex])) {
            int indexHaveGreaterValue = heap[leftChildIndex] > heap[rightChildIndex] ? leftChildIndex : rightChildIndex;
            swap(index, indexHaveGreaterValue);
            fixDown(indexHaveGreaterValue);
        } else if (leftChildIndex < heapSize && rightChildIndex >= heapSize && heap[index] < heap[leftChildIndex]) {
            swap(index, leftChildIndex);
        }
        //instructor's way
//        if (leftChildIndex < heapSize && heap[largestIndex] < heap[leftChildIndex]) {
//            largestIndex = leftChildIndex;
//        }
//        if (rightChildIndex < heapSize && heap[largestIndex] < heap[rightChildIndex]) {
//            largestIndex = rightChildIndex;
//        }
//        if (largestIndex != index) {
//            swap(largestIndex, index);
//            //until the heap properties are violated we keep calling the method recursively
//            fixDown(largestIndex);
//        }
    }

    //O(1)
    public boolean isEmpty() {
        return heapSize == 0;
    }

    //O(1)
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    //O(1)
    private boolean isFull() {
        return heapSize == heap.length;
    }
}
