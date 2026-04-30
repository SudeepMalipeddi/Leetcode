/*
 * Problem: 2487. Remove Nodes From Linked List
 *
 * Problem Statement:
 * Remove every node from a linked list that has a node with a strictly greater value 
 * anywhere to its right side. Return the head of the modified linked list.
 *
 * Intuition:
 * A node is kept only if it is a "suffix maximum"—meaning no value to its right is larger.
 * This suggests we need to know the maximum value encountered from the right side.
 * We can achieve this by either using a Monotonic Stack (to track and remove "dominated" nodes)
 * or by reversing the list to process it from right-to-left.
 *
 * Approach:
 * 1. Monotonic Stack: Traverse the list; if the current node is larger than the stack top, 
 *    the stack top is "dominated" and should be removed.
 * 2. Reverse and Filter: Reverse the list so the rightmost nodes come first. Traverse and 
 *    keep only nodes that are greater than or equal to the maximum value seen so far. 
 *    Reverse the result back to the original relative order.
 *
 * Time Complexity: O(N) where N is the number of nodes. Each node is visited a constant 
 * number of times (e.g., pushed/popped once or reversed twice).
 * Space Complexity: O(N) for the stack-based approach; O(1) extra space for the 
 * reversal-based approach (excluding the nodes themselves).
 *
 * Edge Cases:
 * - Single node: Always kept as there is nothing to its right.
 * - Strictly increasing list: Only the last node remains.
 * - Strictly decreasing list: All nodes remain.
 *
 * Dry Run:
 * Input: 5 -> 2 -> 13 -> 3 -> 8
 * 1. Reverse: 8 -> 3 -> 13 -> 2 -> 5
 * 2. Filter (keep if >= current max): 
 *    - 8 (max=8, keep)
 *    - 3 (3 < 8, skip)
 *    - 13 (13 > 8, max=13, keep)
 *    - 2 (2 < 13, skip)
 *    - 5 (5 < 13, skip)
 * 3. Resulting Reversed: 13 -> 8
 * 4. Reverse back: 13 -> 8
 *
 * Correctness Check:
 * - removeNodes (Stack): Contains a logic bug. It only pushes a node if it is greater 
 *   than the stack top or the stack is empty. It should push every node after 
 *   clearing smaller ones to ensure nodes like '8' in '13 -> 8' are preserved.
 * - removeNodes2 & removeNodes3: Correctly implement the reverse-and-filter logic.
 */
import java.util.*;

class ListNode {
    int data;
    ListNode next;

    ListNode() {
    };

    ListNode(int data) {
        this.data = data;
        this.next = null;
    }

}

public class RemoveNodesFromLinkedList2487 {
    
    public ListNode removeNodes(ListNode head) {
        // Use a stack to maintain nodes in a non-increasing order.
        Stack<ListNode> st = new Stack<>();
        ListNode ptr = head;
        
        while (ptr != null) {
            
            // BUG: This condition prevents smaller nodes from being added to the stack
            // even if they don't have a larger node to their right yet.
            if (st.isEmpty() || ptr.data > st.peek().data) {
                
                // Pop smaller left nodes dominated by current larger right value.
                while (!st.isEmpty() && st.peek().data < ptr.data) {
                    st.pop();
                }
                st.push(ptr);
            }
            ptr = ptr.next;
        }
        ListNode newHead = null;
        
        // Reconstruct the list by popping from the stack (reverses the stack order).
        while (!st.isEmpty()) {
            ListNode temp = st.pop();
            temp.next = newHead;
            newHead = temp;
        }
        return newHead;
    }

    
    public ListNode removeNodes2(ListNode head) {
        
        if (head == null) {
            return head;
        }
        // Step 1: Reverse the list to process from right to left.
        ListNode revhead = null;
        revhead = helper(head);
        ListNode ptr = revhead;
        
        // Step 2: Traverse the reversed list and remove nodes smaller than the current max.
        // In the reversed list, 'ptr' acts as the current maximum seen from the original right.
        while (ptr.next != null) {
            
            if (ptr.next.data < ptr.data) {
                // Skip the next node because it's smaller than a node to its right (ptr).
                ptr.next = ptr.next.next;
            } else {
                // Move forward if the next node is a new maximum or equal.
                ptr = ptr.next;
            }
        }
        // Step 3: Reverse back to restore original order.
        ListNode nom = helper(revhead);
        return nom;
    }

    // Standard iterative linked list reversal.
    public ListNode helper(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    
    public ListNode removeNodes3(ListNode head) {
        
        if (head.next == null) {
            return head;
        }
        ListNode prevNode = head;
        ListNode currNode = head.next;

        
        // First Pass: Reverse the entire list.
        while (currNode != null) {
            ListNode nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }

        head.next = null; // Original head is now the tail.
        head = prevNode; // prevNode is now the head of the reversed list.

        prevNode = head;
        currNode = head.next;

        
        // Second Pass: Filter and Reverse back simultaneously.
        while (currNode != null) {
            
            if (currNode.data < prevNode.data) {
                // If current node is smaller than the max seen so far (prevNode), skip it.
                currNode = currNode.next;
            } else {
                // If current node is >= max, it stays. Reverse its pointer back to original direction.
                ListNode nextNode = currNode.next;
                currNode.next = prevNode;
                prevNode = currNode;
                currNode = nextNode;
            }
        }
        head.next = null; // The head of the reversed list becomes the tail of the final list.
        head = prevNode; // The last valid node processed becomes the final head.
        return head;
    }
}
