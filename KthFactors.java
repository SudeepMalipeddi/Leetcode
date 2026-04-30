/*
 * Problem: LeetCode 1492 - The kth Factor of n
 *
 * Problem Statement:
 * Given two positive integers n and k, return the k-th smallest factor of n. 
 * If n has fewer than k factors, return -1.
 *
 * Intuition:
 * A factor is any integer i that divides n without a remainder (n % i == 0). 
 * By iterating through integers starting from 1 in increasing order, we 
 * encounter factors in their natural sorted order.
 *
 * Approach:
 * 1. Initialize a counter to keep track of how many factors have been found.
 * 2. Iterate through candidate integers starting from 1.
 * 3. For every integer that is a factor of n, check if our counter has reached k.
 * 4. If the counter matches k, return the current integer.
 * 5. If the loop completes without finding k factors, return -1.
 *
 * Time Complexity: O(n) because the algorithm performs a linear scan from 1 up to n-1.
 * Space Complexity: O(1) because we only use a constant amount of extra space for the counter.
 *
 * Edge Cases:
 * - n = 1: The loop condition i < n will prevent any factors from being found.
 * - k = 1: Should return 1, but depends on the counter initialization and loop logic.
 * - k is greater than the total number of factors: Should return -1.
 *
 * Dry Run:
 * n = 6, k = 2
 * 1. i = 1: 6 % 1 == 0. count is 1. count == k (2) is false. Increment count to 2.
 * 2. i = 2: 6 % 2 == 0. count is 2. count == k (2) is true. Return 2.
 * Result: 2 (Correct).
 *
 * Correctness Check:
 * There is a logic bug in the loop condition: `i < n`. This prevents the code from 
 * checking if `n` itself is the k-th factor. For example, if n=7 and k=2, the factors 
 * are [1, 7]. The loop stops at 6, fails to find the 2nd factor, and returns -1 
 * instead of 7.
 */

public class KthFactors {
    public int kthFactor(int n, int k) {
        // Initialize a counter to track the ordinal position of factors found.
        int count = 1;
        
        // Iterate through all numbers from 1 up to (but not including) n.
        // Note: To be fully correct, this should usually be i <= n.
        for (int i = 1; i < n; i++) {
            
            // Check if 'i' divides 'n' perfectly using the modulo operator.
            if (n % i == 0) {
                
                // If this is the k-th factor we've encountered, return it immediately.
                if (count == k) {
                    return i;
                }
                
                // Increment the counter as we have found another valid factor.
                count++;
            }
        }
        
        // If the loop finishes, it means we found fewer than k factors in the range [1, n-1].
        return -1;
    }
}
// Time Complexity reminder: O(n) because divisor checks run from 1 to n-1 in current loop.
// Space Complexity reminder: O(1) because only count/i scalars are used.
