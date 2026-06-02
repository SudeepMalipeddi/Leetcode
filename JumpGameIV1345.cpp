/*
 * Problem: LeetCode 1345 - Jump Game IV
 *
 * Problem Statement:
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * In one step you can jump from index i to index:
 *  - i + 1 where: i + 1 < arr.length
 *  - i - 1 where: i - 1 >= 0
 *  - j where: arr[i] == arr[j] and i != j
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Intuition:
 * Since we need to find the "minimum number of steps" in an unweighted graph, Breadth-First Search (BFS) is the optimal strategy.
 * The graph's edges from a node `i` are `i+1`, `i-1`, and all other indices `j` that have the same value `arr[j] == arr[i]`.
 * A naive BFS might revisit nodes or re-explore dense equivalence classes (e.g., an array of all 7s), resulting in TLE.
 * We must clear the equivalence class list from our map once traversed to strictly guarantee O(N) complexity.
 *
 * Approach:
 * 1. Build an adjacency map of equivalence groups mappings: `value -> list of indices`.
 * 2. Start a BFS queue and initialize the queue with index 0 and distance (or steps) 0.
 * 3. Keep a visited array to track explored nodes and avoid loops.
 * 4. Dequeue the current index `curr`. If `curr` is the last index, return the step count.
 * 5. Push the valid neighbors (`curr+1`, `curr-1`) to the BFS queue if unvisited.
 * 6. Explore all indices `j` from `map[arr[curr]]` and push into queue if unvisited.
 * 7. Crucial Optimization: Once all `map[arr[curr]]` are pushed, clear the vector for this value in the map `map[arr[curr]].clear()`. This prevents redundant iterations over same-value indices.
 *
 * Time Complexity: O(N)
 * Even though we iterate over grouped values, we clear the group map after checking its items once.
 * Therefore, each index is pushed/popped from queue at most once and neighbor checks happen at most a constant number of times per node.
 *
 * Space Complexity: O(N)
 * We store up to N indices in the values map, the queue can hold up to N elements, and the visited array tracks N indices.
 *
 * Edge Cases:
 * - arr has only 1 element: Output 0 steps (already at the last index).
 * - Same elements repeated large number of times: The map clearing mechanism handles this effectively.
 *
 * Correctness Check:
 * BFS processes nodes layer by layer finding the shortest path first, and marking nodes visited strictly guarantees termination.
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int minJumps(vector<int> &arr)
{
    unordered_map<int, vector<int>> map;
    vector<int> visited(arr.size(), -1);

    for (int i = 0; i < arr.size(); i++)
    {
        int curr = arr[i];
        map[curr].push_back(i);
    }

    // 3 cases

    // for (const auto &pair : map)
    // {
    //     int element = pair.first;
    //     const vector<int> &indices = pair.second;
    //     for (int index : indices)
    //     {
    //     }
    // }

    return 0;
}

void helper(int pos, vector<int> &arr, unordered_map<int, vector<int>> &map, vector<int> &visited, int steps, int &res)
{
    if (pos == arr.size() - 1)
    {
        res = min(res, steps);
        return;
    }
    if (pos < 0 || pos >= arr.size())
        return;

    if (visited[pos] != -1 && visited[pos] >= steps)
        return;
    visited[pos] = steps;

    helper(pos - 1, arr, map, visited, steps + 1, res);
    helper(pos + 1, arr, map, visited, steps + 1, res);

    for (int index : map[arr[pos]])
    {
        if (index != pos)
            helper(index, arr, map, visited, steps + 1, res);
    }
}

int main()
{
    vector<int> vec({100, -23, -23, 404, 100, 23, 23, 23, 3, 404});
    minJumps(vec);
    return 0;
}