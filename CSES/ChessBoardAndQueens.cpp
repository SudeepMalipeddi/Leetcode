/*
 * Problem: CSES - Chessboard and Queens
 *
 * Problem Statement:
 * Given an 8x8 chessboard with some reserved squares ('*'), count the number
 * of ways to place 8 queens so that no two attack each other and no queen is
 * placed on a reserved square.
 *
 * Intuition:
 * This is the classic N-Queens problem with blocked cells. Since the board is
 * only 8x8, backtracking column-by-column is computationally feasible. For each
 * column, we try placing a queen in each row that is not reserved and not under
 * attack from previously placed queens. Attacks are checked backward: same row,
 * upper-left diagonal, and lower-left diagonal (since queens to the right haven't
 * been placed yet).
 *
 * Approach:
 * 1. Read the 8x8 board into a character matrix.
 * 2. Use backtracking: backtrack(col, res):
 *    - If col == 8: increment res, return (all 8 queens placed).
 *    - For each row 0..7:
 *      - If board[row][col] != '*' and isValid(row, col):
 *        - Place queen ('q'), recurse on col+1, then backtrack (restore '.').
 * 3. isValid(row, col) checks:
 *    - Same row to the left (no queen in columns < col).
 *    - Upper-left diagonal (no queen in (row-1, col-1), (row-2, col-2), ...).
 *    - Lower-left diagonal (no queen in (row+1, col-1), (row+2, col-2), ...).
 * 4. Print the final count.
 *
 * Time Complexity: O(8! × 8) ≈ O(8^8) worst case, but pruning via constraints
 * reduces it significantly. 8! = 40320 valid placements (without reserved cells).
 * Space Complexity: O(8^2) for the board plus O(8) recursion stack.
 *
 * Edge Cases:
 * - Board entirely reserved except one column: 0 ways.
 * - Board with no reserved cells: standard 8-queens = 92 solutions.
 *
 * Dry Run:
 * For a board with no reserved cells, the backtracking explores all valid
 * configurations. The first few steps for column 0: try row 0 (valid), then
 * column 1: row 2 (valid, not same row/diagonal), column 2: row 4, etc.
 * Total valid: 92.
 *
 * Correctness Check:
 * The column-by-column backtracking guarantees no two queens share a column.
 * The isValid checks prevent attacks via same row and both diagonals. Backtracking
 * explores all possibilities, so the count is exhaustive.
 */
#include <bits/stdc++.h>
using namespace std;

bool isValid(int row, int col, char temp[][8])
{
    // check if the current position holds a queen
    if (temp[row][col] == 'q')
    {
        return false;
    }

    // search backwards in a row
    for (int i = col; i >= 0; i--)
    {
        if (temp[row][i] == 'q')
        {
            return false;
        }
    }
    // search upper left diagonal
    int i = row, j = col;
    while (i >= 0 && j >= 0)
    {
        if (temp[i][j] == 'q')
        {
            return false;
        }
        i--;
        j--;
    }

    // search bottom left diagonal

    i = row;
    j = col;

    while (i <= 7 && j >= 0)
    {
        if (temp[i][j] == 'q')
        {
            return false;
        }
        i++;
        j--;
    }

    return true;
}

void backtrack(char temp[][8], int col, int &res)
{
    if (col == 8)
    {
        res++;
        return;
    }

    for (int row = 0; row < 8; row++)
    {
        if (temp[row][col] != '*' && isValid(row, col, temp))
        {
            temp[row][col] = 'q';
            backtrack(temp, col + 1, res);
            temp[row][col] = '.';
        }
    }
}

int main()
{
    char matrix[8][8];

    for (int i = 0; i < 8; i++)
    {
        for (int j = 0; j < 8; j++)
        {
            cin >> matrix[i][j];
        }
    }

    int res = 0;
    backtrack(matrix, 0, res);
    cout << res << '\n';

    return 0;
}