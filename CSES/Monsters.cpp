/*
 * Problem: CSES - Monsters
 *
 * Problem Statement:
 * You and monsters are in an n×m labyrinth. Each step, you and ALL monsters move
 * simultaneously. Reach any boundary cell without ever sharing a cell with a
 * monster. Print "YES" with the path, or "NO".
 *
 * Intuition:
 * You can reach a cell safely only if you arrive BEFORE any monster. Multi-source
 * BFS from all monsters computes monster_dist[i][j]. Then BFS from your start,
 * only moving to cells where your_arrival_time < monster_arrival_time. Any
 * boundary cell reached safely is an escape.
 *
 * Approach:
 * 1. Read grid, find start (A) and all monster positions (M).
 * 2. Multi-source BFS from all M to compute monster_dist (INF for unreachable).
 * 3. BFS from A: for each neighbor, if your_dist+1 < monster_dist[nx][ny] and
 *    not visited: visit, store parent direction.
 * 4. If a boundary cell is reached: trace back path, print "YES" + path.
 * 5. Else: "NO".
 *
 * Time Complexity: O(n * m) — two BFS passes.
 * Space Complexity: O(n * m) for distance and parent arrays.
 *
 * Edge Cases:
 * - Start on boundary: immediate escape (path length 0).
 * - Monsters surround you: "NO".
 * - Multiple monsters converging from different directions.
 *
 * Dry Run:
 * 5×8 grid with A at (1,3), monsters at (1,1),(2,4),(3,1).
 * Monster BFS fills distances. Your BFS from A explores outward.
 * If path RRDDR reaches boundary at time 5 before any monster, "YES" + RRDDR.
 *
 * Correctness Check:
 * Simultaneous movement modeled by comparing arrival times. Strict less-than
 * ensures you never share a cell with a monster. Multi-source BFS correctly
 * computes minimum monster arrival at each cell.
 */

#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
char dir[] = {'U', 'D', 'L', 'R'};
const int INF = 1e9;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;

    vector<string> grid(n);

    for (int i = 0; i < n; i++)
    {
        string str;
        cin >> str;
        grid[i] = str;
    }

    // find monsters positions
    // find starting position
    vector<pair<int, int>> monsters;
    pair<int, int> start;

    vector<vector<int>> monster_dist(n, vector<int>(m, INF));
    vector<vector<int>> your_dist(n, vector<int>(m, INF));
    vector<vector<int>> parent(n, vector<int>(m, -1));

    queue<pair<int, int>> q;

    // note start position and monster location
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 'A')
            {
                start = {i, j};
            }
            else if (grid[i][j] == 'M')
            {
                // pair<int,int>
                monsters.push_back({i, j});
                q.push({i, j});
                monster_dist[i][j] = 0;
            }
        }
    }

    while (!q.empty())
    {
        auto [x, y] = q.front();
        q.pop();

        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] != '#')
            {
                if (monster_dist[nx][ny] == INF)
                {
                    monster_dist[nx][ny] = monster_dist[x][y] + 1;
                    q.push({nx, ny});
                }
            }
        }
    }

    queue<pair<int, int>> qplayer;
    qplayer.push(start);
    your_dist[start.first][start.second] = 0;

    pair<int, int> escape_tile = {-1, -1};
    while (!qplayer.empty())
    {
        auto [x, y] = qplayer.front();
        qplayer.pop();

        if (x == 0 || x == n - 1 || y == 0 || y == m - 1)
        {
            escape_tile = {x, y};
            break;
        }

        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] != '#')
            {
                int next_step_time = your_dist[x][y] + 1;

                if (next_step_time < monster_dist[nx][ny] && your_dist[nx][ny] == INF)
                {
                    your_dist[nx][ny] = next_step_time;
                    parent[nx][ny] = i;
                    qplayer.push({nx, ny});
                }
            }
        }
    }

    if (escape_tile.first == -1)
    {
        cout << "NO" << endl;
    }
    else
    {
        cout << "YES" << endl;

        // trace back from escape_tile to start using parent array
        string path = "";
        int cx = escape_tile.first;
        int cy = escape_tile.second;

        while (cx != start.first || cy != start.second)
        {
            int d = parent[cx][cy];
            path += dir[d];

            // reverse the direction to go back
            // if d=0 (U, dx=-1), reverse is +1 → cx+1
            // if d=1 (D, dx=+1), reverse is -1 → cx-1
            cx -= dx[d];
            cy -= dy[d];
        }

        reverse(path.begin(), path.end());
        cout << path.size() << endl;
        cout << path << endl;
    }

    return 0;
}