/*
 * Problem Reference: LeetCode 3217 - Delete Nodes From Linked List Present in Array
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Store blocked values in hash set, then filter linked list.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n+m)
 *
 * Space Complexity:
 * O(m)
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
import java.util.HashSet;

public class DeleteNodesFromLinkedListPresentinArray3217 {
    public ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> set = new HashSet<Integer>();
        // Iterate through the active search space while maintaining the intended invariant.
        for (int x : nums) {
            set.add(x);
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode ptr = head, prev = dummy;

        while (ptr != null) {
            ListNode temp = ptr.next;
            if (set.contains(ptr.val)) {
                prev.next = temp;
            } else {
                prev = ptr;
            }
            ptr = ptr.next;
        }
        return dummy.next;
    }
}
