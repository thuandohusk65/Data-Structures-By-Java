package section18;

public class App {
    public static void main(String[] args){
        int[] arr = {8,9,16,11,10,20,17,14,13,5};
        int[] arr2 = {210,100,23,2,5};
        //check min heap
//        CheckingArrayHeap checkingArrayHeap = new CheckingArrayHeap(arr);
//        System.out.println(checkingArrayHeap.isArrayHeap());
//        System.out.println(checkingArrayHeap.isMinHeap());
        HeapConverter heapConverter = new HeapConverter(arr2);
    int[] minHeap = heapConverter.transform();
    for(int i : minHeap){
        System.out.println(i);
    }
    }
}
