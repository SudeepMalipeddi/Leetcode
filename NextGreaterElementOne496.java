/*
 * Problem: 496. Next Greater Element I
 *
 * Problem Statement:
 * For each element in nums1, find the first element to its right in nums2 that is strictly greater.
 * Since nums1 is a subset of nums2, we find the position of the element in nums2 and look to its right.
 * If no such element exists, return -1 for that index.
 *
 * Intuition:
 * We need a way to "remember" elements that haven't found their next greater neighbor yet.
 * A Monotonic Decreasing Stack is ideal here: we keep elements in the stack as long as we see
 * smaller numbers. As soon as we see a larger number, it acts as the "next greater" for everything
 * currently on the stack that is smaller than it.
 *
 * Approach:
 * 1. Initialize a Stack to store elements from nums2 and a HashMap to store the mapping {element: next_greater}.
 * 2. Iterate through nums2:
 *    - While the stack is not empty and the current element is greater than the stack's top,
 *      the current element is the "next greater" for that top element. Pop it and store in the map.
 *    - Push the current element onto the stack.
 * 3. After processing nums2, iterate through nums1.
 * 4. For each element in nums1, look up its next greater value in the map. If not found, use -1.
 *
 * Time Complexity: O(n + m) where n is the length of nums1 and m is the length of nums2.
 * Each element in nums2 is pushed and popped from the stack exactly once.
 * Space Complexity: O(m) to store the HashMap and the Stack in the worst case.
 *
 * Edge Cases:
 * - nums2 is strictly decreasing: No element will have a next greater (all map to -1).
 * - nums1 and nums2 are the same: Standard processing applies.
 * - Single element arrays: No next greater exists.
 *
 * Dry Run:
 * nums1 = [4,1], nums2 = [1,3,4,2]
 * 1. Process 1: Stack=[1]
 * 2. Process 3: 3 > 1. Map={1:3}, Pop 1. Push 3. Stack=[3]
 * 3. Process 4: 4 > 3. Map={1:3, 3:4}, Pop 3. Push 4. Stack=[4]
 * 4. Process 2: 2 < 4. Push 2. Stack=[4, 2]
 * 5. Map results for nums1: 4 -> -1 (not in map), 1 -> 3. Result: [-1, 3].
 *
 * Correctness Check:
 * The solution is correct. It efficiently pre-calculates all "next greater" relationships in nums2
 * using a single pass and a stack, then answers queries for nums1 in O(1) each.
 */
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
        // This stack will maintain elements from nums2 in a monotonic decreasing order.
        Stack<Integer> st = new Stack<>();
        // This map will store the relationship: element -> its first greater element to the right.
        Map<Integer, Integer> map = new HashMap<>();

        
        for (int i = 0; i < nums2.length; i++) {
            
            // Current value is the next greater for all smaller stacked values.
            // We pop elements that are smaller than the current nums2[i] because we just found their "next greater".
            while (!st.isEmpty() && st.peek() < nums2[i]) {
                map.put(st.pop(), nums2[i]);
            }
            // Push the current element onto the stack to wait for its own next greater element.
            st.push(nums2[i]);
        }

        
        for (int i = 0; i < nums1.length; i++) {
            // For each element in nums1, check if we found a next greater in the map.
            // If the element isn't in the map, it means no greater element was found to its right in nums2.
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }

        return nums1;
    }
}
