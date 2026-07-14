#include <iostream>
#include <bits/stdc++.h>
using namespace std;

void solve()
{
    long long a, b, c;
    cin >> a >> b >> c;

    long long anna = a + (c + 1) / 2;
    long long kat = b + c / 2;

    if (anna > kat)
    {
        cout << "First\n";
    }
    else
    {
        cout << "Second\n";
    }
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