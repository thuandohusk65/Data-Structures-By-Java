package section11.maxinstack;

public class App {
    public static void main(String[] args){
        Stack<Integer> nums = new Stack<>();
        nums.push(1);
        nums.push(9);
        nums.push(-12);
        nums.push(15);
        nums.push(7);
        nums.push(3);
        System.out.println(nums.getMax());
    }
}
