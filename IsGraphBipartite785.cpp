/*
 * Problem: LeetCode 785 - Is Graph Bipartite?
 *
 * Problem Statement:
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
 * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that
 * every edge in the graph connects a node in set A and a node in set B.
 * Return true if and only if it is bipartite.
 *
 * Intuition:
 * A graph is bipartite if and only if it is 2-colorable. This means we can color every node using
 * exactly two colors (e.g., 0 and 1) such that no two adjacent nodes have the same color.
 * We can traverse the graph using BFS (or DFS), assigning alternating colors to adjacent nodes.
 * If we ever find an adjacent node that is already colored with the exact same color as the
 * current node, the graph is not bipartite.
 *
 * Approach:
 * 1. Initialize a `color` array with -1 (meaning uncolored).
 * 2. Since the graph might be disconnected, we must loop through all nodes. If a node is
 *    uncolored (-1), start a BFS from it.
 * 3. In the BFS, process nodes using a queue. For each adjacent node:
 *    a. If it is uncolored, assign it the opposite color of the current node and push to queue.
 *    b. If it is already colored AND has the same color as the current node, return false.
 * 4. If all components are successfully colored without conflicts, return true.
 *
 * Time Complexity: O(V + E) where V is the number of nodes and E is the number of edges.
 * Space Complexity: O(V) for the queue and color array.
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    bool isBipartite(vector<vector<int>> &graph)
    {
        int n = graph.size();
        // Array to store color of each node: -1 means uncolored, 0 and 1 represent the two colors
        vector<int> color(n, -1);

        // Loop over all nodes to handle disconnected components
        for (int i = 0; i < n; i++)
        {
            if (color[i] == -1) // Start BFS if node is uncolored
            {
                queue<int> q;
                q.push(i);
                color[i] = 0; // Assign the first color

                while (!q.empty())
                {
                    int currNode = q.front();
                    int currColor = color[currNode];
                    q.pop();

                    for (auto neighbor : graph[currNode])
                    {
                        if (color[neighbor] == currColor)
                        {
                            // A neighbor has the same color, meaning graph is not bipartite
                            return false;
                        }
                        else if (color[neighbor] == -1)
                        {
                            // Node is uncolored; assign it the opposite color and add to queue
                            color[neighbor] = !currColor;
                            q.push(neighbor);
                        }
                    }
                }
            }
        }

        return true;
    }
};

int main()
{

    return 0;
}