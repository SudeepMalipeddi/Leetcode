/*
 * Problem: 66. Plus One
 *
 * Problem Statement:
 * Given a non-negative integer represented as an array of digits, increment the integer by one.
 * The digits are stored such that the most significant digit is at the head of the list, 
 * and each element in the array contains a single digit.
 *
 * Intuition:
 * This problem mimics schoolbook addition. We start from the least significant digit (the end of the array).
 * If a digit is less than 9, adding 1 is straightforward and doesn't generate a carry.
 * If a digit is 9, it "rolls over" to 0, and we must carry the 1 to the next more significant digit.
 *
 * Approach:
 * 1. Iterate through the array backwards (from index n-1 down to 0).
 * 2. If the current digit is less than 9, increment it by 1 and return the array immediately, 
 *    as no further carries are possible.
 * 3. If the current digit is 9, set it to 0 and continue to the next iteration (the carry).
 * 4. If the loop finishes without returning, it means every digit was a 9 (e.g., 999). 
 *    In this case, create a new array of size n+1, set the first element to 1, and return it.
 *
 * Time Complexity: O(n), where n is the number of digits. In the worst case (all 9s), we visit every digit once.
 * Space Complexity: O(n) in the worst case when all digits are 9, as we must allocate a new array of size n+1.
 *                  Otherwise, it is O(1) auxiliary space as we modify the input array in place.
 *
 * Edge Cases:
 * - Single digit [9]: Should return [1, 0].
 * - All digits are 9 [9, 9, 9]: Should return [1, 0, 0, 0].
 * - No carry needed [1, 2, 3]: Should return [1, 2, 4].
 *
 * Dry Run:
 * Input: digits = [1, 2, 9]
 * 1. i = 2: digits[2] is 9. Set digits[2] = 0.
 * 2. i = 1: digits[1] is 2. 2 < 9 is true.
 * 3. Increment digits[1] to 3.
 * 4. Return [1, 3, 0].
 *
 * Correctness Check:
 * The logic in the plusOne method is correct. However, there is a subtle behavior in the main method:
 * the call `t.plusOne(digit)` does not assign the result back to the variable `digit`. 
 * If the input is {9}, the method modifies the original array to {0} and then returns a NEW array {1, 0}.
 * Since the return value is ignored in main, the final print loop will print the modified original array {0}.
 */
class test {
    int[] plusOne(int[] digits) {
        
        // Propagate carry from the least significant digit.
        // We iterate backwards because the last element represents the ones place.
        for (int i = digits.length - 1; i >= 0; i--) {
            
            // If the digit is less than 9, incrementing it finishes the addition.
            if (digits[i] < 9) {
                digits[i]++;
                // Early return because no further carry propagation is needed.
                return digits;
            }
            // If the digit is 9, it becomes 0 and the loop continues to the next index (carry).
            digits[i] = 0;
        }
        // All digits were 9, so result needs one extra leading digit (e.g., 99 -> 100).
        // Java initializes new int arrays with zeros by default.
        digits = new int[digits.length + 1];
        // Set the most significant digit to 1.
        digits[0] = 1;
        return digits;
    }
}

public class PlusOne66 {
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        // Example case: 9. Adding 1 should result in 10.
        int[] digit = { 9 };
        int n = digit.length;
        
        // Print original array elements.
        for (int i = 0; i < n; i++) {
            System.out.println(digit[i]);
        }
        test t = new test();
        // Note: The result of plusOne is not captured here. 
        // For the input {9}, the original array 'digit' is modified to {0} in-place 
        // before the method returns a new array {1, 0}.
        t.plusOne(digit);
        
        
        System.out.println();
        
        // Print the array after the method call. 
        // Because the return value was not captured, this prints the modified original array.
        for (int i = 0; i < digit.length; i++) {
            System.out.println(digit[i]);
        }
    }
}
