/*
 * Problem: CSES - Dice Combinations
 *
 * Problem Statement:
 * Count the number of ways to roll a sum of n using a standard 6-sided die.
 * Each throw produces a value from 1 to 6. Order matters. Print result mod 10^9+7.
 *
 * Intuition:
 * This is a classic DP problem similar to counting ways to make change, but
 * with ordered sequences (permutations matter, not combinations). For a target
 * sum n, the number of ways is the sum of ways to reach n-1, n-2, ..., n-6
 * (since the last throw could be any value 1-6). This gives: dp[n] = sum(dp[n-i])
 * for i=1..6, with dp[0] = 1 (empty sequence).
 *
 * Approach:
 * 1. Read n.
 * 2. Initialize dp array of size n+1 with dp[0] = 1.
 * 3. For i from 1 to n:
 *    - For j from 1 to 6:
 *      - If i-j >= 0: dp[i] = (dp[i] + dp[i-j]) % MOD.
 * 4. Print dp[n].
 * (A recursive memoized version f(n, dp) is also shown but the iterative DP is used.)
 *
 * Time Complexity: O(6 * n) = O(n) — 6 transitions per state.
 * Space Complexity: O(n) for the dp array.
 *
 * Edge Cases:
 * - n = 0: 1 way (do nothing).
 * - n < 0: 0 ways.
 * - Large n up to 10^6: O(n) is fine, use long long and modulo.
 *
 * Dry Run:
 * n = 3
 * dp[0] = 1
 * i=1: only dp[0] → dp[1] = 1 (roll: 1)
 * i=2: dp[1]+dp[0] = 1+1 → dp[2] = 2 (rolls: 1+1, 2)
 * i=3: dp[2]+dp[1]+dp[0] = 2+1+1 → dp[3] = 4 (rolls: 1+1+1, 1+2, 2+1, 3)
 * Output: 4
 *
 * Correctness Check:
 * The recurrence dp[n] = sum(dp[n-i] for i=1..6) correctly counts ordered
 * sequences. Base case dp[0]=1 accounts for the sequence that sums exactly to n.
 * Modulo is applied at each step to prevent overflow.
 */
#include <iostream>
#include <vector>
using namespace std;

long long MOD = 1e9 + 7;

long long f(int n, vector<long long> &dp)
{
    if (n == 0)
        return 1;
    if (n < 0)
    {
        return 0;
    }
    if (dp[n] != -1)
        return dp[n];

    long long temp = (f(n - 1, dp) + f(n - 2, dp) + f(n - 3, dp) + f(n - 4, dp) + f(n - 5, dp) + f(n - 6, dp)) % MOD;
    return dp[n] = temp;
}

void count_number_of_ways(int n, vector<long long> &dp)
{
    dp[0] = 1;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= 6; j++)
        {
            if (i - j >= 0)
            {
                dp[i] = (dp[i] + dp[i - j]) % MOD;
            }
        }
    }
}

// f(0) = 1
// f(1) = 1, f(2) = 2(1+1, 2), f(3) =

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    vector<long long> dp(n + 1, 0);

    // cout << f(n, dp) << '\n';
    count_number_of_ways(n, dp);
    cout << dp[n] << '\n';

    return 0;
}