/*
 * Problem: 1945. Sum of Digits of String After Convert
 *
 * Problem Statement:
 * Given a string 's' consisting of lowercase English letters and an integer 'k', convert 's' into an 
 * integer by replacing each letter with its position in the alphabet (a=1, b=2, ..., z=26). 
 * Then, transform the resulting integer by replacing it with the sum of its digits 'k' times.
 *
 * Intuition:
 * The initial conversion can result in a very large number (e.g., "zzzz..." would be "262626..."), 
 * which exceeds the capacity of standard primitive types like 'long'. Therefore, we represent 
 * the number as a string or StringBuilder initially. Each transformation step significantly 
 * reduces the size of the number, eventually making it small enough to handle as an integer.
 *
 * Approach:
 * 1. Convert each character in the input string to its alphabet position (1-indexed) and 
 *    append these values to a StringBuilder to form the initial large "number" string.
 * 2. Perform the transformation 'k' times:
 *    a. Iterate through the current string representation.
 *    b. Sum the numeric value of each digit character.
 *    c. Update the StringBuilder with the string representation of this new sum.
 * 3. Return the final result as an integer.
 *
 * Time Complexity: O(n + k * m) 
 * - n is the length of the input string 's' (initial conversion).
 * - k is the number of transformations.
 * - m is the number of digits in the intermediate sums. Since the sum of digits 
 *   shrinks rapidly, m becomes very small after the first iteration.
 *
 * Space Complexity: O(n) 
 * - We store the initial converted string which can be up to 2 * n characters long 
 *   (since 'z' is 26, a 2-digit number).
 *
 * Edge Cases:
 * - k = 1: Only one transformation is performed.
 * - s contains 'z': Produces two digits (2 and 6) in the initial string.
 * - s is a single character: Handled correctly by the loops.
 *
 * Dry Run:
 * s = "zbax", k = 2
 * 1. Convert: 'z'->26, 'b'->2, 'a'->1, 'x'->24 => sb = "262124"
 * 2. k=1: Sum digits of "262124" -> 2+6+2+1+2+4 = 17. sb = "17"
 * 3. k=2: Sum digits of "17" -> 1+7 = 8. sb = "8"
 * 4. Return 8.
 *
 * Correctness Check:
 * The solution is correct. Note that after the first transformation, the sum will 
 * easily fit into a standard 32-bit integer, making the final parseInt safe.
 */

public class SumofDigitsofStringAfterConvert1945 {
    public static void main(String[] args) {
        String s = "zbax";
        int k = 2;
        int n = getLucky(s, k);
        System.out.println(n);
    }

    public static int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        
        // Step 1: Convert characters to their alphabet positions (a=1, b=2, ...)
        // We use 'num - 'a' + 1' to map 'a' to 1, 'b' to 2, etc.
        for (char num : s.toCharArray()) {
            sb.append(num - 'a' + 1);
        }
        
        // Step 2: Perform the digit sum transformation k times
        while (k > 0) {
            int temp = 0;
            // Iterate through each character (digit) in the current string representation
            for (char x : sb.toString().toCharArray()) {
                // Convert char digit to its integer value (e.g., '5' becomes 5)
                temp += x - '0';
            }
            
            // Update the StringBuilder with the new sum for the next iteration
            sb = new StringBuilder(String.valueOf(temp));
            k--;
        }
        
        // Final result is the integer value of the transformed string
        return Integer.parseInt(sb.toString());
    }
}
