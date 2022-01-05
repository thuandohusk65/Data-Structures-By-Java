package section18;

public class CheckingArrayHeap {
    private int[] arr;

    public CheckingArrayHeap(int[] arr) {
        this.arr = arr;
    }

    public boolean isArrayHeap() {
        if (arr.length == 1 || arr.length == 0) {
            return true;
        } else {
            return isArrayHeap(0);
        }
    }

    private boolean isArrayHeap(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        if (rightChildIndex < arr.length && arr[index] < Math.min(arr[rightChildIndex], arr[leftChildIndex])) {
            return isArrayHeap(leftChildIndex) && isArrayHeap(rightChildIndex);
        } else if (leftChildIndex == arr.length - 1 && arr[index] < arr[leftChildIndex]) {
            return true;
        } else if (leftChildIndex >= arr.length) {
            return true;
        } else {
            return false;
        }
    }

    //INSTRUCTOR's WAY - WRONG !!!!
    //I have added the first if case, and fix for loop <  -> <=
    public boolean isMinHeap() {
        //leaf nodes do hot have children
        //if 2*i+2 >=N then we know that node is a lead node(no need to consider)
        //so we have to rearrange the equation to get how many nodes to consider
        for (int i = 0; i <= (arr.length - 2) / 2; i++) {
            //for non lead nodes we just have to check the min heap properties
            if (2 * i + 2 == arr.length && arr[i] < arr[2 * i + 1]) {
                return true;
            } else if (arr[i] > arr[2 * i + 1] || arr[i] > arr[2 * i + 2]) {
                return false;
            }
        }
        return true;
    }

}
