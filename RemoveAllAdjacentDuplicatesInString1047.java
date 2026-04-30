/*
 * Problem: 1047. Remove All Adjacent Duplicates In String
 *
 * Problem Statement:
 * Given a string s, remove all adjacent duplicate characters repeatedly until no more can be removed.
 * Return the final string after all such duplicate removals have been made.
 *
 * Intuition:
 * This problem follows a Last-In-First-Out (LIFO) pattern where we compare the current character 
 * with the most recent one we've kept. A stack is the ideal data structure for this. We can 
 * optimize the stack by using a pointer on a character array instead of a formal Stack object.
 *
 * Approach:
 * 1. Convert the input string to a character array to facilitate in-place stack simulation.
 * 2. Initialize a pointer 'i' to -1, which will track the "top" of our simulated stack.
 * 3. Iterate through each character 'c' in the array:
 *    - If the stack is not empty (i >= 0) and 'c' matches the character at the top (chars[i]), 
 *      "pop" the stack by decrementing 'i'.
 *    - Otherwise, "push" 'c' onto the stack by incrementing 'i' and updating chars[i].
 * 4. Return a new string created from the characters in the stack (from index 0 to i).
 *
 * Time Complexity: O(n) because we process each character in the string exactly once.
 * Space Complexity: O(n) to store the character array and the resulting string.
 *
 * Edge Cases:
 * - Empty string: The loop won't execute, and an empty string is returned.
 * - String with no duplicates: Every character is pushed, and the original string is returned.
 * - String that becomes empty (e.g., "aabb"): All characters are popped, returning "".
 *
 * Dry Run:
 * Input: "abbaca"
 * - 'a': i=-1 -> push 'a', i=0, chars=[a...]
 * - 'b': i=0, 'b'!=chars[0] -> push 'b', i=1, chars=[a,b...]
 * - 'b': i=1, 'b'==chars[1] -> pop, i=0
 * - 'a': i=0, 'a'==chars[0] -> pop, i=-1
 * - 'c': i=-1 -> push 'c', i=0, chars=[c...]
 * - 'a': i=0, 'a'!=chars[0] -> push 'a', i=1, chars=[c,a...]
 * Result: "ca" (length = i+1 = 2)
 *
 * Correctness Check:
 * The solution is correct. It effectively handles nested duplicates (like "abba") by using 
 * the stack property to "reveal" previous characters after a pop.
 */
/*
Problem Statement:
- Remove adjacent duplicate pairs repeatedly until stable.

Intuition:
- A write pointer over char array simulates stack push/pop in-place.

Approach:
- If current char equals top (chars[i]), decrement pointer; otherwise push via ++i assignment.

Time Complexity:
- O(n).

Space Complexity:
- O(1) extra besides char array conversion.

Edge Cases:
- Entire string can collapse to empty.

Dry Run:
- "abbaca": process leads to "ca".
*/
public class RemoveAllAdjacentDuplicatesInString1047 {
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray(); // Convert to array for efficient in-place stack simulation.
        int i = -1; // Pointer 'i' tracks the top of the stack. -1 indicates an empty stack.
        
        for (char c : chars) { // Process each character one by one.
            
            // Adjacent duplicate found: simulate stack pop by moving pointer back.
            if ((i >= 0) && c == chars[i]) { // If stack is not empty and current char matches the top...
                i--; // ...we found an adjacent duplicate. "Pop" the stack by moving the pointer back.
            } else {
                chars[++i] = c; // ...otherwise, "Push" the current character onto the stack.
            }
        }
        // Construct the final string from index 0 to the current stack size (i + 1).
        return String.valueOf(chars, 0, ++i);
    }
}
