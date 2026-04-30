/*
 * Problem Statement: Compute minimum insertions needed so each '(' is matched with exactly two consecutive ')'.
 * Intuition: Track unmatched opening brackets and pending right brackets needed to close them properly.
 * Approach: Current code attempts a stack-based simulation of parentheses state transitions.
 * Time Complexity: Intended O(n) scan over the string.
 * Space Complexity: Intended O(n) with stack usage.
 * Edge Cases handled: Empty string path exists, but critical edge handling is incomplete in current implementation.
 * Dry Run: For "))())(", intended answer is 3 in LeetCode; current code path does not safely/fully process all branches.
 * LeetCode matching/assumption: Targets LeetCode 1541 (Minimum Insertions to Balance a Parentheses String).
 * Correctness Check: Current logic is incomplete/unsafe: it calls `peek()` on possibly empty stack and has an empty `else if (st.isEmpty() && x == ')')` branch, so implementation is not reliable as-is.
 */

import java.util.Stack;

public class MinimumInsertionstoBalanceaParenthesesString1541 {
    public static int minInsertions(String s) {
        Stack<Character> st = new Stack<>();
        int res = 0;
        // Attempt to process each parenthesis and maintain stack state.
        for (char x : s.toCharArray()) {
            if (x == '(') {
                st.add(x);
            } else if (x == ')' && st.peek() == ')') { // Unsafe: st.peek() can throw when stack is empty.
                st.pop();
                st.pop();
            } else if (st.peek() == '(' && x == ')') { // Unsafe for same reason: missing empty-check before peek.
                st.add(')');
            } else if (st.isEmpty() && x == ')') { // Incomplete branch: required insertion accounting is missing.

            }
        }
        return st.size();
    }

    public static void main(String[] args) {
        String s = "))())(";
        System.out.println(minInsertions(s));
    }
}
