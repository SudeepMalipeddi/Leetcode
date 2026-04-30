/*
 * Problem: Maximum Sum Subarray of size K
 *
 * Problem Statement:
 * Given an array of integers and a number k, find the maximum sum of any contiguous subarray of size k.
 *
 * Intuition:
 * Instead of recalculating the sum for every possible subarray (which repeats many additions), 
 * we can "slide" a window of size k across the array. When the window moves, we only need 
 * to add the new element entering the window and subtract the old element leaving it.
 *
 * Approach:
 * 1. Brute Force: Use nested loops to calculate the sum of every possible k-length subarray.
 * 2. Sliding Window: Calculate the sum of the first k elements. Then, iterate from index k to n-1, 
 *    updating the sum in O(1) time by adding the current element and removing the element that 
 *    is no longer in the window.
 *
 * Time Complexity: 
 * - maxSubArray (Brute Force): O(n * k) because for each of the (n-k+1) starting positions, we sum k elements.
 * - maxSum (Sliding Window): O(n) because we traverse the array once.
 *
 * Space Complexity: O(1) for both as we only use a constant amount of extra space for variables.
 *
 * Edge Cases:
 * - k > n: The window size is larger than the array size (handled in maxSum).
 * - k = 1: The problem reduces to finding the maximum element in the array.
 * - Negative numbers: The logic holds as long as max_sum is initialized correctly.
 *
 * Dry Run:
 * arr = [1, 4, 2, 10, 2], k = 3
 * 1. Initial window (1+4+2) = 7. max_sum = 7.
 * 2. Slide to index 3: window_sum = 7 + 10 - 1 = 16. max_sum = 16.
 * 3. Slide to index 4: window_sum = 16 + 2 - 4 = 14. max_sum = 16.
 * Final Result: 16.
 *
 * Correctness Check:
 * The solution is correct. The sliding window approach efficiently finds the maximum sum 
 * by maintaining a running total, avoiding the O(k) summation inside the main loop.
 */

public class MaxSumKSubarray {
    public int maxSubArray(int[] nums, int n, int k) {
        // Brute force: evaluate each possible starting index for a k-length subarray.
        int max_sum = Integer.MIN_VALUE;
        // The loop runs until n-k to ensure there are at least k elements remaining to form a window.
        for (int i = 0; i < n - k + 1; i++) {
            int curr_sum = 0;
            // Compute sum for current fixed window [i, i+k-1].
            // This nested loop is what leads to the O(n*k) time complexity.
            for (int j = 0; j < k; j++) {
                curr_sum += nums[i + j];
            }
            max_sum = Math.max(curr_sum, max_sum);
        }
        return max_sum;
    }

    // Sliding Window approach
    static int maxSum(int arr[], int n, int k) {
        // Guard invalid case where window length exceeds array size.
        if (n < k) {
            System.out.println("Invalid");
            return -1;
        }

        // Build initial window sum [0, k-1].
        // This serves as our starting point before we begin sliding.
        int max_sum = 0;
        for (int i = 0; i < k; i++)
            max_sum += arr[i];

        // Shift window right: remove outgoing element, add incoming element.
        int window_sum = max_sum;
        // We start from index k because indices 0 to k-1 are already summed.
        for (int i = k; i < n; i++) {
            // arr[i] is the new element entering the window from the right.
            // arr[i - k] is the element that was at the start of the previous window and is now leaving.
            window_sum += arr[i] - arr[i - k];
            // Keep track of the highest sum encountered during the slide.
            max_sum = Math.max(max_sum, window_sum);
        }

        return max_sum;
    }
}
