#include <iostream>
#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int n;
    cin >> n;
    // vector<int> arr(n);

    int maxi = -1e9;
    int mini = 1e9;

    for (int i = 0; i < n; i++)
    {
        int a;
        cin >> a;
        // arr[i] = a;
        if (a > maxi)
            maxi = a;
        if (a < mini)
            mini = a;
    }

    int res = (maxi - mini + 1) / 2;
    cout << res << "\n";
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t > 0)
    {

        // sort(arr.begin(), arr.end());
        // int median = 0;
        // if (n % 2 == 1)
        // {
        //     median = arr[n / 2];
        //     // cout << "median is " << median << endl;
        // }
        // else
        // {
        //     median = floor((arr[(n / 2) - 1] + arr[n / 2]) / 2.0);
        //     // cout << "median is " << median << endl;
        // }
        // int res = 0;
        // bool flag = false;
        // while (1)
        // {
        //     bool equal = true;
        //     for (int i = 0; i < n; i++)
        //     {
        //         if (arr[i] != median)
        //         {
        //             equal = false;
        //             break;
        //         }
        //     }
        //     if (equal)
        //         break;

        //     for (int i = 0; i < n; i++)
        //     {
        //         if (arr[i] < median)
        //         {
        //             arr[i]++;
        //         }
        //         else if (arr[i] > median)
        //         {
        //             arr[i]--;
        //         }
        //     }
        //     res++;
        // }

        // cout << res << "\n";
        solve();

        t--;
    }
    return 0;
}