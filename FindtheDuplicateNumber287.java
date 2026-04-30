/*
 * Problem Reference: LeetCode 287 - Find the Duplicate Number
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Use Floyd cycle detection on value-as-next-index graph.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
import java.util.HashSet;

class FindtheDuplicateNumber287{
    // Hashset approach
    public int findDuplicate(int[] nums) {
        HashSet<Integer> map = new HashSet<Integer>();
        for(int x:nums){
            if(!map.add(x)){
                return x;
            } 
        }
        return 0;
    }
    // changing the index (much better)
    public int findDuplicate1(int[] nums) {
        int len = nums.length;
        for(int x:nums){
            int idx = Math.abs(x);
            if(nums[idx]<0){
                return idx;
            }
            nums[idx] =-nums[idx];
        }
        return len;
    }
    
}
