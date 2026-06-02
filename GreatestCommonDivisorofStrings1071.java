/*
 * Problem: LeetCode 1071 - Greatest Common Divisor of Strings
 *
 * Problem Statement:
 * For two strings str1 and str2, return the largest string x such that x divides
 * both str1 and str2. A string t divides s if and only if s = t + t + ... + t
 * (t concatenated with itself one or more times).
 *
 * Intuition:
 * If a common divisor string exists, concatenation must be commutative:
 * str1 + str2 must equal str2 + str1 (both are repetitions of the same base
 * pattern). The length of the GCD string must be the GCD of the two lengths,
 * and the candidate is simply the prefix of that length.
 *
 * Approach:
 *   1. Check if (s1 + s2).equals(s2 + s1). If not equal, return "" — no common
 *      divisor string can exist.
 *   2. Compute gcd = gcd(s1.length(), s2.length()) using the Euclidean algorithm.
 *   3. Return s1.substring(0, gcd).
 *
 *   gcd(a, b) helper:
 *   - Base case: if b == 0, return a.
 *   - Recursive case: return gcd(b, a % b).
 *
 * Time Complexity: O(n + m) where n = s1.length(), m = s2.length() — string
 * concatenation and equality comparison dominate.
 * Space Complexity: O(n + m) for the two concatenated strings in the equality
 * check (s1+s2 and s2+s1).
 *
 * Edge Cases:
 * - No common divisor: s1+s2 != s2+s1 → returns "".
 * - One string divides the other: gcd returns the shorter string.
 * - Identical strings: gcd equals length, returns the full string.
 * - Strings with no repeating pattern (e.g., "AB" and "ABC"): s1+s2="ABABC",
 *   s2+s1="ABCAB", not equal → returns "".
 *
 * Dry Run:
 * Example 1: s1 = "ABCABC", s2 = "ABC"
 *   s1+s2 = "ABCABCABC"
 *   s2+s1 = "ABCABCABC"
 *   Equal → continue
 *   gcd(6, 3): b=3 != 0 → gcd(3, 6%3=0)
 *              b=0 → return 3
 *   s1.substring(0, 3) = "ABC"
 *   Returns "ABC".
 *
 * Example 2: s1 = "ABABAB", s2 = "ABAB"
 *   s1+s2 = "ABABABABAB", s2+s1 = "ABABABABAB" → equal
 *   gcd(6, 4): gcd(4, 2) → gcd(2, 0) → return 2
 *   s1.substring(0, 2) = "AB"
 *   Returns "AB".
 *
 * Example 3: s1 = "LEET", s2 = "CODE"
 *   s1+s2 = "LEETCODE", s2+s1 = "CODELEET" → not equal → returns "".
 *
 * Correctness Check:
 * The commutativity check (s1+s2 == s2+s1) is both necessary and sufficient for
 * the existence of a common divisor string (both must be repetitions of the same
 * base). By number theory, the GCD of the lengths gives the length of the largest
 * such repeating block, and taking the prefix of that length yields the answer.
 */
public class GreatestCommonDivisorofStrings1071 {
    public String gcdofStrings(String s1, String s2) {
        // If concatenations differ, no repeating base pattern can generate both strings.
        if (!(s1 + s2).equals(s2 + s1)) {
            return "";
        }
        int gcd = gcd(s1.length(), s2.length());
        return s1.substring(0, gcd);
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
