/*
Problem Statement:
- Remove all linked-list nodes whose value equals val.

Intuition:
- Dummy node simplifies deletions including head removals.

Approach:
- Iterative variants bypass matching nodes; recursive variant filters tail first then current node.

Time Complexity:
- O(n).

Space Complexity:
- O(1) iterative, O(n) recursion stack for recursive method.

Edge Cases:
- All nodes removed returns null.

Dry Run:
- 1->2->6->3, val=6 -> 1->2->3.
*/
class RemoveLinkedListElements203 {
    
    public ListNode removeElements(ListNode head, int val) {
        
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode curr = dummy;
        
        while (curr.next != null) {
            
            // Bypass node when it matches removal value.
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    
    public ListNode removeElements1(ListNode head, int val) {
        
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;

        
        while (cur != null) {
            
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    
    public ListNode removeElements2(ListNode head, int val) {
        
        if (head == null) {
            return null;
        }
        ListNode rightSideHead = removeElements(head.next, val);
        
        
        if (head.val == val) {
            return rightSideHead;
        }
        head.next = rightSideHead;
        return head;
    }

}
