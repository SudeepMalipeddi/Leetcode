/*
 * Problem: 8. String to Integer (atoi)
 *
 * Problem Statement:
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 * The algorithm must handle leading whitespace, an optional plus or minus sign, and overflow 
 * by clamping the result to the 32-bit signed integer range [-2^31, 2^31 - 1].
 *
 * Intuition:
 * This is a parsing problem that follows a specific sequence of states: 
 * [Leading Whitespace] -> [Optional Sign] -> [Digits] -> [Ignore Rest].
 * The core challenge is detecting potential overflow before it actually happens in memory.
 *
 * Approach:
 * 1. Skip all leading spaces using a pointer.
 * 2. Check the next character for a '+' or '-' sign to determine the multiplier.
 * 3. Iterate through the remaining characters as long as they are digits.
 * 4. For each digit, check if appending it to the current result would exceed Integer.MAX_VALUE.
 * 5. If overflow occurs, return the clamped MIN or MAX value based on the sign.
 * 6. Apply the sign to the final accumulated magnitude.
 *
 * Time Complexity: O(N) where N is the length of the input string, as we traverse the string at most once.
 * Space Complexity: O(1) as we only use a few variables regardless of input size.
 *
 * Edge Cases:
 * - Empty string or string with only spaces.
 * - String starting with non-digit/non-sign characters.
 * - Numbers exceeding 32-bit integer limits (overflow/underflow).
 * - Multiple signs (e.g., "+-12") - should return 0 after the first sign is processed.
 *
 * Dry Run:
 * Input: "  -42"
 * 1. Skip spaces: index moves to 2 (char '-').
 * 2. Sign check: isNegative = true, index moves to 3 (char '4').
 * 3. Digit '4': result = 0 * 10 + 4 = 4.
 * 4. Digit '2': result = 4 * 10 + 2 = 42.
 * 5. End of string: return -42.
 *
 * Correctness Check:
 * The solution correctly handles the logic. The overflow check `digit > 7` is correct because 
 * Integer.MAX_VALUE is 2,147,483,647. If result is 214,748,364 and the next digit is > 7, 
 * it will overflow.
 */

public class StringtoInteger_8 {
    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public int myAtoi(String str) {
        final int len = str.length();

        // Basic sanity check for empty input
        if (len == 0) {
            return 0;
        }

        int index = 0;

        // skipping whitespaces: move the pointer past any leading ' ' characters
        while (index < len && str.charAt(index) == ' ') {
            ++index;
        }

        boolean isNegative = false;

        // checking sign: only the first sign character encountered after spaces is valid
        if (index < len && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            isNegative = str.charAt(index) == '-' ? true : false;
            ++index;
        }

        int result = 0;

        // Process characters as long as they are valid digits
        while (index < len && isDigit(str.charAt(index))) {
            /*
             * str.charAt(index) - '0' is to convert the char digit into int digit eg: '5' -
             * '0' --> 5
             * or else it will store the ASCII value of 5 i.e. 53,
             * so we do 53(ASCII of 5) - 48(ASCII of 0(zero)) to get 5 as int
             */
            int digit = str.charAt(index) - '0';

            // checking for overflow:
            // 1. If result > MAX/10, multiplying by 10 will definitely overflow.
            // 2. If result == MAX/10, adding a digit > 7 will overflow (MAX ends in 7).
            if (result > (Integer.MAX_VALUE / 10) || (result == (Integer.MAX_VALUE / 10) && digit > 7)) {
                // Return clamped values based on the sign determined earlier
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            
            // Shift existing digits left by one decimal place and add the new digit
            result = (result * 10) + digit;
            ++index;
        }
        
        // Apply the sign to the accumulated positive magnitude
        return isNegative ? -result : result;
    }
}
