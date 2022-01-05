package section10.queue;

public class App {
    public static void main(String[] args){
        Queue<Integer> myQueue = new Queue<>();
        myQueue.enQueue(1);
        myQueue.enQueue(2);
        myQueue.enQueue(3);
       System.out.println(myQueue.size());
       System.out.println(myQueue.deQueue());
       System.out.println(myQueue.deQueue());
       System.out.println(myQueue.deQueue());
       System.out.println(myQueue.deQueue());
       System.out.println(myQueue.deQueue());

    }
}
