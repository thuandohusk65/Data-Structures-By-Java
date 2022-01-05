package section18;

public class HeapConverter {
    private int[] heap;

    public HeapConverter(int[] heap) {
        this.heap = heap;
    }

    public int[] transform() {
        //we don't have to consider leaf nodes ( that's the reason why we just have to consider has indexed (length-2)/2
        for (int i = (heap.length - 2) / 2; i >= 0; i--) {
            //we "heapify" all the internal nodes: we check whether the parent is smaller than the children
            //if not: than we swap the nodes accordingly
            heapify(i);
        }
        return this.heap;
    }

    private void heapify(int index) {
        //index of the left child
        int leftChildIndex = 2 * index + 1;
        //index of the right child
        int rightChildIndex = 2 * index + 2;
        //index of the smallest child node
        int smallest = index;
        //check the left child whether it is smaller than the parent
        if (leftChildIndex < heap.length && heap[leftChildIndex] < heap[index])
            smallest = leftChildIndex;
        //compare the right child and the left
        if (rightChildIndex < heap.length && heap[rightChildIndex] < heap[smallest])
            smallest = rightChildIndex;
        //we do not want to swap the node with itself only  when necessary
        if (smallest != index) {
            swap(smallest, index);
            //call the method recursively on the child
            heapify(smallest);
        }
    }

    //O(1)
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}
