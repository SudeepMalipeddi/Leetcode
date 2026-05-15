#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 1A - Theatre Square
// Link: https://codeforces.com/problemset/problem/1/A
// Problem: calculate the number of a x a square flagstones needed to cover a m x n rectangular area.

int main()
{
    int m, n, a;
    cin >> m >> n >> a;

    // Calculate the number of flagstones needed along each dimension, rounding up
    long long flag_m = ceil((double)m / a);
    long long flag_n = ceil((double)n / a);
    // Total flagstones is the product of the two dimensions
    long long total_flagstones = flag_m * flag_n;
    cout << total_flagstones << endl;
    return 0;
}