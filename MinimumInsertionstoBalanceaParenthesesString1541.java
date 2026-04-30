/*
 * Problem: 1541. Minimum Insertions to Balance a Parentheses String
 *
 * Problem Statement:
 * Given a string s of '(' and ')', return the minimum number of insertions needed to make the string balanced. 
 * A string is balanced if every '(' has exactly two consecutive ')' and the order is correct.
 *
 * Intuition:
 * To balance the string, we need to ensure that for every opening parenthesis, there are two closing ones. 
 * A stack-based approach (or a counter) helps track the "debt" of closing parentheses or the availability 
 * of opening ones to match incoming characters.
 *
 * Approach:
 * 1. Iterate through the string character by character.
 * 2. If an opening parenthesis '(' is found, it is added to the stack, representing a need for two ')'.
 * 3. If a closing parenthesis ')' is found, the code attempts to match it with existing stack elements.
 * 4. Use a result variable `res` to track the number of manual insertions performed.
 *
 * Time Complexity: O(n) where n is the length of the string, as we perform a single pass.
 * Space Complexity: O(n) in the worst case where the stack stores all characters of the string.
 *
 * Edge Cases:
 * - String starting with ')'.
 * - String ending with '('.
 * - Consecutive ')' without a preceding '('.
 * - Single '(' followed by only one ')'.
 *
 * Dry Run:
 * Input: s = "(()"
 * 1. x = '(': Stack = ['(']
 * 2. x = '(': Stack = ['(', '(']
 * 3. x = ')': st.peek() is '(', enters third branch, Stack = ['(', '(', ')'].
 * Result: Returns stack size (3), which is incorrect for this specific problem logic.
 *
 * Correctness Check:
 * The current implementation is BUGGY and INCOMPLETE:
 * 1. It will throw `java.util.EmptyStackException` because `st.peek()` is called without checking `st.isEmpty()`.
 * 2. The logic for "two consecutive ')'" is not fully implemented.
 * 3. The `else if (st.isEmpty() && x == ')')` block is empty and does nothing.
 * 4. Returning `st.size()` does not correctly represent the total insertions needed.
 */

import java.util.Stack;

public class MinimumInsertionstoBalanceaParenthesesString1541 {
    public static int minInsertions(String s) {
        // Stack to keep track of parentheses state. 
        // Note: A simple integer counter for balance is usually more efficient for this problem.
        Stack<Character> st = new Stack<>();
        int res = 0;
        
        // Iterate through each character in the input string.
        for (char x : s.toCharArray()) {
            if (x == '(') {
                // Store opening parenthesis; each '(' requires two ')' to follow.
                st.add(x);
            } else if (x == ')' && st.peek() == ')') { 
                // Potential Bug: st.peek() will crash if the stack is empty.
                // Logic: If we find a second ')', we pop the previous ')' and the original '('.
                st.pop();
                st.pop();
            } else if (st.peek() == '(' && x == ')') { 
                // Potential Bug: st.peek() will crash if the stack is empty.
                // Logic: If we have an open '(' and see the first ')', we mark it by adding ')' to the stack.
                st.add(')');
            } else if (st.isEmpty() && x == ')') { 
                // This branch is currently empty. 
                // It should handle the case where a ')' appears without any matching '('.
            }
        }
        
        // The return value currently only considers the remaining elements in the stack,
        // which does not accurately reflect the total insertions required by the problem.
        return st.size();
    }

    public static void main(String[] args) {
        String s = "))())(";
        System.out.println(minInsertions(s));
    }
}
