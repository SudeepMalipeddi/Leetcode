#include <iostream>
#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int n;
    cin >> n;
    // find minimum difference between two consecutive elements
    vector<int> arr(n);
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    int minimum = INT_MAX;
    bool flag = false;

    for (int i = 0; i < n - 1; i++)
    {
        int diff = arr[i + 1] - arr[i];
        if (diff < 0)
        {
            flag = true;
            break;
        }
        minimum = min(minimum, (diff + 2) / 2);
    }

    cout << (flag ? 0 : minimum) << "\n";
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