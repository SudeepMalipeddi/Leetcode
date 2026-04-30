/*
 * Problem Statement:
 * Given a valid parentheses string s (with possible letters/operators),
 * return the maximum nesting depth of parentheses.
 *
 * Intuition:
 * Track current open-parenthesis depth while scanning characters.
 * The maximum value reached is the answer.
 *
 * Approach:
 * 1) Use count for current depth and max for best depth.
 * 2) On '(', increment count and update max.
 * 3) On ')', decrement count.
 * 4) Ignore all non-parenthesis characters.
 *
 * Time Complexity:
 * O(n), one pass over the string.
 *
 * Space Complexity:
 * O(1), only counters.
 *
 * Edge Cases handled:
 * - No parentheses -> answer 0.
 * - Deeply nested valid parentheses -> tracks peak correctly.
 * - Valid input assumption ensures count never goes negative unexpectedly.
 *
 * Dry Run:
 * s = "(1+(2*3)+((8)/4))+1"
 * depth sequence around parentheses reaches max = 3.
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 1614 counting-based linear scan.
 *
 * Correctness Check:
 * count always equals active unmatched '('; max records highest such count.
 */

public class MaximumNestingDepthoftheParentheses1614 {
    public int maxDepth(String s) {
        int max = 0;
        int count = 0;
        // Scan each character once and update depth bookkeeping.
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++; // Entering one deeper nesting level.
                max = Math.max(max, count); // Record peak depth seen so far.
            } else if (c == ')') {
                count--; // Closing one nesting level.
            }
        }
        return max;
    }
}
