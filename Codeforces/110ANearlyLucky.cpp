#include <iostream>
#include <bits/stdc++.h>
#include <string>
using namespace std;

// Codeforces 110A - Nearly Lucky Number
// Link: https://codeforces.com/problemset/problem/110/A

// Problem: "Nearly Lucky" — lucky digits are 4 and 7.
// A number is nearly lucky if the COUNT of its lucky digits is itself a lucky number (4 or 7).
int main()
{
    long long n;
    cin >> n;
    long long count47 = 0, count4 = 0, counto = 0;
    int rem = 0;
    // Count how many digits are lucky (4 or 7)
    while (n > 0)
    {
        rem = n % 10;
        if (rem == 4 || rem == 7)
        {
            count47++;
        }
        else
        {
            counto++;
        }
        n /= 10;
    }
    // Check if the count itself is a lucky number
    if (count47 == 4 || count47 == 7)
    {
        cout << "YES" << endl;
        exit(0);
    }
    else
    {
        cout << "NO" << endl;
    }

    return 0;
}
