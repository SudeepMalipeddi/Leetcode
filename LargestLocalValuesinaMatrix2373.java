/*
 * Problem Statement: For each 3x3 submatrix in grid, compute its maximum value and return the matrix of those maxima.
 * Intuition: Every output cell corresponds to one top-left anchored 3x3 window in the original grid.
 * Approach: Iterate all valid window starts (i,j), call helper to scan the 3x3 window, store max in result[i][j].
 * Time Complexity: O((n-2)*(n-2)*9) => O(n^2), because each output cell scans a fixed 3x3 block.
 * Space Complexity: O((n-2)^2) for output matrix; helper uses O(1) extra scalars.
 * Edge Cases handled: Works for minimum n=3 (result is 1x1); handles any integer values via Integer.MIN_VALUE init.
 * Dry Run: grid=[[9,9,8],[5,6,2],[8,2,6]] => only one 3x3 window, max=9, result=[[9]].
 * LeetCode matching: Matches LeetCode 2373 (Largest Local Values in a Matrix).
 * Correctness Check: Window bounds and scans are consistent; no correctness issue observed.
 */

public class LargestLocalValuesinaMatrix2373 {

    public int[][] largestLocal(int[][] grid) {
        // Number of valid 3x3 window start rows/cols.
        int r = grid.length - 2, c = grid[0].length - 2;
        int[][] res = new int[r][c];

        // Enumerate each 3x3 window's top-left row.
        for (int i = 0; i < r; i++) {
            // Enumerate each 3x3 window's top-left column.
            for (int j = 0; j < c; j++) {
                // Compute local maximum for window [i..i+2][j..j+2].
                res[i][j] = helper(grid, i, j);
            }
        }
        return res;
    }

    public int helper(int[][] grid, int i, int j) {
        // Track max inside one 3x3 window.
        int max = Integer.MIN_VALUE;
        // Exclusive bounds for the 3x3 region.
        int r = i + 3, c = j + 3;
        // Scan window rows.
        for (int p = i; p < r; p++) {
            // Scan window columns.
            for (int q = j; q < c; q++) {
                // Keep the largest value seen so far in this window.
                max = Math.max(max, grid[p][q]);
            }
        }
        return max;
    }
}
