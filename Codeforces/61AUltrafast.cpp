#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 61A - Ultra-Fast Mathematician
// Link: https://codeforces.com/problemset/problem/61/A

// Problem: XOR two equal-length binary strings digit by digit.
// Same digits → 0, different digits → 1.
int main()
{
    string n1, n2;
    char res[n1.size() + 1];
    cin >> n1 >> n2;

    for (int i = 0; i < n1.size(); i++)
    {
        if (n1[i] == n2[i])
        {
            cout << '0';
        }
        else
        {
            cout << '1';
        }
    }

    return 0;
}
