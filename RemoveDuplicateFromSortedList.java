/*
Problem Statement:
- Remove duplicate nodes from sorted linked list, leaving one occurrence each.

Intuition:
- In sorted list, duplicates are consecutive; compare current with next.

Approach:
- Traverse once, bypass next node when duplicate value appears.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- Null list returns null unchanged.

Dry Run:
- 1->1->2->3->3 becomes 1->2->3.
*/
public class RemoveDuplicateFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ptr = head;
        
        while (ptr != null) {
            
            if (ptr.next == null)
                break;
            
            // Skip consecutive duplicate node in sorted order.
            if (ptr.val == ptr.next.val) {
                ptr.next = ptr.next.next;
            } else {
                ptr = ptr.next;
            }
        }
        return head;
    }
}
