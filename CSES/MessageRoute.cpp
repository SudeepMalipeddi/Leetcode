#include <iostream>
#include <bits/stdc++.h>
using namespace std;

/*
 * Problem: CSES - Message Route
 *
 * Problem Statement:
 * There are n computers and m connections. Find the shortest route from
 * computer 1 to computer n. Print the route length (number of computers) and
 * the path, or "IMPOSSIBLE" if no route exists.
 *
 * Intuition:
 * Shortest path on an unweighted, undirected graph — BFS from node 1. Use a
 * parent array to reconstruct the path. If node n is never visited
 * (parent[n] == -1), no route exists.
 *
 * Approach:
 * 1. Build adjacency list for the graph.
 * 2. parent array initialized to -1, parent[1] = 0 (sentinel).
 * 3. BFS from node 1: for each unvisited neighbor, set parent and enqueue.
 * 4. If parent[n] == -1: "IMPOSSIBLE".
 * 5. Else: trace back from n to 0 via parent, reverse, print size and path.
 *
 * Time Complexity: O(n + m) — each node/edge processed once in BFS.
 * Space Complexity: O(n + m) for adjacency list and parent array.
 *
 * Edge Cases:
 * - n = 1: route is just [1], length 1.
 * - Disconnected: "IMPOSSIBLE".
 *
 * Dry Run:
 * n=5, m=5, edges: (1,2),(1,3),(1,4),(2,3),(5,4)
 * BFS: 1→neighbors 2,3,4 → parent[2]=1, parent[3]=1, parent[4]=1
 * 4→neighbor 5 → parent[5]=4
 * Trace: 5←4←1←0 → path [1,4,5], length 3
 *
 * Correctness Check:
 * BFS on unweighted graph always finds shortest path. Parent reconstruction
 * is unambiguous since each node has one BFS-tree parent.
 */

int main()
{
    int n, m;
    cin >> n >> m;

    vector<vector<int>> adj(n + 1);

    for (int i = 0; i < m; i++)
    {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    vector<int> parent(n + 1, -1);
    parent[1] = 0;

    queue<int> q;
    q.push(1);

    while (!q.empty())
    {
        int curr = q.front();
        q.pop();
        for (int v : adj[curr])
        {
            if (parent[v] == -1)
            {
                parent[v] = curr;
                q.push(v);
            }
        }
    }

    if (parent[n] == -1)
    {
        cout << "IMPOSSIBLE\n"
             << endl;
        return 0;
    }

    vector<int> path;
    int v = n;
    while (v != 0)
    {
        path.push_back(v);
        v = parent[v];
    }

    reverse(path.begin(), path.end());

    cout << path.size() << "\n";
    for (int i = 0; i < path.size(); i++)
    {
        if (i)
            cout << " ";
        cout << path[i];
    }
    cout << "\n";

    return 0;
}