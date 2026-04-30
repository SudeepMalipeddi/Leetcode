/*
 * Problem: 2259. Remove Digit From Number to Maximize Result
 *
 * Problem Statement:
 * Given a string 'number' representing a positive integer and a character 'digit',
 * remove exactly one occurrence of 'digit' from 'number' such that the resulting
 * string represents the largest possible integer.
 *
 * Intuition:
 * To maximize a number, we want to replace a digit with a larger one at the highest
 * possible place value (leftmost). If we remove 'digit' at index i, the digit at i+1
 * shifts into its place. If number[i+1] > digit, the number increases. The first
 * time this happens from left-to-right is the optimal place to remove. If this
 * never happens (i.e., the next digit is always smaller or equal), removing the
 * last occurrence of 'digit' is the best option to minimize the reduction in value.
 *
 * Approach:
 * 1. Brute Force (removeDigit): Find every occurrence of the digit, generate all
 *    possible strings by removing that occurrence, and compare them using BigInteger
 *    to find the maximum.
 * 2. Greedy (removeDigit1): Iterate through the string. Every time we find 'digit',
 *    update the 'index' variable. If the digit immediately following the current
 *    'digit' is greater than 'digit', removing here is the greedy optimal choice,
 *    so we break. Otherwise, we continue to find the next occurrence.
 *
 * Time Complexity: 
 * - removeDigit: O(N^2) because for each occurrence (up to N), we create a new 
 *   string of length N and perform a BigInteger comparison.
 * - removeDigit1: O(N) where N is the length of the string, as we traverse once.
 *
 * Space Complexity:
 * - removeDigit: O(N^2) to store all candidate strings in an ArrayList.
 * - removeDigit1: O(N) to store the final result string.
 *
 * Edge Cases:
 * - Digit appears only once: The only option is to remove that single occurrence.
 * - Digit is at the end of the string: The greedy check (i < n - 1) handles this.
 * - All digits are identical: Any removal results in the same number.
 *
 * Dry Run:
 * number = "1231", digit = '1'
 * i=0: '1' == '1'. index=0. Next digit '2' > '1'. Greedy break!
 * Result: substring(0,0) + substring(1) = "" + "231" = "231".
 *
 * Correctness Check:
 * Both methods are correct. removeDigit1 is more efficient. Note that BigInteger 
 * in removeDigit is necessary because the input string can be up to 100 digits, 
 * exceeding the capacity of a 64-bit Long.
 */
import java.math.BigInteger;
import java.util.*;

public class RemoveDigitFromNumbertoMaximizeResult2259 {

    
    public String removeDigit(String num, char digit) {

        // List to store all possible strings after removing one instance of 'digit'
        ArrayList<String> a1 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(num);
        
        for (int i = 0; i < sb.length(); i++) {
            
            // Generate a candidate by deleting this occurrence of digit.
            if (sb.charAt(i) == digit) {
                sb.deleteCharAt(i);
                a1.add(sb.toString());
                // Reset the StringBuilder to the original number for the next check
                sb = new StringBuilder(num);
            }
        }
        // Initialize max result; BigInteger is used because the number can be very large
        BigInteger res = BigInteger.ZERO;
        
        for (int i = 0; i < a1.size(); i++) {
            BigInteger no = new BigInteger(a1.get(i));
            
            // Compare current candidate with the best found so far
            if (no.compareTo(res) > 0) {
                res = no;
            }
        }
        return res.toString();
    }

    
    public String removeDigit1(String number, char digit) {
        int n = number.length(), index = 0;
        
        for (int i = 0; i < n; i++) {
            
            if (number.charAt(i) == digit) {
                // Keep track of the most recent occurrence of the digit
                index = i;
                
                // Greedy choice: removing here improves the next digit immediately.
                // If the next digit is larger, removing the current digit shifts a larger 
                // value to a higher power of 10.
                if (i < n - 1 && number.charAt(i + 1) > digit) {
                    break;
                }
            }
        }
        // Construct the final string by removing the digit at the determined index
        return number.substring(0, index) + number.substring(index + 1);
    }

}
