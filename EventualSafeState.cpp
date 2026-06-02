/*
 * Problem: LeetCode 200 - Number of Islands (DFS flood-fill)
 *
 * Problem Statement:
 * Given an m x n 2D binary grid where '1' represents land and '0' represents water,
 * return the number of islands. An island is surrounded by water and formed by
 * connecting adjacent lands horizontally or vertically.
 *
 * Intuition:
 * Use DFS to explore and "sink" each island. When an unvisited land cell ('1') is
 * found, increment the island count and recursively mark all connected land cells
 * as visited so they are not counted again.
 *
 * Approach:
 *   1. numberOfIslands(grid):
 *      - Get rows = grid.size() and cols = grid[0].size(). Initialize visited matrix
 *        (all false) and res = 0.
 *      - Nested loops over all cells (i from 0 to rows-1, j from 0 to cols-1):
 *        If grid[i][j] == '1' and !visited[i][j], increment res and call
 *        dfs(i, j, grid, visited).
 *      - Return res.
 *   2. dfs(i, j, grid, visited):
 *      - Base case: if i or j out of bounds, visited[i][j] is true, or
 *        grid[i][j] == '0', return immediately.
 *      - Mark visited[i][j] = true.
 *      - Recurse in 4 directions: dfs(i+1, j), dfs(i-1, j), dfs(i, j+1), dfs(i, j-1).
 *
 * Time Complexity: O(rows * cols) — each cell is visited at most once.
 * Space Complexity: O(rows * cols) — visited matrix plus recursion stack depth
 * (worst case: all land in one island).
 *
 * Edge Cases:
 * - Empty grid: bounds checks in dfs prevent out-of-bounds access for i < 0 or
 *   i >= grid.size(); cols access requires grid[0].size() which would fail on
 *   empty grid — caller should ensure non-empty grid.
 * - All water ('0'): visited check prevents any dfs calls, returns 0.
 * - All land ('1'): single dfs call covers entire grid, returns 1.
 * - Single cell: returns 1 if '1', 0 if '0'.
 *
 * Dry Run:
 * grid = [['1','1','0'],
 *         ['1','0','0'],
 *         ['0','0','1']]
 * rows=3, cols=3, visited all false, res=0
 *
 * i=0, j=0: grid[0][0]='1', !visited → res=1, dfs(0,0):
 *   visited[0][0]=true
 *   dfs(1,0): visited[1][0]=true
 *     dfs(2,0)='0' returns, dfs(0,0) visited, dfs(1,-1) OOB, dfs(1,1)='0'
 *   dfs(-1,0) OOB
 *   dfs(0,1): visited[0][1]=true
 *     dfs(1,1)='0', dfs(-1,1) OOB, dfs(0,0) visited, dfs(0,2)='0'
 *   dfs(0,-1) OOB
 *
 * i=0, j=1: visited, skip
 * i=0, j=2: '0', skip
 * i=1, j=0: visited, skip
 * i=1, j=1: '0', skip
 * i=1, j=2: '0', skip
 * i=2, j=0: '0', skip
 * i=2, j=1: '0', skip
 * i=2, j=2: grid[2][2]='1', !visited → res=2, dfs(2,2):
 *   visited[2][2]=true
 *   dfs(3,2) OOB, dfs(1,2)='0', dfs(2,3) OOB, dfs(2,1)='0'
 *
 * Returns 2.
 *
 * Correctness Check:
 * DFS flood-fill correctly marks all cells belonging to the same island. Counting
 * only on first discovery ensures each island is counted exactly once. The
 * 4-directional adjacency matches the problem's definition of connected land.
 */

#include <iostream>
#include <bits/stdc++.h>
using namespace std;


/*
802. Find Eventual Safe States
There is a directed graph of n nodes with each node labeled from 0 to n - 1.
The graph is represented by a 0-indexed 2D integer array graph where graph[i]
is an integer array of nodes adjacent to node i, meaning there is an edge from
node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node
if every possible path starting from that node leads to a terminal node (or
another safe node).

Return an array containing all the safe nodes of the graph. The answer should
be sorted in ascending order.
*/


vector<int> eventualSafeNodes(vector<vector<int>> &graph)
{

    vector<int> res()
}



















void dfs(int i, int j, vector<vector<char>> &grid, vector<vector<bool>> &visited)
{
    if (i < 0 || j < 0 || i >= grid.size() || j >= grid[0].size() || visited[i][j] == true || grid[i][j] == '0')
    {
        return;
    }

    visited[i][j] = true;
    dfs(i + 1, j, grid, visited);
    dfs(i - 1, j, grid, visited);
    dfs(i, j + 1, grid, visited);
    dfs(i, j - 1, grid, visited);
}

int numberOfIslands(vector<vector<char>> &grid)
{
    int rows = grid.size(), cols = grid[0].size(), res = 0;
    vector<vector<bool>> visited(rows, vector<bool>(cols, false));

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            if (!visited[i][j] && grid[i][j] == '1')
            {
                res++;
                dfs(i, j, grid, visited);
            }
        }
    }
    return res;
}

int main()
{

    return 0;
}