/*
Problem Statement:
- Add one to a non-negative integer represented as digit array.

Intuition:
- Process from least significant digit; stop once no carry remains.

Approach:
- Increment from right; set 9s to 0; if all digits roll over, create new array with leading 1.

Time Complexity:
- O(n).

Space Complexity:
- O(1), or O(n+1) only when overflow array is allocated.

Edge Cases:
- [9,9] becomes [1,0,0].

Dry Run:
- [1,2,9] -> [1,3,0].
*/
class test {
    int[] plusOne(int[] digits) {
        
        // Propagate carry from the least significant digit.
        for (int i = digits.length - 1; i >= 0; i--) {
            
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        // All digits were 9, so result needs one extra leading digit.
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}

public class PlusOne66 {
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        int[] digit = { 9 };
        int n = digit.length;
        
        for (int i = 0; i < n; i++) {
            System.out.println(digit[i]);
        }
        test t = new test();
        t.plusOne(digit);
        
        
        System.out.println();
        
        for (int i = 0; i < digit.length; i++) {
            System.out.println(digit[i]);
        }
    }
}
