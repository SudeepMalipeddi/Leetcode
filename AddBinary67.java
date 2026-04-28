/*
 * Problem: LeetCode 67. Add Binary
 *
 * Problem Statement:
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Intuition:
 * Binary addition works from least significant bit to most significant bit, carrying
 * over when the sum is 2 or 3. Walking from the end of each string simulates that.
 *
 * Approach:
 * 1. Start from the last character of both strings and keep a carry.
 * 2. Add the current bits plus carry, append sum % 2, update carry = sum / 2.
 * 3. Continue until both strings are exhausted, then append any leftover carry.
 * 4. Reverse the built string because we appended from least to most significant bit.
 *
 * Time Complexity: O(max(N, M)) because each string is scanned once.
 * Space Complexity: O(max(N, M)) for the StringBuilder output.
 *
 * Edge Cases handled:
 * - Different length strings.
 * - Leftover carry at the end (e.g., "1" + "1" => "10").
 * - Strings that represent zero.
 *
 * Dry Run:
 * a = "101", b = "11"
 * Step 1: 1 + 1 = 2 -> append 0, carry 1
 * Step 2: 0 + 1 + carry 1 = 2 -> append 0, carry 1
 * Step 3: 1 + carry 1 = 2 -> append 0, carry 1
 * End: append carry 1 -> "0001", reverse -> "1000"
 *
 * Correctness Check:
 * The implementation follows standard binary addition and is correct.
 *
 * LeetCode Match:
 * LeetCode 67 - "Add Binary".
 */
/**
 * LeetCode 67. Add Binary
 * Given two binary strings a and b, return their sum as a binary string.
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
            // Sum of current bits plus carry
            int sum = carry;
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
