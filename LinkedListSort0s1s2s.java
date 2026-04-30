/*
 * Problem: Sort a linked list of 0s, 1s and 2s
 *
 * Problem Statement:
 * Given a linked list where each node contains a value of 0, 1, or 2, sort the list 
 * so that all 0s come first, followed by all 1s, and finally all 2s.
 *
 * Intuition:
 * Since the set of possible values is extremely small and fixed {0, 1, 2}, we can 
 * solve this without a general-purpose O(N log N) sort. We can either count the 
 * occurrences (Counting Sort logic) or physically move the nodes into three 
 * separate buckets and then link those buckets together.
 *
 * Approach:
 * 1. sortList (Counting): Perform a first pass to count how many 0s, 1s, and 2s exist. 
 *    Perform a second pass to overwrite the 'data' field of each node in sorted order.
 * 2. sortList2 (Partitioning): Create three dummy nodes to represent the heads of 
 *    three sub-lists (zeros, ones, and twos). Iterate through the original list, 
 *    appending each node to its corresponding sub-list. Finally, link the sub-lists.
 *
 * Time Complexity: O(N)
 * Both approaches require linear passes through the list. Counting takes two passes, 
 * while partitioning takes one.
 *
 * Space Complexity: O(1)
 * We only use a fixed number of pointers or integer counters, regardless of the 
 * input size. No extra nodes are created (except for 3 dummy nodes in sortList2).
 *
 * Edge Cases:
 * - Empty list (head is null).
 * - List with only one node.
 * - List containing only one type of value (e.g., all 2s).
 * - List missing one of the values (e.g., only 0s and 2s).
 *
 * Dry Run (sortList):
 * Input: 2 -> 1 -> 0
 * 1. Pass 1: count0=1, count1=1, count2=1.
 * 2. Pass 2: 
 *    - Node 1: count0 > 0? Yes. data=0, count0=0.
 *    - Node 2: count0 > 0? No. count1 > 0? Yes. data=1, count1=0.
 *    - Node 3: count0 > 0? No. count1 > 0? No. data=2, count2=0.
 * Result: 0 -> 1 -> 2
 *
 * Correctness Check:
 * - sortList is correct and robust.
 * - BUG ALERT in sortList2: The 'while' loop does not update the 'current' pointer 
 *   (missing current = current.next). This will result in an infinite loop.
 * - sortList2 stitching logic: Correctly handles cases where the 'ones' list is 
 *   empty by checking if oneHead.next is null.
 */

class ListNode {
    int data;
    ListNode next;

    ListNode() {
    }

    ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListSort0s1s2s {
    /**
     * Approach 1: Counting Sort (Data Replacement)
     * This is simpler but modifies the data within nodes rather than rearranging pointers.
     */
    public ListNode sortList(ListNode head) {
        ListNode ptr = head;
        int count0 = 0, count1 = 0, count2 = 0;
        
        // First pass: count frequencies of each allowed value.
        while (ptr != null) {
            if (ptr.data == 0) {
                count0++;
            } else if (ptr.data == 1) {
                count1++;
            } else {
                count2++;
            }
            ptr = ptr.next;
        }
        
        ptr = head;
        // Second pass: overwrite node data in sorted bucket order.
        // We fill the list with 0s first, then 1s, then 2s based on our counts.
        while (ptr != null) {
            if (count0 > 0) {
                ptr.data = 0;
                count0--;
            } else if (count1 > 0) {
                ptr.data = 1;
                count1--;
            } else {
                ptr.data = 2;
                count2--;
            }
            ptr = ptr.next;
        }
        return head;
    }

    /**
     * Approach 2: Three-Way Partitioning (Pointer Manipulation)
     * This rearranges the actual nodes, which is often preferred if node data is large 
     * or if the nodes themselves carry identity.
     */
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Create dummy nodes to simplify list building. 
        // These act as placeholders so we don't have to check for null on every insertion.
        ListNode zeroHead = new ListNode(0), zero = zeroHead;
        ListNode oneHead = new ListNode(0), one = oneHead;
        ListNode twoHead = new ListNode(0), two = twoHead;
        
        ListNode current = head;
        
        // Partition nodes by value into three chains using tail pointers.
        // NOTE: This loop is currently broken as it lacks 'current = current.next'.
        while (current != null) {
            if (current.data == 0) {
                zero.next = current;
                zero = zero.next;
            } else if (current.data == 1) {
                one.next = current;
                one = one.next;
            } else {
                two.next = current;
                two = two.next;
            }
            // Logic Error: current is never advanced, leading to an infinite loop.
        }
        
        // Stitching logic:
        // 1. Connect the end of the 0-list to the start of the 1-list.
        //    If the 1-list is empty, connect the 0-list directly to the 2-list.
        zero.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        
        // 2. Connect the end of the 1-list to the start of the 2-list.
        one.next = twoHead.next;
        
        // 3. Crucial: Terminate the 2-list to avoid cycles in the linked list.
        two.next = null;
        
        // The real head is the first node after the zero dummy head.
        return zeroHead.next;
    }

}
