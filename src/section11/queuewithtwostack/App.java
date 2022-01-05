package section11.queuewithtwostack;

public class App {
    public static void main(String[] args){
        QueueByStack<Integer> nums = new QueueByStack();
        nums.enQueue(1);
        nums.enQueue(10);
        nums.enQueue(100);
        nums.enQueue(1000);
        System.out.println(nums.deQueue());
        nums.enQueue(10000);
        nums.enQueue(100000);
        while(!nums.isEmpty()){
            System.out.println(nums.deQueue());
        }
    }
}
