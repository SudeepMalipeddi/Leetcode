/*
 * Problem: 1021. Remove Outermost Parentheses
 *
 * Problem Statement:
 * A valid parentheses string is primitive if it is non-empty and cannot be split into two or more
 * non-empty valid parentheses strings. Given a valid parentheses string s, remove the outermost
 * parentheses of every primitive string in its decomposition and return the result.
 *
 * Intuition:
 * The outermost parentheses of a primitive string are the ones that start and end the primitive
 * sequence. We can identify these by tracking the nesting depth. A '(' is outermost if it starts
 * at depth 0, and a ')' is outermost if it ends at depth 0.
 *
 * Approach:
 * 1. Use a counter 'count' to track the current nesting depth.
 * 2. Iterate through the string character by character.
 * 3. If the character is ')', decrement the depth first. If the depth is still >= 1, it's an inner parenthesis.
 * 4. If the depth is >= 1, append the character to the result.
 * 5. If the character is '(', increment the depth after the check. This ensures the depth 0 '(' is not appended.
 *
 * Time Complexity: O(n), where n is the length of the string, as we process each character exactly once.
 * Space Complexity: O(n), for the StringBuilder used to store the resulting string.
 *
 * Edge Cases:
 * - Single primitive "()": Both parentheses are at the boundary (depth 0) and are removed.
 * - Multiple primitives "()()": All are removed, resulting in "".
 * - Nested primitives "((()))": Only the outermost pair is removed, resulting in "(())".
 *
 * Dry Run:
 * Input: "(()())"
 * 1. '(': count=0. count not >= 1. count becomes 1. res=""
 * 2. '(': count=1. count >= 1, append '('. count becomes 2. res="("
 * 3. ')': count becomes 1. count >= 1, append ')'. res="()"
 * 4. '(': count=1. count >= 1, append '('. count becomes 2. res="()("
 * 5. ')': count becomes 1. count >= 1, append ')'. res="()()"
 * 6. ')': count becomes 0. count not >= 1. res="()()"
 *
 * Correctness Check:
 * The solution is correct. It uses a single pass and a counter to filter out characters at depth 0.
 */
class RemoveOutermostParentheses1021 {
    public String removeOuterParentheses(String s) {
        // StringBuilder is used for efficient string construction.
        StringBuilder res = new StringBuilder();
        // count tracks the current nesting depth.
        int count = 0;
        
        // Iterate through each character in the input string.
        for (char c : s.toCharArray()) {
            
            // If we encounter a closing bracket, decrement depth first.
            // If count is still >= 1, this ')' is not the outermost one.
            if (c == ')')
                count--;
            
            // Only append characters that are inside at least one outer layer.
            // This condition filters out the outermost '(' (where count was 0)
            // and the outermost ')' (where count becomes 0).
            if (count >= 1)
                res.append(c);
            
            // If we encounter an opening bracket, increment depth after the check.
            // This ensures the first '(' of a primitive string is not appended.
            if (c == '(')
                count++;
        }
        // Return the final string after removing outermost parentheses.
        return res.toString();
    }
}
