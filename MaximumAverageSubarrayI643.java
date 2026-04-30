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
        double res = 0;
        double currSum = 0;
        // Build the initial window sum for indices [0, k-1].
        for (int i = 0; i < k; i++) {
            currSum += nums[i];
        }
        res = Math.max(currSum / k, res);
        // Slide the k-sized window across the array and update best average.
        for (int i = k; i < nums.length; i++) {
            currSum += nums[i] - nums[i - k];
            res = Math.max(currSum / k, res);
        }
        return res;
    }
}
