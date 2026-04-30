/*
 * Problem: 1020. Number of Enclaves
 *
 * Problem Statement:
 * Given an m x n binary matrix grid, where 0 represents water and 1 represents land,
 * count the number of land cells for which we cannot walk off the boundary of the grid
 * in any number of moves (i.e., land cells that are not connected to the edge).
 *
 * Intuition:
 * Any land cell connected to the boundary is not an enclave. If we identify all land
 * reachable from the four edges and "sink" them (convert them to water), the remaining
 * land cells in the grid must be enclaves because they have no path to the boundary.
 *
 * Approach:
 * 1. Iterate through the grid and identify land cells (1s) located on the boundaries.
 * 2. For every boundary land cell, perform a Depth First Search (DFS) to mark all
 *    connected land cells as 0 (water). This effectively removes non-enclaves.
 * 3. After processing all boundary-connected components, iterate through the grid again.
 * 4. Count the remaining 1s; these are the enclaves that were isolated from the edges.
 *
 * Time Complexity: O(m * n) where m is the number of rows and n is the number of columns.
 * Each cell is visited at most a constant number of times during the boundary scan, DFS, and final count.
 * Space Complexity: O(m * n) in the worst case for the recursion stack if the grid is entirely land.
 *
 * Edge Cases:
 * - Grid with no land: Returns 0.
 * - Grid where all land is on the boundary: Returns 0.
 * - Grid where all land is in the center: Returns the total count of land cells.
 *
 * Dry Run:
 * Grid: [[0,0,0,0], [1,0,1,0], [0,1,1,0], [0,0,0,0]]
 * 1. Boundary scan finds grid[1][0] is 1.
 * 2. DFS(1,0) starts: grid[1][0] becomes 0. No neighbors are 1.
 * 3. Grid is now: [[0,0,0,0], [0,0,1,0], [0,1,1,0], [0,0,0,0]]
 * 4. Final count pass: grid[1][2], grid[2][1], and grid[2][2] are 1s.
 * 5. Result: 3.
 *
 * Correctness Check:
 * The solution correctly uses a "sink-from-boundary" strategy to isolate enclaves.
 * The DFS handles 4-directional connectivity as required.
 */
public class NumberofEnclaves1020 {

    public static void dfs(int i, int j, int[][] grid) {
        // Base case: stop if indices are out of bounds or if the current cell is water/already visited
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] == 0) {
            return;
        }

        // Mark the current land cell as water (0) to indicate it's connected to the boundary
        grid[i][j] = 0;

        // Recursively visit all four adjacent neighbors to sink the entire connected island
        dfs(i + 1, j, grid);
        dfs(i - 1, j, grid);
        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);
    }

    public static int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // First pass: Scan the grid to find land cells that touch the boundary
        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j < n; j++) {
                
                if (grid[i][j] == 1) {
                    
                    // If the land cell is on the first/last row or first/last column, it can reach the boundary.
                    // Flood-fill boundary land so only enclosed land remains for counting.
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                        dfs(i, j, grid);
                    }
                }
            }
        }
        int res = 0;
        
        // Second pass: Count all land cells that were not reachable from the boundary (still marked as 1)
        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j < n; j++) {
                
                if (grid[i][j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }
}
