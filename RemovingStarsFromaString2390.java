/*
 * Problem: 2390. Removing Stars From a String
 *
 * Problem Statement:
 * You are given a string s, which contains stars '*'. In one operation, you can choose a star 
 * and remove the closest non-star character to its left, as well as remove the star itself.
 * Return the string after all stars have been removed.
 *
 * Intuition:
 * This problem follows a "Last-In, First-Out" (LIFO) pattern. When we encounter a star, 
 * it acts as a "backspace" that cancels out the most recently seen character. A Stack 
 * is the ideal data structure to handle this "undo" behavior. Alternatively, scanning 
 * from right to left allows us to treat stars as "pending deletions" for characters 
 * appearing earlier in the string.
 *
 * Approach:
 * 1. Stack Approach: Iterate through the string from left to right. If the character is 
 *    not a star, push it onto the stack. If it is a star, pop the top element from the stack.
 * 2. Reverse Scan Approach: Iterate from right to left. Maintain a counter for stars 
 *    encountered. If we see a star, increment the counter. If we see a character and the 
 *    counter is greater than zero, skip the character and decrement the counter. Otherwise, 
 *    keep the character.
 *
 * Time Complexity: O(n), where n is the length of the string. We traverse the string once.
 * Space Complexity: O(n) to store the characters in the stack or the result StringBuilder.
 *
 * Edge Cases:
 * - String with no stars: Result should be the original string.
 * - Stars at the end: They will remove the characters immediately preceding them.
 * - Note: The problem guarantees that a star will always have a character to its left to remove.
 *
 * Dry Run:
 * Input: "leet**cod*e"
 * - 'l', 'e', 'e', 't' pushed to stack.
 * - '*' encountered: pop 't'. Stack: ['l', 'e', 'e']
 * - '*' encountered: pop 'e'. Stack: ['l', 'e']
 * - 'c', 'o', 'd' pushed. Stack: ['l', 'e', 'c', 'o', 'd']
 * - '*' encountered: pop 'd'. Stack: ['l', 'e', 'c', 'o']
 * - 'e' pushed. Stack: ['l', 'e', 'c', 'o', 'e']
 * Result: "lecoe"
 *
 * Correctness Check:
 * The solution correctly implements the removal logic. The stack approach is standard for 
 * "undo" operations. The reverse scan is a clever optimization that avoids explicit stack 
 * overhead but requires a final reverse of the result.
 */
import java.util.Stack;

public class RemovingStarsFromaString2390 {
    
    public String removeStars(String s) {
        // Use a stack to keep track of characters that have not been removed by a star.
        Stack<Character> st = new Stack<>();

        
        for (char c : s.toCharArray()) {
            
            // Star removes the closest previously kept character.
            if (c == '*') {
                // LIFO: The most recently pushed character is the "closest to the left".
                st.pop();
            } else {
                // Not a star? Keep it for now.
                st.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        
        // Transfer characters from stack to builder.
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }

        // Since stack is LIFO, the characters were appended in reverse order.
        return sb.reverse().toString();
    }

    
    public String removeStars2(String s) {
        // count tracks "deletion debt" — how many characters to the left must be deleted.
        int count = 0;
        StringBuilder res = new StringBuilder();

        
        // Scanning from right to left allows us to know how many stars are ahead.
        for (int i = s.length() - 1; i >= 0; i--) {
            
            if (s.charAt(i) == '*') {
                // Every star encountered adds to the deletion debt.
                count++;
                continue;
            }
            
            // Consume this character due to pending star deletions from the right.
            if (s.charAt(i) != '*' && count > 0) {
                // This character is "eaten" by a star that was to its right.
                count--;
            } else {
                // No pending deletions? This character survives.
                res.append(s.charAt(i));
            }
        }
        // Since we scanned backwards, the result string is built in reverse.
        return res.reverse().toString();
    }
}
