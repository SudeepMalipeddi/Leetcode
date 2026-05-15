#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 263A - Beautiful Matrix
// Link: https://codeforces.com/problemset/problem/263/A
// Problem: find the single '1' in a 5x5 matrix and count the minimum adjacent
// moves to bring it to the center cell (2, 2). Answer is Manhattan distance.
int main()
{
    int matrix[5][5];
    int x = 0, y = 0;
    for (int i = 0; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
            cin >> matrix[i][j];
            if (matrix[i][j] == 1)
            {
                x = i;
                y = j;
            }
        }
    }
    // Manhattan distance from (x, y) to center (2, 2)
    int moves = abs(x - 2) + abs(y - 2);
    cout << moves << endl;

    return 0;
}