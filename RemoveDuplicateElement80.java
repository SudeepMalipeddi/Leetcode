/*
Problem Statement:
- From sorted array, keep each value at most twice and return resulting length.

Intuition:
- A new value is valid if fewer than two items written or current value exceeds value two positions back.

Approach:
- Primary method writes n when i<2 or n>nums[i-2].
- Improvement idea: removeDuplicates1 enforces at most one copy, so it is not an alternative for this exact problem.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- Arrays shorter than 2 are unchanged.

Dry Run:
- [1,1,1,2,2,3] -> [1,1,2,2,3], length 5.
*/
class RemoveDuplicateElement80 {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        
        for (int n : nums) {
            
            // Keep at most two duplicates by comparing against the value 2 slots back.
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        }
        return i;
    }

    public int removeDuplicates1(int[] nums) {
        
        
        
        
        
        
        
        
        int j = 1;
        
        for (int i = 1; i < nums.length; i++) {
            
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j; 
    }
}
