#include <iostream>
#include <iostream>
#include <vector>
#include <queue>
// #include <priority_queu>
using namespace std;
/*
 * Problem: CSES - Shortest Routes I
 *
 * Problem Statement:
 * There are n cities and m directed flight connections with weights. Find the
 * shortest route length from city 1 (Syrjälä) to all other cities.
 *
 * Intuition:
 * This is a single-source shortest path problem on a directed weighted graph
 * with non-negative weights. Dijkstra's algorithm is the correct choice,
 * using a min-priority queue to always expand the closest unvisited node.
 *
 * Approach:
 * 1. Read n, m. Build adjacency list: adj[u] = list of (v, weight).
 * 2. Initialize distance array with INF (1e18), distance[1] = 0.
 * 3. Min-heap priority queue of (dist, node). Push (0, 1).
 * 4. While pq not empty:
 *    - Pop (curr_dist, node). If curr_dist > distance[node], skip.
 *    - For each neighbor (v, w): if curr_dist + w < distance[v]:
 *      update distance[v], push (distance[v], v).
 * 5. Print distance[1..n].
 *
 * Time Complexity: O((n + m) log n) — each edge relaxed at most once, heap ops O(log n).
 * Space Complexity: O(n + m) for adjacency list and distance array.
 *
 * Edge Cases:
 * - City 1 to itself: distance 0.
 * - Multiple edges between same cities: adjacency list stores all, Dijkstra
 *   naturally picks the shortest path.
 * - Large weights up to 10^9: use long long.
 *
 * Dry Run:
 * n=3, m=4
 * Edges: (1,2,6), (1,3,2), (3,2,3), (1,3,4) — duplicate 1→3
 * adj[1] = [(2,6), (3,2), (3,4)]
 * Dijkstra: push (0,1). Pop 1: relax 2→dist[2]=6, relax 3→dist[3]=2.
 * Pop (2,3): relax 2→2+3=5 < 6 → dist[2]=5.
 * Pop (5,2): no unvisited neighbors.
 * Output: 0 5 2
 *
 * Correctness Check:
 * Dijkstra correctly finds single-source shortest paths in O((n+m)log n) for
 * graphs with non-negative edge weights. The "if curr_dist > distance[node]"
 * check avoids processing stale heap entries.
 */

// class Edge
// {
// public:
//     int source;
//     int destination;
//     int weight;

//     Edge(int source, int destination, int weight) : source(source), destination(destination), weight(weight) {}
// };

/*
This solution works but is giving TLE


*/

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    // n is total number of cities
    // m is total number of edges

    // apply dijkstra's algorithm ( but has multiple edges from a single node to another node)
    // choose the shortest one while making adjacency list

    // adjacency list

    // vector<Edge> adj(n);
    vector<vector<pair<int, long long>>> adj(n + 1);

    for (int i = 0; i < m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back({v, w});
    }

    vector<long long> distance(n + 1, 1e18);

    // pq stores distance first and then the node
    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;

    // push source into the pq
    // mark distance to source as 0

    distance[1] = 0;

    pq.push({0, 1});

    while (!pq.empty())
    {
        // first pop node
        long long curr_dist = pq.top().first;
        int node = pq.top().second;

        pq.pop();

        if (curr_dist > distance[node])
            continue;

        // push the adjacent nodes into pq and before pushing add the current distance to their weights
        for (pair<int, long long> p : adj[node])
        {
            int neighbour = p.first;
            long long dist = p.second;

            if (curr_dist + dist < distance[neighbour])
            {
                // update the weight
                distance[neighbour] = curr_dist + dist;
                // add to pq first distance, then node
                pq.push({distance[neighbour], neighbour});
            }
        }
    }

    for (int i = 1; i < distance.size(); i++)
    {
        cout << distance[i] << " ";
    }

    return 0;
}