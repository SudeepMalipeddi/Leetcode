#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 32B - Borze
// Link: https://codeforces.com/problemset/problem/32/B

// Problem: decode a Borze-encoded ternary string.
// Encoding: '.' = 0, '-.' = 1, '--' = 2
int main()
{

    string st;
    cin >> st;

    for (int i = 0; i < st.size(); i++)
    {
        if (st[i] == '.')
        {
            cout << 0;
        }
        else if (st[i] == '-')
        {
            // '-' is always a two-character code; peek at next char
            if (st[i + 1] == '-')
            {
                cout << 2;
                i++;  // consume both chars
            }
            else if (st[i + 1] == '.')
            {
                cout << 1;
                i++;  // consume both chars
            }
        }
    }

    return 0;
}
