/*
 * Problem: 167. Two Sum II - Input Array Is Sorted
 *
 * Problem Statement:
 * Given a 1-indexed array of integers that is already sorted in non-decreasing order, 
 * find two numbers such that they add up to a specific target number.
 *
 * Intuition:
 * Because the array is sorted, we can use a two-pointer approach to efficiently narrow 
 * down the search space. If the current sum is too small, we need a larger value, which 
 * is found to the right of the 'start' pointer. If the sum is too large, we need a 
 * smaller value, found to the left of the 'end' pointer.
 *
 * Approach:
 * 1. Initialize two pointers: 'start' at the beginning (index 0) and 'end' at the last index.
 * 2. Calculate the sum of elements at these two positions.
 * 3. If sum == target, return the 1-based indices [start + 1, end + 1].
 * 4. If sum < target, increment 'start' to increase the total sum.
 * 5. If sum > target, decrement 'end' to decrease the total sum.
 *
 * Time Complexity: O(n) - In the worst case, we examine each element at most once.
 * Space Complexity: O(1) - We only use a constant amount of extra space for pointers.
 *
 * Edge Cases:
 * - Array with exactly two elements.
 * - Target is the sum of the first and last elements.
 * - Negative numbers (the logic still holds as long as the array is sorted).
 *
 * Dry Run:
 * Input: nums = [2, 7, 11, 15], target = 9
 * 1. start=0 (val 2), end=3 (val 15). sum=17. 17 > 9, so end--.
 * 2. start=0 (val 2), end=2 (val 11). sum=13. 13 > 9, so end--.
 * 3. start=0 (val 2), end=1 (val 7). sum=9. 9 == 9, return [1, 2].
 *
 * Correctness Check:
 * The solution is correct. The problem guarantees exactly one solution, so the loop 
 * will always find the target and return from within. The final return statement 
 * is technically unreachable but necessary for Java's compilation rules.
 */
public class TwoSum2 {
    public int[] twoSum(int[] nums, int target) {
        // We have to return the indices+1 of the elements that add up to the target
        // We will be using two pointers like the solution to the question of Valid
        // Palindrome question
        
        // Initialize pointers at the two extremes of the sorted array
        int start = 0;
        int end = nums.length - 1;

        // Keeping a loop to check if the sum of the elements in start and end indices
        // are equal to target or not
        while (start <= end) {
            // Calculate the current sum to determine which pointer to move
            int sum = nums[start] + nums[end];

            if (sum < target) {
                // Since the array is sorted, moving the start pointer right 
                // increases the sum for the next iteration
                start++;
            } else if (sum > target) {
                // Moving the end pointer left decreases the sum for the next iteration
                end--;
            } else {
                // Found the target; return 1-based indices as requested by the problem
                return new int[] { start + 1, end + 1 };
            }
        }

        // Fallback return statement to satisfy the compiler; 
        // under problem constraints, this line is never reached.
        return new int[] { start + 1, end + 1 };
    }
}
