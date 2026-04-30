/*
 * Problem Reference: LeetCode 2095 - Delete the Middle Node of a Linked List
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Use slow/fast pointers to find middle predecessor, then unlink middle.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
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
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode ptr) {
        this.val = val;
        this.next = ptr;
    }
}

public class DeletetheMiddleNodeofaLinkedList2095 {
    public ListNode deleteMiddle(ListNode head) {
        // Important guard: this branch handles a boundary or constraint-critical condition.
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head, prev = null;
        // Iterate through the active search space while maintaining the intended invariant.
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        DeletetheMiddleNodeofaLinkedList2095 obj = new DeletetheMiddleNodeofaLinkedList2095();
        obj.deleteMiddle(head);

        ListNode ptr = head;
        while (ptr != null) {
            System.out.print(ptr.val + " ");
            ptr = ptr.next;
        }
    }
}
