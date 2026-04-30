/*
 * Problem: 2373. Largest Local Values in a Matrix
 *
 * Problem Statement:
 * Given an n x n integer matrix 'grid', generate an (n-2) x (n-2) matrix 'res' where 
 * each element res[i][j] is the maximum value found in the 3x3 submatrix of 'grid' 
 * starting at row i and column j.
 *
 * Intuition:
 * This is a 2D sliding window problem. Since the window size is fixed at 3x3, 
 * we can iterate through every possible top-left corner (i, j) that allows a 
 * full 3x3 window to fit within the original grid boundaries.
 *
 * Approach:
 * 1. Calculate the dimensions of the result matrix, which will be (n-2) x (n-2).
 * 2. Use nested loops to iterate through each valid top-left corner (i, j).
 * 3. For each corner, use a helper function to scan the 3x3 area and find the maximum value.
 * 4. Store the found maximum in the result matrix.
 *
 * Time Complexity: O(N^2), where N is the side length of the grid. We iterate through 
 * approximately (N-2)^2 windows, and for each window, we perform exactly 9 comparisons.
 * Space Complexity: O(N^2) to store the resulting (N-2) x (N-2) matrix.
 *
 * Edge Cases:
 * - Minimum grid size (3x3): Results in a single 1x1 matrix.
 * - Grid with all identical values: Every cell in the result will be that value.
 *
 * Dry Run:
 * Input: grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]] (4x4)
 * Output size: (4-2)x(4-2) = 2x2.
 * - i=0, j=0: helper scans 3x3 from (0,0) to (2,2). Max is 9. res[0][0]=9.
 * - i=0, j=1: helper scans 3x3 from (0,1) to (2,3). Max is 9. res[0][1]=9.
 * - i=1, j=0: helper scans 3x3 from (1,0) to (3,2). Max is 8. res[1][0]=8.
 * - i=1, j=1: helper scans 3x3 from (1,1) to (3,3). Max is 6. res[1][1]=6.
 * Result: [[9,9],[8,6]]
 *
 * Correctness Check:
 * The solution is correct. It properly handles the boundary conditions (n-2) and 
 * correctly implements the 3x3 local maximum search.
 */

public class LargestLocalValuesinaMatrix2373 {

    public int[][] largestLocal(int[][] grid) {
        // The output matrix is (n-2)x(n-2) because a 3x3 window cannot start 
        // in the last two rows or last two columns of the original grid.
        int r = grid.length - 2, c = grid[0].length - 2;
        int[][] res = new int[r][c];

        // Iterate through every possible top-left corner (i, j) of a 3x3 submatrix.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // Delegate the task of finding the maximum in the 3x3 block to a helper.
                res[i][j] = helper(grid, i, j);
            }
        }
        return res;
    }

    public int helper(int[][] grid, int i, int j) {
        // Initialize max with the smallest possible integer to ensure any grid value updates it.
        int max = Integer.MIN_VALUE;
        
        // Define the exclusive upper boundaries for the 3x3 scan.
        int r = i + 3, c = j + 3;
        
        // Standard nested loop to traverse a 3x3 region starting at (i, j).
        for (int p = i; p < r; p++) {
            for (int q = j; q < c; q++) {
                // Update the local maximum if the current cell is larger.
                max = Math.max(max, grid[p][q]);
            }
        }
        return max;
    }
}
