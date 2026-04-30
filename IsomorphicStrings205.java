/*
 * Problem Statement: Check whether two strings are isomorphic (each char in s maps to one char in t, consistently and bijectively).
 * Intuition: If last-seen positions for current chars differ, mapping history is inconsistent.
 * Approach: Use two 256-sized arrays storing last index+1 seen for each char in s and t; compare before updating.
 * Time Complexity: O(n) because one loop processes each index once.
 * Space Complexity: O(1) because arrays are fixed size (256 each), independent of n.
 * Edge Cases handled: Different lengths fail immediately; repeated chars are validated through last-seen position checks.
 * Dry Run: s="egg", t="add": i0 e/a both 0 -> set 1; i1 g/d both 0 -> set 2; i2 g/d both 2 -> set 3, return true.
 * LeetCode matching: Matches LeetCode 205 (Isomorphic Strings) for ASCII-sized mapping arrays.
 * Correctness Check: Logic is correct for chars in 0..255; wider Unicode chars could exceed array bounds.
 */

public class IsomorphicStrings205 {
    public boolean isIsomorphic(String s, String t) {
        // Isomorphic strings must have identical length.
        if (s.length() != t.length())
            return false;
        // Store last seen position+1 for each character to avoid default-zero ambiguity.
        int[] sMap = new int[256];
        int[] tMap = new int[256];

        // Compare and record mapping history character by character.
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            // Mismatch in last seen positions means conflicting mapping.
            if (sMap[sChar] != tMap[tChar]) {
                return false;
            }
            // Update both histories with current position (+1 to keep 0 as "unseen").
            sMap[sChar] = i + 1;
            tMap[tChar] = i + 1;
        }
        return true;
    }
}
