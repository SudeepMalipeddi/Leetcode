/*
Problem Statement:
- Remove the nth node from end of singly linked list.

Intuition:
- Keep fast pointer n steps ahead so slow lands before target when fast reaches tail.

Approach:
- Advance fast by n, handle head-removal case, then move both until fast.next is null.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- n equal to list length removes head.

Dry Run:
- 1->2->3->4->5, n=2: slow stops at 3, remove 4.
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

public class RemoveNthNodeFromEndofList19 {
    public ListNode removeNode(ListNode head, int n) {
        ListNode fast = head, slow = head;
        
        // Create a gap of n nodes between fast and slow pointers.
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        
        if (fast == null) {
            return head.next;
        }
        
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        return head;
    }
}
