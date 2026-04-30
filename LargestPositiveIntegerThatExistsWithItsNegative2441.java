/*
 * Problem: 2441. Largest Positive Integer That Exists With Its Negative
 *
 * Problem Statement:
 * Given an integer array nums, find the largest positive integer k such that both k and -k appear in nums.
 * Return -1 if no such k exists.
 *
 * Intuition:
 * To identify a pair (k, -k) efficiently, we need a way to check if the "complement" of a number exists in the array.
 * A HashSet provides O(1) average time complexity for lookups, allowing us to find these pairs in a single linear scan.
 *
 * Approach:
 * 1. Initialize a HashSet to store numbers as we iterate through the array.
 * 2. For each number 'num', check if its negation '-num' is already present in the set.
 * 3. If it is, update the 'max' variable with the absolute value of 'num'.
 * 4. Add the current 'num' to the set to allow future numbers to find their complement.
 * 5. Return the maximum found, or -1 if no pairs were identified.
 *
 * Time Complexity: O(n) where n is the number of elements in the array. We perform a single pass with O(1) average time set operations.
 * Space Complexity: O(n) in the worst case to store all unique elements of the array in the HashSet.
 *
 * Edge Cases:
 * - No pairs exist: The 'max' variable remains 0, and we return -1.
 * - Multiple pairs: The Math.max logic ensures we only keep the largest k.
 * - Array contains 0: Since k must be positive, 0 is ignored by the logic (Math.abs(0) is 0).
 *
 * Dry Run:
 * nums = [-1, 2, -3, 3, 1]
 * 1. num = -1: set={}, -(-1)=1 not in set. set={-1}, max=0
 * 2. num = 2: set={-1}, -2 not in set. set={-1, 2}, max=0
 * 3. num = -3: set={-1, 2}, -(-3)=3 not in set. set={-1, 2, -3}, max=0
 * 4. num = 3: set={-1, 2, -3}, -3 is in set! max=max(0, 3)=3. set={-1, 2, -3, 3}
 * 5. num = 1: set={...}, -1 is in set! max=max(3, 1)=3. set={...}
 * Result: 3
 *
 * Correctness Check:
 * The solution is correct. By checking the negation and updating the max with the absolute value, 
 * we correctly identify the largest k regardless of whether we encounter the positive or negative 
 * version of the integer first.
 */

import java.util.*;

public class LargestPositiveIntegerThatExistsWithItsNegative2441 {
    public int findMaxK(int[] nums) {
        // Use a HashSet for O(1) average time complexity lookups.
        HashSet<Integer> set = new HashSet<>();
        int max = 0;
        // Single pass: for each number, check if its opposite already appeared.
        for (int num : nums) {
            // If the negation exists, we've found a (k, -k) pair.
            if (set.contains(-num)) {
                // Update max with the positive value k.
                max = Math.max(max, Math.abs(num));
            }
            // Store current value for opposite checks of future elements.
            set.add(num);
        }

        // If max is still 0, it means no valid pair was found.
        return max == 0 ? -1 : max;
    }

    public int findMaxK1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        // Preload all values so opposite lookup is available immediately.
        // This two-pass approach is also O(n) but uses more constant time.
        for (int i : nums) {
            set.add(i);
        }
        int res = -1;
        // Only positive candidates can be valid answers.
        for (int k : nums) {
            // Check if the current number is positive and its negative counterpart exists in the set.
            if (k > 0 && set.contains(-k)) {
                res = Math.max(res, k);
            }
        }
        return res;
    }
}
