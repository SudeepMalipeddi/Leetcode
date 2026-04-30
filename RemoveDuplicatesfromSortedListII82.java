/*
Problem Statement:
- Delete all nodes that have duplicate numbers, leaving only distinct values.

Intuition:
- Dummy head plus slow/fast pointers helps remove duplicate blocks at list start or middle.

Approach:
- Advance fast over equal-value block; if slow.next != fast, skip whole block, else move both one step.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- All-duplicate list returns null.

Dry Run:
- 1->2->3->3->4->4->5 becomes 1->2->5.
*/
public class RemoveDuplicatesfromSortedListII82 {
    public ListNode deleteDuplicates(ListNode head){
        ListNode dummy = new ListNode(0);
        ListNode fast = head, slow = dummy;
        slow.next = fast;

        
        while(fast != null){
            
            while(fast.next != null && fast.val == fast.next.val){
                fast = fast.next;
            }
            
            // fast advanced over duplicates, so unlink the entire duplicate block.
            if(slow.next != fast){
                slow.next = fast.next;
                fast = slow.next;
            }
            else{
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummy.next;
    }
}
