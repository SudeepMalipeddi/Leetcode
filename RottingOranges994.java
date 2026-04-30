/*
 * Problem: 994. Rotting Oranges
 *
 * Problem Statement:
 * You are given an m x n grid where each cell can have one of three values: 0 (empty), 1 (fresh), 
 * or 2 (rotten). Every minute, any fresh orange that is 4-directionally adjacent to a rotten 
 * orange becomes rotten. Return the minimum number of minutes that must elapse until no cell 
 * has a fresh orange. If this is impossible, return -1.
 *
 * Intuition:
 * This is a classic Breadth-First Search (BFS) problem on a 2D grid. Since all rotten oranges 
 * spread the "rot" simultaneously to their neighbors every minute, we need to process the 
 * grid level-by-level. BFS is ideal for finding the shortest time (distance) in unweighted 
 * scenarios where multiple sources (rotten oranges) start spreading at once.
 *
 * Approach:
 * 1. Scan the grid to find all initially rotten oranges and add their coordinates to a Queue.
 * 2. Simultaneously, count the total number of oranges (fresh + rotten) to verify at the end 
 *     if all fresh oranges were reached.
 * 3. Perform BFS: For each level (representing one minute), process all oranges currently in 
 *     the queue.
 * 4. For each rotten orange, check its 4 neighbors. If a neighbor is fresh (1), turn it 
 *     rotten (2) and add it to the queue for the next minute's processing.
 * 5. Increment the minute counter only if new oranges were infected in the current round.
 *
 * Time Complexity: O(M * N) where M is rows and N is columns. We visit each cell at most once.
 * Space Complexity: O(M * N) in the worst case where all oranges are rotten and stored in the queue.
 *
 * Edge Cases:
 * - Grid with no fresh oranges: Should return 0 immediately.
 * - Fresh oranges that are unreachable (surrounded by empty cells): Should return -1.
 * - Empty grid: Handled by initial checks.
 *
 * Dry Run:
 * Grid: [[2,1,1], [1,1,0], [0,1,1]]
 * 1. Initial: Queue = [(0,0)], count_fresh (total oranges) = 7.
 * 2. Min 0: Pop (0,0). Neighbors (0,1) and (1,0) become rotten. Queue = [(0,1), (1,0)]. cnt = 1.
 * 3. Min 1: Pop (0,1), (1,0). Neighbors (0,2), (1,1) become rotten. Queue = [(0,2), (1,1)]. cnt = 3.
 * 4. Min 2: Pop (0,2), (1,1). Neighbor (2,1) becomes rotten. Queue = [(2,1)]. cnt = 5.
 * 5. Min 3: Pop (2,1). Neighbor (2,2) becomes rotten. Queue = [(2,2)]. cnt = 6.
 * 6. Min 4: Pop (2,2). No more fresh neighbors. Queue empty. cnt = 7.
 * Result: count_fresh (7) == cnt (7), return min (4).
 *
 * Correctness Check:
 * The solution is correct. Note that the variable 'count_fresh' actually tracks the total 
 * number of oranges (fresh + rotten), and 'cnt' tracks how many oranges have been processed 
 * by the BFS. If they match, all oranges became rotten.
 */

import java.util.*;

public class RottingOranges994 {
    public int rottenOranges(int[][] grid) {

        if (grid.length == 0 || grid == null)
            return 0;
        int m = grid.length;
        int n = grid[0].length;

        // Queue stores coordinates [row, col] of rotten oranges for BFS
        Queue<int[]> q = new LinkedList<>();

        // count_fresh here represents the total number of oranges (fresh + rotten)
        int count_fresh = 0;

        // Initial pass: find all rotten oranges and count total oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[] { i, j });
                }
                if (grid[i][j] != 0) {
                    count_fresh++;
                }
            }
        }

        // If there are no oranges at all, 0 minutes are needed
        if (count_fresh == 0)
            return 0;

        int min = 0, cnt = 0;
        // Direction vectors for 4-way movement: right, left, down, up
        int dx[] = { 0, 0, 1, -1 };
        int dy[] = { 1, -1, 0, 0 };

        // Standard BFS loop
        while (!q.isEmpty()) {
            int size = q.size();
            // cnt tracks how many oranges have entered/exited the queue
            cnt += size;
            
            // Process all oranges that turned rotten at the exact same minute
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                
                // Check all 4 adjacent neighbors
                for (int j = 0; j < 4; j++) {
                    int x = cur[0] + dx[j];
                    int y = cur[1] + dy[j];

                    // Skip if out of bounds, empty cell, or already rotten
                    if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0 || grid[x][y] == 2) {
                        continue;
                    }
                    
                    // Infect the fresh orange
                    grid[x][y] = 2;
                    q.offer(new int[] { x, y });
                }
            }
            
            // Only increment time if the current batch of rotten oranges 
            // actually infected new oranges (which are now in the queue)
            if (!q.isEmpty()) {
                min++;
            }
        }
        
        // If the number of processed oranges equals the total oranges, return time; else -1
        return count_fresh == cnt ? min : -1;
    }
}
