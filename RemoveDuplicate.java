// 26. Remove Duplicates from Sorted Array

class removeDuplicate{
    public int removeDuplicates(int[] nums){
        int j=1;
        for(int i = 1; i<nums.length; i++ ){
            if( nums[i] != nums[i-1]){
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}

// Time complexity: O(n)
// Space complexity: O(1)
