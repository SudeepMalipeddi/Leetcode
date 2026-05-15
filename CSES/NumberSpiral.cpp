#include <iostream>
#include <bits/stdc++.h>
using namespace std;

#define ll long long

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        ll row, col;
        cin >> row >> col;
        ll res = 0;
        if (col >= row)
        {
            if (col % 2 == 1)
            {
                res = (col * col) - (row - 1);
                cout << res << endl;
            }
            else
            {
                res = ((col - 1) * (col - 1)) + (row);
                cout << res << endl;
            }
        }
        else
        {
            if (row % 2 == 1)
            {
                res = ((row - 1) * (row - 1)) + (col);
                cout << res << endl;
            }
            else
            {
                res = (row * row) - (col - 1);
                cout << res << endl;
            }
        }
    }
    return 0;
}