/*
 * Problem: LeetCode 207 - Course Schedule
 *
 * Problem Statement:
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Intuition:
 * This problem is equivalent to determining if a directed graph has a cycle. If there is
 * a cycle (course A requires course B, and B requires A), completing the courses is impossible.
 * If there are no cycles (it's a Directed Acyclic Graph - DAG), we can find a topological
 * sort and complete all courses.
 *
 * Approach (Cycle Detection via DFS):
 * 1. Build an adjacency list denoting prerequisites (u -> v meaning u must be taken before v).
 * 2. Use a boolean `visited` array to ensure we don't process completed nodes again.
 * 3. *Crucially*, use an `inPath` array to keep track of nodes in the *current* DFS path.
 * 4. Perform a DFS. If we encounter a node already marked `inPath`, we found a cycle (return false).
 * 5. Backtrack: unmark the node from `inPath` as we exit the DFS step.
 * 6. If no cycle is found after checking all nodes, return true.
 *
 * Note: The Kahn's Algorithm (BFS) approach is often easier, where if the topological
 * sort count equals numCourses, it returns true.
 *
 * Time Complexity: O(V + E) where V is numCourses and E is the number of prerequisites.
 * We visit each course and its dependencies once.
 * Space Complexity: O(V + E) for the adjacency list graph.
 *
 * Edge Cases:
 * - Empty prerequisites (no dependencies): returns true.
 * - Disconnected graphs: works because the loop checks all remaining unvisited nodes.
 */

#include <iostream>
#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    bool canFinish(int V, vector<vector<int>> &graph)
    {
        // V = numCourses
        vector<bool> visited(V, false);
        stack<int> st;
        vector<int> res;
        vector<int> indegree(V, 0);

        vector<vector<int>> adj(V);
        for (int i = 0; i < graph.size(); i++)
        {
            int v = graph[i][0];
            int u = graph[i][1];
            indegree[v]++;
            adj[u].push_back(v);
        }

        queue<int> q;
        for (int i = 0; i < V; i++)
        {
            if (indegree[i] == 0)
                q.push(i);
        }

        int count = 0;
        while (!q.empty())
        {
            int curr = q.front();
            q.pop();
            count++;
            for (int v : adj[curr])
            {
                indegree[v]--;
                if (indegree[v] == 0)
                {
                    q.push(v);
                }
            }
        }
        return count == V;
    }
};

int main()
{

    return 0;
}