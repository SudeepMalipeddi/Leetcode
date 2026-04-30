/*
 * Problem Statement: Find the maximum sum among all contiguous subarrays of fixed size k.
 * Intuition: Compare every k-length window sum; sliding window avoids recomputing shared elements.
 * Approach: This file includes both brute force O(n*k) and optimized sliding window O(n) implementations.
 * Time Complexity: maxSubArray -> O((n-k+1)*k), maxSum -> O(n).
 * Space Complexity: O(1) for both methods.
 * Edge Cases handled: n<k guard in sliding version and any integer values in the array.
 * Dry Run: For [100,200,300,400], k=2 -> window sums 300,500,700, answer 700.
 * LeetCode matching/assumption: Generic fixed-size window maximum sum (no exact LeetCode id assumed here).
 * Correctness Check: Sliding window remains correct by subtracting outgoing element and adding incoming element per shift.
 */

public class MaxSumKSubarray {
    public int maxSubArray(int[] nums, int n, int k) {
        // Brute force: evaluate each possible starting index for a k-length subarray.
        int max_sum = Integer.MIN_VALUE;
        for (int i = 0; i < n - k + 1; i++) {
            int curr_sum = 0;
            // Compute sum for current fixed window [i, i+k-1].
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
        int max_sum = 0;
        for (int i = 0; i < k; i++)
            max_sum += arr[i];

        // Shift window right: remove outgoing element, add incoming element.
        int window_sum = max_sum;
        for (int i = k; i < n; i++) {
            window_sum += arr[i] - arr[i - k];
            max_sum = Math.max(max_sum, window_sum);
        }

        return max_sum;
    }
}
