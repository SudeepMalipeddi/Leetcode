#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Codeforces 271A - Beautiful Year
// Link: https://codeforces.com/problemset/problem/271/A

/* A. Beautiful Year
time limit per test2 seconds
memory limit per test256 megabytes
It seems like the year of 2013 came only yesterday. Do you know a curious fact? The year of 2013 is the first year after the old 1987 with only distinct digits.

Now you are suggested to solve the following problem: given a year number, find the minimum year number which is strictly larger than the given one and has only distinct digits.

Input
The single line contains integer y (1000 ≤ y ≤ 9000) — the year number.

Output
Print a single integer — the minimum year number that is strictly larger than y and all it's digits are distinct. It is guaranteed that the answer exists.*/

// Check if all digits of year are unique using a seen[10] bitmask
bool hasDistinctDigits(int year)
{
    bool seen[10] = {false};

    while (year > 0)
    {
        int d = year % 10;
        if (seen[d])
            return false;
        seen[d] = true;
        year /= 10;
    }
    return true;
}

// Problem: find the smallest year strictly greater than n with all distinct digits.
// Brute-force: increment from n+1 until hasDistinctDigits passes.
int main()
{
    int n;
    cin >> n;
    int res = n + 1;
    while (!hasDistinctDigits(res))
    {
        res++;
    }
    cout << res << '\n';
    return 0;
}
