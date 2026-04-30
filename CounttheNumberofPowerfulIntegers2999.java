/*
 * Problem: 2999. Count the Number of Powerful Integers
 *
 * Problem Statement:
 * An integer is "powerful" if its suffix matches a given string 's' and every digit in the 
 * number is less than or equal to a given 'limit'. Given a range [start, finish], 
 * count how many powerful integers exist in this range.
 *
 * Intuition:
 * This specific implementation uses a brute-force approach, iterating through every 
 * integer in the range [start, finish] and checking two conditions: 
 * 1. Does the number end with the required suffix 's'?
 * 2. Is every digit in the number within the allowed 'limit'?
 *
 * Approach:
 * 1. Initialize a counter to zero.
 * 2. Loop from the 'start' value to the 'finish' value.
 * 3. Convert the current number to a String to facilitate suffix and digit checking.
 * 4. Use the String.endsWith() method to verify the suffix requirement.
 * 5. Iterate through each character of the string, converting it back to a digit to 
 *    compare against the 'limit'.
 * 6. If both conditions are met, increment the counter.
 *
 * Time Complexity: O(N * D) where N is the number of integers in the range (finish - start + 1)
 * and D is the number of digits in the numbers. Note: This approach is inefficient for 
 * the actual LeetCode constraints (up to 10^15) and would typically require Digit DP.
 *
 * Space Complexity: O(D) to store the string representation of the current number being checked.
 *
 * Edge Cases:
 * - start == finish: The loop runs exactly once.
 * - s is longer than the current number: endsWith() will correctly return false.
 * - limit is 0: Only numbers composed of '0' and ending in 's' (if 's' is also '0's) would pass.
 *
 * Dry Run:
 * Input: start=20, finish=25, limit=2, s="2"
 * - i=20: endsWith("2")? No.
 * - i=21: endsWith("2")? No.
 * - i=22: endsWith("2")? Yes. Digits: 2, 2. Both <= 2? Yes. count = 1.
 * - i=23: endsWith("2")? No.
 * - i=24: endsWith("2")? No.
 * - i=25: endsWith("2")? No.
 * Result: 1
 *
 * Correctness Check:
 * The logic correctly implements the requirements for small ranges. However, for the 
 * LeetCode problem constraints where finish can be 10^15, this brute-force method 
 * will result in a Time Limit Exceeded (TLE) error. A Digit Dynamic Programming 
 * approach is required for the optimized solution.
 */
public class CounttheNumberofPowerfulIntegers2999 {
    public static long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        // Initialize the counter for integers that satisfy all conditions.
        long count = 0;
        // Iterate through the active search space while maintaining the intended invariant.
        for (long i = start; i <= finish; i++) {
            // Convert the number to a string to easily check the suffix and individual digits.
            String curr = String.valueOf(i);
            // Check if the current number ends with the required suffix string 's'.
            if (!curr.endsWith(s)) {
                continue;
            }
            boolean valid = true;
            // Iterate through each digit of the current number.
            for (char c : curr.toCharArray()) {
                // Important guard: this branch handles a boundary or constraint-critical condition.
                // Convert character digit to integer and compare against the maximum allowed limit.
                if ((c - '0') > limit) {
                    valid = false;
                    break;
                }
            }
            // If the suffix matched and no digit exceeded the limit, increment the result.
            if (valid) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "20";
        long start = 20, finish = 1159;
        int limit = 5;
        // for (int i = 0; i <= limit; i++) {
        // // a = String.valueOf(i);
        // String prefix = String.format("", i);
        // String concatenated = String.valueOf(i).concat(s);
        // if (Integer.parseInt(concatenated) < finish && Integer.parseInt(concatenated)
        // > start) {
        // count++;
        // System.out.println(concatenated);
        // }
        // }

        // while()

        // Execute the counting logic and print the final result to the console.
        long n = numberOfPowerfulInt(start, finish, limit, s);
        System.out.println(n);
    }
}
