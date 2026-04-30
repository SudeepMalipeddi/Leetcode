/*
 * Problem Statement:
 * Given a numeric string, return the largest-valued odd-number substring that is also a prefix of the string.
 * If no odd digit exists, return an empty string.
 *
 * Intuition:
 * Any valid answer must end at an odd digit. To maximize value, keep the prefix as long as possible,
 * so scan from right to left and stop at the first odd digit.
 *
 * Approach:
 * 1) Start from the last index.
 * 2) Check digit parity using char code parity (odd digit chars have odd ASCII codes).
 * 3) On first odd digit at i, return substring(0, i + 1).
 * 4) If scan finishes, return "".
 *
 * Time Complexity (with concrete justification):
 * O(n) worst case, because each character is checked at most once in the reverse scan.
 *
 * Space Complexity (with concrete justification):
 * O(1) auxiliary space; only index and result references are used (output substring storage is language-managed result).
 *
 * Edge Cases handled:
 * - String with no odd digits (e.g., "4200") -> "".
 * - Last digit already odd -> whole string returned.
 * - Single-character input.
 *
 * Dry Run (concrete example with state):
 * num = "35420"
 * i=4('0', even) -> continue
 * i=3('2', even) -> continue
 * i=2('4', even) -> continue
 * i=1('5', odd)  -> res = num.substring(0,2) = "35", break
 * return "35"
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 1903. Assumes input contains only digit characters as per problem constraints.
 *
 * Correctness Check:
 * The first odd digit from the right gives the longest possible odd-ending prefix, hence the largest odd substring.
 * Main risk is non-digit input; under LeetCode constraints this does not occur.
 */

public class LargestOddNumberinString1903 {
    public String largestOddNumber(String num) {
        int n = num.length();
        String res = "";
        // Scan from right so the first odd digit found keeps the prefix as long as possible.
        for (int i = n - 1; i >= 0; i--) {
            // Odd digits ('1','3','5','7','9') have odd char-code parity as well.
            if ((int) num.charAt(i) % 2 == 1) {
                res = num.substring(0, i + 1);
                break;
            }
        }
        return res;
    }
}
