#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int rows, cols;
    cin >> rows >> cols;
    
    vector<vector<int>> matrix(rows, vector<int>(cols, 0));

    int dx[] = {0, 1, 0, -1};
    int dy[] = {1, 0, -1, 0};
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            int count = 0;
            for (int k = 0; k < 4; k++)
            {
                int nx = dx[k] + i, ny = dy[k] + j;
                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
                    continue;
                else
                {
                    count++;
                }
            }
            matrix[i][j] = count;
        }
    }
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            cout << matrix[i][j] << " ";
        }
        cout << "\n";
    }
    return 0;
}