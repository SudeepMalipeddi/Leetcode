/*
 * Problem: CSES - Counting Rooms
 *
 * Problem Statement:
 * Given an n×m grid of floor ('.') and wall ('#') cells, count the number of
 * distinct rooms. A room consists of floor cells connected horizontally or
 * vertically.
 *
 * Intuition:
 * This is a connected components problem on a 2D grid. We can use DFS (or BFS)
 * to flood-fill each room: when we encounter an unvisited floor cell, we
 * increment the room count and recursively mark all reachable floor cells as
 * visited (by turning them into walls '#' to avoid a separate visited array).
 *
 * Approach:
 * 1. Read dimensions n, m and the grid.
 * 2. For each cell (i, j):
 *    - If grid[i][j] == '.', increment res and call dfs(i, j).
 * 3. dfs(i, j) marks grid[i][j] = '#' and recursively explores four directions.
 *    Bounds check and wall check at each call.
 * 4. Print res.
 *
 * Time Complexity: O(n * m) — each cell is visited at most once.
 * Space Complexity: O(n * m) for recursion stack in worst case (all floor).
 *
 * Edge Cases:
 * - All walls: 0 rooms.
 * - All floor: 1 room.
 * - Single cell grid.
 *
 * Dry Run:
 * n=5, m=8
 * ########
 * #..#...#
 * ####.#.#
 * #..#...#
 * ########
 * Floor cells: (1,1) → dfs fills (1,1). Room 1.
 * (1,4) → dfs fills (1,4),(1,5),(1,6),(2,6),(3,4),(3,5),(3,6). Room 2.
 * (3,1) → dfs fills (3,1),(3,2). Room 3.
 * Result: 3
 *
 * Correctness Check:
 * The flood-fill correctly identifies connected components in a 2D grid.
 * Marking visited cells as '#' prevents revisiting and works because we only
 * need to count components once.
 */
#include <iostream>
#include <vector>

using namespace std;

void dfs(int i, int j, int n, int m, vector<string> &matrix)
{
    if (i < 0 || j < 0 || i >= n || j >= m || matrix[i][j] == '#')
        return;

    matrix[i][j] = '#';

    dfs(i + 1, j, n, m, matrix);
    dfs(i - 1, j, n, m, matrix);
    dfs(i, j + 1, n, m, matrix);
    dfs(i, j - 1, n, m, matrix);
}

int main()
{
    int n, m;
    cin >> n >> m;

    cin.ignore();

    vector<string> matrix(n);

    for (int i = 0; i < n; i++)
    {
        string temp;
        getline(cin, temp);
        matrix[i] = temp;
    }

    int res = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (matrix[i][j] == '.')
            {
                res++;
                dfs(i, j, n, m, matrix);
            }
        }
    }

    cout << res << "\n";

    return 0;
}