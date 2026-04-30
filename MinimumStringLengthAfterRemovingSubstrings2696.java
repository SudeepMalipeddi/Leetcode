/*
Problem Statement:
- Repeatedly remove substrings "AB" and "CD" until impossible, then return final length.

Intuition:
- Adjacent canceling pairs can be simulated using stack behavior.

Approach:
- minLength uses repeated string replacement; minLength1 uses stack to cancel AB/CD on the fly.
- Improvement idea: prefer minLength1 as the primary method to avoid repeated full-string scans.

Time Complexity:
- minLength is potentially O(n^2); minLength1 is O(n).

Space Complexity:
- O(n) for stack-based method.

Edge Cases:
- If no removable pair exists, length stays unchanged.

Dry Run:
- "ACDB": remove "CD" -> "AB", remove "AB" -> "".
*/
import java.util.Stack;

public class MinimumStringLengthAfterRemovingSubstrings2696 {

    
    public int minLength(String s) {
        String t = "AB";
        String u = "CD";
        
        // Keep collapsing until no removable pattern remains.
        while (s.contains(t) || s.contains(u)) {
            
            if (s.contains(t)) {
                s = s.replace(t, "");
            } else if (s.contains(u)) {
                s = s.replace(u, "");
            }
        }
        return s.length();
    }

    
    public int minLength1(String s) {
        Stack<Character> st = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            
            if (st.isEmpty()) {
                st.push(s.charAt(i));
            // Stack top + current char forms an AB pair, so remove both.
            } else if (s.charAt(i) == 'B' && st.peek() == 'A') {
                st.pop();
            } else if (s.charAt(i) == 'D' && st.peek() == 'C') {
                st.pop();
            } else {
                st.push(s.charAt(i));
            }
        }
        return st.size();
    }

    public static void main(String[] args) {

    }
}
