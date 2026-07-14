#include <iostream>
#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int n;
    cin >> n;
    vector<int> arr(n);
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }
    // sort elements
    sort(arr.begin(), arr.end());
    // check if all elements are same

    if (arr[0] == arr[n - 1])
    {
        cout << -1 << "\n";
        return;
    }

    vector<int> b, c;

    // else solution exists
    int maxi = arr[n - 1];
    for (int i = 0; i < n; i++)
    {
        // add all the maximum elements into C and the rest in B
        if (arr[i] == maxi)
        {
            c.push_back(arr[i]);
        }
        else
        {
            b.push_back(arr[i]);
        }
    }

    int b1 = b.size();
    int c1 = c.size();

    cout << b1 << " " << c1 << "\n";
    for (int i = 0; i < b1; i++)
    {
        cout << b[i] << (i == b.size() - 1 ? "" : " ");
    }
    cout << "\n";

    for (int i = 0; i < c1; i++)
    {
        cout << c[i] << (i == c.size() - 1 ? "" : " ");
    }
    cout << "\n";
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