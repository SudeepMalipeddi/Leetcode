#include <iostream>
#include <vector>
#include <set>

/*
 * Problem: CSES - Grid Coloring I (Grid Paths / Grid Colouring)
 *
 * Problem Statement:
 * Given an n×m grid where each cell contains A, B, C, or D, change each cell to
 * A, B, C, or D (must be different from original) such that no two adjacent
 * cells share the same character. Print any valid final grid, or "IMPOSSIBLE".
 *
 * Intuition:
 * Since there are exactly 4 possible characters, each cell's new value must be
 * different from its original value and different from its already-processed
 * top and left neighbors. This is essentially a greedy coloring: process the
 * grid row-by-row, left-to-right, and for each cell pick the first character
 * from {A,B,C,D} that doesn't conflict with the original value, the cell above,
 * or the cell to the left.
 *
 * Approach:
 * 1. Read the grid.
 * 2. For each cell (i, j):
 *    - Build a set of forbidden characters: original, top neighbor, left neighbor.
 *    - Pick the first character from {A,B,C,D} not in the forbidden set.
 *    - Assign it to grid[i][j].
 * 3. Print the modified grid.
 *
 * Time Complexity: O(n * m) — each cell processed once, checking 4 characters.
 * Space Complexity: O(n * m) to store the grid.
 *
 * Edge Cases:
 * - 1x1 grid: just pick any character different from original.
 * - All cells same original character: still solvable (can alternate).
 * - Grid where only 1 free character per cell: always possible since 4 options,
 *   original + max 2 neighbors = 3 forbidden max → at least 1 valid choice.
 *
 * Dry Run:
 * 3x4 grid:
 * A A A A
 * B B B B
 * C C D D
 *
 * Cell (0,0): A→forbidden={A}, pick B
 * Cell (0,1): A→forbidden={A, B(top?) no, B(left)=B}, wait: top doesn't exist yet.
 * Let me trace accurately:
 * (0,0): orig=A, no top, no left → forbidden={A}, pick B
 * (0,1): orig=A, no top, left=B → forbidden={A,B}, pick C
 * (0,2): orig=A, no top, left=C → forbidden={A,C}, pick B
 * (0,3): orig=A, no top, left=B → forbidden={A,B}, pick C
 * (1,0): orig=B, top=B, no left → forbidden={B}, pick A
 * (1,1): orig=B, top=C, left=A → forbidden={B,C,A}, pick D
 * ...continues similarly.
 * Output: CDCD / DCDC / ABAB (one valid solution)
 *
 * Correctness Check:
 * With 4 character options, each cell has at most 3 constraints (original +
 * top + left), guaranteeing at least 1 valid choice. The greedy left-to-right,
 * top-to-bottom approach never backtracks and always produces a valid solution.
 */

using namespace std;

int main()
{
    int rows, cols;
    cin >> rows >> cols;

    char arr[4] = {'A', 'B', 'C', 'D'};
    set<char> s;

    vector<vector<char>> grid(rows, vector<char>(cols));

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            char c;
            cin >> c;
            grid[i][j] = c;
        }
    }

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            s.clear();
            s.insert(grid[i][j]);
            if (i > 0)
                s.insert(grid[i - 1][j]);
            if (j > 0)
                s.insert(grid[i][j - 1]);
            for (char c : arr)
            {
                if (s.find(c) == s.end())
                {
                    grid[i][j] = c;
                    break;
                }
            }
        }
    }
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            cout << grid[i][j];
        }
        cout << endl;
    }
}