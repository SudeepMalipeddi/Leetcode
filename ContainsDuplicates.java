// This is the Solution to the Question 217 in Leetcode
import java.util.HashSet;

class containsDuplicates{
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> numbers = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            if(numbers.contains(nums[i])) return true;
            else numbers.add(nums[i]);
        }
        return false;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)

// Another Solution: Sort the array and check if the adjacent elements are equal
// class containsDuplicate{
//     public boolean containsDuplicate(int[] nums){
//         Arrays.sort(nums);
//         for(int i=0;i<nums.length-1;i++){
//             if(nums[i]==nums[i+1]) return true;
//         }
//         return false;
//     }
// }

// Time Complexity: O(nlogn)
// Space Complexity: O(1)