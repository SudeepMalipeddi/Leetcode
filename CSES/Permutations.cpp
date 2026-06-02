#include <iostream>
#include <bits/stdc++.h>
using namespace std;

/*
 * Problem: CSES - Permutations
 *
 * Problem Statement:
 * Construct a "beautiful" permutation of 1..n where no two adjacent elements
 * differ by 1. Print it or "NO SOLUTION" if impossible.
 *
 * Intuition:
 * To avoid adjacent differences of 1, we can separate evens and odds. Even
 * numbers differ by 2, odds differ by 2, so printing all evens then all odds
 * (or vice versa) guarantees no pair differs by 1, EXCEPT at the transition
 * point. For n <= 3, it's impossible (only for n=2,3; n=1 works). For n=4,
 * 2 4 1 3 works.
 *
 * Approach:
 * 1. If n == 1: print 1.
 * 2. If n == 2 or n == 3: print "NO SOLUTION".
 * 3. If n is even: print evens (2,4,...,n) then odds (1,3,...,n-1).
 * 4. If n is odd: print evens descending (n-1,n-3,...,2) then odds descending
 *    (n,n-2,...,1) to avoid the transition being adjacent.
 *
 * Time Complexity: O(n) — printing each number once.
 * Space Complexity: O(1).
 *
 * Edge Cases:
 * - n=1: output 1.
 * - n=2 or 3: impossible.
 * - n=4: 2 4 1 3.
 *
 * Dry Run:
 * n=5 (odd): evens desc: 4 2. odds desc: 5 3 1. Output: 4 2 5 3 1.
 * Check: |4-2|=2, |2-5|=3, |5-3|=2, |3-1|=2. All >= 2. ✓
 *
 * Correctness Check:
 * The even-odd separation guarantees within-group differences of 2. The
 * transition from largest even to largest odd ensures difference >= 2 for n>3.
 */

int main()
{
    int n;
    cin >> n;

    if (n == 1)
    {
        cout << 1 << endl;
        return 0;
    }
    if (n == 2 || n == 3)
    {
        cout << "NO SOLUTION" << endl;
        return 0;
    }
    if (n % 2 == 0)
    {
        for (int i = 2; i <= n; i += 2)
        {
            cout << i << " ";
        }
        for (int i = 1; i < n; i += 2)
        {
            cout << i << " ";
        }
    }
    else
    {
        for (int i = n - 1; i > 0; i -= 2)
        {
            cout << i << " ";
        }
        for (int i = n; i > 0; i -= 2)
        {
            cout << i << " ";
        }
    }

    return 0;
}