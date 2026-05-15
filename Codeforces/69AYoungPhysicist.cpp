#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 69A - Young Physicist
// Link: https://codeforces.com/problemset/problem/69/A

// Problem: given n force vectors, check if they sum to zero (system in equilibrium).
int main()
{
    int n;
    cin >> n;
    int x = 0, y = 0, z = 0;
    // Accumulate component-wise sum of all vectors
    while (n > 0)
    {
        int a, b, c;
        cin >> a >> b >> c;
        x += a;
        y += b;
        z += c;
        n--;
    }
    // Resultant is zero iff all components cancel out
    if (x == 0 && y == 0 && z == 0)
    {
        cout << "YES" << endl;
    }
    else
    {
        cout << "NO" << endl;
    }

    return 0;
}
