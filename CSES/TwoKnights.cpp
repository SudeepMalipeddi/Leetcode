/*
 * Problem: CSES - Two Knights
 *
 * Problem Statement:
 * For each k from 1 to n, calculate the number of ways to place two knights on
 * a k×k chessboard so that they do NOT attack each other.
 *
 * Intuition:
 * Total ways to place 2 knights on a k×k board: C(k^2, 2) = k^2*(k^2-1)/2.
 * From this, subtract the number of attacking positions. Two knights attack
 * each other when placed in a 2×3 or 3×2 rectangle (in either corner). The
 * number of such 2×3 rectangles on a k×k board is (k-1)*(k-2), and a 3×2
 * rectangle also appears (k-2)*(k-1) times. Each rectangle has 2 attacking
 * configurations → 2 * 2 * (k-1)*(k-2) = 4*(k-1)*(k-2).
 *
 * Approach:
 * For each k:
 * - total = k*k * (k*k - 1) / 2
 * - attacking = 4 * (k-1) * (k-2)
 * - answer = total - attacking
 * - Handle k=1 specially: answer = 0.
 *
 * Time Complexity: O(n).
 * Space Complexity: O(1).
 *
 * Edge Cases:
 * - k=1: 0 (only one cell, can't place two knights).
 * - k=2: 0 (no 2×3 or 3×2 rectangle fits).
 * - Use long long to avoid overflow.
 *
 * Dry Run:
 * k=1: total=0, attacks=0 → 0
 * k=2: total=4*3/2=6, attacks=4*1*0=0 → 6
 * k=3: total=9*8/2=36, attacks=4*2*1=8 → 28
 * k=4: total=16*15/2=120, attacks=4*3*2=24 → 96
 *
 * Correctness Check:
 * Combinatorial counting: total placements - attacking placements. The 4*(k-1)*(k-2)
 * formula accounts for both orientations (2×3 and 3×2) and both knight positions
 * within each rectangle.
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

#define ll long long

int main()
{

    int n;
    cin >> n;

    for (int k = 1; k <= n; k++)
    {
        ll cell = k * k;
        if (k == 1)
            cout << 0 << endl;
        else
        {
            ll total = (cell * (cell - 1)) / 2;
            ll slabs = (k - 2) * (k - 1) * 4;
            cout << total - slabs << endl;
        }
    }

    return 0;
}