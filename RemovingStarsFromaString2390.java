/*
Problem Statement:
- Remove each '*' and closest non-star character to its left.

Intuition:
- Stack-like cancellation works because each '*' removes latest kept character.

Approach:
- Method 1 uses explicit stack; method 2 scans from right counting pending deletions.

Time Complexity:
- O(n).

Space Complexity:
- O(n) for stack/builder.

Edge Cases:
- Input guarantees enough characters before each '*'.

Dry Run:
- "leet**cod*e" -> "lecoe".
*/
import java.util.Stack;

public class RemovingStarsFromaString2390 {
    
    public String removeStars(String s) {
        Stack<Character> st = new Stack<>();

        
        for (char c : s.toCharArray()) {
            
            // Star removes the closest previously kept character.
            if (c == '*') {
                st.pop();
            } else {
                st.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }

        return sb.reverse().toString();
    }

    
    public String removeStars2(String s) {
        int count = 0;
        StringBuilder res = new StringBuilder();

        
        for (int i = s.length() - 1; i >= 0; i--) {
            
            if (s.charAt(i) == '*') {
                count++;
                continue;
            }
            
            // Consume this character due to pending star deletions from the right.
            if (s.charAt(i) != '*' && count > 0) {
                count--;
            } else {
                res.append(s.charAt(i));
            }
        }
        return res.reverse().toString();
    }
}
