/*
 * Problem Statement: Find the element that appears more than n/2 times in the array.
 * Intuition: Pairwise-cancel different values; the true majority cannot be fully canceled out.
 * Approach: Boyer-Moore voting keeps a candidate and counter, resetting candidate when count reaches zero.
 * Time Complexity: O(n) single pass.
 * Space Complexity: O(1).
 * Edge Cases handled: Single-element arrays and mixed values where majority still exists.
 * Dry Run: [2,2,1,1,1,2,2] -> candidate changes/cancels, final candidate becomes 2.
 * LeetCode matching/assumption: Assumes LeetCode 169 guarantee that a majority element always exists.
 * Correctness Check: Under majority-exists guarantee, final candidate is correct without needing a second validation pass.
 */

public class Majorityelement {
    public int majorityElement(int[] nums){
        int count = 0;
        int candidate = 0;
        // Scan each value and adjust vote balance for current candidate.
        for(int num:nums){
            // When balance is zero, choose current number as new candidate.
            if(count==0){
                candidate = num;
            }
            // Same value strengthens candidate; different value cancels one vote.
            if(num==candidate){
                count++;
            }
            else{
                count--;
            }
        }
        return candidate;
        // This is Github co pilot's code
        

    }
}
