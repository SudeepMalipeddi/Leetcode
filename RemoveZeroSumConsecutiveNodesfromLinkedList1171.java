/*
 * Problem: 1171. Remove Zero Sum Consecutive Nodes from Linked List
 *
 * Problem Statement:
 * Given the head of a linked list, repeatedly delete consecutive sequences of nodes 
 * that sum to 0 until no such sequences remain. Return the head of the final linked list.
 *
 * Intuition:
 * The core insight relies on Prefix Sums. If the cumulative sum at node A is equal to 
 * the cumulative sum at node B (where B appears after A), then the sum of all nodes 
 * between A and B must be exactly zero. By mapping each prefix sum to the last node 
 * that produces it, we can effectively "skip" the zero-sum segments.
 *
 * Approach:
 * 1. Use a dummy node pointing to the head to handle cases where the entire list 
 *    or the starting segment sums to zero.
 * 2. First Pass: Traverse the list to calculate prefix sums. Store each sum in a 
 *    HashMap where the key is the sum and the value is the node. If a sum repeats, 
 *    the map will naturally store the reference to the *last* node seen with that sum.
 * 3. Second Pass: Traverse the list again. For each node, retrieve the "last" node 
 *    associated with its current prefix sum from the map. Set the current node's 
 *    'next' pointer to that last node's 'next'. This deletes all intermediate nodes.
 *
 * Time Complexity: O(n) where n is the number of nodes. We perform two linear passes.
 * Space Complexity: O(n) to store the prefix sums in the HashMap.
 *
 * Edge Cases:
 * - Empty list: Returns null.
 * - Entire list sums to zero: Dummy node's next will eventually point to null.
 * - No zero-sum sublists: The map logic will point nodes to their original next.
 *
 * Dry Run:
 * Input: 1 -> 2 -> -3 -> 3 -> 1
 * 1. Dummy(0) -> 1 -> 2 -> -3 -> 3 -> 1
 * 2. Pass 1 (Sums): 0(dummy), 1, 3, 0, 3, 4
 *    Map: {0: node(-3), 1: node(1_at_end), 3: node(3), 4: node(1_at_end)}
 *    Wait, let's trace map updates: 
 *    {0: dummy} -> {0: dummy, 1: n1} -> {0: dummy, 1: n1, 3: n2} -> {0: n3, 1: n1, 3: n2} -> {0: n3, 1: n1, 3: n4} -> {0: n3, 1: n1, 3: n4, 4: n5}
 * 3. Pass 2:
 *    - At dummy (sum 0): dummy.next = map.get(0).next (node 3). List: 0 -> 3 -> 1
 *    - At node 3 (sum 3): node3.next = map.get(3).next (node 1). List: 0 -> 3 -> 1
 *    - At node 1 (sum 4): node1.next = map.get(4).next (null).
 * Result: 3 -> 1
 *
 * Correctness Check:
 * The logic is sound. The two-pass approach correctly identifies the furthest 
 * occurrence of a prefix sum, ensuring that nested or overlapping zero-sum 
 * sublists are handled by jumping to the latest possible point for a given sum.
 */
import java.util.*;

public class RemoveZeroSumConsecutiveNodesfromLinkedList1171 {
    public ListNode removeZeroSumSublists(ListNode head) {
        
        if (head == null)
            return null;
        // Dummy node handles cases where the head itself is removed.
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // Map stores the prefix sum and the last node that reached that sum.
        Map<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        
        for (ListNode i = dummy; i != null; i = i.next) {
            sum += i.val;
            // Keep last node for each prefix sum to remove longest zero-sum span.
            // If a sum repeats, the previous entry is overwritten by the later node.
            map.put(sum, i);
        }

        sum = 0;
        
        for (ListNode i = dummy; i != null; i = i.next) {
            sum += i.val;
            // Jump over nodes whose cumulative sum cancels to zero.
            // By pointing to map.get(sum).next, we bypass all nodes between 
            // the current node and the last node that had this same prefix sum.
            i.next = map.get(sum).next;
        }
        return dummy.next;
    }
}
