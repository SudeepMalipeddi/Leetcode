/*
 * Problem Statement: Return the k-th smallest positive factor of n; if fewer than k factors exist, return -1.
 * Intuition: Enumerating integers in increasing order guarantees factors are discovered in sorted order.
 * Approach: Loop i from 1 upward, count divisors where n % i == 0, and return i when count reaches k.
 * Time Complexity: O(n) because loop checks divisibility for each i from 1 to n-1 in this code.
 * Space Complexity: O(1) using only scalar counters.
 * Edge Cases handled: Returns -1 when fewer than k discovered factors are found in the scanned range.
 * Dry Run: n=12,k=3 -> factors encountered: 1(count1),2(count2),3(count3) => returns 3.
 * LeetCode matching: Intended for LeetCode 1492 (The kth Factor of n).
 * Correctness Check: Real bug present—loop condition i < n excludes factor n itself; cases needing n as k-th factor return -1 incorrectly.
 */

public class KthFactors {
    public int kthFactor(int n, int k) {
        // Count of factors found so far in ascending order.
        int count = 1;
        // Test each candidate divisor from 1 up to n-1.
        for (int i = 1; i < n; i++) {
            // i is a factor only when it divides n exactly.
            if (n % i == 0) {
                // Return immediately when this is the k-th discovered factor.
                if (count == k) {
                    return i;
                }
                // Move to the next factor rank.
                count++;
            }
        }
        // Did not find k factors in scanned range.
        return -1;
    }
}
// Time Complexity reminder: O(n) because divisor checks run from 1 to n-1 in current loop.
// Space Complexity reminder: O(1) because only count/i scalars are used.
