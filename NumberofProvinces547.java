/*
Problem Statement:
- Given adjacency matrix of cities, return number of connected components (provinces).

Intuition:
- Each unvisited city starts one DFS traversal covering its province.

Approach:
- Iterate cities; on unvisited node, increment answer and DFS neighbors with connection=1.

Time Complexity:
- O(n^2) due to matrix scan per DFS layer.

Space Complexity:
- O(n) visited + recursion stack.

Edge Cases:
- Fully disconnected matrix gives n provinces.

Dry Run:
- Start DFS at city 0, mark all reachable; next unvisited city starts next province.
*/
public class NumberofProvinces547 {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length, ans = 0;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            
            // A new DFS from an unvisited city identifies one full province.
            if (!visited[i]) {
                ans++;
                dfs(isConnected, visited, i);
            }
        }
        return ans;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int i) {
        visited[i] = true;
        int n = isConnected.length;
        
        for (int j = 0; j < n; j++) {
            
            if (isConnected[i][j] == 1 && !visited[j]) {
                dfs(isConnected, visited, j);
            }
        }
    }
}
