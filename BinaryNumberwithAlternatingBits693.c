/*
 * Problem: LeetCode 693. Binary Number with Alternating Bits
 *
 * Problem Statement:
 * Given a positive integer n, return true if its binary representation has alternating
 * bits (no two adjacent bits are the same).
 *
 * Intuition:
 * By examining bits from least significant to most, we can ensure each bit differs
 * from the previous one.
 *
 * Approach:
 * 1. Store the least significant bit as prev.
 * 2. Shift n right, compare each current bit to prev.
 * 3. If any adjacent bits are equal, return false.
 * 4. If we finish, return true.
 *
 * Time Complexity: O(b) where b is the number of bits in n.
 * Space Complexity: O(1).
 *
 * Edge Cases handled:
 * - Single-bit numbers (always alternating).
 * - n = 0 returns true in this implementation (LeetCode typically uses n >= 1).
 *
 * Dry Run:
 * n = 5 (101b)
 * prev=1, next bit=0 (ok), next bit=1 (ok) -> true
 *
 * Correctness Check:
 * Bitwise comparison is correct for detecting equal adjacent bits.
 *
 * LeetCode Match:
 * LeetCode 693 - "Binary Number with Alternating Bits".
 */
#include <stdio.h>
#include <stdbool.h>

bool hasAlternateBits(int n)
{
    // Start with the least significant bit
    int prev = n & 1;
    n >>= 1;
    while (n > 0)
    {
        // Extract current bit
        int curr = n & 1;
        // If adjacent bits are the same, it's not alternating
        if (curr == prev)
        {
            return false;
        }
        // Move forward: current becomes previous, shift to next bit
        prev = curr;
        n >>= 1;
    }
    return true;
}

int main()
{
    int n = 5;
    // Example usage
    bool res = hasAlternateBits(n);
    printf("%d\n", res);
    return 0;
}
