/*
 * Problem: LeetCode 1559 - Detect Cycles in 2D Grid
 *
 * Problem Statement:
 * Given a 2D grid of characters, determine if there exists any cycle of length 4 or more 
 * consisting of the same character. A cycle is a path that starts and ends at the same 
 * cell and never visits the same cell twice (except for the start/end).
 *
 * Intuition:
 * A cycle in a graph is detected when we encounter a node that has already been visited. 
 * In a grid, we can move in four directions. To avoid false positives (moving back to 
 * the cell we just came from), we must track the "parent" cell. If we reach a visited 
 * cell of the same character that is NOT our parent, we have found a cycle.
 *
 * Approach:
 * 1. Maintain a 2D boolean array `vis` to track visited cells across the entire grid.
 * 2. Iterate through every cell in the grid. If a cell is not visited, start a DFS.
 * 3. In the DFS, pass the current coordinates, the parent coordinates, and the character.
 * 4. Use directional flags (s, n, e, w) to ensure the DFS does not move directly back 
 *     to the parent cell.
 * 5. If the DFS encounters a cell already marked in `vis` that matches the character 
 *     and isn't the parent, return true.
 *
 * Time Complexity: O(m * n) - Each cell is visited at most once by the DFS.
 * Space Complexity: O(m * n) - For the visited array and the recursion stack in the worst case.
 *
 * Edge Cases:
 * - Grid with only 1 or 2 cells (cannot form a cycle of length 4).
 * - Grid with no cycles.
 * - Multiple disjoint components of the same or different characters.
 *
 * Dry Run:
 * Grid: [['a', 'a'], ['a', 'a']]
 * 1. Start DFS at (0,0). vis[0][0] = true.
 * 2. Move to (0,1). vis[0][1] = true. Parent is (0,0).
 * 3. Move to (1,1). vis[1][1] = true. Parent is (0,1).
 * 4. Move to (1,0). vis[1][0] = true. Parent is (1,1).
 * 5. From (1,0), try moving to (0,0). (0,0) is visited and same char. 
 *    Since (0,0) is not the parent (1,1), cycle detected!
 *
 * Correctness Check:
 * The logic for preventing moving back to the parent (s, n, e, w) is correct. However, 
 * the initial call `dfs(i, j, 0, 0, ...)` uses (0,0) as a dummy parent. If the starting 
 * cell is (0,1), the logic `j - prevj != 1` will be false, preventing the DFS from 
 * checking (0,0) in the first step. While this doesn't break the overall algorithm 
 * (because (0,0) would have been visited by the outer loop already), using (-1, -1) 
 * as the initial parent would be more robust.
 */
public class DetectCyclesin2DGrid1559 {

    public boolean dfs(int i, int j, int previ, int prevj, char[][] grid, boolean[][] vis, char pc) {

        // Boundary check: Ensure we are within the grid limits.
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return false;
        }
        // Character match check: Only continue if the current cell matches the cycle's character.
        if (grid[i][j] != pc) {
            return false;
        }
        // Cycle detection: If we reach a visited cell of the same character, a cycle exists.
        // Note: The directional flags below ensure this visited cell isn't the one we just came from.
        if (vis[i][j]) {
            return true;
        }
        
        // Mark the current cell as visited to prevent infinite recursion and identify cycles.
        vis[i][j] = true;

        // Parent tracking logic:
        // s (South): Can we move to i+1? Only if the cell at i+1 wasn't our parent.
        // n (North): Can we move to i-1? Only if the cell at i-1 wasn't our parent.
        // e (East): Can we move to j+1? Only if the cell at j+1 wasn't our parent.
        // w (West): Can we move to j-1? Only if the cell at j-1 wasn't our parent.
        boolean s = i - previ != -1;
        boolean n = i - previ != 1;
        boolean e = j - prevj != -1;
        boolean w = j - prevj != 1;

        // Explore all four directions recursively. If any path finds a cycle, return true.
        return (s && dfs(i + 1, j, i, j, grid, vis, grid[i][j])) || (n && dfs(i - 1, j, i, j, grid, vis, grid[i][j]))
                || (e && dfs(i, j + 1, i, j, grid, vis, grid[i][j])) ||
                // Recursive/helper call processes a smaller subproblem before combining results.
                (w && dfs(i, j - 1, i, j, grid, vis, grid[i][j]));
    }

    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        // Global visited array to ensure we don't restart DFS on cells already processed.
        boolean[][] vis = new boolean[m][n];

        // Iterate through every cell in the grid.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If a cell hasn't been visited, it's the start of a new potential component.
                if (!vis[i][j] && dfs(i, j, 0, 0, grid, vis, grid[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }
}
