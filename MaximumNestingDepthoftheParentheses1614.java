/*
 * Problem: 1614. Maximum Nesting Depth of the Parentheses
 *
 * Problem Statement:
 * Given a valid parentheses string s (a string where every opening bracket has a matching closing bracket 
 * in the correct order), return the maximum nesting depth of the parentheses.
 *
 * Intuition:
 * Since the input is guaranteed to be a Valid Parentheses String (VPS), we don't need to use a Stack 
 * to validate the structure. We only need to track the "balance" of open parentheses. Every '(' 
 * increases the current depth, and every ')' decreases it. The maximum balance reached at any 
 * point during the traversal is the maximum nesting depth.
 *
 * Approach:
 * 1. Initialize 'max' to 0 (to track the peak depth) and 'count' to 0 (to track current depth).
 * 2. Convert the string to a character array and iterate through it.
 * 3. When an opening parenthesis '(' is encountered, increment 'count' and update 'max' if 'count' is greater.
 * 4. When a closing parenthesis ')' is encountered, decrement 'count'.
 * 5. Ignore all other characters like digits or operators.
 *
 * Time Complexity: O(N) where N is the length of the string, as we perform a single pass.
 * Space Complexity: O(N) in this specific implementation because s.toCharArray() creates a new array. 
 *                  It could be O(1) if we used s.charAt(i).
 *
 * Edge Cases:
 * - s = "" or "1+2": No parentheses, returns 0.
 * - s = "()(())": Multiple groups, returns 2.
 * - s = "((()))": Deeply nested, returns 3.
 *
 * Dry Run:
 * s = "(1+(2*3)+((8)/4))+1"
 * - '(' : count=1, max=1
 * - '(' : count=2, max=2
 * - ')' : count=1, max=2
 * - '(' : count=2, max=2
 * - '(' : count=3, max=3
 * - ')' : count=2, max=3
 * - ')' : count=1, max=3
 * - ')' : count=0, max=3
 * Final max = 3.
 *
 * Correctness Check:
 * The solution correctly tracks the maximum depth because in a VPS, the depth only increases 
 * with '(' and decreases with ')'. The peak 'count' value is mathematically equivalent to the 
 * maximum nesting depth.
 */

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
        // 'max' stores the overall maximum depth found, 'count' tracks the current depth level.
        int max = 0;
        int count = 0;
        // Scan each character once and update depth bookkeeping.
        // Converting to char array is faster for access than charAt() in some JVMs, though it uses O(N) space.
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++; // Entering one deeper nesting level.
                // Update the global maximum whenever we go deeper.
                max = Math.max(max, count); // Record peak depth seen so far.
            } else if (c == ')') {
                // Exiting a nesting level. Since the string is a VPS, count will not drop below 0.
                count--; // Closing one nesting level.
            }
            // Non-parenthesis characters are ignored as they don't affect nesting depth.
        }
        return max;
    }
}
