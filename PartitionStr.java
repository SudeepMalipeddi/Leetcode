/*
Problem Statement:
- Partition string into minimum substrings with unique characters in each part.

Intuition:
- When current character repeats inside active segment, start a new segment immediately.

Approach:
- Track lastSeen index for each char and current segment start boundary.

Time Complexity:
- O(n).

Space Complexity:
- O(1) fixed 26 array.

Edge Cases:
- Empty/repeat-free strings produce one segment by this implementation's initialization logic.

Dry Run:
- "abac": repeat 'a' forces split into "ab" and "ac" -> 2.
*/
import java.util.Arrays;
class partitionStr{
    class Solution {
    public int partitionString(String s) {
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        int count = 1, substringStart = 0;
        
        for (int i = 0; i < s.length(); i++) {
            
            // Character repeats in current segment, so start a new partition here.
            if (lastSeen[s.charAt(i) - 'a'] >= substringStart) {
                count++;
                substringStart = i;
                }
            lastSeen[s.charAt(i) - 'a'] = i;
            }
        return count;
        }
    }
}
