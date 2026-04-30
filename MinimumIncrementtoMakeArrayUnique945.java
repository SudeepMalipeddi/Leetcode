/*
 * Problem Statement: Make all array values unique using minimum increments, and return total increments used.
 * Intuition: After sorting, each value only needs to be compared with previous value to enforce strict increase.
 * Approach: Sort array; if nums[i] is not greater than nums[i-1], bump nums[i] to nums[i-1]+1 and add cost.
 * Time Complexity: O(n log n) due to sorting.
 * Space Complexity: O(1) extra (excluding sort internals).
 * Edge Cases handled: Already unique array; many duplicates; consecutive collisions after an increment.
 * Dry Run: [3,2,1,2,1,7] -> sorted [1,1,2,2,3,7] -> adjusted [1,2,3,4,5,7], total increments 6.
 * LeetCode matching/assumption: Matches LeetCode 945 (Minimum Increment to Make Array Unique).
 * Correctness Check: Sorted order ensures local fix at index i is globally optimal because only increments are allowed.
 */

import java.util.*;

public class MinimumIncrementtoMakeArrayUnique945 {
    public int minIncrementForUnique(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        // Ensure each element is strictly greater than its left neighbor.
        for (int i = 1; i < nums.length; i++) {
            // Collision or regression: raise current value to previous+1 and add required increments.
            if (nums[i] <= nums[i - 1]) {
                res += nums[i - 1] + 1 - nums[i];
                nums[i] = nums[i - 1] + 1;
            }
        }
        return res;
    }
}
