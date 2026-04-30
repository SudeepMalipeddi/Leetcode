/*
 * Problem: 2843. Count Symmetric Integers
 *
 * Problem Statement:
 * Given two positive integers low and high, return the count of symmetric integers in the range [low, high].
 * An integer is symmetric if it has an even number of digits and the sum of its first half of digits 
 * equals the sum of its second half of digits.
 *
 * Intuition:
 * Since the maximum value of high is 10,000, the number of integers to check is small. A brute-force 
 * approach that examines each number individually is efficient enough. Converting numbers to strings 
 * simplifies the process of splitting digits into two halves.
 *
 * Approach:
 * 1. Iterate through every integer i from low to high.
 * 2. Convert the integer to a string to determine its length and access digits by index.
 * 3. Check if the length of the string is even; if not, it cannot be symmetric.
 * 4. Split the string into two equal halves and calculate the sum of digits for each half.
 * 5. If the sums are equal, increment the count.
 *
 * Time Complexity: O(N * D), where N is the number of integers in the range (high - low + 1) 
 * and D is the maximum number of digits (at most 5 in this problem).
 * Space Complexity: O(D) to store the string representation of the current number.
 *
 * Edge Cases:
 * - Numbers with an odd number of digits (e.g., 5, 123): Correctly skipped by the modulo check.
 * - Numbers at the boundaries (low and high): Included in the loop.
 * - Numbers like 10000: Length is 5 (odd), so it is correctly skipped.
 *
 * Dry Run:
 * low = 1190, high = 1201
 * i = 1190: length 4. Half1: 1+1=2, Half2: 9+0=9. 2 != 9.
 * i = 1200: length 4. Half1: 1+2=3, Half2: 0+0=0. 3 != 0.
 * i = 1201: length 4. Half1: 1+2=3, Half2: 0+1=1. 3 != 1.
 * Result: 0.
 *
 * Correctness Check:
 * The solution correctly implements the symmetry logic. The use of charAt(j) - '0' is a standard 
 * way to convert a character digit to its integer value.
 */
public class CountSymmetricIntegers2843 {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        // Iterate through every integer in the inclusive range [low, high].
        for (int i = low; i <= high; i++) {
            // Convert integer to string to easily calculate length and access individual digits.
            String curr = String.valueOf(i);
            int n = curr.length();
            
            // Symmetric integers must have an even number of digits. Skip odd-length numbers.
            if (n % 2 != 0) {
                continue;
            }
            
            int res1 = 0;
            int res2 = 0;
            
            // Simultaneously calculate the sum of the first half and the second half of the digits.
            // The first half indices are [0, n/2 - 1], the second half indices are [n/2, n - 1].
            for (int j = 0; j < n / 2; j++) {
                // Subtract '0' to convert the char digit to its integer value.
                res1 += (curr.charAt(j) - '0');
                res2 += (curr.charAt(n / 2 + j) - '0');
            }
            
            // If the sum of the first half equals the sum of the second half, the integer is symmetric.
            if (res1 == res2) {
                count++;
            }
        }
        return count;
    }
}
