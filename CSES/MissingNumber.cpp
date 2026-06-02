/*
 * Problem: CSES - Missing Number
 *
 * Problem Statement:
 * You are given all numbers between 1, 2, ..., n except one.
 * Your task is to find the missing number.
 *
 * Intuition:
 * The sum of all natural numbers from 1 to `n` is given by the formula `n*(n+1)/2`.
 * If we calculate this total sum and subtract the sum of all the given `n-1` numbers,
 * the remainder will be exactly the value of the missing number. Since the sum can
 * exceed the 32-bit integer limit for large `n` (up to 2*10^5), we need to use a
 * 64-bit integer type (`long long`).
 *
 * Approach:
 * 1. Read `n` and initialize a variable `total_sum` = `n * (n + 1) / 2`.
 * 2. Alternatively, initialize `sum = 0` to compute the sum of the given array.
 * 3. Loop `n-1` times to read each provided number and add it to `sum`.
 * 4. At the end, output the difference: `(n * (n + 1) / 2) - sum`.
 *
 * Time Complexity: O(N), as we iterate exactly `n-1` times to read the input.
 * Space Complexity: O(1), since we only store a few variables (no need for an array).
 *
 * Edge Cases:
 * - n = 2 (the minimum possible value), works normally.
 * - Missing number is the first or last number in the sequence.
 *
 * Dry Run:
 * n = 5, given numbers = 2, 3, 1, 5
 * 1. total_sum expected = 5 * 6 / 2 = 15.
 * 2. sum of given = 2 + 3 + 1 + 5 = 11.
 * 3. Missing number = 15 - 11 = 4.
 * Output: 4
 *
 * Correctness Check:
 * By keeping track of the sum using `long long`, we prevent signed integer overflow
 * when calculating both `n * (n + 1) / 2` and the sum of the series.
 */
#include <iostream>
using namespace std;

typedef long long ll;
int main()
{
    ll n, sum = 0;
    cin >> n;
    for (int i = 0; i < n - 1; i++)
    {
        ll x;
        cin >> x;
        sum += x;
    }
    cout << n * (n + 1) / 2 - sum << endl;

    return 0;
}