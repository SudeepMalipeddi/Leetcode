/*
Problem Statement:
- Remove all occurrences of val from array in-place and return count of kept elements.

Intuition:
- Overwrite unwanted elements by compacting survivors to front.

Approach:
- Scan array and copy nums[i] to nums[index] only when nums[i] != val.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- If val absent, length stays original.

Dry Run:
- [3,2,2,3], val=3 -> front becomes [2,2], return 2.
*/
public class RemoveElement {
    public int removeelement(int[] nums, int val) {
        int index = 0;
        
        for (int i = 0; i < nums.length; i++) {
            
            // Compact non-target values to the front segment.
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
