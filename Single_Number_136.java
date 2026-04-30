/*
 * Problem: 136. Single Number
 *
 * Problem Statement:
 * Given a non-empty array of integers nums, every element appears twice except for one. 
 * Find that single one. You must implement a solution with a linear runtime complexity 
 * and use only constant extra space.
 *
 * Intuition:
 * This problem is solved efficiently using the Bitwise XOR (^) operator. 
 * XOR has three critical properties:
 * 1. x ^ x = 0 (Any number XORed with itself results in zero)
 * 2. x ^ 0 = x (Any number XORed with zero remains unchanged)
 * 3. XOR is commutative and associative (Order of operations doesn't matter)
 * Because every number except one appears twice, XORing all numbers together 
 * will cause the pairs to cancel each other out (become 0), leaving only the unique number.
 *
 * Approach:
 * 1. Initialize a variable 'result' to 0.
 * 2. Iterate through every integer in the input array.
 * 3. Apply the XOR operation between 'result' and the current element.
 * 4. After the loop, 'result' will hold the value of the single number.
 *
 * Time Complexity: O(n) where n is the number of elements in the array, as we visit each element exactly once.
 * Space Complexity: O(1) as we only use a single integer variable regardless of the input size.
 *
 * Edge Cases:
 * - Array contains only one element: The loop runs once, result becomes that element.
 * - The single number is at the start, middle, or end: XOR properties ensure position doesn't matter.
 *
 * Dry Run:
 * Input: nums = [4, 1, 2, 1, 2]
 * 1. result = 0
 * 2. result = 0 ^ 4 = 4
 * 3. result = 4 ^ 1 = 5
 * 4. result = 5 ^ 2 = 7
 * 5. result = 7 ^ 1 = 6  (Note: 4 ^ 1 ^ 2 ^ 1 is same as 4 ^ (1 ^ 1) ^ 2 = 4 ^ 0 ^ 2 = 4 ^ 2)
 * 6. result = 6 ^ 2 = 4
 * Final result: 4
 *
 * Correctness Check:
 * The solution is correct and meets the O(n) time and O(1) space constraints.
 */
public class Single_Number_136 {
    int singleNumber(int[] nums) {
        // declaring a variable to store the length of the array
        int n = nums.length;
        // declaring a variable to store the result
        // We start at 0 because 0 ^ x = x.
        int result = 0;
        // using the XOR operator to find the single number
        for(int i = 0; i<n; i++){
            // XOR operator
            // This bitwise operation effectively "cancels out" numbers seen twice.
            // If nums[i] has been seen before, result ^ nums[i] removes its bits.
            // If it's new, it adds its bits to the result.
            result ^= nums[i];
        }
        // returning the result
        // After the loop, all pairs have canceled out to 0, leaving the unique value.
        return result;
    }
}
