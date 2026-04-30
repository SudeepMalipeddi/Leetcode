/*
Problem Statement:
- For each value in nums1, find first greater element to its right in nums2.

Intuition:
- A decreasing stack lets us resolve next greater values when a larger number appears.

Approach:
- Traverse nums2, pop smaller stack elements and map them to current value; answer nums1 via map lookups.

Time Complexity:
- O(n+m), each nums2 element is pushed/popped once.

Space Complexity:
- O(n) stack + hashmap.

Edge Cases:
- Values with no greater element map to -1.

Dry Run:
- nums2=[1,3,2]: when 3 arrives, 1 pops and maps to 3.
*/
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElementOne496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        
        for (int i = 0; i < nums2.length; i++) {
            
            // Current value is the next greater for all smaller stacked values.
            while (!st.isEmpty() && st.peek() < nums2[i]) {
                map.put(st.pop(), nums2[i]);
            }
            st.push(nums2[i]);
        }

        
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }

        return nums1;
    }
}
