package section21;

import java.util.HashMap;

public class DynamicProgramingTwoSum {
    public void Solution(int[] nums, int S) {
        HashMap<Integer, Integer> hashTable = new HashMap<>();
        //consider all the items once so it has O(N) linear running time complexity
        for (int i = 0; i < nums.length; i++) {
            //check the remainder( actual value - target value)
            int remainder = S - nums[i];

            //check if the remainder exists in the hashtable: it means we have found a pair
            if (hashTable.containsKey(remainder)) {
                System.out.println("Solution: " + nums[i] + "+" + remainder + "=" + S);
            }
            //add the current number to the hashtable
            hashTable.put(nums[i], i);
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 5, 2, -4, 8, 11};
        int s = 7;
        DynamicProgramingTwoSum dynamicProgramingTwoSum = new DynamicProgramingTwoSum();
        dynamicProgramingTwoSum.Solution(nums, s);
    }
}
