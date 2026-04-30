/*
Problem Statement:
- Delete all consecutive node sequences summing to zero until none remain.

Intuition:
- Equal prefix sums indicate zero-sum segment between occurrences.

Approach:
- First pass maps prefix sum to last node; second pass jumps next pointers to remove zero-sum spans.

Time Complexity:
- O(n).

Space Complexity:
- O(n) hashmap.

Edge Cases:
- Handles zero-sum segments touching head via dummy node.

Dry Run:
- 1->2->-3->3->1 removes first three nodes, leaving 3->1.
*/
import java.util.*;

public class RemoveZeroSumConsecutiveNodesfromLinkedList1171 {
    public ListNode removeZeroSumSublists(ListNode head) {
        
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        
        for (ListNode i = dummy; i != null; i = i.next) {
            sum += i.val;
            // Keep last node for each prefix sum to remove longest zero-sum span.
            map.put(sum, i);
        }

        sum = 0;
        
        for (ListNode i = dummy; i != null; i = i.next) {
            sum += i.val;
            // Jump over nodes whose cumulative sum cancels to zero.
            i.next = map.get(sum).next;
        }
        return dummy.next;
    }
}
