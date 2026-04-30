/*
Problem Statement:
- Repeatedly delete leftmost occurrence of part from s until part no longer exists.

Intuition:
- Looping on indexOf(part) directly models repeated removals.

Approach:
- While part exists, remove one occurrence via replaceFirst.
- Improvement idea: stack/StringBuilder simulation avoids regex-based replaceFirst overhead.

Time Complexity:
- Potentially O(n^2) due to repeated searches/copies.

Space Complexity:
- O(n) from string rebuilding.

Edge Cases:
- If part never appears, original string is returned.

Dry Run:
- s="daabcbaabcbc", part="abc" -> remove repeatedly to final string.
*/
public class RemoveAllOccurrencesofaSubstring1910 {

    
    public String removeOccurrences(String s, String part) {
        
        // Remove one occurrence at a time until the substring disappears.
        while (s.indexOf(part) != -1) {
            s = s.replaceFirst(part, "");
        }
        return s;
    }
}
