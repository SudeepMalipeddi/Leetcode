#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n, m;
    queue<pair<int, int>> q;
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        int temp;
        cin >> temp;
        pair<int, int> t(temp, i);
        q.push(t);
    }
    while (q.size() > 1)
    {
        pair<int, int> temp = q.front();
        if (temp.first > m)
        {
            temp.first -= m;
            q.pop();
            q.push(temp);
        }
        else
        {
            q.pop();
        }
    }
    pair<int, int> temp = q.front();
    cout << temp.second + 1 << '\n';
}