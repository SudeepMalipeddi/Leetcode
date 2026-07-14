#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t-- > 0)
    {
        int n, x;
        cin >> n >> x;
        int dist = 0;
        vector<int> arr(n, 0);
        for (int i = 0; i < n; i++)
        {
            cin >> arr[i];
        }
        dist = max(dist, arr[0]);
        for (int i = 0; i < n - 1; i++)
        {
            dist = max(arr[i + 1] - arr[i], dist);
        }
        dist = max(dist, 2 * (abs(arr[n - 1] - x)));

        cout << dist << '\n';
    }
    return 0;
}