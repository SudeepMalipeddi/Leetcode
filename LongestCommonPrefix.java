/*
 * Problem: 14. Longest Common Prefix
 *
 * Problem Statement:
 * Find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Intuition:
 * The longest common prefix of an entire array is constrained by the commonality 
 * between the two most "different" strings. In a sorted list, these are the first 
 * and last elements. This implementation assumes that comparing the boundaries 
 * of the array is sufficient to find the global prefix.
 *
 * Approach:
 * 1. Identify the first string (s1) and the last string (s2) in the input array.
 * 2. Iterate through both strings simultaneously using an index pointer.
 * 3. Compare characters at each position; if they match, continue to the next index.
 * 4. Stop when a mismatch is found or the end of either string is reached.
 * 5. Return the substring of s1 from the start to the last matching index.
 *
 * Time Complexity: O(M) where M is the length of the shorter string between strs[0] and strs[n-1].
 * Space Complexity: O(1) auxiliary space, as we only use a single integer pointer.
 *
 * Edge Cases:
 * - Empty array: Will throw ArrayIndexOutOfBoundsException at strs[0].
 * - Single string: Returns the string itself (s1 and s2 are the same).
 * - No common prefix: Returns "" as idx remains 0.
 *
 * Dry Run:
 * Input: ["flower", "flow", "flight"]
 * s1 = "flower", s2 = "flight"
 * idx 0: 'f' == 'f' -> idx = 1
 * idx 1: 'l' == 'l' -> idx = 2
 * idx 2: 'o' != 'i' -> break
 * Result: "flower".substring(0, 2) -> "fl"
 *
 * Correctness Check:
 * The logic is only correct for the general "Longest Common Prefix" problem if 
 * the array is lexicographically sorted first. Without sorting, comparing only 
 * the first and last elements may yield a prefix that is not present in middle 
 * strings (e.g., ["a", "b", "a"] would return "a" despite "b" being in the middle).
 * Additionally, the code lacks a null/empty check for the input array.
 */

/*
 * Problem Statement:
 * Return the longest common prefix string among all strings in the array.
 * If none exists, return "".
 *
 * Intuition:
 * This implementation compares only the first and last array elements character-by-character.
 * That works only when input is lexicographically sorted first.
 *
 * Approach:
 * - Set s1 = strs[0], s2 = strs[strs.length-1].
 * - Move index while characters match in both strings.
 * - Return s1.substring(0, idx).
 *
 * Time Complexity (with concrete justification):
 * O(min(|s1|, |s2|)) for the comparison loop in current implementation.
 *
 * Space Complexity (with concrete justification):
 * O(1) auxiliary space; only pointers/index used.
 *
 * Edge Cases handled:
 * - Immediate mismatch at index 0 -> returns "".
 * - One string fully consumed -> prefix bounded by shorter string length.
 *
 * Dry Run (concrete example with state):
 * strs = ["flower","flow","flight"]
 * s1="flower", s2="flight"
 * idx=0: f==f ->1
 * idx=1: l==l ->2
 * idx=2: o!=i -> stop
 * return "fl"
 *
 * LeetCode matching/assumption:
 * Intended for LeetCode 14 style problem, but this exact logic assumes `strs` is pre-sorted lexicographically,
 * which LeetCode input does not guarantee.
 *
 * Correctness Check:
 * Actual risk: because no sorting is done, comparing only first and last may miss disagreements in middle elements.
 * Example unsorted ["dog","racecar","car"] returns prefix based on "dog" vs "car", not globally validated.
 */

public class LongestCommonPrefix {
    public String longestCommonPrefix(String [] strs){
        // Reference the first and last strings in the array to find their shared prefix
        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        int idx = 0;
        // Grow prefix length while both boundary strings match at the same position.
        // Ensure the pointer does not exceed the length of either string.
        while(idx < s1.length() && idx < s2.length()){
            // Compare characters at the current index
            if(s1.charAt(idx)==s2.charAt(idx)){
                idx++; // Characters match, move to the next position
            }
            else{
                break; // First mismatch found, stop comparison
            }
        }
        // Return the portion of the string that matched in both s1 and s2
        return s1.substring(0, idx);
    }
}
