
/*
 * Problem: LeetCode 210 - Course Schedule II
 *
 * Problem Statement:
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them.
 * If it is impossible to finish all courses (due to a cycle), return an empty array.
 *
 * Intuition:
 * This problem is a direct application of Topological Sorting on a Directed Graph.
 * We can use Kahn's Algorithm (BFS) to achieve this. By keeping track of the in-degree
 * (number of prerequisites) for each course, we can process courses that currently
 * have no prerequisites (in-degree == 0). As we process a course, we reduce the in-degree
 * of all its dependent courses, simulating "taking" the prerequisite.
 *
 * Approach:
 * 1. Build an adjacency list (graph) from the prerequisites array, where an edge u -> v
 *    means course u is a prerequisite for course v.
 * 2. Compute the in-degree (incoming edges) for each course.
 * 3. Initialize a queue and push all courses with an in-degree of 0 (ready to be taken).
 * 4. While the queue is not empty:
 *    a. Pop a course and add it to the result array.
 *    b. Decrement the in-degree of all its neighbors (courses that depend on it).
 *    c. If a neighbor's in-degree drops to 0, push it into the queue.
 * 5. Cycle Detection: If the result array's size equals numCourses, return the result.
 *    Otherwise, a cycle exists (some in-degrees never reached 0), so return an empty array.
 *
 * Time Complexity: O(V + E) where V is numCourses and E is the number of prerequisites.
 * We process each node and its outgoing edges exactly once.
 * Space Complexity: O(V + E) to store the adjacency list graph, queue, and indegree array.
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    vector<int> findOrder(int numCourses, vector<vector<int>> &prerequisites)
    {
        int V = numCourses;

        // Track the number of prerequisites for each course
        vector<int> indegree(V, 0);

        // Adjacency list to represent the graph (prerequisite -> dependent courses)
        vector<vector<int>> adj(V);

        // Build the graph and calculate in-degrees
        for (int i = 0; i < prerequisites.size(); i++)
        {
            int u = prerequisites[i][1]; // Prerequisite course
            int v = prerequisites[i][0]; // Dependent course
            adj[u].push_back(v);
            indegree[v]++;
        }

        queue<int> q;
        // Start with courses that have no prerequisites
        for (int i = 0; i < V; i++)
            if (indegree[i] == 0)
                q.push(i);

        vector<int> res;
        // Process courses using BFS (Kahn's Algorithm for Topological Sort)
        while (!q.empty())
        {
            int curr = q.front();
            q.pop();
            res.push_back(curr);

            // Unlock dependent courses by reducing their in-degree
            for (int v : adj[curr])
            {
                indegree[v]--;
                // If a dependent course has no more prerequisites, it's ready to be taken
                if (indegree[v] == 0)
                    q.push(v);
            }
        }

        // If we couldn't take all courses, there must be a cycle
        if (res.size() != V)
            return {}; // cycle detected
        return res;
    }
};
int main()
{

    return 0;
}