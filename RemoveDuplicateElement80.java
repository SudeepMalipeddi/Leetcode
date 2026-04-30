/*
 * Problem: 80. Remove Duplicates from Sorted Array II
 *
 * Problem Statement:
 * Given an integer array 'nums' sorted in non-decreasing order, remove some duplicates in-place 
 * such that each unique element appears at most twice. The relative order of the elements 
 * should be kept the same. Return the final length of the modified array.
 *
 * Intuition:
 * Since the array is already sorted, all duplicate elements are grouped together. 
 * We can maintain a "write" pointer 'i' that tracks where the next valid element should be placed.
 * A value is valid to be written if we haven't yet written two instances of that value. 
 * Because the array is sorted, we only need to compare the current element 'n' with the 
 * element written two positions ago (nums[i-2]). If 'n' is greater than nums[i-2], 
 * it means we haven't filled the current "slot" with two identical values yet.
 *
 * Approach:
 * 1. Initialize a pointer 'i' to 0, representing the index where the next valid element will be stored.
 * 2. Iterate through every element 'n' in the input array.
 * 3. For each element, check if it's one of the first two elements (i < 2) or if it's 
 *    strictly greater than the element placed two positions back (n > nums[i - 2]).
 * 4. If either condition is true, write 'n' at nums[i] and increment 'i'.
 * 5. Return 'i' as the new length.
 *
 * Time Complexity: O(n), where n is the length of the array, as we perform a single pass.
 * Space Complexity: O(1), as we modify the array in-place without extra data structures.
 *
 * Edge Cases:
 * - Array length <= 2: The loop handles this naturally; the length remains unchanged.
 * - All elements are the same: Only the first two will be kept.
 * - No duplicates: The array remains unchanged.
 *
 * Dry Run:
 * Input: nums = [1, 1, 1, 2, 2, 3]
 * - n=1, i=0: i < 2 is true. nums[0]=1, i=1.
 * - n=1, i=1: i < 2 is true. nums[1]=1, i=2.
 * - n=1, i=2: i < 2 is false, 1 > nums[0] (1 > 1) is false. Skip.
 * - n=2, i=2: i < 2 is false, 2 > nums[0] (2 > 1) is true. nums[2]=2, i=3.
 * - n=2, i=3: i < 2 is false, 2 > nums[1] (2 > 1) is true. nums[3]=2, i=4.
 * - n=3, i=4: i < 2 is false, 3 > nums[2] (3 > 2) is true. nums[4]=3, i=5.
 * Result: [1, 1, 2, 2, 3], returns 5.
 *
 * Correctness Check:
 * The logic correctly handles the "at most twice" constraint by looking back 2 steps 
 * in the modified portion of the array.
 */
class RemoveDuplicateElement80 {
    public int removeDuplicates(int[] nums) {
        // 'i' is the write pointer for the modified array.
        int i = 0;
        
        for (int n : nums) {
            
            // Keep at most two duplicates by comparing against the value 2 slots back.
            // If i < 2, we haven't even filled two slots yet, so any element is valid.
            // If n > nums[i - 2], then n is a new value or the second occurrence of a value.
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        }
        // The write pointer 'i' represents the count of elements in the modified array.
        return i;
    }

    public int removeDuplicates1(int[] nums) {
        
        
        
        
        
        
        
        
        // This alternative method implements "Remove Duplicates from Sorted Array I" (LeetCode 26),
        // where each element is allowed at most ONCE.
        int j = 1;
        
        for (int i = 1; i < nums.length; i++) {
            
            // If current element is different from the previous one, it's a unique element.
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j; 
    }
}
