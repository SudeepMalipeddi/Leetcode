/*
 * Problem: LeetCode 3174 - Clear Digits
 * Problem Statement: Given a string with lowercase letters and digits, remove
 *   each digit and the closest non-removed letter to its left, and return the final string.
 * Intuition: A stack of characters models the current surviving letters so a
 *   digit can "undo" the most recent letter.
 * Approach:
 *   1) Iterate characters; if a letter, push to stack.
 *   2) If a digit, pop the last kept letter.
 *   3) Pop stack to build the result in reverse, then reverse it.
 * Time Complexity: O(n) for a single pass.
 * Space Complexity: O(n) for the stack/result.
 * Edge Cases: String with no digits, digits at the end, single-letter input.
 * Dry Run: s="abc3d" -> push a,b,c, digit pops c, push d => "abd".
 * Correctness Check: Each digit removes the most recent unremoved letter, which
 *   is exactly the stack top. Assumes input guarantees a removable letter before each digit.
 */
import java.util.Stack;

public class ClearDigits3174 {
    public String clearDigits(String s) {
        Stack<Character> st = new Stack<>(); // track surviving letters
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                st.pop(); // remove closest previous letter
            } else {
                st.push(c); // keep letters
            }
        }
        while (!st.isEmpty()) {
            sb.append(st.pop()); // build in reverse order
        }
        return sb.reverse().toString();
    }
}
