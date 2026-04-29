/*
 * Problem: LeetCode 258 - Add Digits
 * Problem Statement: Repeatedly sum the digits of num until a single-digit
 *   number remains, and return that digit.
 * Intuition: The repeated sum is the digital root, which follows a mod-9 rule.
 * Approach:
 *   1) If num is 0, the answer is 0.
 *   2) If num is divisible by 9, the digital root is 9.
 *   3) Otherwise, the digital root is num % 9.
 * Time Complexity: O(1) using constant-time math.
 * Space Complexity: O(1).
 * Edge Cases: num=0, multiples of 9, very large numbers.
 * Dry Run: num=38 -> 38 % 9 = 2 (since 3+8=11, 1+1=2).
 * Correctness Check: The digital-root identity holds for base-10 numbers, so
 *   the computation matches repeated digit sums without iteration.
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
