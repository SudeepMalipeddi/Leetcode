/*
 * Problem: 70. Climbing Stairs
 *
 * Problem Statement:
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct
 * ways can you climb to the top?
 *
 * Intuition:
 * To reach step 'i', you must have arrived from either step 'i-1' (by taking a 1-step jump)
 * or step 'i-2' (by taking a 2-step jump). Therefore, the total number of ways to reach 
 * step 'i' is the sum of the ways to reach 'i-1' and 'i-2'. This relationship is 
 * identical to the Fibonacci sequence.
 *
 * Approach:
 * 1. Handle base cases: for n = 0 or n = 1, there is only 1 way to be at that position.
 * 2. Use a space-optimized Dynamic Programming approach by storing only the results 
 *     of the two previous steps instead of an entire array.
 * 3. Iteratively calculate the sum from step 2 up to n.
 *
 * Time Complexity: O(n) because we iterate from 2 to n exactly once.
 * Space Complexity: O(1) because we only maintain a constant number of integer variables.
 *
 * Edge Cases:
 * - n = 1: The loop is skipped, returns 1.
 * - n = 2: The loop runs once, returns 2.
 *
 * Dry Run:
 * n = 4
 * - Initial: prev = 1 (ways to step 0), curr = 1 (ways to step 1)
 * - i = 2: temp = 1, curr = 1 + 1 = 2, prev = 1
 * - i = 3: temp = 2, curr = 1 + 2 = 3, prev = 2
 * - i = 4: temp = 3, curr = 2 + 3 = 5, prev = 3
 * - Result: 5
 *
 * Correctness Check:
 * The solution is correct. It correctly implements the state transition 
 * dp[i] = dp[i-1] + dp[i-2] using constant space.
 */
public class ClimbingStairs70 {
    public int climbStairs(int n) {
        // Base cases: If there are 0 or 1 steps, there is only 1 distinct way to reach the top.
        if (n == 0 || n == 1) {
            return 1;
        }
        
        // prev represents ways(i-2), curr represents ways(i-1).
        // For i=2, these represent ways to reach step 0 and step 1.
        int prev = 1, curr = 1; // ways for steps 0 and 1
        
        // Start calculating from step 2 up to n.
        for (int i = 2; i <= n; i++) {
            // Store the current value (ways to reach i-1) before updating it.
            int temp = curr;
            
            // The number of ways to reach the current step is the sum of the ways to reach the previous two.
            curr = prev + curr; // ways(i) = ways(i-1) + ways(i-2)
            
            // Update prev to be the old curr (ways to reach i-1) for the next iteration.
            prev = temp;
        }
        
        // After the loop, curr holds the total ways to reach step n.
        return curr;
    }
}
