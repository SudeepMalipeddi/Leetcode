/*
 * Problem: 82. Remove Duplicates from Sorted List II
 *
 * Problem Statement:
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, 
 * leaving only distinct numbers from the original list. Return the linked list sorted.
 *
 * Intuition:
 * Because the list is sorted, all duplicate values are guaranteed to be adjacent. 
 * To remove the entire group of duplicates (including the first occurrence), we need 
 * a reference to the node immediately preceding the duplicate block. A dummy node 
 * is used to handle cases where the head itself is part of a duplicate sequence.
 *
 * Approach:
 * 1. Initialize a 'dummy' node pointing to head and a 'slow' pointer at dummy.
 * 2. Use a 'fast' pointer to traverse the list starting from head.
 * 3. For each node, check if it is the start of a duplicate sequence by comparing it with its next neighbor.
 * 4. If duplicates exist, move 'fast' to the last node of that duplicate sequence.
 * 5. Check if 'slow.next' is still 'fast'. If it is, no duplicates were found for that value; move 'slow' forward.
 * 6. If 'slow.next' is not 'fast', it means 'fast' skipped duplicates. Link 'slow.next' to 'fast.next' to discard the block.
 *
 * Time Complexity: O(N) where N is the number of nodes in the list. Each node is visited at most twice.
 * Space Complexity: O(1) as we only use a constant amount of extra space for pointers.
 *
 * Edge Cases:
 * - Empty list: returns null.
 * - Single node: returns the node.
 * - All nodes are duplicates: returns null.
 * - Duplicates at the very beginning or very end of the list.
 *
 * Dry Run:
 * Input: 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
 * 1. slow=dummy, fast=1. fast.next(2) != 1. slow.next == fast, so slow=1, fast=2.
 * 2. slow=1, fast=2. fast.next(3) != 2. slow.next == fast, so slow=2, fast=3.
 * 3. slow=2, fast=3. fast.next(3) == 3. Move fast to second 3. slow.next(3) != fast(second 3).
 *    Set slow.next = fast.next (4). fast = 4.
 * 4. slow=2, fast=4. fast.next(4) == 4. Move fast to second 4. slow.next(4) != fast(second 4).
 *    Set slow.next = fast.next (5). fast = 5.
 * 5. slow=2, fast=5. fast.next == null. slow.next == fast, so slow=5, fast=null.
 * Result: 1 -> 2 -> 5.
 *
 * Correctness Check:
 * The logic correctly identifies and bypasses contiguous blocks of identical values. 
 * Using slow.next != fast is a robust way to detect if the inner while loop executed.
 */
public class RemoveDuplicatesfromSortedListII82 {
    public ListNode deleteDuplicates(ListNode head){
        // Dummy node handles the case where the head itself needs to be removed.
        ListNode dummy = new ListNode(0);
        ListNode fast = head, slow = dummy;
        slow.next = fast;

        
        while(fast != null){
            
            // Move fast to the last node of a sequence of identical values.
            while(fast.next != null && fast.val == fast.next.val){
                fast = fast.next;
            }
            
            // If slow.next is not fast, the inner while loop ran, meaning duplicates were found.
            // We skip the entire duplicate block by pointing slow.next to the node after the block.
            if(slow.next != fast){
                slow.next = fast.next;
                fast = slow.next;
            }
            else{
                // No duplicates were found for the current value at fast.
                // It is safe to move the slow pointer forward.
                slow = slow.next;
                fast = fast.next;
            }
        }
        // Return the head of the modified list, which is dummy.next.
        return dummy.next;
    }
}
