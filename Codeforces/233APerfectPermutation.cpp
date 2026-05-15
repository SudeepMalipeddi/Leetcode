#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 233A - Perfect Permutation
// Link: https://codeforces.com/problemset/problem/233/A

// Problem: construct a permutation of [1..n] where every element is NOT in its natural position
// (i.e. p[i] != i+1 for all i). This is a derangement.
// Strategy: swap adjacent pairs — (2,1,4,3,6,5,...). Only works if n is even.
int main()
{
    int n;
    cin >> n;

    if (n % 2 == 1)
    {
        // Impossible for odd n: the middle element would have to map to itself
        cout << -1 << endl;
        exit(0);
    }

    // Output pairs: (2,1), (4,3), (6,5), ...
    for (int i = 1; i <= n; i += 2)
    {
        cout << i + 1 << " ";
        if (i == n - 1)
        {
            cout << i;
            break;
        }
        cout << i << " ";
    }
    return 0;
}
