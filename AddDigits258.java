/*
 * Problem: LeetCode 258. Add Digits
 *
 * Problem Statement:
 * Given a non-negative integer num, repeatedly add its digits until the result
 * has only one digit, and return that digit.
 *
 * Intuition:
 * This is the digital root problem. The result follows a cycle with period 9,
 * allowing a constant-time formula instead of repeated summation.
 *
 * Approach:
 * 1. If num is 0, the answer is 0.
 * 2. If num is divisible by 9, the digital root is 9.
 * 3. Otherwise, the digital root is num % 9.
 *
 * Time Complexity: O(1) because only arithmetic operations are used.
 * Space Complexity: O(1).
 *
 * Edge Cases handled:
 * - num = 0.
 * - num divisible by 9 (e.g., 9, 18, 27).
 *
 * Dry Run:
 * num = 38 -> 3 + 8 = 11 -> 1 + 1 = 2
 * Formula: 38 % 9 = 2 => answer 2.
 *
 * Correctness Check:
 * Digital root formula is correct for base-10 numbers and matches repeated digit sums.
 *
 * LeetCode Match:
 * LeetCode 258 - "Add Digits".
 */
/**
 * LeetCode 258. Add Digits
 * Given an integer num, repeatedly add all its digits until the result has only
 * one digit, and return it.
 */
public class AddDigits258 {
    /**
     * Time Complexity: O(1) - uses mathematical digital root logic.
     * Space Complexity: O(1)
     */
    public int addDigits(int num) {
        // 0 returns 0
        if (num == 0)
            return 0;
        // Multiples of 9 have a digital root of 9
        else if (num % 9 == 0)
            return 9;
        // Otherwise, the digital root is num % 9
        return num % 9;
    }
}
