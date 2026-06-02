/*
 * Problem: CSES - Minimizing Coins
 *
 * Problem Statement:
 * Given n coin denominations and a target sum x, find the minimum number of
 * coins needed to make exactly x. If impossible, output -1.
 *
 * Intuition:
 * Classic unbounded knapsack / coin change DP. dp[i] = min coins for sum i.
 * For each sum, try subtracting each coin: dp[i] = min(dp[i], dp[i-coin] + 1).
 * If dp[target] remains INF, it's impossible.
 *
 * Approach:
 * 1. Read n, target, and coin denominations.
 * 2. dp array of size target+1 initialized to INF, dp[0] = 0.
 * 3. For i=1..target: for each coin j: if i>=coin[j], dp[i]=min(dp[i], dp[i-coin[j]]+1).
 * 4. Print dp[target] if < INF, else -1.
 *
 * Time Complexity: O(n * target).
 * Space Complexity: O(target) for dp array.
 *
 * Edge Cases:
 * - target=0: answer 0.
 * - No combination: output -1.
 *
 * Dry Run:
 * n=3, target=11, coins=[1,5,7]
 * dp[0]=0, dp[1]=1, dp[5]=1, dp[7]=1
 * dp[11]=min(dp[10]+1, dp[6]+1, dp[4]+1); dp[6]=2 (1+5), dp[10]=2 → dp[11]=3 (5+5+1)
 *
 * Correctness Check:
 * The DP correctly considers all coin choices for each sum. INF initialization
 * ensures impossible sums are detected. Unbounded since same coin can be reused.
 */
#include <iostream>
#include <vector>
using namespace std;

const int INF = 1e9;

int minCoins(int target, vector<int> &coins_deno)
{
    if (target == 0)
        return 0;
    if (target < 0)
        return INF;

    int best = INF;
    for (int i = 0; i < coins_deno.size(); i++)
    {
        int res = minCoins(target - coins_deno[i], coins_deno);
        if (res != INF)
        {
            best = min(best, 1 + res);
        }
    }

    return 1 + // min of every call
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, x;
    vector<int> coin_denominations(n);

    return 0;
}