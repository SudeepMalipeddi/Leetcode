/*
 * Problem: 547. Number of Provinces
 *
 * Problem Statement:
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, 
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * Given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly 
 * connected, and isConnected[i][j] = 0 otherwise, return the total number of provinces.
 *
 * Intuition:
 * This problem asks for the number of connected components in an undirected graph. 
 * Each city is a node, and an entry of 1 in the adjacency matrix represents an edge. 
 * By performing a traversal (DFS or BFS) starting from an unvisited node, we can visit 
 * all nodes in its connected component. The number of times we need to initiate a 
 * traversal to cover all nodes equals the number of provinces.
 *
 * Approach:
 * 1. Initialize a boolean array 'visited' of size n to keep track of cities already processed.
 * 2. Iterate through every city from 0 to n-1.
 * 3. If a city 'i' has not been visited, it marks the discovery of a new province. Increment the count.
 * 4. Trigger a Depth First Search (DFS) starting from city 'i' to mark all cities in this province as visited.
 * 5. In the DFS, for a given city, check all other cities 'j'. If city 'i' and 'j' are connected and 'j' 
 *    is unvisited, recursively call DFS for city 'j'.
 *
 * Time Complexity: O(n^2)
 * We iterate through the matrix of size n x n. Each node is visited once, and for each node, 
 * we check all its n potential neighbors in the adjacency matrix.
 *
 * Space Complexity: O(n)
 * The 'visited' array takes O(n) space. The recursion stack for DFS can go up to O(n) 
 * in the worst case (e.g., a single province where cities are connected in a line).
 *
 * Edge Cases:
 * - No connections: Every city is its own province (n provinces).
 * - Fully connected: All cities belong to one province (1 province).
 * - Single city: Only 1 province.
 *
 * Dry Run:
 * isConnected = [[1,1,0], [1,1,0], [0,0,1]]
 * 1. i = 0: visited[0] is false. Increment ans to 1. Call dfs(0).
 *    - dfs(0): visited[0] = true. Check neighbors of 0.
 *    - j = 1: isConnected[0][1] is 1 and visited[1] is false. Call dfs(1).
 *      - dfs(1): visited[1] = true. Check neighbors of 1.
 *      - j = 0: visited[0] is true. Skip.
 *      - j = 2: isConnected[1][2] is 0. Skip.
 *    - j = 2: isConnected[0][2] is 0. Skip.
 * 2. i = 1: visited[1] is true. Skip.
 * 3. i = 2: visited[2] is false. Increment ans to 2. Call dfs(2).
 *    - dfs(2): visited[2] = true. Check neighbors of 2.
 *    - j = 0, 1: isConnected[2][j] is 0. Skip.
 * 4. Loop ends. Return ans = 2.
 *
 * Correctness Check:
 * The solution correctly identifies connected components using DFS. The use of a visited array 
 * ensures each node is processed exactly once, preventing infinite loops and redundant work.
 */
public class NumberofProvinces547 {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length, ans = 0;
        // visited array ensures we don't count the same province multiple times
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            
            // A new DFS from an unvisited city identifies one full province.
            if (!visited[i]) {
                ans++;
                // Explore all cities reachable from city i to mark the entire province
                dfs(isConnected, visited, i);
            }
        }
        return ans;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int i) {
        // Mark current city as visited so it's not processed again
        visited[i] = true;
        int n = isConnected.length;
        
        // Check all potential neighbors j for the current city i
        for (int j = 0; j < n; j++) {
            
            // If there is a direct connection and city j hasn't been visited yet
            if (isConnected[i][j] == 1 && !visited[j]) {
                // Move deeper into the graph to find all indirectly connected cities
                dfs(isConnected, visited, j);
            }
        }
    }
}
