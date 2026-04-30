/*
 * Problem: 945. Minimum Increment to Make Array Unique
 *
 * Problem Statement:
 * Given an integer array nums, in one move, you can pick an index i and increment nums[i] by 1.
 * Return the minimum number of moves to make every value in nums unique.
 *
 * Intuition:
 * To minimize increments, we want each number to be as small as possible while still being unique.
 * By sorting the array, we can process numbers in increasing order. If a number is not strictly 
 * greater than the one before it, we must increment it to at least (previous + 1). This greedy 
 * approach works because any larger increment would only increase the total cost without 
 * helping satisfy the uniqueness constraint more efficiently.
 *
 * Approach:
 * 1. Sort the array to bring duplicates together and establish a processing order.
 * 2. Iterate through the array starting from the second element (index 1).
 * 3. Compare the current element with its predecessor. If it's not greater, calculate the 
 *    difference needed to make it exactly 1 greater than the predecessor.
 * 4. Add this difference to the result and update the current element in the array.
 *
 * Time Complexity: O(n log n) because sorting is the dominant operation. The linear scan is O(n).
 * Space Complexity: O(log n) for the space used by the sorting algorithm (Dual-Pivot Quicksort in Java).
 *
 * Edge Cases:
 * - Array with all identical elements: [2, 2, 2] -> [2, 3, 4], total 3 increments.
 * - Array already unique: [1, 2, 3] -> 0 increments.
 * - Array with large gaps: [1, 10, 10] -> [1, 10, 11], total 1 increment.
 *
 * Dry Run:
 * Input: [3, 2, 1, 2, 1, 7]
 * Sorted: [1, 1, 2, 2, 3, 7]
 * i=1: nums[1]=1, nums[0]=1. 1 <= 1 is true. res += (1+1-1) = 1. nums[1]=2.
 * i=2: nums[2]=2, nums[1]=2. 2 <= 2 is true. res += (2+1-2) = 1. nums[2]=3.
 * i=3: nums[3]=2, nums[2]=3. 2 <= 3 is true. res += (3+1-2) = 2. nums[3]=4.
 * i=4: nums[4]=3, nums[3]=4. 3 <= 4 is true. res += (4+1-3) = 2. nums[4]=5.
 * i=5: nums[5]=7, nums[4]=5. 7 <= 5 is false. No change.
 * Total res = 6.
 *
 * Correctness Check:
 * The solution is correct. Sorting allows a greedy local decision (making nums[i] = nums[i-1] + 1)
 * to be globally optimal because we only move values "up" and never need to look back at 
 * previously processed elements.
 */

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
        // Sorting is the crucial first step; it allows us to handle duplicates greedily in a single pass.
        Arrays.sort(nums);
        // Ensure each element is strictly greater than its left neighbor.
        for (int i = 1; i < nums.length; i++) {
            // Collision or regression: raise current value to previous+1 and add required increments.
            // If the current number is a duplicate or smaller than the previous unique value.
            if (nums[i] <= nums[i - 1]) {
                // Calculate the gap needed to make nums[i] exactly nums[i-1] + 1.
                res += nums[i - 1] + 1 - nums[i];
                // Update the current element to its new unique value so the next iteration can compare against it.
                nums[i] = nums[i - 1] + 1;
            }
        }
        return res;
    }
}
