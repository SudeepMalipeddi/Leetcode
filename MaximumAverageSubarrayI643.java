/*
 * Problem: 643. Maximum Average Subarray I
 *
 * Problem Statement:
 * Given an array consisting of n integers, find the contiguous subarray of given length k 
 * that has the maximum average value. You need to return this maximum value.
 *
 * Intuition:
 * Since the length k is fixed, the subarray with the maximum average is also the subarray 
 * with the maximum sum (Average = Sum / k). Instead of recalculating the sum for every 
 * possible subarray of length k, we can use a "Sliding Window" to update the sum in O(1) 
 * time as we move across the array.
 *
 * Approach:
 * 1. Calculate the sum of the first k elements to initialize our window.
 * 2. Calculate the average of this first window and set it as our initial result.
 * 3. Slide the window from index k to the end of the array.
 * 4. For each step, add the new element entering the window and subtract the element leaving it.
 * 5. Update the maximum average found so far.
 *
 * Time Complexity: O(n), where n is the number of elements in the array. We traverse the array once.
 * Space Complexity: O(1), as we only use a few variables to store the sum and the result.
 *
 * Edge Cases:
 * - k = 1: The window is a single element; the max average is the max element.
 * - k = nums.length: The window is the entire array.
 * - Negative numbers: The maximum average can be negative, requiring careful initialization.
 *
 * Dry Run:
 * nums = [1, 12, -5, -6, 50, 3], k = 4
 * 1. Initial window (0 to 3): 1 + 12 - 5 - 6 = 2. res = 2 / 4 = 0.5.
 * 2. Slide to index 4: Add 50, subtract 1. currSum = 2 + 50 - 1 = 51. res = max(0.5, 51/4) = 12.75.
 * 3. Slide to index 5: Add 3, subtract 12. currSum = 51 + 3 - 12 = 42. res = max(12.75, 42/4) = 12.75.
 * Result: 12.75.
 *
 * Correctness Check:
 * The current implementation initializes 'res = 0'. This is a logic bug for arrays where 
 * all possible k-length averages are negative (e.g., nums = [-5], k = 1). In such cases, 
 * Math.max(-5, 0) would incorrectly return 0. To fix this, 'res' should be initialized 
 * to the average of the first window or Double.NEGATIVE_INFINITY.
 */

/*
 * Problem Statement: Return the maximum average value among all contiguous subarrays of length k.
 * Intuition: Maximum average for fixed k is equivalent to maximum sum for fixed-size sliding windows.
 * Approach: Compute first window sum, then slide window by adding new element and removing old element, tracking best average.
 * Time Complexity: O(n), one pass after initial k-sum.
 * Space Complexity: O(1).
 * Edge Cases handled: k equals array length, mixed positive/negative numbers, and repeated values.
 * Dry Run: nums=[1,12,-5,-6,50,3], k=4 -> window sums: 2, 51, 42; best average = 51/4.
 * LeetCode matching/assumption: Matches LeetCode 643 fixed-window approach.
 * Correctness Check: Important note: current code initializes res=0, which is wrong for all-negative arrays; robust logic should initialize from first window average.
 */

public class MaximumAverageSubarrayI643 {
    public static double findMaxAverage(int[] nums, int k) {
        // Potential Bug: Initializing to 0 will fail if all subarray averages are negative.
        double res = 0;
        double currSum = 0;
        
        // Build the initial window sum for indices [0, k-1].
        // This establishes our starting point for the sliding window.
        for (int i = 0; i < k; i++) {
            currSum += nums[i];
        }
        
        // Calculate the average of the first window.
        // Note: Division by k is performed here; ensure k is not 0 (usually guaranteed by constraints).
        res = Math.max(currSum / k, res);
        
        // Slide the k-sized window across the array and update best average.
        // i represents the index of the element entering the window from the right.
        for (int i = k; i < nums.length; i++) {
            // Sliding Window Logic:
            // Add the new element (nums[i]) and subtract the element that just left (nums[i - k]).
            // This maintains the sum of exactly k elements in O(1) time.
            currSum += nums[i] - nums[i - k];
            
            // Compare the average of the current window with the best seen so far.
            res = Math.max(currSum / k, res);
        }
        
        return res;
    }
}
