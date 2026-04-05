/*Given a sorted array of distinct integers and a target value, 
return the index if the target is found. If not,
return the index where it would be if it were inserted in order.
You must write an algorithm with O(log n) runtime complexity. */

public class search_insert_position35 {
    public int searchInsertPosition(int[] nums, int target){
        // Using binary search to find the element
        int start = 0;
        int end = nums.length-1;
        while(start<=end){
            int mid = (start + end)/2;
            if(nums[mid]<target){
                start = mid + 1;
            }
            else if(nums[mid]>target){
                end = mid - 1;
            }
            else{
                return mid;
            }
        }
        // returning start as the element to insert 
        // because that is the place where the element should be if
        // it isn't present in the array
        return start;
    }
}
