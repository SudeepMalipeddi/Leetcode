/*
Problem Statement:
- For each index, return product of all elements except itself without division.

Intuition:
- Prefix products cover left side, suffix products cover right side.

Approach:
- Store running left product in result, then multiply running right product in reverse pass.

Time Complexity:
- O(n).

Space Complexity:
- O(1) extra beyond output array.

Edge Cases:
- Handles zeros naturally via prefix/suffix multiplication.

Dry Run:
- [1,2,3,4] -> left pass [1,1,2,6], right pass => [24,12,8,6].
*/
public class ProductofArrayExceptSelf238 {
    public int[] productExceptSelf(int[] nums){
        int n = nums.length;
        int[] res = new int[n];
        int left = 1;
        
        for(int i = 0; i<n;i++){
            // Store product of all elements strictly to the left of i.
            res[i] = left;
            left *= nums[i];
        }
        int right = 1;
        
        for(int i = n-1;i >= 0; i--){
            // Multiply by product of all elements strictly to the right of i.
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
