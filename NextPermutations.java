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
        int ind1=-1;
        int ind2=-1;
        
        // Find the rightmost pivot where nums[i] < nums[i+1].
        for(int i=nums.length-2;i>=0;i--){
            
            if(nums[i]<nums[i+1]){
                ind1=i;
                break;
            }
        }
        
        if(ind1==-1){
            reverse(nums,0);
        }
        
        else{
            
            for(int i=nums.length-1;i>=0;i--){
                
                if(nums[i]>nums[ind1]){
                    ind2=i;
                    break;
                }
            }

            swap(nums,ind1,ind2);
            
            // After swapping pivot, reverse suffix to get the smallest higher order.
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
        
        while(i<j){
            swap(nums,i,j);
            i++;
            j--;
        }
    }
}
