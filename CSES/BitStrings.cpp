#include <iostream>
#include <bits/stdc++.h>
using namespace std;

/* Your task is to calculate the number of bit strings of length n.
For example, if n=3, the correct answer is 8, because the possible bit strings are 000, 001, 010, 011, 100, 101, 110, and 111.
Input
The only input line has an integer n.
Output
Print the result modulo 10^9 + 7
Constraints

1 <= n <= 10^6

Example
Input:
3

Output:
8
*/

int main()
{
    int n;
    cin >> n;
    long long mod = 1000000007;
    long long res = 1, base = 2;
    long long exp = n;
    while (exp > 0)
    {
        if (exp % 2 == 1)
        {
            res = (res * base) % mod;
        }
        base = (base * base) % mod;
        exp /= 2;
    }
    cout << res;

    return 0;
}