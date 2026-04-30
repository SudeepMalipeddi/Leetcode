/*
 * Problem: 2696. Minimum String Length After Removing Substrings
 *
 * Problem Statement:
 * Given a string s consisting only of uppercase English letters, you can repeatedly remove any 
 * occurrences of the substrings "AB" or "CD". Return the minimum possible length of the 
 * resulting string after all possible removals.
 *
 * Intuition:
 * This problem is a variation of the "Valid Parentheses" problem. When we encounter a 'B', 
 * we want to know if the character immediately before it (that hasn't already been removed) 
 * was an 'A'. Similarly for 'D' and 'C'. A Stack is the ideal data structure for this 
 * "last-in, first-out" matching logic, as it allows us to look back at the most recent 
 * unmatched character.
 *
 * Approach:
 * 1. Method 1 (Brute Force Replacement): Use a while loop to check if "AB" or "CD" exists 
 *    in the string. If found, replace the first occurrence with an empty string. Repeat 
 *    until neither substring is present.
 * 2. Method 2 (Stack-based): Iterate through the string character by character. If the 
 *    current character is 'B' and the stack top is 'A', or if the current character is 'D' 
 *    and the stack top is 'C', pop the stack. Otherwise, push the current character.
 *
 * Time Complexity:
 * - minLength: O(n^2) because string searching (contains) and replacement (replace) 
 *   take O(n) time, and we might perform O(n) removals.
 * - minLength1: O(n) because we traverse the string exactly once and stack operations 
 *   (push/pop/peek) are O(1).
 *
 * Space Complexity:
 * - minLength: O(n) for the creation of new string objects during replacement.
 * - minLength1: O(n) in the worst case where no characters are removed and all are stored in the stack.
 *
 * Edge Cases:
 * - Empty string: Length 0.
 * - String with no "AB" or "CD": Length remains unchanged.
 * - Nested removals: "AABB" -> "AB" -> "" (The stack handles this naturally).
 *
 * Dry Run (minLength1):
 * Input: "ACBBD"
 * 1. 'A': Stack [A]
 * 2. 'C': Stack [A, C]
 * 3. 'B': Stack top is 'C' (not 'A'), Push 'B' -> Stack [A, C, B]
 * 4. 'B': Stack top is 'B' (not 'A'), Push 'B' -> Stack [A, C, B, B]
 * 5. 'D': Stack top is 'B' (not 'C'), Push 'D' -> Stack [A, C, B, B, D]
 * Result: 5
 *
 * Correctness Check:
 * The stack approach is correct because it processes characters linearly and immediately 
 * resolves any "AB" or "CD" pairs as they form, including those created by previous removals.
 */
import java.util.Stack;

public class MinimumStringLengthAfterRemovingSubstrings2696 {

    
    public int minLength(String s) {
        String t = "AB";
        String u = "CD";
        
        // Keep collapsing until no removable pattern remains.
        // contains() and replace() both involve scanning the string, leading to O(n^2) total.
        while (s.contains(t) || s.contains(u)) {
            
            if (s.contains(t)) {
                // replace() creates a new string, which is an O(n) operation.
                s = s.replace(t, "");
            } else if (s.contains(u)) {
                s = s.replace(u, "");
            }
        }
        return s.length();
    }

    
    public int minLength1(String s) {
        // Using a stack to keep track of characters that haven't been part of a removed pair.
        Stack<Character> st = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            
            if (st.isEmpty()) {
                st.push(s.charAt(i));
            // Stack top + current char forms an AB pair, so remove both.
            // peek() looks at the most recent character added without removing it.
            } else if (s.charAt(i) == 'B' && st.peek() == 'A') {
                st.pop();
            // Stack top + current char forms a CD pair, so remove both.
            } else if (s.charAt(i) == 'D' && st.peek() == 'C') {
                st.pop();
            } else {
                // If no pair is formed, the current character is a candidate for a future pair.
                st.push(s.charAt(i));
            }
        }
        // The remaining characters in the stack represent the final string after all removals.
        return st.size();
    }

    public static void main(String[] args) {

    }
}
