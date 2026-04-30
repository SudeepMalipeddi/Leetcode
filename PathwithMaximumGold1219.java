/*
Problem Statement:
- Find maximum gold collectable by walking adjacent non-zero cells without revisiting a cell.

Intuition:
- DFS from each start cell with backtracking explores all valid path sums.

Approach:
- Temporarily mark current cell as 0, recurse 4 directions, restore cell, return current gold + best branch.

Time Complexity:
- Exponential in path length; bounded by DFS branching over non-zero cells.

Space Complexity:
- O(path length) recursion stack.

Edge Cases:
- Zero cells stop traversal immediately.

Dry Run:
- Start at a gold cell, explore all 4 branches, choose max returned branch.
*/
public class PathwithMaximumGold1219 {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxGold = 0;
        
        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j < n; j++) {
                maxGold = Math.max(maxGold, dfs(grid, i, j));
            }
        }
        return maxGold;
    }

    public int dfs(int[][] grid, int i, int j) {
        
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int temp = grid[i][j];
        // Mark visited to prevent revisiting this cell within the same path.
        grid[i][j] = 0;
        int left = dfs(grid, i, j - 1);
        int right = dfs(grid, i, j + 1);
        int up = dfs(grid, i - 1, j);
        int down = dfs(grid, i + 1, j);
        // Restore value so other DFS starts can reuse this cell.
        grid[i][j] = temp;
        return temp + Math.max(left, Math.max(right, Math.max(up, down)));
    }
}
