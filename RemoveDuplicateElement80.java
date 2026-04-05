class RemoveDuplicateElement80 {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        }
        return i;
    }

    public int removeDuplicates1(int[] nums) {
        // int j=1;
        // for(int i = 1; i<nums.length; i++ ){
        // if( nums[i] != nums[i-1]){
        // nums[j] = nums[i];
        // j++;
        // }
        // }
        // return j;
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j; // returning size of the array
    }
}