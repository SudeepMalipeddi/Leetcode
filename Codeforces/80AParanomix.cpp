#include <iostream>
#include <cmath>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 80A - Panoramix's Prediction
// Link: https://codeforces.com/problemset/problem/80/A

bool isprime(int n)
{
    if (n == 2)
        return true;
    if (n % 2 == 0)
        return false;
    for (int i = 3; i <= sqrt(n); i++)
    {
        if (n % i == 0)
            return false;
    }
    return true;
}

// Problem: output YES if d2 is prime AND there is no prime strictly between d1 and d2.
// i.e. d1 and d2 are consecutive primes (or adjacent with no prime gap between them).
int main()
{
    int d1, d2;
    cin >> d1 >> d2;
    if (!isprime(d2))
    {
        cout << "NO" << endl;
        exit(0);
    }
    else if (isprime(d2))
    {
        // Check no prime exists strictly between d1 and d2
        for (int i = d1 + 1; i < d2; i++)
        {
            if (isprime(i) == true)
            {
                cout << "NO" << endl;
                exit(0);
            }
        }
    }
    cout << "YES" << endl;

    return 0;
}
