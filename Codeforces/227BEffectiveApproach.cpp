#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 227B - Effective Approach
// Link: https://codeforces.com/problemset/problem/227/B

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<int> pos(n + 1);

    for (int i = 1; i <= n; ++i)
    {
        int x;
        cin >> x;
        pos[x] = i;
    }

    int m;
    cin >> m;

    long long vasya = 0, petya = 0;

    for (int i = 0; i < m; i++)
    {
        int q;
        cin >> q;
        int p = pos[q];
        vasya += p;
        petya += (n - p + 1);
    }
    cout << vasya << ' ' << petya << '\n';
}
