/*
 * Problem: CSES - Number Spiral
 *
 * Problem Statement:
 * A number spiral is an infinite grid where numbers are placed in a spiral
 * starting from 1 at the top-left. Given t queries (row, col), output the
 * number at that position. Rows and columns are 1-indexed.
 *
 * Intuition:
 * The spiral follows a pattern: for the outer layer of a k×k square, the bottom-
 * right corner is k^2. Depending on whether the row or column is larger, we can
 * compute the value by counting from the nearest square boundary.
 *
 * Approach:
 * For each query (row, col):
 * - If col >= row: (we're in a column-dominant section)
 *   - If col is odd: value = col^2 - (row - 1) [counting down from top-right]
 *   - If col is even: value = (col-1)^2 + row [counting up from bottom-left]
 * - If row > col: (row-dominant section)
 *   - If row is odd: value = (row-1)^2 + col [counting right from left boundary]
 *   - If row is even: value = row^2 - (col - 1) [counting left from right boundary]
 *
 * Time Complexity: O(t) per query, O(1) each — total O(t).
 * Space Complexity: O(1).
 *
 * Edge Cases:
 * - (1,1): col>=row, col=1 odd: 1^2-(1-1)=1.
 * - Large values up to 10^9: use long long to avoid overflow.
 *
 * Dry Run:
 * row=2, col=3: col>=row, col=3 odd: 3^2-(2-1)=9-1=8.
 * row=4, col=2: row>col, row=4 even: 4^2-(2-1)=16-1=15.
 *
 * Correctness Check:
 * The piecewise formula maps the spiral's layer structure. Each case handles
 * the direction of numbering within that layer.
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

#define ll long long

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        ll row, col;
        cin >> row >> col;
        ll res = 0;
        if (col >= row)
        {
            if (col % 2 == 1)
            {
                res = (col * col) - (row - 1);
                cout << res << endl;
            }
            else
            {
                res = ((col - 1) * (col - 1)) + (row);
                cout << res << endl;
            }
        }
        else
        {
            if (row % 2 == 1)
            {
                res = ((row - 1) * (row - 1)) + (col);
                cout << res << endl;
            }
            else
            {
                res = (row * row) - (col - 1);
                cout << res << endl;
            }
        }
    }
    return 0;
}