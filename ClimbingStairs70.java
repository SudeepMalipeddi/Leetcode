/*
 * Problem: LeetCode 70 - Climbing Stairs
 * Problem Statement: Given n steps, each move can climb 1 or 2 steps. Return
 *   the number of distinct ways to reach the top.
 * Intuition: The number of ways to reach step i equals ways(i-1) + ways(i-2),
 *   which is the Fibonacci recurrence.
 * Approach:
 *   1) Handle base cases for n = 0 or 1.
 *   2) Iteratively compute the Fibonacci sequence up to n using two variables.
 * Time Complexity: O(n).
 * Space Complexity: O(1).
 * Edge Cases: n=1, n=2, large n.
 * Dry Run: n=4 -> ways: 1,1,2,3,5 => answer 5.
 * Correctness Check: The recurrence matches the choice of the last step (1 or 2).
 */
public class ClimbingStairs70 {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int prev = 1, curr = 1; // ways for steps 0 and 1
        for (int i = 2; i <= n; i++) {
            int temp = curr;
            curr = prev + curr; // ways(i) = ways(i-1) + ways(i-2)
            prev = temp;
        }
        return curr;
    }
}
