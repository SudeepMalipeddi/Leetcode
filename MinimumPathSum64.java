/*
Problem Statement:
- Find minimum sum from top-left to bottom-right moving only right or down.

Intuition:
- Minimum path to (i,j) depends on min of top and left subproblems.

Approach:
- Use recursion with memoization table initialized to -1.

Time Complexity:
- O(m*n) because each cell is solved once.

Space Complexity:
- O(m*n) memo + O(m+n) recursion depth.

Edge Cases:
- Out-of-bounds branches return MAX_VALUE to avoid invalid paths.

Dry Run:
- At cell (i,j), compute min(minpath(i-1,j), minpath(i,j-1)) + grid[i][j].
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
