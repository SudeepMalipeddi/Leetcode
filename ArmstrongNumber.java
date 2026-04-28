/*
 * Problem: Armstrong Number (closest LeetCode match: 1134. Armstrong Number)
 *
 * Problem Statement:
 * Determine whether a given integer n is an Armstrong number, meaning the sum of
 * each digit raised to the power of the number of digits equals the original number.
 *
 * Intuition:
 * An Armstrong number can be verified by decomposing it into digits, counting digits,
 * and summing digit^count; the sum should match the original.
 *
 * Approach:
 * 1. Count how many digits are in n.
 * 2. Extract digits with n % 10 and accumulate digit^count.
 * 3. Compare the accumulated sum to the original number.
 *
 * Time Complexity: O(d) where d is the number of digits.
 * Space Complexity: O(1).
 *
 * Edge Cases handled:
 * - Single-digit numbers (always Armstrong).
 * - n = 0 (treated as Armstrong by this implementation).
 *
 * Dry Run:
 * n = 153 -> digits count = 3
 * sum = 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153 -> true
 *
 * Correctness Check:
 * Logic matches the definition. Note: Math.pow returns double; for larger values,
 * floating-point rounding could be an issue. For typical interview constraints, it is fine.
 *
 * LeetCode Match:
 * Closest match is LeetCode 1134 - "Armstrong Number".
 */
/**
 * Armstrong Number Check
 * Determines whether a given number is an Armstrong number or not.
 * An Armstrong number is a number that is equal to the sum of cubes (or power
 * of its length) of its digits.
 */
class armstrong_number {
    /**
     * Time Complexity: O(log N) where N is the number, corresponding to the number
     * of digits in N.
     * Space Complexity: O(1)
     */
    static boolean armstrongNumber(int n) {
        int og_no = n; // Keep original number to compare later
        int count = 0;
        int temp = n;

        // Count the number of digits
        while (temp != 0) {
            count++;
            temp = temp / 10;
        }

        int sum = 0;
        // Calculate the sum of the digits raised to the power of `count`
        while (n != 0) {
            int digit = n % 10;
            // Add digit^count to the running sum
            sum += Math.pow(digit, count);
            n /= 10;
        }

        // Return true if the sum equals the original number
        return (sum == og_no);
    }

    public static void main(String args[]) {
        int n1 = 152;
        // Simple test harness
        if (armstrongNumber(n1)) {
            System.out.println("Yes, it is an Armstrong Number\n");
        } else {
            System.out.println("No, it is not an Armstrong Number\n");
        }

    }
}
