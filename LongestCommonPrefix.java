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
        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        int idx = 0;
        // Grow prefix length while both boundary strings match at the same position.
        while(idx < s1.length() && idx < s2.length()){
            if(s1.charAt(idx)==s2.charAt(idx)){
                idx++;
            }
            else{
                break;
            }
        }
        return s1.substring(0, idx);
    }
}
