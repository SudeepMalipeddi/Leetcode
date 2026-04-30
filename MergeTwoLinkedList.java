/*
 * Problem: 21. Merge Two Sorted Lists
 *
 * Problem Statement:
 * Merge two sorted linked lists into one sorted list. The list should be made by splicing 
 * together the nodes of the first two lists.
 *
 * Intuition:
 * Since both input lists are already sorted, the smallest overall element must be at the 
 * head of either l1 or l2. By selecting the smaller head, we can solve the sub-problem 
 * of merging the remaining nodes recursively.
 *
 * Approach:
 * 1. Base Cases: If either list is null, return the other list (the remaining sorted portion).
 * 2. Comparison: Compare the values of the current heads of l1 and l2.
 * 3. Recursion: Point the 'next' of the smaller node to the result of merging the rest of the lists.
 * 4. Return: Return the smaller node as the new head of the merged segment.
 *
 * Time Complexity: O(n + m), where n and m are the lengths of the two lists. We visit each node exactly once.
 * Space Complexity: O(n + m) due to the recursion stack. In the worst case, the stack depth equals the total number of nodes.
 *
 * Edge Cases:
 * - One list is empty: Returns the other list immediately.
 * - Both lists are empty: Returns null.
 * - Lists with different lengths: The longer list's tail is appended once the shorter list is exhausted.
 *
 * Dry Run:
 * l1: 1 -> 3, l2: 2 -> 4
 * 1. merge(1, 2): 1 < 2, so 1.next = merge(3, 2)
 * 2. merge(3, 2): 3 > 2, so 2.next = merge(3, 4)
 * 3. merge(3, 4): 3 < 4, so 3.next = merge(null, 4)
 * 4. merge(null, 4): l1 is null, return 4.
 * 5. Unwinding: 3.next=4, 2.next=3, 1.next=2. Result: 1 -> 2 -> 3 -> 4.
 *
 * Correctness Check:
 * The solution is correct. It preserves the sorted order by always picking the minimum 
 * available node and correctly handles the end of lists via base cases.
 */

/*
 * Problem Statement: Merge two sorted linked lists and return one sorted list formed by re-linking existing nodes.
 * Intuition: At each step, the smaller current node must be the next node in the final sorted list.
 * Approach: Use recursion; pick the smaller head between l1 and l2, then recursively merge the remaining nodes.
 * Time Complexity: O(m + n), where m and n are lengths of the two lists.
 * Space Complexity: O(m + n) in recursion stack depth in the worst case.
 * Edge Cases handled: Either list is null; lists with different lengths; duplicate values across lists.
 * Dry Run: l1=1->3->5, l2=1->2->4 -> pick 1(l2), then 1(l1), then 2, 3, 4, 5 -> merged sorted list.
 * LeetCode matching/assumption: Matches LeetCode 21 (Merge Two Sorted Lists) style input/output.
 * Correctness Check: Base cases return the non-null remainder; recursive step preserves sorted order by always choosing the smaller head.
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

public class MergeTwoLinkedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // If l1 is exhausted, the rest of l2 is already sorted.
        // Base case: if one list is null, the merge result is simply the other list.
        if (l1 == null)
            return l2;
        // If l2 is exhausted, the rest of l1 is already sorted.
        if (l2 == null)
            return l1;
        // Choose the smaller head and recursively connect the remaining merged tail.
        // This comparison ensures we always pick the smallest available value for the current position.
        if (l1.val < l2.val) {
            // The current node of l1 is smaller, so it stays in front.
            // We recursively find what should be attached to l1.next.
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            // The current node of l2 is smaller or equal, so it stays in front.
            // We recursively find what should be attached to l2.next.
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
