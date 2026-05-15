#include <iostream>
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