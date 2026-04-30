/*
 * Problem Reference: LeetCode 1071 - Greatest Common Divisor of Strings
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Use divisibility and gcd(lengths) to build largest valid base string.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n+m)
 *
 * Space Complexity:
 * O(1)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
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
