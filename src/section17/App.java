package section17;

public class App {
    public static void main(String[] args){
        Heap heap = new Heap();


        heap.insert(6);
        heap.insert(1);
        heap.insert(12);
        heap.insert(-1);
        heap.insert(3);
        heap.insert(8);
        heap.insert(5);
        heap.poll();
        heap.poll();
        System.out.println(heap.getMax());
        heap.heapSort();
    }
}
