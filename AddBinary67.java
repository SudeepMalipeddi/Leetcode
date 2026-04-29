/*
 * Problem: LeetCode 67 - Add Binary
 * Problem Statement: Given two binary strings a and b, return their sum as a
 *   binary string.
 * Intuition: Binary addition is performed from right to left while carrying
 *   overflow, exactly like manual addition in base 2.
 * Approach:
 *   1) Start from the least significant bits of both strings.
 *   2) Add digits plus carry, append sum % 2, update carry = sum / 2.
 *   3) Continue until both strings are exhausted, then append any leftover carry.
 *   4) Reverse the built string because we append from LSB to MSB.
 * Time Complexity: O(max(N, M)) for lengths of a and b.
 * Space Complexity: O(max(N, M)) for the StringBuilder output.
 * Edge Cases: Different lengths, carry at the final position, inputs like "0".
 * Dry Run: a="101", b="11" -> process from right: (1+1)=0 carry1,
 *   then (0+1+carry)=0 carry1, then (1+carry)=0 carry1, append carry -> "1000".
 * Correctness Check: Each position uses the correct base-2 sum and carry, so the
 *   resulting reversed string exactly matches binary addition.
 */
public class AddBinary67 {
    /**
     * Time Complexity: O(max(N, M)) where N and M are lengths of strings a and b.
     * Space Complexity: O(max(N, M)) for the StringBuilder.
     */
    public String addBinary(String a, String b) {
        // 100 + 111 = (1)011 carry extra both msbs 1 = carry
        StringBuilder sb = new StringBuilder();

        // Start from the end of both strings
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        // Continue while there are digits left in either string
        while (i >= 0 || j >= 0) {
            int sum = carry; // carry from previous bit
            // Add digit from string b if available
            if (j >= 0)
                sum += b.charAt(j--) - '0';
            // Add digit from string a if available
            if (i >= 0)
                sum += a.charAt(i--) - '0';

            // Append the remainder (0 or 1) to the result
            sb.append(sum % 2);
            // Update the carry for the next position
            carry = sum / 2;
        }
        // If there's a leftover carry, append it
        if (carry != 0)
            sb.append(carry);

        // Reverse the string since we appended from least significant bit to most
        // significant
        return sb.reverse().toString();
    }
}
