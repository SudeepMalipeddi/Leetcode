/*
 * Problem: LeetCode 64 - Minimum Path Sum
 *
 * Problem Statement:
 * Given an m x n grid filled with non-negative numbers, find a path from the top-left corner to the
 * bottom-right corner that minimizes the sum of all numbers along the path. You can only move either
 * down or right at any point.
 *
 * Intuition:
 * This is a classic 2D dynamic programming problem on a grid. At each cell (i,j), the minimum path
 * sum to reach it is the cell's own value plus the minimum of the path sums from the cell above
 * (i-1,j) and the cell to the left (i,j-1). The first row and first column have only one way to
 * reach each cell, so they serve as the base cases for the DP recurrence.
 *
 * Approach:
 *   1. Get dimensions m = grid.size() (rows) and n = grid[0].size() (columns).
 *   2. Create a 2D DP table result of size m x n initialized to 0.
 *   3. Initialize result[0][0] = grid[0][0] (starting cell).
 *   4. Fill the first column: for i from 1 to m-1, result[i][0] = result[i-1][0] + grid[i][0]
 *      (only one path: straight down).
 *   5. Fill the first row: for j from 1 to n-1, result[0][j] = result[0][j-1] + grid[0][j]
 *      (only one path: straight right).
 *   6. Fill remaining cells: for i from 1 to m-1, j from 1 to n-1:
 *      int mini = min(result[i-1][j], result[i][j-1]) — best path to reach (i,j) from above or left.
 *      result[i][j] = mini + grid[i][j] — add current cell's value.
 *   7. Return result[m-1][n-1] — the minimum path sum to the bottom-right corner.
 *
 * Time Complexity: O(m * n) — each cell in the grid is processed exactly once.
 * Space Complexity: O(m * n) — the result DP table stores a value for every cell. (Can be optimized
 *   to O(n) or O(1) by modifying grid in-place, but this implementation uses a separate table.)
 *
 * Edge Cases:
 * - 1x1 grid: loops don't execute, result[0][0] = grid[0][0] is returned. Correct.
 * - Single row (m=1): first row init handles all columns, nested loops skipped. Correct.
 * - Single column (n=1): first column init handles all rows, nested loops skipped. Correct.
 * - All zeros: result matches straightforward path sums.
 * - Large values: standard int addition may overflow for extreme inputs, but works within LeetCode
 *   constraints.
 *
 * Dry Run:
 * grid = [[1, 3, 1],
 *         [1, 5, 1],
 *         [4, 2, 1]]
 * m=3, n=3.
 * Initialize result[0][0] = 1.
 * First column: result[1][0] = 1+1=2, result[2][0] = 2+4=6.
 * First row: result[0][1] = 1+3=4, result[0][2] = 4+1=5.
 * i=1, j=1: mini = min(result[0][1]=4, result[1][0]=2) = 2, result[1][1] = 2+5=7.
 * i=1, j=2: mini = min(result[0][2]=5, result[1][1]=7) = 5, result[1][2] = 5+1=6.
 * i=2, j=1: mini = min(result[1][1]=7, result[2][0]=6) = 6, result[2][1] = 6+2=8.
 * i=2, j=2: mini = min(result[1][2]=6, result[2][1]=8) = 6, result[2][2] = 6+1=7.
 * Output: 7 (optimal path: 1→3→1→1→1, i.e. right, right, down, down).
 *
 * Correctness Check:
 * The DP recurrence result[i][j] = grid[i][j] + min(result[i-1][j], result[i][j-1]) with proper
 * base cases for the first row and column exhaustively computes the minimum path sum to every cell.
 * Since moves are restricted to down and right, the optimal substructure holds: the optimal path to
 * (i,j) must come from either (i-1,j) or (i,j-1). The final answer result[m-1][n-1] is the global
 * minimum.
 */
#include <iostream>
#include <vector>
using namespace std;

int minPathSum(vector<vector<int>> &grid)
{
    int m = grid.size(), n = grid[0].size();
    vector<vector<int>> result(grid.size(), vector<int>(grid[0].size(), 0));

    // first row init
    result[0][0] = grid[0][0];
    for (int i = 1; i < m; i++)
    {
        result[i][0] = result[i - 1][0] + grid[i][0];
    }

    // first col
    for (int j = 1; j < n; j++)
    {
        result[0][j] = result[0][j - 1] + grid[0][j];
    }

    for (int i = 1; i < m; i++)
    {
        for (int j = 1; j < n; j++)
        {
            int mini = min(result[i - 1][j], result[i][j - 1]);
            result[i][j] = mini + grid[i][j];
        }
    }
    return result[m - 1][n - 1];
}
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;

    while (t-- != 0)
    {

        int m, n;
        cin >> m >> n;
        vector<vector<int>> grid(m, vector<int>(n, 0));
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                cin >> grid[i][j];
            }
        }

        cout << minPathSum(grid) << '\n';
    }

    return 0;
}
