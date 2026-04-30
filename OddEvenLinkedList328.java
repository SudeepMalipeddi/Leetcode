/*
Problem Statement:
- Rearrange linked list so odd-index nodes come first, then even-index nodes.

Intuition:
- Maintain separate odd and even chains and stitch them at the end.

Approach:
- Advance odd and even pointers alternately; finally connect odd tail to even head.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- Lists of size 0 or 1 are unchanged.

Dry Run:
- 1->2->3->4->5 becomes 1->3->5->2->4.
*/
public class OddEvenLinkedList328 {
    public ListNode oddevenList(ListNode head) {
        
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head, even = head.next, evenhead = head.next;

        
        // Relink odd and even chains in-place while preserving relative order.
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenhead;

        return head;
    }
}
