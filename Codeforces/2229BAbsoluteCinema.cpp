#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;
        vector<long long> a(n), b(n);
        for (int i = 0; i < n; i++)
            cin >> a[i];
        for (int i = 0; i < n; i++)
            cin >> b[i];

        long long sum = 0;
        long long maxl = 0;
        for (int i = 0; i < n; i++)
        {
            sum += max(a[i], b[i]);
            maxl = max(maxl, min(a[i], b[i]));
        }
        cout << sum + maxl << "\n";
    }
    return 0;
}