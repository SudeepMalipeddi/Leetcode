/*
Problem Statement:
- Remove outermost parentheses from each primitive valid parentheses substring.

Intuition:
- Track depth; characters at depth 1 boundaries are outer wrappers.

Approach:
- Decrement before appending ')' and increment after appending '(' to exclude primitive boundaries.

Time Complexity:
- O(n).

Space Complexity:
- O(n) output builder.

Edge Cases:
- Single primitive "()" becomes empty string.

Dry Run:
- "(()())(())" -> "()()()".
*/
class RemoveOutermostParentheses1021 {
    public String removeOuterParentheses(String s) {
        StringBuilder res = new StringBuilder();
        int count = 0;
        
        for (char c : s.toCharArray()) {
            
            if (c == ')')
                count--;
            
            // Only append characters that are inside at least one outer layer.
            if (count >= 1)
                res.append(c);
            
            if (c == '(')
                count++;
        }
        return res.toString();
    }
}
