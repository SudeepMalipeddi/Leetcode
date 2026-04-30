/*
 * Problem: 63. Unique Paths II
 *
 * Problem Statement:
 * Given a grid where 1 represents an obstacle and 0 represents an empty space, find the 
 * number of unique paths from the top-left corner (0,0) to the bottom-right corner (m-1, n-1).
 * You can only move down or right at any point in time.
 *
 * Intuition:
 * This is a classic dynamic programming problem. The number of ways to reach a specific cell (r, c)
 * is the sum of the ways to reach the cell above it (r-1, c) and the cell to its left (r, c-1).
 * If a cell contains an obstacle, the number of ways to reach it is 0.
 *
 * Approach:
 * 1. Use a 2D array `dp` for memoization to store the number of paths calculated for each cell.
 * 2. Initialize the `dp` table with -1 to distinguish between unvisited and visited cells.
 * 3. Use a recursive function `f` to explore paths from the destination back to the source.
 * 4. Base cases handle boundaries, obstacles, and the starting cell.
 *
 * Time Complexity: O(m * n) because each cell in the grid is computed at most once due to memoization.
 * Space Complexity: O(m * n) for the memoization table and the recursion stack depth.
 *
 * Edge Cases:
 * - The starting cell (0,0) is an obstacle.
 * - The destination cell (m-1, n-1) is an obstacle.
 * - A grid of size 1x1.
 * - Obstacles blocking all possible paths.
 *
 * Dry Run:
 * Input: obs = [[0,0],[0,1],[0,0]], Target: (2,1)
 * f(2,1) -> calls f(1,1) and f(2,0)
 * f(1,1) -> obstacle, returns 0
 * f(2,0) -> calls f(1,0) and f(2,-1)
 * f(1,0) -> calls f(0,0) and f(1,-1)
 * f(0,0) -> start, returns 1
 * Result propagates back: 1 + 0 = 1 path.
 *
 * Correctness Check:
 * - BUG 1: The memoization check `if (dp[r][c] != 1)` is logically incorrect. Since the array is 
 *   initialized with -1, it should check `if (dp[r][c] != -1)`. As written, it will likely 
 *   re-calculate paths repeatedly if the result isn't exactly 1.
 * - BUG 2: The recursive call `int left = f(obs, r - 1, c, dp);` is incorrect. To move "left" 
 *   relative to the current cell (or find the cell to the left), it should decrement the 
 *   column index: `f(obs, r, c - 1, dp)`.
 */

import java.util.Arrays;

public class UniquePathsII63 {
    public static int uniquePathsWithObstacles(int[][] obs) {
        int m = obs.length, n = obs[0].length;
        // Initialize memoization table to store results of subproblems
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            // Fill with -1 to indicate that no paths have been calculated for these cells yet
            Arrays.fill(row, -1);
        }
        // Start the recursive process from the bottom-right corner
        return f(obs, m - 1, n - 1, dp);
    }

    public static int f(int[][] obs, int r, int c, int[][] dp) {
        // Base Case: If out of bounds or the current cell is an obstacle, no paths exist through here
        if (r < 0 || c < 0 || obs[r][c] == 1) {
            return 0;
        }
        // Base Case: If we reached the starting cell (0,0), we found exactly 1 valid path
        if (r == 0 && c == 0) {
            return 1;
        }
        // Memoization Lookup: Return the value if already computed
        // NOTE: Logic error here; should check against -1, not 1
        if (dp[r][c] != 1) {
            return dp[r][c];
        }
        // Recursive Step: The number of ways to reach (r, c) is the sum of ways to reach 
        // the cell above it and the cell to its left.
        int up = f(obs, r - 1, c, dp);
        // NOTE: Logic error here; the second call should decrement 'c' to move left, not 'r'
        int left = f(obs, r - 1, c, dp);
        
        // Store the result in the memoization table and return it
        return dp[r][c] = up + left;
    }
}
