/*
 * Problem: CSES - Building Roads
 *
 * Problem Statement:
 * There are n cities and m roads connecting them. To connect all cities into a
 * single component, find the minimum number of new roads needed and print one
 * possible set of new roads to build.
 *
 * Intuition:
 * The problem reduces to finding connected components in an undirected graph
 * and connecting them with a spanning set of edges. If we find all components
 * using DFS/BFS, we need exactly (components - 1) new roads. We can connect
 * them by picking one representative city from each component and linking them
 * sequentially (component[0] to component[1], component[1] to component[2], ...).
 *
 * Approach:
 * 1. Build an adjacency list for the graph.
 * 2. Initialize a visited array.
 * 3. For each unvisited city i, run DFS to mark all reachable cities and add
 *    i to a components list (as representative of that component).
 * 4. Print (components.size() - 1) as the answer.
 * 5. Print pairs of consecutive components to show which roads to build.
 *
 * Time Complexity: O(n + m) — each city and road is visited once in DFS.
 * Space Complexity: O(n + m) for adjacency list and visited array.
 *
 * Edge Cases:
 * - Already connected (single component): answer is 0, no roads printed.
 * - No existing roads (m = 0): need n-1 roads.
 *
 * Dry Run:
 * n=4, m=2, edges: (1,2), (3,4)
 * DFS from 1: visits {1,2}, representative=1
 * DFS from 3: visits {3,4}, representative=3
 * Components found: 2
 * New roads needed: 1 → connect 1 and 3
 * Output: 1\n1 3
 *
 * Correctness Check:
 * DFS correctly identifies connected components. Connecting them in a chain
 * with (c-1) edges is minimal and guarantees connectivity since all components
 * become linked through the chain.
 */
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

void dfs(int src, vector<vector<int>> &adj, vector<bool> &visited)
{

    if (visited[src])
        return;

    visited[src] = true;

    for (int v : adj[src])
    {
        if (!visited[v])
        {
            dfs(v, adj, visited);
        }
    }
}

int main()
{
    int cities, roads;
    cin >> cities >> roads;

    vector<vector<int>> matrix(cities + 1);

    for (int i = 0; i < roads; i++)
    {
        int u, v;
        cin >> u >> v;
        matrix[u].push_back(v);
        matrix[v].push_back(u);
    }

    vector<bool> visited(cities + 1, false);

    vector<int> components;
    int comp = 0;
    for (int i = 1; i <= cities; i++)
    {
        if (!visited[i])
        {
            components.push_back(i);
            dfs(i, matrix, visited);
            comp++;
        }
    }

    cout << comp - 1 << endl;

    for (int i = 0; i < comp - 1; i++)
    {
        cout << components[i] << " " << components[i + 1] << endl;
    }
    return 0;
}