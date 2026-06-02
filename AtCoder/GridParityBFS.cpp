#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int rows, cols;
    cin >> rows >> cols;

    vector<string> matrix(rows);
    for (int i = 0; i < rows; i++)
    {
        cin >> matrix[i];
    }

    vector<vector<int>> dist(rows, vector<int>(cols, -1));
    queue<pair<int, int>> q;

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            if (matrix[i][j] == '#')
            {
                bool has_white_neighbor = false;
                for (int dx = -1; dx <= 1 && !has_white_neighbor; dx++)
                {
                    for (int dy = -1; dy <= 1; dy++)
                    {
                        if (dx == 0 && dy == 0)
                            continue;
                        int ni = i + dx;
                        int nj = j + dy;
                        if (ni < 0 || nj < 0 || ni >= rows || nj >= cols)
                            continue;
                        if (matrix[ni][nj] == '.')
                        {
                            has_white_neighbor = true;
                            break;
                        }
                    }
                }

                if (has_white_neighbor)
                {
                    dist[i][j] = 0;
                    q.push({i, j});
                }
            }
        }
    }

    int dx[] = {0, 0, -1, 1, 1, 1, -1, -1};
    int dy[] = {1, -1, -1, -1, 0, 1, 0, 1};

    while (!q.empty())
    {
        int x = q.front().first, y = q.front().second;
        q.pop();
        for (int i = 0; i < 8; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
                continue;

            if (dist[nx][ny] != -1)
                continue;

            dist[nx][ny] = dist[x][y] + 1;
            q.push({nx, ny});
        }
    }

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            if (dist[i][j] != -1 && dist[i][j] % 2 == 0)
            {
                matrix[i][j] = '#';
            }
            else
            {
                matrix[i][j] = '.';
            }
        }
    }

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            cout << matrix[i][j];
        }
        cout << "\n";
    }

    return 0;
}