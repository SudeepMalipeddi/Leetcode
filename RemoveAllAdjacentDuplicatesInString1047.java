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
        char[] chars = s.toCharArray();
        int i = -1;
        
        for (char c : chars) {
            
            // Adjacent duplicate found: simulate stack pop by moving pointer back.
            if ((i >= 0) && c == chars[i]) {
                i--;
            } else {
                chars[++i] = c;
            }
        }
        return String.valueOf(chars, 0, ++i);
    }
}
