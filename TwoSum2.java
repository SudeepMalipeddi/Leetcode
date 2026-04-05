public class twosum2 {
    public int[] twoSum(int[] nums, int target) {
        // We have to return the indices+1 of the elements that add up to the target
        // We will be using two pointers like the solution to the question of Valid Palindrome question
        int start = 0;
        int end = nums.length - 1;
        // Keeping a loop to check if the sum of the elements in start and end indices are equal to target or not
        while(start <= end){
            int sum = nums[start] + nums[end];
            if(sum < target){
                start++;
            }
            else if(sum > target){
                end--;
            }
            else{
                return new int[] {start+1, end+1};
            }
        }

        return new int[] {start+1, end+1};
    }
}
