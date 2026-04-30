/*
 * Problem: 13. Roman to Integer
 *
 * Problem Statement:
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Given a roman numeral, convert it to an integer.
 *
 * Intuition:
 * Roman numerals are generally written largest to smallest from left to right. However, 
 * subtractive notation (like IV for 4) breaks this rule. By traversing the string 
 * from right to left, we can easily identify these cases: if a value we encounter 
 * is significantly smaller than the sum we've already accumulated from its right, 
 * it must be a subtractive prefix.
 *
 * Approach:
 * 1. Initialize 'ans' to store the total and 'num' for the current character's value.
 * 2. Iterate through the string backwards (from right to left).
 * 3. Use a switch statement to map the Roman character to its integer value.
 * 4. Apply the subtraction rule: If the current value multiplied by 4 is less than 
 *    the current total, it indicates a subtractive relationship (like I before V or X).
 *    Otherwise, add the value to the total.
 * 5. Return the accumulated total.
 *
 * Time Complexity: O(n), where n is the length of the string. We visit each character once.
 * Space Complexity: O(1), as we only use a few integer variables regardless of input size.
 *
 * Edge Cases:
 * - Single character strings ("I", "X", "M").
 * - Strings with only additive numerals ("VIII", "MDCLXVI").
 * - Strings with subtractive numerals ("IV", "XC", "CM").
 *
 * Dry Run:
 * Input: "MCMXCIV"
 * 1. i=6, char='V', num=5, ans=5 (4*5 < 0 is false)
 * 2. i=5, char='I', num=1, ans=4 (4*1 < 5 is true, so 5-1)
 * 3. i=4, char='C', num=100, ans=104 (4*100 < 4 is false, so 4+100)
 * 4. i=3, char='X', num=10, ans=94 (4*10 < 104 is true, so 104-10)
 * 5. i=2, char='M', num=1000, ans=1094 (4*1000 < 94 is false, so 94+1000)
 * 6. i=1, char='C', num=100, ans=994 (4*100 < 1094 is true, so 1094-100)
 * 7. i=0, char='M', num=1000, ans=1994 (4*1000 < 994 is false, so 994+1000)
 * Result: 1994
 *
 * Correctness Check:
 * The solution is correct. The heuristic `4 * num < ans` works because the next 
 * largest Roman numeral rank is always at least 5x the current one (I=1, V=5; X=10, L=50).
 * Multiplying by 4 creates a threshold that correctly identifies subtractive pairs.
 */
public class RomanToInteger13 {
    public int romanToInt(String s) {
        int ans = 0, num = 0;
        // Process the string from right to left to handle subtractive logic naturally
        for (int i = s.length() - 1; i >= 0; i--) {
            // Map Roman numeral characters to their respective integer values
            switch (s.charAt(i)) {
                case 'I':
                    num = 1;
                    break;
                case 'V':
                    num = 5;
                    break;
                case 'X':
                    num = 10;
                    break;
                case 'L':
                    num = 50;
                    break;
                case 'C':
                    num = 100;
                    break;
                case 'D':
                    num = 500;
                    break;
                case 'M':
                    num = 1000;
                    break;
            }
            
            /* 
             * Logic for Subtraction:
             * In Roman numerals, if a smaller value precedes a larger value, it is subtracted.
             * Since we are moving right-to-left, 'ans' contains the larger values already.
             * The condition '4 * num < ans' is a clever way to detect if 'num' is a 
             * subtractive prefix (like I in IV, or X in XC).
             */
            if (4 * num < ans)
                ans -= num;
            else
                ans += num;
        }
        return ans;
    }
}
