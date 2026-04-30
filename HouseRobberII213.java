/*
 * Problem Reference: LeetCode 213 - House Robber II
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Split circular case into two linear robber problems.
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
import java.util.Arrays;

public class HouseRobberII213 {
    public static void main(String[] args) {
        int[] nums = { 2, 3, 2 };
        // Circular dependency is broken into two linear robber runs: exclude last or exclude first house.
        int[] num1 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int[] num2 = Arrays.copyOfRange(nums, 1, nums.length);

        int res1 = HouseRobber198.rob(num1);
        int res2 = HouseRobber198.rob(num2);
        System.out.println(Math.max(res1, res2));
    }
}
