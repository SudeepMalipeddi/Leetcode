/*
 * Problem: 79. Word Search
 *
 * Problem Statement:
 * Given an m x n grid of characters 'board' and a string 'word', return true if 'word' exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are 
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a path.
 *
 * Intuition:
 * This is a path-finding problem in a 2D grid where we need to match a specific sequence. 
 * Backtracking (Depth-First Search) is ideal here because it allows us to explore a potential 
 * path character by character and "undo" our steps (backtrack) if we hit a dead end.
 *
 * Approach:
 * 1. Iterate through every cell in the grid to find a potential starting point (matching word[0]).
 * 2. For each starting point, initiate a DFS to explore all 4 adjacent directions.
 * 3. Use a 'visited' matrix to ensure we don't reuse the same cell in a single path.
 * 4. If the current character matches and we've reached the end of the word, we found a solution.
 * 5. If a path fails, backtrack by marking the current cell as unvisited so it can be used in other paths.
 *
 * Time Complexity: O(M * N * 3^L)
 * Where M*N is the grid size and L is the length of the word. From each cell, we explore 3 directions 
 * (excluding the direction we just came from).
 *
 * Space Complexity: O(L) for the recursion stack, plus O(M * N) for the visited array.
 *
 * Edge Cases:
 * - Word length is 1.
 * - Word is longer than the total number of cells in the grid.
 * - The word does not exist in the grid.
 *
 * Dry Run:
 * Board: [['A','B'],['C','D']], Word: "AC"
 * 1. Start at (0,0): 'A' matches "AC"[0].
 * 2. Mark (0,0) visited. Call DFS for neighbors.
 * 3. Check (1,0): 'C' matches "AC"[1]. index (1) == length-1. Set res = true.
 * 4. Return true.
 *
 * Correctness Check:
 * The solution is logically sound. Note: Using a static variable 'res' is generally discouraged 
 * in multi-threaded environments or competitive programming platforms that run multiple test 
 * cases in the same JVM instance without resetting state, though 'res = false' at the start 
 * of 'exist' mitigates this.
 */

public class WordSearch79 {
    // Global flag to stop recursion early once the word is found
    static boolean res = false;

    public static void dfs(int i, int j, int index, char[][] board, boolean[][] visited, String target) {
        // Base Case: Out of bounds, already visited, character mismatch, or word already found
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]
                || board[i][j] != target.charAt(index) || res) {
            return;
        }

        // Success Case: If we reached the last character of the target string
        if (index == target.length() - 1) {
            res = true;
            return;
        }

        // Mark the current cell as visited before exploring neighbors
        visited[i][j] = true;

        // Explore all 4 cardinal directions: Down, Up, Right, Left
        dfs(i + 1, j, index + 1, board, visited, target);
        dfs(i - 1, j, index + 1, board, visited, target);
        dfs(i, j + 1, index + 1, board, visited, target);
        dfs(i, j - 1, index + 1, board, visited, target);

        // Backtracking Step: Unmark the current cell so it can be used by other potential paths
        visited[i][j] = false;
    }

    public static boolean exist(char[][] board, String word) {
        // Reset the global result for every new search call
        res = false;
        int m = board.length;
        int n = board[0].length;

        // Iterate through every cell in the grid as a potential starting point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Optimization: Only start DFS if the first character matches
                if (board[i][j] == word.charAt(0)) {
                    // Create a fresh visited array for each starting point to track the current path
                    dfs(i, j, 0, board, new boolean[m][n], word);
                    // If DFS found the word, return immediately
                    if (res == true) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
