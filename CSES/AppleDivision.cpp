/*
 * Problem: CSES - Apple Division
 *
 * Problem Statement:
 * There are n apples with given weights. Divide the apples into two groups so
 * that the difference between the sum of weights in the two groups is minimal.
 *
 * Intuition:
 * Since n <= 20, we can use recursion/backtracking to explore all 2^n possible
 * divisions. At each step, we assign the current apple to either group 1 or
 * group 2, then recurse on the remaining apples. When all apples are assigned,
 * we update the answer with the absolute difference between the two group sums.
 *
 * Approach:
 * 1. Read n and the weight array.
 * 2. Define a recursive function divide_apples(index, g1, g2):
 *    - If index == n: ans = min(ans, abs(g1 - g2)), return.
 *    - Recurse with apple[index] added to g1.
 *    - Recurse with apple[index] added to g2.
 * 3. Initialize ans = 1e18 (a very large number).
 * 4. Call divide_apples(0, 0, 0) and print ans.
 *
 * Time Complexity: O(2^n) — we explore every subset assignment.
 * Space Complexity: O(n) for the recursion stack depth.
 *
 * Edge Cases:
 * - n = 1: the difference is the weight of the single apple.
 * - All weights equal: optimal is splitting as evenly as possible.
 * - Large weights: use long long to avoid overflow.
 *
 * Dry Run:
 * n = 3, weights = [3, 2, 7]
 * Path 1: g1=[3,2,7], g2=[] → |12-0| = 12
 * Path 2: g1=[3,2], g2=[7] → |5-7| = 2
 * Path 3: g1=[3,7], g2=[2] → |10-2| = 8
 * Path 4: g1=[3], g2=[2,7] → |3-9| = 6
 * Path 5: g1=[2,7], g2=[3] → |9-3| = 6
 * Path 6: g1=[2], g2=[3,7] → |2-10| = 8
 * Path 7: g1=[7], g2=[3,2] → |7-5| = 2
 * Path 8: g1=[], g2=[3,2,7] → |0-12| = 12
 * Minimum = 2
 *
 * Correctness Check:
 * The brute-force enumeration of all 2^n partitions guarantees finding the
 * globally optimal division. The use of long long prevents overflow for sums
 * up to n * max(weight) = 20 * 1e9 = 2e10.
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

void divide_apples(vector<ll> &weights, ll &ans, int index, ll g1, ll g2)
{
    if (index == weights.size())
    {
        ans = min(ans, abs(g1 - g2));
        return;
    }

    divide_apples(weights, ans, index + 1, g1 + weights[index], g2);

    divide_apples(weights, ans, index + 1, g1, g2 + weights[index]);
}

int main()
{
    int n;
    cin >> n;
    vector<ll> weights(n);
    for (int i = 0; i < n; i++)
    {
        cin >> weights[i];
    }

    ll ans = 1e18;
    divide_apples(weights, ans, 0, 0, 0);

    cout << ans << endl;
    return 0;
}