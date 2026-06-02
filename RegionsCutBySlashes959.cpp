
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

/*
 * Problem: LeetCode 959 - Regions Cut By Slashes
 *
 * Problem Statement:
 * An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '.
 * These characters divide the square into contiguous regions.
 * Given the grid represented as a string array, return the number of regions.
 * Note: backslash '\' is passed as '\\'.
 *
 * Intuition:
 * This problem is a topology problem where counting regions separated by lines translates well into finding connected components.
 * To use a standard graph traversal (DFS/BFS/Union-Find) method to find distinct connected components, we can scale or "zoom in" the original 1x1 cells.
 * By representing every 1x1 block as a 3x3 block, a slash ('/') and backslash ('\') visually "cut" through the 3x3 layout diagonally by placing 1s (walls). Blank spaces remain 0s (walkable path).
 *
 * Approach:
 * 1. Create a matrix of size 3N x 3N initialized to 0.
 * 2. Traverse every cell in the input grid:
 *    a. If cell is '/', mark matrix[r][c+2], matrix[r+1][c+1], matrix[r+2][c] as 1.
 *    b. If cell is '\', mark matrix[r][c], matrix[r+1][c+1], matrix[r+2][c+2] as 1.
 *    where `r = i*3` and `c = j*3`.
 * 3. Count connected components in this new 3N x 3N grid by doing a DFS/BFS over all 0s.
 * 4. Each isolated cluster of 0s marks a unique component (region).
 *
 * Time Complexity: O(N^2)
 * We scale an N x N matrix by 3, making it 3N x 3N. Building and traversing the 3N x 3N grid takes O(9 * N^2) which is O(N^2).
 *
 * Space Complexity: O(N^2)
 * A matrix of size 3N x 3N is constructed. Recursion stack size in the worst case could also go up to O(9 * N^2).
 *
 * Edge Cases:
 * - n=1 grid that isolates two spaces completely e.g. ["/"].
 * - Grid fully populated by slashes.
 * - Grid is entirely blank.
 *
 * Correctness Check:
 * Connecting 3x3 segments handles tight corners without artificially conjoining two independent diagonal spaces, preventing corner leakage that simple 2x2 grids might suffer from.
 */

class Solution
{
public:
    void dfs(int i, int j, vector<vector<int>> &matrix, vector<vector<bool>> &visited)
    {
        if (i < 0 || j < 0 || i >= matrix.size() || j >= matrix.size() || visited[i][j] || matrix[i][j] == 1)
            return;

        visited[i][j] = true;

        dfs(i + 1, j, matrix, visited);
        dfs(i - 1, j, matrix, visited);
        dfs(i, j + 1, matrix, visited);
        dfs(i, j - 1, matrix, visited);
    }
    int regionsBySlashes(vector<string> &grid)
    {
        int n = grid.size();
        vector<vector<int>> matrix(n * 3, vector<int>(n * 3));

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid[i][j] == '/')
                {
                    matrix[(i * 3) + 2][j * 3] = 1;
                    matrix[(i * 3) + 1][(j * 3) + 1] = 1;
                    matrix[(i * 3) + 0][(j * 3) + 2] = 1;
                }
                else if (grid[i][j] == '\\')
                {
                    matrix[(i * 3)][j * 3] = 1;
                    matrix[(i * 3) + 1][(j * 3) + 1] = 1;
                    matrix[(i * 3) + 2][(j * 3) + 2] = 1;
                }
            }
        }

        int components = 0, n1 = matrix.size();
        vector<vector<bool>> visited(n1, vector<bool>(n1, false));
        for (int i = 0; i < n1; i++)
        {
            for (int j = 0; j < n1; j++)
            {
                if (!visited[i][j] && matrix[i][j] != 1)
                {
                    components++;
                    dfs(i, j, matrix, visited);
                }
            }
        }

        return components;
    }
};

int main()
{

    return 0;
}