/*
 * Problem: LeetCode 213 - House Robber II
 *
 * Problem Statement:
 * You are a professional robber planning to rob houses along a street. All houses are arranged in a circle, 
 * meaning the first house is the neighbor of the last one. You cannot rob two adjacent houses. 
 * Given an integer array nums, return the maximum amount of money you can rob without alerting the police.
 *
 * Intuition:
 * The circular constraint means we can never rob both the first and the last house simultaneously. 
 * This allows us to break the circular problem into two linear sub-problems:
 * 1. Rob houses from index 0 to n-2 (ignore the last house).
 * 2. Rob houses from index 1 to n-1 (ignore the first house).
 * The maximum of these two linear scenarios will be the global maximum for the circular arrangement.
 *
 * Approach:
 * 1. Create two sub-arrays representing the two linear scenarios.
 * 2. Use a standard linear House Robber algorithm (implemented in HouseRobber198) to solve each.
 * 3. Return the maximum of the two results.
 *
 * Time Complexity: O(n) because we perform a constant number of linear passes (copying and robbing).
 * Space Complexity: O(n) in this implementation because Arrays.copyOfRange creates new arrays of size n-1.
 *
 * Edge Cases:
 * - Single house: The current code will return 0 because both slices will be empty.
 * - Two houses: Correctly picks the maximum of the two.
 *
 * Dry Run:
 * nums = [2, 3, 2]
 * 1. num1 = [2, 3] (indices 0 to 1)
 * 2. num2 = [3, 2] (indices 1 to 2)
 * 3. res1 = HouseRobber198.rob([2, 3]) -> 3
 * 4. res2 = HouseRobber198.rob([3, 2]) -> 3
 * 5. Math.max(3, 3) -> 3
 *
 * Correctness Check:
 * The logic for handling circularity is sound. However, there is a bug for the case where nums.length == 1. 
 * If nums = [5], num1 and num2 will both be empty arrays, resulting in 0 instead of 5. 
 * A check for nums.length == 1 should be added before slicing.
 */
import java.util.Arrays;

public class HouseRobberII213 {
    public static void main(String[] args) {
        int[] nums = { 2, 3, 2 };
        // Circular dependency is broken into two linear robber runs: exclude last or exclude first house.
        
        // Scenario 1: Exclude the last house. This allows the first house to be a candidate for robbing.
        int[] num1 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        
        // Scenario 2: Exclude the first house. This allows the last house to be a candidate for robbing.
        int[] num2 = Arrays.copyOfRange(nums, 1, nums.length);

        // Solve the linear version of the problem for the first range.
        int res1 = HouseRobber198.rob(num1);
        
        // Solve the linear version of the problem for the second range.
        int res2 = HouseRobber198.rob(num2);
        
        // The final answer is the maximum loot possible from either breaking the circle at the end or the start.
        System.out.println(Math.max(res1, res2));
    }
}
