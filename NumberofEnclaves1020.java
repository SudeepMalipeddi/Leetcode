/*
Problem Statement:
- Count land cells that cannot reach boundary in a binary grid.

Intuition:
- Remove boundary-connected land first; remaining land is enclosed.

Approach:
- DFS from all boundary 1-cells to mark reachable land as 0, then count remaining 1s.

Time Complexity:
- O(m*n).

Space Complexity:
- O(m*n) worst-case recursion stack.

Edge Cases:
- All-water and all-boundary-connected grids return 0.

Dry Run:
- Boundary DFS floods reachable islands; inner untouched island cells are counted.
*/
public class NumberofEnclaves1020 {

    public static void dfs(int i, int j, int[][] grid) {
        
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;

        dfs(i + 1, j, grid);
        dfs(i - 1, j, grid);
        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);
    }

    public static int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        
        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j < n; j++) {
                
                if (grid[i][j] == 1) {
                    
                    // Flood-fill boundary land so only enclosed land remains for counting.
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                        dfs(i, j, grid);
                    }
                }
            }
        }
        int res = 0;
        
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
