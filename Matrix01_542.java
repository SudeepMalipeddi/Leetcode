/*
 * Problem: 542. 01 Matrix
 *
 * Problem Statement:
 * Given an m x n binary matrix, return the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * Intuition:
 * This is a shortest path problem on an unweighted grid. Instead of running a separate BFS 
 * from every '1' (which would be O(M^2 * N^2)), we use Multi-Source BFS. By starting 
 * from all '0's simultaneously, the BFS "wavefront" reaches each '1' at the earliest 
 * possible time, which is guaranteed to be the shortest distance.
 *
 * Approach:
 * 1. Create a result matrix 'ans' and initialize all values to -1 (representing unvisited).
 * 2. Add all coordinates of '0' cells into a Queue and set their distance in 'ans' to 0.
 * 3. Perform BFS: Poll a cell, check its 4 neighbors.
 * 4. If a neighbor is within bounds and unvisited (ans == -1), update its distance 
 *    to current_distance + 1 and add it to the queue.
 *
 * Time Complexity: O(m * n) - Each cell is added to the queue and processed exactly once.
 * Space Complexity: O(m * n) - Required for the 'ans' matrix and the BFS queue in the worst case.
 *
 * Edge Cases:
 * - Matrix with all 0s: All distances remain 0.
 * - Matrix with only one 0: Distances increment linearly away from that single source.
 * - Rectangular matrices (m != n).
 *
 * Dry Run:
 * Input: [[0,0,0], [0,1,0], [1,1,1]]
 * 1. Queue starts with all 0s: (0,0), (0,1), (0,2), (1,0), (1,2). ans[i][j]=0 for these.
 * 2. Pop (1,0): Neighbor (1,1) is -1. Set ans[1][1] = 0 + 1 = 1. Add (1,1) to Queue.
 * 3. Pop (1,1): Neighbor (2,1) is -1. Set ans[2][1] = 1 + 1 = 2. Add (2,1) to Queue.
 * ... and so on until all -1s are filled.
 *
 * Correctness Check:
 * CRITICAL BUG: The condition `ans[x][y] != -1` in the BFS loop is logically inverted. 
 * BFS should process neighbors that are UNVISITED (ans[x][y] == -1). 
 * As written, the code skips unvisited cells and only attempts to process cells 
 * that have already been assigned a distance, which will lead to incorrect results 
 * or an empty result set for '1's.
 */

import java.util.*;

public class Matrix01_542 {
    // Helper class to store coordinates in the BFS queue.
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

        // Initialize all distances as unvisited (-1).
        // This allows us to distinguish between a distance of 0 and a cell not yet reached.
        for (int[] a : ans) {
            Arrays.fill(a, -1);
        }

        // Seed BFS queue with every zero-cell (multi-source start points).
        // All zeros are at distance 0 from the nearest zero.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    q.offer(new Pair(i, j));
                    ans[i][j] = 0;
                }
            }
        }

        // Direction vectors for 4-way movement: Down, Right, Up, Left.
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

        // Standard BFS traversal from nearest known distance outward.
        while (!q.isEmpty()) {
            Pair p = q.poll();

            // Explore 4-connected neighbors.
            for (int i = 0; i < 4; i++) {
                int x = p.x + dir[i][0];
                int y = p.y + dir[i][1];

                // Check bounds and if the neighbor has been visited.
                // NOTE: The logic 'ans[x][y] != -1' is a bug. It should be 'ans[x][y] == -1'.
                // BFS logic requires visiting the unvisited nodes to propagate distances.
                if (x >= 0 && y >= 0 && x < m && y < n && ans[x][y] != -1) {
                    // Update neighbor's distance based on current cell's distance + 1.
                    ans[x][y] = ans[p.x][p.y] + 1;
                    // Add neighbor to queue to explore its own neighbors in the next BFS layer.
                    q.add(new Pair(x, y));
                }
            }
        }
        return ans;
    }
}
