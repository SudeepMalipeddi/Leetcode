/*
 * Problem Statement:
 * Given an integer array nums, find the contiguous subarray with the largest sum
 * and return that sum.
 *
 * Intuition:
 * If the running sum becomes negative, carrying it forward only hurts future sums,
 * so restart from the current element.
 *
 * Approach:
 * Implements Kadane-style dynamic updates:
 * - maxSubArray: current best ending at i vs start fresh at i.
 * - maxSubarray: running sum reset when negative.
 *
 * Time Complexity:
 * O(n), one pass.
 *
 * Space Complexity:
 * O(1), constant extra variables.
 *
 * Edge Cases handled:
 * - All values negative -> returns largest (least negative) element.
 * - Single element array -> returns that element.
 * - Mixed values with multiple candidate ranges.
 *
 * Dry Run:
 * nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Best segment becomes [4,-1,2,1] with sum 6.
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 53 (Kadane's algorithm pattern).
 *
 * Correctness Check:
 * At each index, maintain optimal subarray ending there and global optimum so far.
 */

public class MaximumSubarray53 {

    public int maxSubArray(int[] nums) {
        // Base state: best answer and current running best both start at first value.
        int max_sum = nums[0];
        int curr_sum = nums[0];

        // Extend or restart subarray at each index, then update global best.
        for (int i = 1; i < nums.length; i++) {
            curr_sum = Math.max(nums[i], nums[i] + curr_sum);
            max_sum = Math.max(curr_sum, max_sum);
        }
        return max_sum;
    }

    public int maxSubarray(int[] nums) {
        int sum = 0;
        int res = nums[0];

        // Running-sum variant of Kadane.
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res = Math.max(res, sum); // Best subarray sum seen so far.
            if (sum < 0) {
                sum = 0; // Negative prefix cannot help future subarrays.
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
