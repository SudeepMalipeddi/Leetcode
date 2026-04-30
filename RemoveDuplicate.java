/*
Problem Statement:
- From sorted array, keep one copy of each value in-place and return new length.

Intuition:
- Two pointers: read scans all elements, write tracks next unique slot.

Approach:
- When nums[i] differs from previous value, copy to nums[j] and advance j.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- Single-element arrays return length 1.

Dry Run:
- [1,1,2,2,3] -> write unique values to front, return 3.
*/
class removeDuplicate{
    public int removeDuplicates(int[] nums){
        int j=1;
        
        for(int i = 1; i<nums.length; i++ ){
            
            // Copy only new values to the compacted prefix.
            if( nums[i] != nums[i-1]){
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}



