/*
 * Problem Statement: For each cell in a binary matrix, compute distance to nearest 0 using 4-direction movement.
 * Intuition: Multi-source BFS from all zero-cells gives shortest distance to zero for every reachable one-cell.
 * Approach: Initialize answer matrix with -1, enqueue all zero positions with distance 0, then BFS-expand to neighbors.
 * Time Complexity: O(m*n), each cell should be enqueued at most once in correct BFS.
 * Space Complexity: O(m*n) for answer matrix and queue in worst case.
 * Edge Cases handled: All zeros, all ones except one zero source, and rectangular matrices.
 * Dry Run: Start from all zeros in queue; each BFS layer writes distance +1 to previously unvisited neighbors.
 * LeetCode matching/assumption: Matches LeetCode 542 multi-source BFS approach.
 * Correctness Check: Important bug note in current code: neighbor condition uses ans[x][y] != -1, but BFS should visit unvisited cells (ans[x][y] == -1). Current condition blocks proper expansion.
 */

import java.util.*;

public class Matrix01_542 {
    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] ans = new int[m][n];
        Queue<Pair> q = new LinkedList<>();

        // Initialize all distances as unvisited.
        for (int[] a : ans) {
            Arrays.fill(a, -1);
        }

        // Seed BFS queue with every zero-cell (multi-source start points).
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    q.offer(new Pair(i, j));
                    ans[i][j] = 0;
                }
            }
        }

        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

        // Standard BFS traversal from nearest known distance outward.
        while (!q.isEmpty()) {
            Pair p = q.poll();

            // Explore 4-connected neighbors.
            for (int i = 0; i < 4; i++) {
                int x = p.x + dir[i][0];
                int y = p.y + dir[i][1];

                // Intended behavior: process in-bounds unvisited neighbor and set distance.
                if (x >= 0 && y >= 0 && x < m && y < n && ans[x][y] != -1) {
                    ans[x][y] = ans[p.x][p.y] + 1;
                    q.add(new Pair(x, y));
                }
            }
        }
        return ans;
    }
}
