/*
 * Problem: Number of Connected Components in an Undirected Graph (LeetCode 323)
 * Problem Statement: Given n nodes labeled 0..n-1 and undirected edges, return
 *   the number of connected components.
 * Intuition: Build an adjacency list and run DFS from each unvisited node.
 * Approach:
 *   1) Build adjacency lists for all nodes.
 *   2) Maintain a visited array.
 *   3) For each node not visited, increment count and DFS to mark its component.
 * Time Complexity: O(n + m) where m is number of edges.
 * Space Complexity: O(n + m) for the graph and recursion stack.
 * Edge Cases: No edges (n components), single node, disconnected clusters.
 * Dry Run: n=5, edges=[[0,1],[1,2],[3,4]] -> DFS from 0 marks {0,1,2}, from 3 marks {3,4} => 2.
 * Correctness Check: DFS visits exactly the nodes in a component, so each new DFS
 *   corresponds to one distinct component.
 */
import java.util.ArrayList;
import java.util.List;

public class ConnectedComponents {

    public int countComponents(int n, int[][] edges) {
        // Create an adjacency list to represent the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        // Perform DFS to count connected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++; // new component found
                dfs(graph, visited, i); // mark all nodes in this component
            }
        }
        return count;
    }

    public void dfs(List<List<Integer>> graph, boolean[] visited, int node) {
        visited[node] = true; // mark current node
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, visited, neighbor);
            }
        }
    }
}
