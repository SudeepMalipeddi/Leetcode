/*
 * Problem: CSES - Tower of Hanoi
 *
 * Problem Statement:
 * Solve the classic Tower of Hanoi puzzle for n disks. Print the minimum number
 * of moves (2^n - 1) and the sequence of moves (source → destination peg).
 * Pegs are numbered 1 (source), 2 (auxiliary), 3 (destination).
 *
 * Intuition:
 * Recursive solution: to move n disks from src to dst:
 * 1. Move n-1 disks from src to auxiliary (using dst as helper).
 * 2. Move the largest disk from src to dst.
 * 3. Move n-1 disks from auxiliary to dst (using src as helper).
 * Base case: n=0 does nothing.
 *
 * Approach:
 * 1. Read n.
 * 2. Print total moves: (1 << n) - 1.
 * 3. Call recursive function toh(n, 1, 2, 3):
 *    - toh(n-1, src, dst, middle) — move n-1 to auxiliary.
 *    - Print src → dst.
 *    - toh(n-1, middle, src, dst) — move n-1 to destination.
 *
 * Time Complexity: O(2^n) — each recursive call prints one move, total 2^n-1 moves.
 * Space Complexity: O(n) recursion stack depth.
 *
 * Edge Cases:
 * - n = 1: 1 move (1→3).
 * - n = 0: 0 moves.
 * - n up to 16: 2^16-1 = 65535 moves, feasible.
 *
 * Dry Run:
 * n = 2:
 * toh(2,1,2,3): toh(1,1,3,2) → print "1 2" → toh(1,2,1,3) → print "2 3"
 * Wait: let me trace properly:
 * toh(2,1,2,3):
 *   toh(1,1,3,2): print "1 3"? No, toh(1, src=1, middle=3, right=2):
 *     toh(0,...) return. print "1 2". toh(0,...) return.
 *   print "1 3"
 *   toh(1,2,1,3): print "2 3"
 * Output: 1 2 / 1 3 / 2 3. Total: 3 moves. ✓
 *
 * Correctness Check:
 * The classic recursive solution is provably optimal. The recurrence T(n) =
 * 2T(n-1) + 1 gives exactly 2^n - 1 moves.
 */
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

void toh(int n, int src, int middle, int right)
{
    if (n == 0)
        return;
    toh(n - 1, src, right, middle);

    cout << src << " " << right << endl;

    toh(n - 1, middle, src, right);
}

int main()
{
    int n;
    cin >> n;

    cout << (1 << n) - 1 << endl;

    toh(n, 1, 2, 3);

    return 0;
}