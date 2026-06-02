#include <iostream>
#include <bits/stdc++.h>
using namespace std;

/*
 * Problem: CSES - Building Teams
 *
 * Problem Statement:
 * There are n pupils and m friendships. Divide the pupils into two teams such
 * that no two pupils in the same team are friends. Print the team assignment
 * (1 or 2) for each pupil, or "IMPOSSIBLE" if no valid division exists.
 *
 * Intuition:
 * This is a bipartite graph coloring problem. The friendships form an undirected
 * graph. If the graph is bipartite, we can assign alternating colors (1 and 2)
 * to adjacent nodes. If we ever find two adjacent nodes with the same color,
 * the graph is not bipartite and a solution is impossible.
 *
 * Approach:
 * 1. Build adjacency list for the graph.
 * 2. Initialize a visited array with -1 (uncolored).
 * 3. For each unvisited node i:
 *    - Assign it color 1 and push to BFS queue.
 *    - BFS: for each neighbor, if uncolored, assign opposite color; if same
 *      color as current, set flag = true (conflict found).
 * 4. If flag is true, print "IMPOSSIBLE".
 * 5. Otherwise, print the color assignment for each node.
 *
 * Time Complexity: O(n + m) — each node and edge is processed once in BFS.
 * Space Complexity: O(n + m) for adjacency list and visited array.
 *
 * Edge Cases:
 * - Disconnected components: each component is colored independently.
 * - Self-loops: not possible per problem constraints.
 * - Graph with an odd-length cycle: not bipartite → "IMPOSSIBLE".
 *
 * Dry Run:
 * n=5, m=3, edges: (1,2), (1,3), (4,5)
 * Component 1: start at 1 (color=1)
 *   neighbors: 2→color=2, 3→color=2
 *   No conflicts, all visited.
 * Component 2: start at 4 (color=1)
 *   neighbor: 5→color=2
 *   No conflicts.
 * Output: 1 2 2 1 2
 *
 * Correctness Check:
 * BFS bipartite coloring correctly determines 2-colorability. A graph is
 * bipartite iff it contains no odd-length cycles. The BFS catches any same-color
 * adjacency, which signals an odd cycle.
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

    queue<pair<int, int>> q;

    vector<int> visited(n + 1, -1);
    int nextColor = 1;
    bool flag = false;

    for (int i = 1; i <= n; i++)
    {
        if (visited[i] == -1)
        {
            // cout << i << " ";
            q.push({i, nextColor});
            visited[i] = nextColor;
            while (!q.empty())
            {
                int curr = q.front().first;
                int color = q.front().second;
                nextColor = (color == 1) ? 2 : 1;

                // cout << "current element is " << curr << " whose color is " << color << " \n";
                q.pop();
                for (int v : adj[curr])
                {
                    if (visited[v] == color)
                    {
                        flag = true;
                        break;
                    }
                    if (visited[v] == -1)
                    {
                        visited[v] = nextColor;
                        q.push({v, nextColor});
                    }
                }
                if (flag == true)
                {
                    break;
                }
            }
        }
        if (flag == true)
        {
            break;
        }
    }

    if (flag == true)
    {
        cout << "IMPOSSIBLE\n";
        exit(0);
    }

    for (int i = 1; i <= n; i++)
    {
        cout << visited[i] << " ";
    }
    cout << "\n";

    return 0;
}