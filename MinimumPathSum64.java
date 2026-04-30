/*
 * Problem: LeetCode 64 - Minimum Path Sum
 *
 * Problem Statement:
 * Given an m x n grid filled with non-negative numbers, find a path from the
 * top-left to the bottom-right corner that minimizes the sum of all numbers
 * along the path. You can only move right or down at each step.
 *
 * Intuition:
 * The minimum cost to reach cell (i, j) depends only on the minimum cost to
 * reach its top neighbor (i-1, j) or its left neighbor (i, j-1) — whichever
 * is cheaper. This optimal substructure makes it a textbook DP problem.
 * Memoizing the recursive calls avoids recomputing overlapping sub-problems.
 *
 * Approach:
 * 1. Use top-down recursion: minpath(i, j) = grid[i][j] + min(minpath(i-1,j), minpath(i,j-1)).
 * 2. Base case: (0,0) returns grid[0][0].
 * 3. Out-of-bounds indices return Integer.MAX_VALUE so they are never chosen by Math.min.
 * 4. Store results in a dp table (initialized to -1) to skip recomputation.
 * 5. Entry point calls minpath(m-1, n-1) to get the answer for the bottom-right cell.
 *
 * Time Complexity: O(m*n) — each cell is computed exactly once and cached.
 * Space Complexity: O(m*n) for the dp table + O(m+n) recursion stack depth.
 *
 * Edge Cases:
 * - Single cell grid: returns grid[0][0] directly via the base case.
 * - Paths that go out of bounds return MAX_VALUE, ensuring they are never selected.
 *
 * Dry Run (grid = [[1,3,1],[1,5,1],[4,2,1]]):
 * minpath(2,2) = 1 + min(minpath(1,2), minpath(2,1))
 *   minpath(1,2) = 1 + min(minpath(0,2), minpath(1,1)) = 1 + min(4, 7) = 5
 *   minpath(2,1) = 2 + min(minpath(1,1), minpath(2,0)) = 2 + min(7, 5) = 7
 * minpath(2,2) = 1 + min(5,7) = 6  ✓ (path: 1→3→1→1→1)
 *
 * Correctness Check:
 * Looks correct. Integer.MAX_VALUE sentinel works as long as no actual path
 * sum overflows — safe here since grid values are non-negative and grids are small.
 */
import java.util.Arrays;

public class MinimumPathSum64 {
    public int minpath(int[][] arr, int i, int j, int[][] dp) {
        
        if (i == 0 && j == 0) {
            return arr[0][0];
        }
        
        // Return a sentinel so invalid paths are never chosen by Math.min.
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        // Current cell cost equals its value plus min(top, left) subpath.
        int left = minpath(arr, i, j - 1, dp);
        int up = minpath(arr, i - 1, j, dp);
        return dp[i][j] = Math.min(left, up) + arr[i][j];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return minpath(grid, m - 1, n - 1, dp);
    }
}
