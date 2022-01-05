package section11.queuewithonestack;



public class App {
    public static void main(String[] args){
        QueueByStack<Integer> nums = new QueueByStack<>();
        nums.enQueue(1);
        nums.enQueue(2);
        nums.enQueue(3);
        nums.enQueue(4);
        nums.enQueue(5);

        System.out.println(nums.deQueue());
        System.out.println(nums.deQueue());
        System.out.println(nums.deQueue());
        System.out.println(nums.deQueue());
        System.out.println(nums.deQueue());
        System.out.println(nums.deQueue());
        System.out.println(nums.deQueue());
        System.out.println(nums.deQueue());
    }
}
