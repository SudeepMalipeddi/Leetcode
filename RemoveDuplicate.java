/*
 * Problem: 26. Remove Duplicates from Sorted Array
 *
 * Problem Statement:
 * Given an integer array 'nums' sorted in non-decreasing order, remove the duplicates in-place 
 * such that each unique element appears only once. The relative order of the elements 
 * should be kept the same. Return the number of unique elements.
 *
 * Intuition:
 * In a sorted array, all duplicate elements are grouped together. This allows us to 
 * identify a unique element simply by comparing it to its predecessor. By using two 
 * pointers, we can "write" unique values to the front of the array as we "read" them.
 *
 * Approach:
 * 1. Use a pointer 'j' (slow pointer) to keep track of the index where the next unique 
 *    element should be stored. Start 'j' at 1 because the first element is always unique.
 * 2. Use a loop with pointer 'i' (fast pointer) to scan the array starting from index 1.
 * 3. Whenever nums[i] is not equal to nums[i-1], we have encountered a new unique value.
 * 4. Copy this unique value to nums[j] and increment 'j'.
 * 5. After the loop, 'j' represents the count of unique elements.
 *
 * Time Complexity: O(n), where n is the length of the array. We perform a single pass.
 * Space Complexity: O(1), as the modification is done in-place without extra memory.
 *
 * Edge Cases:
 * - Array with no duplicates: 'j' will increment every time, returning the original length.
 * - Array with all identical elements: The condition nums[i] != nums[i-1] will never be 
 *   true, returning 1.
 * - Empty array: The code will return 1, which is technically incorrect (should be 0).
 *
 * Dry Run:
 * nums = [1, 1, 2]
 * - j = 1
 * - i = 1: nums[1] (1) == nums[0] (1). No action.
 * - i = 2: nums[2] (2) != nums[1] (1). nums[j] = nums[2] -> nums[1] = 2. j becomes 2.
 * - Loop ends. Return j = 2. Array is now [1, 2, 2].
 *
 * Correctness Check:
 * The solution is correct for non-empty arrays. Note: For an empty array input, 
 * the code returns 1 instead of 0 because 'j' is initialized to 1 and the loop 
 * condition (1 < 0) is immediately false.
 */
class removeDuplicate{
    public int removeDuplicates(int[] nums){
        // 'j' serves as the write-pointer, tracking where the next unique element should be placed.
        int j=1;
        
        // 'i' serves as the read-pointer, scanning the array for changes in value.
        for(int i = 1; i<nums.length; i++ ){
            
            // Copy only new values to the compacted prefix.
            // Since the array is sorted, a change in value indicates we've found a new unique element.
            if( nums[i] != nums[i-1]){
                nums[j] = nums[i]; // Overwrite the element at the write-pointer with the new unique value.
                j++; // Advance the write-pointer to the next available slot.
            }
        }
        // 'j' represents the total number of unique elements found.
        return j;
    }
}
