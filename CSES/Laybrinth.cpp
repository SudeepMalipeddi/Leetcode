#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

/*
 * Problem: CSES - Labyrinth
 *
 * Problem Statement:
 * Given an n×m labyrinth with floor ('.'), walls ('#'), start ('A'), and
 * end ('B'), find the shortest path from A to B. Print "YES" with the path
 * length and direction string (L/R/U/D), or "NO" if unreachable.
 *
 * Intuition:
 * Classic shortest path on an unweighted grid — BFS. BFS explores level by
 * level, so the first time we reach B we've found the shortest path. Track
 * parent cells and direction taken to reconstruct the path.
 *
 * Approach:
 * 1. Read grid, locate start (A) and end (B).
 * 2. BFS from A: use dx/dy arrays for 4 directions (R,D,L,U).
 * 3. For each neighbor: if in bounds, not a wall, not visited: mark visited,
 *    store parent, push to queue. If it's B, set found=true.
 * 4. If found: trace back from B to A via parent array, determine direction
 *    taken at each step, reverse the string. Print "YES", length, path.
 * 5. Else print "NO".
 *
 * Time Complexity: O(n * m) — each cell visited at most once.
 * Space Complexity: O(n * m) for visited and parent arrays.
 *
 * Edge Cases:
 * - A and B adjacent: path length 1.
 * - B unreachable (surrounded by walls): "NO".
 * - Grid up to 1000×1000: O(n*m) memory and time is fine.
 *
 * Dry Run:
 * 5×8 grid with A at (1,2), B at (2,6). BFS explores outward.
 * Path: (1,2) → D to (2,2) → R,R,R,R to (2,6). Or other route.
 * Output: YES, length, and path string.
 *
 * Correctness Check:
 * BFS on unweighted grid guarantees shortest path. Parent reconstruction traces
 * back correctly. 4-direction bounds/wall checks ensure valid moves.
 */

int main()
{
    int rows, cols;
    cin >> rows >> cols;

    vector<string> matrix(rows);
    int startx, starty, endx, endy;

    for (int i = 0; i < rows; i++)
    {
        cin >> matrix[i];
        for (int j = 0; j < cols; j++)
        {
            if (matrix[i][j] == 'A')
            {
                startx = i;
                starty = j;
            }
            if (matrix[i][j] == 'B')
            {
                endx = i;
                endy = j;
            }
        }
    }

    int dx[] = {0, 1, 0, -1};
    int dy[] = {1, 0, -1, 0};
    char dc[] = {'R', 'D', 'L', 'U'};

    vector<vector<pair<int, int>>> parent(rows, vector<pair<int, int>>(cols, {-1, -1}));
    vector<vector<bool>> visited(rows, vector<bool>(cols, false));

    queue<pair<int, int>> q;
    q.push({startx, starty});
    visited[startx][starty] = true;
    bool found = false;

    while (!q.empty() && !found)
    {
        auto [x, y] = q.front();
        q.pop();

        for (int k = 0; k < 4; k++)
        {
            int nx = x + dx[k], ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || visited[nx][ny] || matrix[nx][ny] == '#')
                continue;

            visited[nx][ny] = true;
            parent[nx][ny] = {x, y};

            if (nx == endx && ny == endy)
            {
                found = true;
                break;
            }
            q.push({nx, ny});
        }
    }

    if (!found)
    {
        cout << "NO\n";
        return 0;
    }

    // trace back from B to A using parent array
    string path = "";
    int cx = endx, cy = endy;
    while (cx != startx || cy != starty)
    {
        auto [px, py] = parent[cx][cy];
        // figure out which direction was taken from parent to current
        for (int k = 0; k < 4; k++)
        {
            if (px + dx[k] == cx && py + dy[k] == cy)
            {
                path += dc[k];
                break;
            }
        }
        cx = px;
        cy = py;
    }

    reverse(path.begin(), path.end());
    cout << "YES\n"
         << path.size() << "\n"
         << path << "\n";
    return 0;
}