/*
 * Problem: CSES - Two Sets
 *
 * Problem Statement:
 * Divide the numbers 1..n into two sets of equal sum. Print "YES" with both
 * sets, or "NO" if impossible.
 *
 * Intuition:
 * The total sum S = n*(n+1)/2 must be even for an equal split. If S is odd,
 * output "NO". Otherwise, target = S/2. We greedily pick the largest remaining
 * numbers to reach the target, since larger numbers give us fewer items in set 1.
 *
 * Approach:
 * 1. Read n. Compute sum = n*(n+1)/2.
 * 2. If sum is odd: print "NO".
 * 3. Else: target = sum/2. Iterate i from n down to 1:
 *    - If i <= target: add i to set1, subtract i from target.
 *    - Else: add i to set2.
 * 4. Print "YES", size of set1, then set1, then size of set2, then set2.
 *
 * Time Complexity: O(n).
 * Space Complexity: O(n) to store the sets.
 *
 * Edge Cases:
 * - n=1: sum=1 odd → NO.
 * - n=2: sum=3 odd → NO.
 * - n=3: sum=6 even, target=3: 3≤3→set1={3}, 2≤0? no→set2={2}, 1≤0? no→set2={1}.
 *   set1={3}, set2={2,1}. YES.
 *
 * Dry Run:
 * n=7, sum=28, target=14
 * i=7≤14→set1={7}, target=7
 * i=6≤7→set1={7,6}, target=1
 * i=5>1→set2={5}
 * i=4>1→set2={5,4}
 * i=3>1→set2={5,4,3}
 * i=2>1→set2={5,4,3,2}
 * i=1≤1→set1={7,6,1}, target=0
 * set1: {7,6,1}, sum=14. set2: {5,4,3,2}, sum=14. ✓
 *
 * Correctness Check:
 * Greedy from largest to smallest always finds a valid subset summing to target
 * because the running target is always achievable with the remaining numbers
 * (they form a consecutive range).
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<int> arr1;
    vector<int> arr2;
    long long sum = 1LL * n * (n + 1) / 2;
    if (sum % 2 == 1)
    {
        cout << "NO" << endl;
    }
    else
    {
        sum = sum / 2;

        for (int i = n; i >= 1; i--)
        {
            // cout << i << endl;
            if (sum > 0 && i <= sum)
            {
                arr1.push_back(i);
                sum = sum - i;
            }
            else
            {
                arr2.push_back(i);
            }
        }
        cout << "YES" << endl;
        cout << arr1.size() << endl;
        for (int x : arr1)
        {
            cout << x << " ";
        }
        cout << endl;
        cout << arr2.size() << endl;
        for (int x : arr2)
        {
            cout << x << " ";
        }
    }
    return 0;
}