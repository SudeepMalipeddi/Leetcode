/*
 * Problem: 160. Intersection of Two Linked Lists
 *
 * Problem Statement:
 * Given the heads of two singly linked lists, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 *
 * Intuition:
 * The core difficulty is that the lists may have different lengths before the intersection point.
 * By having each pointer traverse both lists (A then B, and B then A), we equalize the total 
 * distance traveled. Both pointers will cover exactly (LengthA + LengthB) distance, ensuring 
 * they arrive at the intersection point (or the end null) at the same time.
 *
 * Approach:
 * 1. Initialize two pointers at the heads of the respective lists.
 * 2. Traverse both lists node by node.
 * 3. When a pointer reaches the end of its list (null), redirect it to the head of the OTHER list.
 * 4. If an intersection exists, the pointers will meet at that node during the second pass.
 * 5. If no intersection exists, both pointers will eventually become null at the same time.
 *
 * Time Complexity: O(m + n) where m and n are the lengths of the two lists. Each pointer 
 * travels at most m + n nodes.
 * Space Complexity: O(1) as we only use two additional pointers regardless of list size.
 *
 * Edge Cases:
 * - Lists are of different lengths (handled by the switch-head logic).
 * - No intersection (pointers will both reach null simultaneously after switching).
 * - One or both lists are null (handled by the initial guard clause).
 * - Intersection occurs at the very first node.
 *
 * Dry Run:
 * List A: [1, 2, 3], List B: [4, 5, 2, 3] (Intersection at node '2')
 * Step 1: p1=1, p2=4
 * Step 2: p1=2, p2=5
 * Step 3: p1=3, p2=2
 * Step 4: p1=null, p2=3
 * Step 5: p1=4 (switched to head B), p2=null
 * Step 6: p1=5, p2=1 (switched to head A)
 * Step 7: p1=2, p2=2 -> Match found!
 *
 * Correctness Check:
 * The solution is correct. The logic ensures that even if there is no intersection, 
 * the pointers will eventually both be null at the same time (after traversing m+n nodes), 
 * terminating the loop and returning null.
 */
public class IntersectionofTwoLinkedLists160 {
    public ListNode getIntersection(ListNode h1, ListNode h2) {
        // If either list is empty, there can be no intersection point.
        if (h1 == null || h2 == null)
            return null;

        ListNode ptr1 = h1, ptr2 = h2;

        // The loop continues as long as the pointers are at different memory addresses.
        // They will eventually meet either at the intersection node or both at null.
        while (ptr1 != ptr2) {
            // Move both pointers forward by one step.
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;

            // Check if they met immediately after the increment (handles intersection or both being null).
            if (ptr1 == ptr2) {
                return ptr1;
            }

            // If ptr1 reaches the end of list A, redirect it to the start of list B.
            // This "swapping" ensures both pointers travel the same total distance.
            if (ptr1 == null)
                ptr1 = h2;

            // If ptr2 reaches the end of list B, redirect it to the start of list A.
            if (ptr2 == null)
                ptr2 = h1;
        }

        // Return the meeting point (either the intersection node or null).
        return ptr1;
    }
}
