#include <iostream>
#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int n;
    cin >> n;
    int count = 0;
    for (int i = 0; i < n; i++)
    {
        int x;
        cin >> x;
        if (x % 2 != 0)
            count++;
    }
    cout << (count % 2 == 0 ? "YES" : "NO") << "\n";
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--)
    {
        solve();
    }
    return 0;
}