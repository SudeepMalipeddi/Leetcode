/*
Problem Statement:
- Reverse digits of 32-bit signed integer; return 0 on overflow.

Intuition:
- Digit extraction with modulo/division builds reversed number.

Approach:
- Use long accumulator while stripping digits and then cast if in range.
- Correctness concern: overflow upper bound check uses 2^31 instead of Integer.MAX_VALUE (2^31-1).

Time Complexity:
- O(d).

Space Complexity:
- O(1).

Edge Cases:
- Negative values are handled by processing sign through x and k variables.

Dry Run:
- x=120 -> digits build 21.
*/
public class ReverseInt {
    public int reverse(int x) {
        long sum = 0;
        int k = x;
        
        if (x < 0) {
            k = (-1) * k;
        }
        
        // Extract and append digits to build reversed number.
        while (k > 0) {
            sum = sum * 10 + x % 10;
            x = x / 10;
            k = k / 10;
        }
        
        // Overflow guard before narrowing long to int.
        if (sum < Math.pow(-2, 31) || sum > Math.pow(2, 31))
        
            return 0;
        else
            return (int) sum;
    }
}
