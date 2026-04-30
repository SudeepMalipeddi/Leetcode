/*
Problem Statement:
- Given an even-length binary string, compute the minimum flips so every 2-length block has equal characters.

Intuition:
- Each pair contributes independently: only mismatched pairs need one change.

Approach:
- Scan indices (0,1), (2,3), ... and count mismatches.

Time Complexity:
- O(n) because each character is visited once through paired iteration.

Space Complexity:
- O(1) extra space.

Edge Cases:
- Works for already-beautiful strings and all-0/all-1 inputs.

Dry Run:
- s="0101": pairs (0,1) and (0,1) mismatch twice, so answer is 2.
*/
public class MinimumNumberofChangestoMakeBinaryStringBeautiful2914 {
    public static int minChanges(String s) {
        int res = 0, n = s.length();
        
        // Each adjacent pair is independent, so count only mismatched pairs.
        for (int i = 1; i < n; i += 2) {
            
            if (s.charAt(i - 1) != s.charAt(i)) {
                res++;
            }
        }
        return res;
    }
}
