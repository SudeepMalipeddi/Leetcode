/*
 * Problem: Disjoint Set Union (DSU) / Union-Find
 *
 * Problem Statement:
 * Implements a Disjoint Set Union data structure supporting Find (determine which set
 * an element belongs to) and Union (merge two sets into one), with path compression
 * and union by rank/size optimizations for near-constant amortized time.
 *
 * Intuition:
 * Represent each set as a tree where the root node is the representative. Path compression
 * flattens the tree during Find by making every node on the search path point directly
 * to the root. Union by rank/size keeps trees balanced by always attaching the smaller
 * tree under the larger tree's root.
 *
 * Approach:
 *   1. Constructor DisjointSet(n): Initialize rank[] (all 0), parent[] (each node is its
 *      own parent: parent[i] = i), and size[] (all 1) arrays of size n+1.
 *   2. findParent(node): Recursively find the root. If node == parent[node], return node.
 *      Otherwise, set parent[node] = findParent(parent[node]) (path compression on the
 *      fly) and return it.
 *   3. unionByRank(u, v): Find ultimate parents ulp_u and ulp_v via findParent.
 *      - If ulp_u == ulp_v, return (already in the same set).
 *      - Attach the lower-rank root under the higher-rank root. If ranks are equal,
 *        arbitrarily make ulp_u the parent and increment rank[ulp_u].
 *   4. unionBySize(u, v): Find ultimate parents ultP_u and ultP_v via findParent.
 *      - If equal, return.
 *      - Attach the smaller-size component's root under the larger one's root.
 *        Update size[newRoot] += size[oldRoot].
 *
 * Time Complexity: O(α(N)) amortized per operation, where α is the inverse Ackermann
 * function (effectively ≤ 4 for all practical N).
 * Space Complexity: O(N) for the three arrays: parent, rank, and size.
 *
 * Edge Cases:
 * - Single-element set: parent[i]=i, size[i]=1, rank[i]=0; find returns itself, union
 *   with itself is a no-op.
 * - Union of already-connected elements: detected by equal ultimate parents, early return
 *   avoids redundant work.
 * - Large N: path compression plus union by rank/size guarantees extremely flat trees,
 *   keeping operations fast even at scale.
 *
 * Dry Run:
 * DisjointSet ds(5);  // elements 1..5
 * parent = [0,1,2,3,4,5], rank = [0,0,0,0,0,0], size = [1,1,1,1,1,1]
 *
 * ds.unionByRank(1, 2):
 *   findParent(1)=1, findParent(2)=2, ranks equal (0==0)
 *   → parent[2]=1, rank[1]=1
 *
 * ds.unionByRank(3, 4):
 *   findParent(3)=3, findParent(4)=4, ranks equal
 *   → parent[4]=3, rank[3]=1
 *
 * ds.unionByRank(1, 4):
 *   findParent(1)=1, findParent(4): 4→parent[4]=3→parent[3]=3 → returns 3
 *   rank[1]=1, rank[3]=1, equal → parent[3]=1, rank[1]=2
 *
 * ds.findParent(4):
 *   4→parent[4]=3, 3→findParent(3): 3→parent[3]=1, 1→parent[1]=1 → returns 1
 *   Path compressed: parent[4]=1, parent[3]=1
 *
 * Correctness Check:
 * Path compression preserves the invariant that parent[node] always points to a valid
 * ancestor (or the root). Union by rank/size ensures the tree depth grows at most
 * logarithmically, maintaining the near-constant amortized bound proven by Tarjan.
 */
#include <bits/stdc++.h>
using namespace std;

class DisjointSet
{
    vector<int> rank, parent, size;

public:
    DisjointSet(int n)
    {
        rank.resize(n + 1, 0);
        parent.resize(n + 1);
        size.resize(n + 1);
        for (int i = 0; i <= n; i++)
        {
            parent[i] = i;
            size[i] = 1; // Initially, every node is its own component of size 1
        }
    }

    // Path Compression: find the root parent and update nodes along the path
    int findParent(int node)
    {
        if (node == parent[node])
            return node;
        // Recursively find the ultimate parent and attach this node directly to it
        return parent[node] = findParent(parent[node]);
    }

    // Union by Rank: attach smaller depth tree under larger depth tree
    void unionByRank(int u, int v)
    {
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        // If they already have the same ultimate parent, they are in the same component
        if (ulp_u == ulp_v)
            return;

        // Attach the smaller rank tree to the larger rank tree
        if (rank[ulp_u] < rank[ulp_v])
        {
            parent[ulp_u] = ulp_v;
        }
        else if (rank[ulp_v] < rank[ulp_u])
        {
            parent[ulp_v] = ulp_u;
        }
        else
        {
            // If ranks are equal, pick one as the new root and increment its rank
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }

    // Union by Size: attach smaller size component under larger size component
    void unionBySize(int u, int v)
    {
        int ultP_u = findParent(u);
        int ultP_v = findParent(v);

        if (ultP_u == ultP_v)
            return;

        // The larger component becomes the parent. Update its size.
        if (size[ultP_u] < size[ultP_v])
        {
            parent[ultP_u] = ultP_v;
            size[ultP_v] += size[ultP_u];
        }
        else
        {
            parent[ultP_v] = ultP_u;
            size[ultP_u] += size[ultP_v];
        }
    }
};

int main()
{
    // #ifndef ONLINE_JUDGE
    //     freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    // #endif

    return 0;
}