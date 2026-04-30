/*
 * Problem: 1219. Path with Maximum Gold
 *
 * Problem Statement:
 * Given an m x n grid of gold, find the maximum amount of gold you can collect. 
 * You can start and stop at any cell with gold, move in four directions (up, down, left, right), 
 * but you cannot visit the same cell more than once in a single path or enter a cell with 0 gold.
 *
 * Intuition:
 * This is a path-finding problem where we need to explore all possible simple paths to find the one 
 * with the maximum sum. Since the grid dimensions are small (up to 15x15), we can use Depth First 
 * Search (DFS) with backtracking. By starting a DFS from every cell that contains gold, we ensure 
 * we cover all possible starting points. Backtracking allows us to "unvisit" a cell so it can be 
 * part of a different path starting from a different neighbor or a different initial cell.
 *
 * Approach:
 * 1. Iterate through every cell (i, j) in the grid using nested loops.
 * 2. For each cell containing gold, initiate a DFS to calculate the maximum gold obtainable starting there.
 * 3. In the DFS function:
 *    - Base Case: If the coordinates are out of bounds or the cell has 0 gold, return 0.
 *    - Recursive Step: 
 *      a. Store the current cell's gold value.
 *      b. Mark the cell as visited by setting its value to 0.
 *      c. Recursively find the maximum gold from the four adjacent directions.
 *      d. Backtrack: Restore the cell's original gold value.
 *      e. Return the current cell's gold plus the maximum gold found from the neighbors.
 * 4. Maintain a global maximum to track the highest value returned by any DFS call.
 *
 * Time Complexity: O(K * 3^L), where K is the number of gold-bearing cells and L is the maximum 
 * length of a path. From each cell, we have at most 3 directions to explore (excluding the one we came from).
 * Space Complexity: O(L) for the recursion stack, where L is the maximum depth of the path.
 *
 * Edge Cases:
 * - Grid with no gold: Should return 0.
 * - Grid with all gold: DFS will explore the longest possible simple path.
 * - Disconnected gold patches: DFS from each patch will correctly find the local maximums.
 *
 * Dry Run:
 * Grid: [[1,0,7],[2,0,6]]
 * 1. Start at (0,0): Gold 1. Neighbors: (1,0) is 2. Path 1->2. Sum = 3.
 * 2. Start at (1,0): Gold 2. Neighbors: (0,0) is 1. Path 2->1. Sum = 3.
 * 3. Start at (0,2): Gold 7. Neighbors: (1,2) is 6. Path 7->6. Sum = 13.
 * 4. Start at (1,2): Gold 6. Neighbors: (0,2) is 7. Path 6->7. Sum = 13.
 * Result: Max(3, 3, 13, 13) = 13.
 *
 * Correctness Check:
 * The solution correctly uses backtracking to explore all paths. Marking the grid itself 
 * (grid[i][j] = 0) is an efficient way to track visited cells without extra space.
 */
public class PathwithMaximumGold1219 {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxGold = 0;
        
        // Iterate through every cell in the grid to consider it as a potential starting point.
        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j < n; j++) {
                // Update the global maximum with the result of a DFS starting from the current cell.
                maxGold = Math.max(maxGold, dfs(grid, i, j));
            }
        }
        return maxGold;
    }

    public int dfs(int[][] grid, int i, int j) {
        
        // Base case: If out of bounds or the cell has no gold (or is already visited), return 0.
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int temp = grid[i][j];
        // Mark visited to prevent revisiting this cell within the same path.
        grid[i][j] = 0;
        // Explore all four possible directions: left, right, up, and down.
        int left = dfs(grid, i, j - 1);
        int right = dfs(grid, i, j + 1);
        int up = dfs(grid, i - 1, j);
        int down = dfs(grid, i + 1, j);
        // Restore value so other DFS starts can reuse this cell.
        grid[i][j] = temp;
        // Return the gold at the current cell plus the maximum gold found in any of the four directions.
        return temp + Math.max(left, Math.max(right, Math.max(up, down)));
    }
}
