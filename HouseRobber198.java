/*
 * Problem: LeetCode 198 - House Robber
 *
 * Problem Statement:
 * You are a professional robber planning to rob houses along a street. Each house
 * has a certain amount of money stashed. Adjacent houses have connected security
 * systems that will automatically contact the police if two adjacent houses are
 * broken into on the same night. Given an integer array nums representing the
 * amount of money in each house, return the maximum amount you can rob without
 * alerting the police.
 *
 * Intuition:
 * At each house i, the robber has two choices: skip house i (take the best from
 * houses 0..i-1) or rob house i (take nums[i] plus the best from houses 0..i-2).
 * This gives the recurrence: dp[i] = max(dp[i-1], dp[i-2] + nums[i]).
 *
 * Approach:
 *   rob (Top-Down Memoization):
 *   1. Create dp array of size n filled with -1.
 *   2. Call rec(nums, n-1, dp).
 *   3. rec(nums, n, dp): Base case: if n < 0 return 0. If dp[n] != -1 return
 *      dp[n]. Return dp[n] = max(rec(nums, n-1, dp), rec(nums, n-2, dp) + nums[n]).
 *
 *   rob1 (Bottom-Up Tabulation):
 *   1. If n == 1 return nums[0].
 *   2. Initialize dp[0] = nums[0], dp[1] = max(nums[0], nums[1]).
 *   3. For i from 2 to n-1: dp[i] = max(dp[i-1], dp[i-2] + nums[i]).
 *   4. Return dp[n-1].
 *
 *   rob2 (Space-Optimized):
 *   1. If n == 1 return nums[0].
 *   2. Set first = nums[0], second = max(nums[0], nums[1]).
 *   3. For i from 2 to n-1: temp = second, second = max(second, first + nums[i]),
 *      first = temp.
 *   4. Return second.
 *
 * Time Complexity: O(n) for all three approaches — each house is processed once.
 * Space Complexity: rob: O(n) (dp array + recursion stack), rob1: O(n) (dp array),
 * rob2: O(1) (two variables).
 *
 * Edge Cases:
 * - n = 1: rob returns rec(nums, 0, dp); rob1 and rob2 early-return nums[0].
 * - n = 2: dp[1] = max(nums[0], nums[1]), correct for the two-house scenario.
 * - All zeros: returns 0.
 * - Large values: within int range per LeetCode constraints (no overflow).
 *
 * Dry Run (rob2):
 * nums = [2, 7, 9, 3, 1]
 * n = 5, n != 1
 * first = 2, second = max(2, 7) = 7
 *
 * i=2: temp = 7, second = max(7, 2 + 9) = max(7, 11) = 11, first = 7
 * i=3: temp = 11, second = max(11, 7 + 3) = max(11, 10) = 11, first = 11
 * i=4: temp = 11, second = max(11, 11 + 1) = max(11, 12) = 12, first = 11
 *
 * Loop ends. Return second = 12.
 * (Optimal: rob houses at indices 0, 2, 4 → 2 + 9 + 1 = 12.)
 *
 * Correctness Check:
 * The DP recurrence dp[i] = max(dp[i-1], dp[i-2] + nums[i]) exhaustively covers
 * the two choices at each step. By induction, dp[i] contains the optimal profit
 * for the subarray nums[0..i], so dp[n-1] gives the global optimum.
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
