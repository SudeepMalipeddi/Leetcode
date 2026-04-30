/*
 * Problem: 53. Maximum Subarray
 *
 * Problem Statement:
 * Given an integer array nums, find the contiguous subarray (containing at least one number) 
 * which has the largest sum and return its sum.
 *
 * Intuition:
 * This is a classic Dynamic Programming problem solvable via Kadane's Algorithm. 
 * The core insight is that a subarray sum is only worth extending if the sum accumulated 
 * so far is positive. If the previous sum is negative, it will only reduce the value 
 * of the current element, so we are better off starting a new subarray from the current index.
 *
 * Approach:
 * 1. Initialize two variables: one for the maximum sum found so far and one for the current subarray sum.
 * 2. Iterate through the array. At each step, decide whether to "join" the existing subarray 
 *    or "start fresh" at the current element.
 * 3. Update the global maximum whenever the current subarray sum exceeds it.
 *
 * Time Complexity: O(n) - We perform a single pass through the array.
 * Space Complexity: O(1) - We only use a constant amount of extra space for variables.
 *
 * Edge Cases:
 * - Array with one element: The element itself is the maximum sum.
 * - All negative numbers: The result will be the largest (least negative) single element.
 *
 * Dry Run:
 * nums = [-2, 1, -3, 4]
 * 1. Start: max_sum = -2, curr_sum = -2
 * 2. i=1 (val=1): curr_sum = max(1, 1 + -2) = 1. max_sum = max(-2, 1) = 1.
 * 3. i=2 (val=-3): curr_sum = max(-3, -3 + 1) = -2. max_sum = max(1, -2) = 1.
 * 4. i=3 (val=4): curr_sum = max(4, 4 + -2) = 4. max_sum = max(1, 4) = 4.
 * Result: 4
 *
 * Correctness Check:
 * The solution correctly handles negative numbers by initializing with the first element 
 * rather than zero, ensuring that if all numbers are negative, the maximum single element is returned.
 */

public class MaximumSubarray53 {

    public int maxSubArray(int[] nums) {
        // Base state: best answer and current running best both start at first value.
        // We use nums[0] instead of 0 to correctly handle arrays containing only negative numbers.
        int max_sum = nums[0];
        int curr_sum = nums[0];

        // Extend or restart subarray at each index, then update global best.
        for (int i = 1; i < nums.length; i++) {
            // DP Transition: Should we add the current element to the existing sum, 
            // or is the current element alone better than the sum?
            curr_sum = Math.max(nums[i], nums[i] + curr_sum);
            
            // Keep track of the highest curr_sum we have ever encountered.
            max_sum = Math.max(curr_sum, max_sum);
        }
        return max_sum;
    }

    public int maxSubarray(int[] nums) {
        int sum = 0;
        int res = nums[0];

        // Running-sum variant of Kadane.
        for (int i = 0; i < nums.length; i++) {
            // Add current element to the running sum.
            sum += nums[i];
            
            // Update the global result. We do this before resetting sum to 0 
            // to handle cases where all elements are negative.
            res = Math.max(res, sum); 
            
            // If the running sum drops below zero, it will only decrease the sum 
            // of any future subarray. We "reset" by discarding the current prefix.
            if (sum < 0) {
                sum = 0; 
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int arr[] = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        MaximumSubarray53 obj = new MaximumSubarray53();
        System.out.println(obj.maxSubArray(arr));
    }
}
