/*
 * Problem: 238. Product of Array Except Self
 *
 * Problem Statement:
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product 
 * of all the elements of nums except nums[i]. The solution must run in O(n) time and 
 * without using the division operator.
 *
 * Intuition:
 * The product of all elements except nums[i] can be decomposed into two parts: 
 * (product of all elements to the left of i) * (product of all elements to the right of i).
 * By pre-calculating these prefix and suffix products, we can determine the result for 
 * each index in linear time.
 *
 * Approach:
 * 1. Initialize an output array 'res' where res[i] will eventually store the final product.
 * 2. Perform a forward pass: Use a variable 'left' to maintain the running product of all 
 *    elements to the left of the current index. Store 'left' in res[i] and then update 'left'.
 * 3. Perform a backward pass: Use a variable 'right' to maintain the running product of all 
 *    elements to the right of the current index. Multiply the existing value in res[i] 
 *    by 'right' and then update 'right'.
 *
 * Time Complexity: O(n) because we iterate through the array of size n exactly twice.
 * Space Complexity: O(1) extra space, as the problem statement specifies that the output 
 * array does not count towards the extra space complexity.
 *
 * Edge Cases:
 * - Array with a single zero: All elements in the result will be zero except for the index 
 *   where the zero was located.
 * - Array with multiple zeros: All elements in the result will be zero.
 * - Minimum array size (n=2): Handled correctly by the logic.
 *
 * Dry Run:
 * nums = [1, 2, 3, 4]
 * 1. Forward Pass:
 *    i=0: res[0]=1, left=1*1=1
 *    i=1: res[1]=1, left=1*2=2
 *    i=2: res[2]=2, left=2*3=6
 *    i=3: res[3]=6, left=6*4=24
 *    res = [1, 1, 2, 6]
 * 2. Backward Pass:
 *    i=3: res[3]=6*1=6, right=1*4=4
 *    i=2: res[2]=2*4=8, right=4*3=12
 *    i=1: res[1]=1*12=12, right=12*2=24
 *    i=0: res[0]=1*24=24, right=24*1=24
 *    res = [24, 12, 8, 6]
 *
 * Correctness Check:
 * The logic is sound. It avoids division (handling zeros) and meets the O(n) time 
 * and O(1) space constraints.
 */
public class ProductofArrayExceptSelf238 {
    public int[] productExceptSelf(int[] nums){
        int n = nums.length;
        // Initialize the result array to store prefix products first, then final results.
        int[] res = new int[n];
        // 'left' tracks the cumulative product of all elements to the left of index i.
        int left = 1;
        
        // First pass: Calculate prefix products (everything to the left of i).
        for(int i = 0; i<n;i++){
            // Store product of all elements strictly to the left of i.
            res[i] = left;
            left *= nums[i];
        }
        // 'right' tracks the cumulative product of all elements to the right of index i.
        int right = 1;
        
        // Second pass: Calculate suffix products and multiply them with the prefix products.
        for(int i = n-1;i >= 0; i--){
            // Multiply by product of all elements strictly to the right of i.
            // res[i] currently contains the prefix product; multiplying by 'right' completes the calculation.
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
