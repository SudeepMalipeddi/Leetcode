#include <iostream>
#include <bits/stdc++.h>
using namespace std;

#define ll long long

int main()
{

    int n;
    cin >> n;

    for (int k = 1; k <= n; k++)
    {
        ll cell = k * k;
        if (k == 1)
            cout << 0 << endl;
        else
        {
            ll total = (cell * (cell - 1)) / 2;
            ll slabs = (k - 2) * (k - 1) * 4;
            cout << total - slabs << endl;
        }
    }

    return 0;
}