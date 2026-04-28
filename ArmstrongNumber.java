/*
 * Problem: Armstrong Number (Narcissistic Number) Check
 * Problem Statement: Determine whether a given integer equals the sum of its
 *   digits each raised to the power of the number of digits.
 * Intuition: Split the number into digits, compute digit^count, and compare to
 *   the original value.
 * Approach:
 *   1) Count the number of digits.
 *   2) Re-traverse the number, summing digit^count.
 *   3) Return true if the sum equals the original number.
 * Time Complexity: O(d) where d is the number of digits.
 * Space Complexity: O(1).
 * Edge Cases: Single-digit numbers (always Armstrong), large values.
 * Dry Run: n=153 -> digits=3, sum=1^3+5^3+3^3=1+125+27=153 => true.
 * Correctness Check: The algorithm matches the mathematical definition exactly.
 * Assumption: n is non-negative (standard definition for Armstrong numbers).
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
            sum += Math.pow(digit, count);
            n /= 10;
        }

        // Return true if the sum equals the original number
        return (sum == og_no);
    }

    public static void main(String args[]) {
        int n1 = 152;
        if (armstrongNumber(n1)) {
            System.out.println("Yes, it is an Armstrong Number\n");
        } else {
            System.out.println("No, it is not an Armstrong Number\n");
        }

    }
}
