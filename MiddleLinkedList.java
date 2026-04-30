/*
 * Problem Statement: Return the middle node of a singly linked list (for even length, return the second middle).
 * Intuition: Move one pointer twice as fast as another; when the fast pointer ends, the slow pointer is at middle.
 * Approach: Use fast/slow pointers starting at head; advance slow by 1 and fast by 2 until fast cannot continue.
 * Time Complexity: O(n), single traversal.
 * Space Complexity: O(1).
 * Edge Cases handled: Single-node list; even-length list (returns second middle); null head input.
 * Dry Run: 1->2->3->4->5->6 -> slow visits 1,2,3,4 while fast visits 1,3,5,null -> answer is node 4.
 * LeetCode matching/assumption: Matches LeetCode 876 (Middle of the Linked List), second middle for even length.
 * Correctness Check: Fast reaches end in roughly half the steps, guaranteeing slow has advanced exactly to middle position.
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class MiddleLinkedList {
    static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // Fast moves two steps, slow moves one step.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = (fast.next).next;
        }
        return slow;
    }
}
