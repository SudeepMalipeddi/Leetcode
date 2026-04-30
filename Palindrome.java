/*
Problem Statement:
- Return whether an integer reads the same forward and backward.

Intuition:
- Reversing digits and comparing with original value determines palindrome status.

Approach:
- Reject negatives, build reverse using modulo/division, compare against saved original.

Time Complexity:
- O(d), d = number of digits.

Space Complexity:
- O(1).

Edge Cases:
- Negative numbers are immediately false.

Dry Run:
- 121 -> reverse builds 121, comparison succeeds.
*/
public class Palindrome {
    public boolean isPalindrome(int x) {
        
        if (x < 0) {
            return false;
        }
        long var = x;
        long temp = 0;
        long reverse = 0;
        
        // Build reversed digits from least significant to most significant.
        while (x > 0) {
            temp = x % 10;
            reverse = ((reverse * 10) + temp);
            x = x / 10;
        }
        return (reverse == var);
    }
}
