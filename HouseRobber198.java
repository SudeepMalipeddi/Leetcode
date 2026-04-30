/*
 * Problem Reference: LeetCode 198 - House Robber
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * DP: best up to i is max(skip i, rob i + best i-2).
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1) or O(n)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */

/*
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police 
 * if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money 
 * of each house, return the maximum amount of money you can rob
 *  tonight without alerting the police.
 * 
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    Total amount you can rob = 1 + 3 = 4.
    Input: nums = [2,7,9,3,1]
    Output: 12
    Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
    Total amount you can rob = 2 + 9 + 1 = 12.
 */

import java.util.Arrays;

public class HouseRobber198 {

    public static int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return rec(nums, n - 1, dp);
    }

    public static int rec(int[] nums, int n, int[] dp) {
        // Important guard: this branch handles a boundary or constraint-critical condition.
        if (n < 0)
            return 0;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = Math.max(rec(nums, n - 1, dp), rec(nums, n - 2, dp) + nums[n]);
    }

    // optimized solution
    public int rob1(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    // space optimized solution
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }
        return second;
    }
}
