/*
 * Problem Reference: LeetCode 1559 - Detect Cycles in 2D Grid
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * DFS/BFS with parent tracking to detect back-edge in same-character region.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(m*n)
 *
 * Space Complexity:
 * O(m*n)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
public class DetectCyclesin2DGrid1559 {

    public boolean dfs(int i, int j, int previ, int prevj, char[][] grid, boolean[][] vis, char pc) {

        // Important guard: this branch handles a boundary or constraint-critical condition.
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return false;
        }
        if (grid[i][j] != pc) {
            return false;
        }
        if (vis[i][j]) {
            return true;
        }
        vis[i][j] = true;
        boolean s = i - previ != -1;
        boolean n = i - previ != 1;
        boolean e = j - prevj != -1;
        boolean w = j - prevj != 1;

        return (s && dfs(i + 1, j, i, j, grid, vis, grid[i][j])) || (n && dfs(i - 1, j, i, j, grid, vis, grid[i][j]))
                || (e && dfs(i, j + 1, i, j, grid, vis, grid[i][j])) ||
                // Recursive/helper call processes a smaller subproblem before combining results.
                (w && dfs(i, j - 1, i, j, grid, vis, grid[i][j]));
    }

    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];

        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j] && dfs(i, j, 0, 0, grid, vis, grid[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }
}
