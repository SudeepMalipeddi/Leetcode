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
