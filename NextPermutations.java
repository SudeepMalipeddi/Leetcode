/*
 * Problem: LeetCode 31 - Next Permutation
 *
 * Problem Statement:
 * Implement the next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * Intuition:
 * To find the next lexicographical permutation, we need to find the rightmost element that can be increased. 
 * This occurs at the first "dip" from the right (where nums[i] < nums[i+1]). By swapping this pivot with 
 * the smallest value larger than it to its right, and then reversing the remaining suffix to be in 
 * ascending order, we ensure we have the smallest possible increment to the sequence.
 *
 * Approach:
 * 1. Find the rightmost index 'ind1' such that nums[ind1] < nums[ind1 + 1]. This is the pivot.
 * 2. If no such index exists, the array is in descending order (last permutation); reverse the entire array.
 * 3. If 'ind1' exists, find the rightmost index 'ind2' such that nums[ind2] > nums[ind1].
 * 4. Swap nums[ind1] and nums[ind2].
 * 5. Reverse the subarray starting from ind1 + 1 to the end of the array.
 *
 * Time Complexity: O(N) where N is the length of the array. We perform at most three linear passes.
 * Space Complexity: O(1) as the transformation is performed in-place.
 *
 * Edge Cases:
 * - Array is strictly decreasing: The whole array is reversed to become strictly increasing.
 * - Array has duplicate elements: Handled correctly by the strict inequality checks.
 * - Array length is 1: The loops will not execute, and the array remains unchanged.
 *
 * Dry Run:
 * Input: [1, 2, 3]
 * 1. Find pivot: nums[1] (2) < nums[2] (3), so ind1 = 1.
 * 2. Find successor: nums[2] (3) > nums[1] (2), so ind2 = 2.
 * 3. Swap ind1 and ind2: [1, 3, 2].
 * 4. Reverse suffix from index 2: [1, 3, 2].
 * Result: [1, 3, 2].
 *
 * Correctness Check:
 * The solution correctly implements the standard algorithm. Note: The class name 'nextpermutatuions' 
 * is misspelled, but per instructions, it has not been modified.
 */
/*
Problem Statement:
- Transform array into lexicographically next permutation in-place.

Intuition:
- Find pivot where ascending order from right breaks, swap with just-larger suffix value, then reverse suffix.

Approach:
- Scan from right for ind1, find ind2 > nums[ind1], swap, reverse tail; if no pivot, reverse all.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- Descending input becomes smallest permutation after full reverse.

Dry Run:
- [1,2,3] -> pivot 2, swap with 3, reverse suffix => [1,3,2].
*/
class nextpermutatuions {
    public void nextPermutation(int[] nums) {
        int ind1=-1; // Tracks the pivot: the first element from the right that is smaller than its successor.
        int ind2=-1; // Tracks the successor: the smallest element to the right of the pivot that is larger than it.
        
        // Find the rightmost pivot where nums[i] < nums[i+1].
        for(int i=nums.length-2;i>=0;i--){
            
            if(nums[i]<nums[i+1]){
                ind1=i; // Found the point where the lexicographical order can be increased.
                break;
            }
        }
        
        if(ind1==-1){
            // If no pivot is found, the array is in descending order (e.g., [3, 2, 1]).
            // The next permutation is the first one (sorted ascending).
            reverse(nums,0);
        }
        
        else{
            
            for(int i=nums.length-1;i>=0;i--){
                
                // Find the rightmost element larger than the pivot.
                // Since the suffix is in descending order, this is the smallest element > nums[ind1].
                if(nums[i]>nums[ind1]){
                    ind2=i;
                    break;
                }
            }

            swap(nums,ind1,ind2);
            
            // After swapping pivot, reverse suffix to get the smallest higher order.
            // The suffix is guaranteed to be in descending order, so reversing makes it ascending.
            reverse(nums,ind1+1);
        }
    }
    void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    void reverse(int[] nums,int start){
        int i=start;
        int j=nums.length-1;
        
        // Standard two-pointer approach to reverse the subarray in-place.
        while(i<j){
            swap(nums,i,j);
            i++;
            j--;
        }
    }
}
