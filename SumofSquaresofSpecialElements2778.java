/*
 * Problem: 2778. Sum of Squares of Special Elements
 *
 * Problem Statement:
 * Given a 1-indexed integer array nums of length n, an element nums[i] is considered 
 * "special" if n is divisible by i (where i is the 1-based index). The goal is to 
 * return the sum of the squares of all special elements in the array.
 *
 * Intuition:
 * The problem uses 1-based indexing for the mathematical condition (divisibility), 
 * but arrays in Java are 0-indexed. To bridge this, for any element at index 'i', 
 * its 1-based position is 'i + 1'. We simply need to check if the total length 
 * of the array is divisible by this 1-based position.
 *
 * Approach:
 * 1. Store the length of the array as 'n'.
 * 2. Iterate through the array using a standard loop (0 to n-1).
 * 3. For each index 'i', check if 'n' modulo '(i + 1)' equals 0.
 * 4. If the condition is met, square the value at that index and add it to a running total.
 * 5. Return the final sum.
 *
 * Time Complexity: O(n)
 * We perform a single linear scan through the array of size n.
 *
 * Space Complexity: O(1)
 * We only use a few integer variables (res, n, i) regardless of the input size.
 *
 * Edge Cases:
 * - n = 1: The single element is always special because 1 % 1 == 0.
 * - n is prime: Only the first element (index 0, position 1) and the last 
 *   element (index n-1, position n) will be special.
 *
 * Dry Run:
 * nums = [2, 7, 1, 19, 18, 3], n = 6
 * i=0: (i+1)=1. 6%1==0? Yes. res = 0 + 2^2 = 4.
 * i=1: (i+1)=2. 6%2==0? Yes. res = 4 + 7^2 = 53.
 * i=2: (i+1)=3. 6%3==0? Yes. res = 53 + 1^2 = 54.
 * i=3: (i+1)=4. 6%4==0? No.
 * i=4: (i+1)=5. 6%5==0? No.
 * i=5: (i+1)=6. 6%6==0? Yes. res = 54 + 3^2 = 63.
 * Result: 63.
 *
 * Correctness Check:
 * The solution correctly maps 0-based indices to 1-based positions. 
 * Note: For very large input values, squaring might overflow an int, 
 * but based on typical LeetCode constraints for this problem (nums[i] <= 100), 
 * an int is perfectly safe.
 */
public class SumofSquaresofSpecialElements2778 {
    public static int sumOfSquares(int[] nums) {
        int res = 0;
        int n = nums.length;
        
        // Iterate through the array using 0-based indexing
        for (int i = 0; i < n; i++) {
            // Convert 0-based index 'i' to 1-based index 'i + 1'
            // Check if the array length 'n' is divisible by this 1-based index
            if (n % (i + 1) == 0) {
                // If special, add the square of the element to our result
                res += nums[i] * nums[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 1, 19, 18, 3 };
        System.out.println(sumOfSquares(nums));
    }
}
