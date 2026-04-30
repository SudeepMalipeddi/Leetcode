/*
 * Problem: 62. Unique Paths
 *
 * Problem Statement:
 * A robot is located at the top-left corner (0, 0) of an m x n grid. The robot 
 * can only move either down or right at any point in time. The robot is trying 
 * to reach the bottom-right corner (m-1, n-1). How many unique paths are there?
 *
 * Intuition:
 * To reach any cell (i, j), the robot must have come from either the cell above it (i-1, j) 
 * or the cell to its left (i, j-1). Therefore, the number of ways to reach (i, j) is 
 * the sum of the ways to reach (i-1, j) and (i, j-1). This is a classic 
 * Combinatorics/Dynamic Programming problem.
 *
 * Approach:
 * 1. Recursive: Break the problem into subproblems: paths(m, n) = paths(m-1, n) + paths(m, n-1).
 * 2. Memoization: Store results of subproblems in a 2D array to avoid redundant calculations.
 * 3. Tabulation: Build a 2D table bottom-up, where each cell stores the number of paths to reach it.
 *
 * Time Complexity: O(m * n) for DP approaches, as we visit each cell once.
 * Space Complexity: O(m * n) to store the DP table.
 *
 * Edge Cases:
 * - m=1, n=1: Only 1 path (already at destination).
 * - m=1 or n=1: Only 1 path (straight line).
 *
 * Dry Run (m=3, n=2):
 * dp[0][0] = 1
 * dp[0][1] = dp[0][0] = 1
 * dp[1][0] = dp[0][0] = 1
 * dp[1][1] = dp[0][1] + dp[1][0] = 2
 * dp[2][0] = dp[1][0] = 1
 * dp[2][1] = dp[1][1] + dp[2][0] = 3
 * Result: 3
 *
 * Correctness Check:
 * - BUG ALERT: In f1(), the recursive calls are to f() instead of f1(). This means 
 *   memoization is bypassed for subproblems, leading to exponential time complexity.
 * - BUG ALERT: In unqpath(), the loops run to m-1 and n-1 (exclusive), meaning the 
 *   last row and column are never calculated. The return dp[m-1][n-1] will be 0.
 */

import java.util.Arrays;

public class UniquePaths62 {
    // Pure Recursive Approach (Brute Force)
    public static int f(int m, int n) {
        // Base Case: If we reach the starting cell, we found 1 valid path
        if (m == 0 && n == 0)
            return 1;
        // Base Case: If we go out of bounds, this path is invalid
        if (m < 0 || n < 0)
            return 0;

        // The total paths to (m, n) is the sum of paths from above and from the left
        return f(m - 1, n) + f(m, n - 1);
    }

    // Top-Down DP (Memoization)
    public static int f1(int m, int n, int[][] dp) {
        if (m == 0 && n == 0)
            return 1;
        if (m < 0 || n < 0)
            return 0;
        
        // Return cached result if we have already computed paths for this cell
        if (dp[m][n] != -1)
            return dp[m][n];

        // NOTE: Logic error here - should call f1 recursively to utilize memoization.
        // Calling f() performs a fresh recursive search without checking the dp table.
        return dp[m][n] = f(m - 1, n) + f(m, n - 1);
    }

    // Wrapper for Memoization
    public static int uniquePath(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] a : dp)
            Arrays.fill(a, -1);
        dp[0][0] = 1;
        return f1(m - 1, n - 1, dp);
    }

    // Bottom-Up DP (Tabulation)
    public static int unqpath(int m, int n) {
        int[][] dp = new int[m][n];
        // NOTE: Loop boundaries 'm-1' and 'n-1' are incorrect; they should be 'm' and 'n'.
        // As written, the last row and last column of the DP table remain 0.
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                // Base case: Starting point
                if (i == 0 && j == 0) {
                    dp[0][0] = 1;
                } else {
                    int up = 0;
                    int left = 0;
                    // If not on the top edge, we can come from above
                    if (i > 0)
                        up = dp[i - 1][j];
                    // If not on the left edge, we can come from the left
                    if (j > 0)
                        left = dp[i][j - 1];
                    
                    // State Transition: Current cell = paths from top + paths from left
                    dp[i][j] = up + left;
                }
            }
        }
        // Returns the value at the target destination
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        // System.out.println(uniquePath(m, n));
        System.out.println(unqpath(m, n));
    }

}
