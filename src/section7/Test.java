package section7;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);

        ArrayList<Integer> nums2 = nums;
        nums2 = new ArrayList<>();
        nums2.add(6);
        nums2.add(7);
        if(nums2 == nums)
        System.out.println(nums.size());
    }
}
