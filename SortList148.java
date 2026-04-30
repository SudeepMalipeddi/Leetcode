/*
 * Problem: 148. Sort List
 *
 * Problem Statement:
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * The solution should ideally achieve O(n log n) time complexity and O(log n) space complexity.
 *
 * Intuition:
 * Merge Sort is the most natural fit for sorting linked lists. Unlike arrays, linked lists 
 * do not support random access, which makes algorithms like QuickSort or HeapSort less 
 * efficient to implement. Merge Sort's divide-and-conquer strategy works by recursively 
 * splitting the list into halves and then merging them back in sorted order.
 *
 * Approach:
 * 1. Base Case: If the list is empty or has only one node, it is already sorted.
 * 2. Split: Use the "Slow and Fast Pointer" (Tortoise and Hare) technique to find the middle.
 * 3. Disconnect: Break the link between the first and second halves.
 * 4. Recurse: Recursively sort both halves.
 * 5. Merge: Use a helper function to stitch two sorted lists into one sorted list.
 *
 * Time Complexity: O(n log n), where n is the number of nodes. The list is split log n times, 
 * and at each level of recursion, we perform O(n) work to merge the sublists.
 * Space Complexity: O(log n) due to the recursion stack depth.
 *
 * Edge Cases:
 * - Empty list: Handled by the base case (returns null).
 * - Single node: Handled by the base case (returns head).
 * - Two nodes: Correctly splits into two single-node lists and merges them.
 *
 * Dry Run:
 * Input: 4 -> 2 -> 1 -> 3
 * 1. Split: [4, 2] and [1, 3]
 * 2. Sort Left: [4, 2] splits to [4] and [2]. Merged to [2, 4].
 * 3. Sort Right: [1, 3] splits to [1] and [3]. Merged to [1, 3].
 * 4. Final Merge: Compare [2, 4] and [1, 3].
 *    - 1 < 2: Result [1]
 *    - 2 < 3: Result [1, 2]
 *    - 3 < 4: Result [1, 2, 3]
 *    - Remaining 4: Result [1, 2, 3, 4]
 *
 * Correctness Check:
 * The solution is correct. It properly handles the split by maintaining a 'prev' pointer 
 * to nullify the end of the first half, preventing infinite recursion.
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

public class SortList148 {
    public static ListNode sortList(ListNode head) {
        // Base case: if list is empty or has only one node, it's already sorted
        if (head == null || head.next == null) {
            return head;
        }
        
        // Use slow and fast pointers to find the middle of the linked list
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow; // Tracks the node immediately before the middle
            slow = slow.next; // Moves one step
            fast = fast.next.next; // Moves two steps
        }
        
        // Sever the connection between the first half and the second half
        // This creates two independent linked lists
        prev.next = null;

        // Recursively sort the left half (starting at head)
        ListNode l1 = sortList(head);
        // Recursively sort the right half (starting at slow/middle)
        ListNode l2 = sortList(slow);

        // Merge the two sorted halves back together
        return merge(l1, l2);
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        // Dummy node acts as a placeholder to simplify the head of the new list
        ListNode l = new ListNode(0);
        ListNode p = l;
        
        // Iterate while both lists have nodes remaining
        while (l1 != null && l2 != null) {
            // Pick the smaller value and attach it to the merged list
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        
        // If one list is exhausted, attach the remainder of the other list
        if (l1 != null) {
            p.next = l1;
        }
        
        if (l2 != null) {
            p.next = l2;
        }
        
        // Return the actual head (skipping the dummy node)
        return l.next;
    }
}
