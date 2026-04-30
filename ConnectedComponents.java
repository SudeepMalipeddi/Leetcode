/*
 * Problem: 323. Number of Connected Components in an Undirected Graph
 *
 * Problem Statement:
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the total number of connected components in the graph.
 *
 * Intuition:
 * In an undirected graph, a connected component is a set of nodes where any two nodes are connected
 * by a path. If we start a graph traversal (like DFS) from an unvisited node, we will visit every
 * node in its component. The number of times we need to start a new traversal to cover all nodes
 * represents the total number of connected components.
 *
 * Approach:
 * 1. Build an adjacency list from the given edges to represent the graph structure.
 * 2. Use a boolean array 'visited' to keep track of nodes that have already been explored.
 * 3. Iterate through all nodes from 0 to n-1.
 * 4. For each node, if it hasn't been visited, it belongs to a new component. Increment the count.
 * 5. Launch a Depth First Search (DFS) from that node to mark all nodes in that component as visited.
 *
 * Time Complexity: O(V + E), where V is the number of nodes (n) and E is the number of edges.
 * We visit every node once and traverse every edge twice (once for each endpoint).
 * Space Complexity: O(V + E) to store the adjacency list, plus O(V) for the visited array
 * and the recursion stack in the worst-case scenario (a skewed/linear graph).
 *
 * Edge Cases:
 * - n = 0: The loop won't run, returns 0.
 * - No edges: Each node is its own component, returns n.
 * - Fully connected graph: All nodes visited in the first DFS, returns 1.
 *
 * Dry Run:
 * n = 5, edges = [[0, 1], [1, 2], [3, 4]]
 * 1. Adjacency List: {0:[1], 1:[0, 2], 2:[1], 3:[4], 4:[3]}
 * 2. i = 0: Not visited. count = 1. DFS(0) marks {0, 1, 2} as visited.
 * 3. i = 1: Visited. Skip.
 * 4. i = 2: Visited. Skip.
 * 5. i = 3: Not visited. count = 2. DFS(3) marks {3, 4} as visited.
 * 6. i = 4: Visited. Skip.
 * Result: 2.
 *
 * Correctness Check:
 * The solution is correct. It handles undirected edges by adding both directions to the adjacency list
 * and uses a standard traversal pattern to identify disjoint sets of nodes.
 */
import java.util.ArrayList;
import java.util.List;

public class ConnectedComponents {

    public int countComponents(int n, int[][] edges) {
        // Create an adjacency list to represent the graph. 
        // This allows O(1) access to a node's neighbors during traversal.
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Populate the adjacency list. Since the graph is undirected, 
        // we must add the connection in both directions (u -> v and v -> u).
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        // Iterate through every node in the graph.
        for (int i = 0; i < n; i++) {
            // If a node is not visited, it means we've encountered a node 
            // belonging to a component we haven't explored yet.
            if (!visited[i]) {
                count++; // Found a new connected component.
                
                // Use DFS to traverse and mark all nodes reachable from 'i' 
                // as visited so they aren't counted as new components later.
                dfs(graph, visited, i); 
            }
        }
        return count;
    }

    public void dfs(List<List<Integer>> graph, boolean[] visited, int node) {
        // Mark the current node as visited to prevent cycles and redundant processing.
        visited[node] = true; 
        
        // Explore all adjacent nodes (neighbors) that haven't been visited yet.
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, visited, neighbor);
            }
        }
    }
}
