/*
 * Problem Reference: LeetCode 160 - Intersection of Two Linked Lists
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Two-pointer switch-head trick aligns path lengths.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(m+n)
 *
 * Space Complexity:
 * O(1)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
public class IntersectionofTwoLinkedLists160 {
    public ListNode getIntersection(ListNode h1, ListNode h2) {
        // Important guard: this branch handles a boundary or constraint-critical condition.
        if (h1 == null || h2 == null)
            return null;
        ListNode ptr1 = h1, ptr2 = h2;
        // Iterate through the active search space while maintaining the intended invariant.
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            if (ptr1 == ptr2) {
                return ptr1;
            }
            if (ptr1 == null)
                ptr1 = h2;
            if (ptr2 == null)
                ptr2 = h1;
        }
        return ptr1;
    }
}
