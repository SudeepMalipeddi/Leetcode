/*
 * Problem: 1903. Largest Odd Number in String
 *
 * Problem Statement:
 * Given a string `num` representing a large integer, return the largest-valued odd
 * integer (as a string) that is a non-empty substring of `num`, or an empty string
 * if no odd integer exists.
 *
 * Intuition:
 * A number is odd if and only if its last digit is odd. To find the "largest" odd
 * substring, we want the longest possible prefix that ends in an odd digit.
 * Scanning from right to left allows us to find the longest such prefix immediately.
 *
 * Approach:
 * 1. Start a pointer at the last character of the string.
 * 2. Move the pointer to the left until an odd digit is encountered.
 * 3. The substring from the beginning of the string to this pointer (inclusive)
 *    is the largest odd number.
 * 4. If the pointer reaches the beginning without finding an odd digit, return "".
 *
 * Time Complexity: O(N), where N is the length of the string. We perform a single
 * linear scan from right to left.
 * Space Complexity: O(1) auxiliary space, as we only use a few primitive variables.
 * Note: The substring method in modern Java creates a new string, which takes O(N) space.
 *
 * Edge Cases:
 * - No odd digits: "2468" -> returns ""
 * - String is already odd: "1357" -> returns "1357"
 * - Only the first digit is odd: "3246" -> returns "3"
 *
 * Dry Run:
 * num = "3542"
 * i = 3: '2' is even.
 * i = 2: '4' is even.
 * i = 1: '5' is odd.
 * Found! Return num.substring(0, 1 + 1) -> "35".
 *
 * Correctness Check:
 * The solution is correct. Any prefix ending in an odd digit is an odd number.
 * The rightmost such digit provides the longest prefix, which is the largest value.
 */

public class LargestOddNumberinString1903 {
    public String largestOddNumber(String num) {
        int n = num.length();
        String res = "";
        // Scan from right so the first odd digit found keeps the prefix as long as possible.
        // Starting from the end (n-1) ensures we find the longest possible substring that ends in an odd digit.
        for (int i = n - 1; i >= 0; i--) {
            // Odd digits ('1','3','5','7','9') have odd char-code parity as well.
            // In ASCII/Unicode, '0' is 48, '1' is 49, '2' is 50, etc.
            // Since the values alternate, (char % 2 == 1) is a valid check for odd digits.
            if ((int) num.charAt(i) % 2 == 1) {
                // substring(start, end) is inclusive of start and exclusive of end.
                // Using i + 1 ensures the odd digit at index i is included in the result.
                res = num.substring(0, i + 1);
                // Once the rightmost odd digit is found, we have the largest possible odd substring.
                break;
            }
        }
        // If no odd digit was found, res remains an empty string "".
        return res;
    }
}
