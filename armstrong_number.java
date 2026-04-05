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