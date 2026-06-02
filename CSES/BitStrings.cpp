#include <iostream>
#include <bits/stdc++.h>
using namespace std;

/*
 * Problem: CSES - Bit Strings
 *
 * Problem Statement:
 * Calculate the number of bit strings of length n. Print the result modulo 10^9+7.
 *
 * Intuition:
 * For each of the n positions, we have 2 choices (0 or 1). The total number of
 * distinct bit strings is 2^n. Since n can be up to 10^6, we must compute this
 * efficiently using binary exponentiation and take the result modulo 10^9+7 to
 * avoid overflow.
 *
 * Approach:
 * 1. Read n.
 * 2. Use fast exponentiation (exponentiation by squaring):
 *    - Initialize res = 1, base = 2, exp = n.
 *    - While exp > 0:
 *      - If exp is odd: res = (res * base) % MOD.
 *      - base = (base * base) % MOD.
 *      - exp /= 2.
 * 3. Print res.
 *
 * Time Complexity: O(log n) — binary exponentiation.
 * Space Complexity: O(1).
 *
 * Edge Cases:
 * - n = 0: result should be 1 (empty string).
 * - n = 10^6: works within time, O(log n) is fast.
 *
 * Dry Run:
 * n = 3
 * res=1, base=2, exp=3
 * exp=3(odd): res = 1*2%MOD = 2, base=4, exp=1
 * exp=1(odd): res = 2*4%MOD = 8, base=16, exp=0
 * Output: 8
 *
 * Correctness Check:
 * Binary exponentiation correctly computes 2^n mod MOD in O(log n). The modulo
 * is applied at each step to keep numbers within 64-bit bounds.
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