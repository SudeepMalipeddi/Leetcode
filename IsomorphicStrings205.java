/*
 * Problem: 205. Isomorphic Strings
 *
 * Problem Statement:
 * Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic
 * if the characters in s can be replaced to get t. All occurrences of a character must 
 * be replaced with another character while preserving the order of characters. No two 
 * characters may map to the same character, but a character may map to itself.
 *
 * Intuition:
 * The core idea is to track the "transformation pattern" rather than the characters themselves.
 * If two strings are isomorphic, then at any index i, the character s[i] and t[i] must have 
 * appeared at the exact same previous positions in their respective strings.
 *
 * Approach:
 * 1. Check if lengths are equal; if not, they cannot be isomorphic.
 * 2. Use two integer arrays of size 256 (to cover the extended ASCII set) to store the 
 *    last seen position of each character.
 * 3. Iterate through the strings. For each pair of characters, compare their last seen positions.
 * 4. If the positions differ, it means the mapping is inconsistent (one character is 
 *    trying to map to a different character than it did previously).
 * 5. Update the "last seen" position with the current index + 1.
 *
 * Time Complexity: O(n) where n is the length of the strings, as we traverse the strings once.
 * Space Complexity: O(1) because the size of the mapping arrays (256) is constant and 
 * does not grow with the input size.
 *
 * Edge Cases:
 * - Strings of length 1: Always true.
 * - Strings with different lengths: Handled by the initial length check.
 * - Characters mapping to themselves: Handled naturally by the index tracking.
 *
 * Dry Run:
 * s = "paper", t = "title"
 * i=0: 'p' and 't'. sMap['p']=0, tMap['t']=0. Match. Update sMap['p']=1, tMap['t']=1.
 * i=1: 'a' and 'i'. sMap['a']=0, tMap['i']=0. Match. Update sMap['a']=2, tMap['i']=2.
 * i=2: 'p' and 't'. sMap['p']=1, tMap['t']=1. Match. Update sMap['p']=3, tMap['t']=3.
 * i=3: 'e' and 'l'. sMap['e']=0, tMap['l']=0. Match. Update sMap['e']=4, tMap['l']=4.
 * i=4: 'r' and 'e'. sMap['r']=0, tMap['e']=0. Match. Update sMap['r']=5, tMap['e']=5.
 * Result: true.
 *
 * Correctness Check:
 * The solution is correct for ASCII inputs. If the input contains Unicode characters 
 * (e.g., emojis), the 256-sized array would cause an ArrayIndexOutOfBoundsException.
 */

public class IsomorphicStrings205 {
    public boolean isIsomorphic(String s, String t) {
        // Isomorphic strings must have identical length.
        if (s.length() != t.length())
            return false;
        // Store last seen position+1 for each character to avoid default-zero ambiguity.
        // Using 256 assumes standard extended ASCII character set.
        int[] sMap = new int[256];
        int[] tMap = new int[256];

        // Compare and record mapping history character by character.
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            
            // If the last recorded position of sChar doesn't match the last recorded 
            // position of tChar, the strings are not following the same mapping pattern.
            if (sMap[sChar] != tMap[tChar]) {
                return false;
            }
            
            // Update both histories with current position.
            // We store i + 1 so that the value 0 remains reserved for "never seen before".
            sMap[sChar] = i + 1;
            tMap[tChar] = i + 1;
        }
        
        // If we complete the loop, all character positions matched their counterparts.
        return true;
    }
}
