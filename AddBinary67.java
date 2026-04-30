/*
 * Problem: 67. Add Binary
 *
 * Problem Statement:
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Intuition:
 * Binary addition is identical to decimal addition but uses base 2. We process 
 * digits from right to left (least significant to most significant), keeping 
 * track of a carry whenever the sum of digits at a position equals or exceeds 2.
 *
 * Approach:
 * 1. Initialize two pointers at the end of strings a and b, and a carry variable to 0.
 * 2. Use a StringBuilder to build the result string from right to left.
 * 3. While there are digits to process in either string or a carry remains:
 *    a. Add the current digits (if available) and the carry.
 *    b. The new bit for the current position is sum % 2.
 *    c. The new carry for the next position is sum / 2.
 * 4. After processing all bits, reverse the StringBuilder to get the correct order.
 *
 * Time Complexity: O(max(N, M)) where N and M are the lengths of strings a and b.
 * We iterate through the characters of both strings once.
 * Space Complexity: O(max(N, M)) to store the resulting sum in the StringBuilder.
 *
 * Edge Cases:
 * - Strings of different lengths (e.g., "1" + "111").
 * - Addition resulting in an extra bit at the end (e.g., "11" + "1" = "100").
 * - Inputs being "0" and "0".
 *
 * Dry Run:
 * a = "11", b = "1"
 * 1. i=1, j=0, carry=0: sum = 0 + (b:'1') + (a:'1') = 2. sb="0", carry=1.
 * 2. i=0, j=-1, carry=1: sum = 1 + (a:'1') = 2. sb="00", carry=1.
 * 3. Loop ends. carry=1: sb="001".
 * 4. Reverse: "100".
 *
 * Correctness Check:
 * The logic correctly handles the carry-out and varying string lengths. 
 * Using (char - '0') is a standard way to convert numeric characters to integers.
 */
public class AddBinary67 {
    /**
     * Time Complexity: O(max(N, M)) where N and M are lengths of strings a and b.
     * Space Complexity: O(max(N, M)) for the StringBuilder.
     */
    public String addBinary(String a, String b) {
        // 100 + 111 = (1)011 carry extra both msbs 1 = carry
        // StringBuilder is used because strings in Java are immutable; appending is O(1) amortized.
        StringBuilder sb = new StringBuilder();

        // Start from the end of both strings (the least significant bit)
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        // Continue while there are digits left in either string or a carry to process
        while (i >= 0 || j >= 0) {
            int sum = carry; // Start with the carry from the previous bit addition
            
            // Add digit from string b if we haven't exhausted it
            if (j >= 0)
                // Subtract '0' to convert the character '0' or '1' to its integer value
                sum += b.charAt(j--) - '0';
            
            // Add digit from string a if we haven't exhausted it
            if (i >= 0)
                sum += a.charAt(i--) - '0';

            // In binary: 0+0=0, 0+1=1, 1+1=0 (carry 1), 1+1+1=1 (carry 1)
            // sum % 2 gives the bit for the current position
            sb.append(sum % 2);
            
            // sum / 2 gives the carry for the next position (1 if sum >= 2, else 0)
            carry = sum / 2;
        }
        
        // If there's a leftover carry after the last digits are processed, it becomes the MSB
        if (carry != 0)
            sb.append(carry);

        // Since we appended bits from right to left, the string is currently backwards.
        // Reverse it to restore the correct Most Significant Bit to Least Significant Bit order.
        return sb.reverse().toString();
    }
}
