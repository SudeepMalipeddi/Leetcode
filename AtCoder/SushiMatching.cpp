#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n, m;
    cin >> n >> m;
    vector<long long> shari(n);
    vector<long long> neta(m);

    for (int i = 0; i < n; i++)
    {
        cin >> shari[i];
    }
    for (int i = 0; i < m; i++)
    {
        cin >> neta[i];
    }

    sort(shari.begin(), shari.end());
    sort(neta.begin(), neta.end());

    int i = 0, j = 0, ans = 0;
    while (i < n && j < m)
    {
        if (neta[j] <= 2 * shari[i])
        {
            ans++;
            i++;
            j++;
        }
        else
        {
            i++;
        }
    }

    cout << ans << '\n';
    return 0;
}