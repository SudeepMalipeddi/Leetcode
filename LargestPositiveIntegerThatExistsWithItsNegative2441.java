/*
 * Problem Statement:
 * Given an integer array nums, find the largest positive integer k such that both k and -k appear in nums.
 * Return -1 if no such k exists.
 *
 * Intuition:
 * As soon as we know one value, we can check whether its opposite has appeared.
 * A hash set gives O(1)-average opposite lookup.
 *
 * Approach:
 * findMaxK:
 * - Iterate nums once.
 * - If -num already exists in set, update max with |num|.
 * - Add num to set.
 * - Return -1 when max stayed 0.
 *
 * findMaxK1:
 * - Build set from all nums.
 * - Re-scan nums; for positive k, if -k is in set, track best k.
 *
 * Time Complexity (with concrete justification):
 * Both methods are O(n) average time: each element is processed constant times with average O(1) set ops.
 *
 * Space Complexity (with concrete justification):
 * O(n) due to storing up to all distinct elements in HashSet.
 *
 * Edge Cases handled:
 * - No positive-negative pair -> returns -1.
 * - Duplicates do not affect correctness.
 * - Zero present: does not count because k must be positive.
 *
 * Dry Run (concrete example with state):
 * nums = [-1, 2, -2, 3]
 * set={}, max=0
 * num=-1 -> set lacks 1, add -1
 * num=2  -> set lacks -2, add 2
 * num=-2 -> set has 2, max=max(0,2)=2, add -2
 * num=3  -> set lacks -3, add 3
 * return 2
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 2441. Assumes standard integer bounds from the problem statement.
 *
 * Correctness Check:
 * Pair detection is symmetric: whenever second member of a pair appears, max is updated with absolute value.
 * In findMaxK, sentinel max=0 is safe because valid k is strictly positive.
 */

import java.util.*;

public class LargestPositiveIntegerThatExistsWithItsNegative2441 {
    public int findMaxK(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int max = 0;
        // Single pass: for each number, check if its opposite already appeared.
        for (int num : nums) {
            if (set.contains(-num)) {
                max = Math.max(max, Math.abs(num));
            }
            // Store current value for opposite checks of future elements.
            set.add(num);
        }

        return max == 0 ? -1 : max;
    }

    public int findMaxK1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        // Preload all values so opposite lookup is available immediately.
        for (int i : nums) {
            set.add(i);
        }
        int res = -1;
        // Only positive candidates can be valid answers.
        for (int k : nums) {
            if (k > 0 && set.contains(-k)) {
                res = Math.max(res, k);
            }
        }
        return res;
    }
}
