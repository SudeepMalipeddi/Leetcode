#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 272A - Dima and Friends
// Link: https://codeforces.com/problemset/problem/272/A

int main()
{
    int n, sum = 0;
    cin >> n;
    vector<int> v(n);

    for (int i = 0; i < n; i++)
    {
        int temp;
        cin >> temp;
        sum += temp;
    }

    int res = 0;
    for (int i = 1; i <= 5; i++)
    {
        int curr = (sum + i) % (n + 1);
        if (curr != 1)
        {
            res++;
        }
    }

    cout << res << '\n';

    return 0;
}
