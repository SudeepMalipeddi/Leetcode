/*
 * Problem: LeetCode 3174 - Clear Digits
 *
 * Problem Statement:
 * You are given a string s containing lowercase English letters and digits. Your task is to 
 * remove all digits. For every digit found, you must remove the digit itself and the 
 * closest non-digit character to its left. Return the resulting string.
 *
 * Intuition:
 * This problem follows a "cancellation" or "undo" pattern where a new element (a digit) 
 * interacts with the most recently seen available element (a letter). This behavior is 
 * perfectly modeled by a Stack (Last-In-First-Out), where the top of the stack always 
 * represents the "closest" character to the left that hasn't been removed yet.
 *
 * Approach:
 * 1. Use a stack to keep track of characters that are not yet cleared.
 * 2. Iterate through the string character by character.
 * 3. If the character is a digit, pop the top element from the stack (removing the nearest letter).
 * 4. If the character is a letter, push it onto the stack to save it.
 * 5. After the loop, the stack contains the surviving characters in their original relative order.
 * 6. Build the final string by popping elements (which come out in reverse) and then reversing the result.
 *
 * Time Complexity: O(n), where n is the length of the string. We visit each character exactly once, 
 * and stack operations (push/pop) are O(1).
 * Space Complexity: O(n) to store the characters in the stack and the StringBuilder.
 *
 * Edge Cases:
 * - String with no digits: All letters are pushed and none are popped; original string is returned.
 * - String where all letters are cleared: Stack ends up empty; returns an empty string.
 * - Multiple digits in a row: Each digit clears the current most-recent letter.
 *
 * Dry Run:
 * s = "abc3d"
 * 1. 'a' is a letter -> push('a') | Stack: [a]
 * 2. 'b' is a letter -> push('b') | Stack: [a, b]
 * 3. 'c' is a letter -> push('c') | Stack: [a, b, c]
 * 4. '3' is a digit  -> pop()     | Stack: [a, b] (removes 'c')
 * 5. 'd' is a letter -> push('d') | Stack: [a, b, d]
 * Final Stack to String: "abd"
 *
 * Correctness Check:
 * The solution is correct. It uses the Stack to identify the "closest non-digit character to the left" 
 * as required. Note: The problem constraints typically guarantee that a digit will always have 
 * a character to its left to remove; otherwise, st.pop() would require an empty check.
 */
import java.util.Stack;

public class ClearDigits3174 {
    public String clearDigits(String s) {
        // We use a Stack to store characters because the problem requires removing the "closest" 
        // character to the left, which fits the Last-In-First-Out (LIFO) property.
        Stack<Character> st = new Stack<>(); // track surviving letters
        StringBuilder sb = new StringBuilder();
        
        // Iterate through the string to process each character one by one.
        for (char c : s.toCharArray()) {
            // Character.isDigit() is a utility method to check if the char is between '0' and '9'.
            if (Character.isDigit(c)) {
                // When a digit is encountered, it "clears" itself and the nearest letter to its left.
                // In a stack, the nearest letter to the left is the current top element.
                st.pop(); // remove closest previous letter
            } else {
                // If it's a letter, we push it onto the stack. It will stay there unless 
                // a future digit causes it to be popped.
                st.push(c); // keep letters
            }
        }
        
        // After processing the entire string, the stack contains only the characters that survived.
        while (!st.isEmpty()) {
            // Popping from the stack retrieves characters from right to left (reverse order).
            sb.append(st.pop()); // build in reverse order
        }
        
        // Since the StringBuilder was built by popping (reverse order), we must reverse it 
        // one last time to restore the original sequence of the surviving characters.
        return sb.reverse().toString();
    }
}
