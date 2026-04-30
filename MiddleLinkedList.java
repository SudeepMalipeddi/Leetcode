/*
 * Problem: LeetCode 876 - Middle of the Linked List
 *
 * Problem Statement:
 * Given the head of a singly linked list, return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 *
 * Intuition:
 * This solution uses the "Tortoise and Hare" (Two Pointers) algorithm. If one pointer moves 
 * twice as fast as the other, the slower pointer will be at the halfway mark exactly when 
 * the faster pointer reaches the end of the list.
 *
 * Approach:
 * 1. Initialize two pointers, 'slow' and 'fast', both pointing to the head of the list.
 * 2. While 'fast' and 'fast.next' are not null, move 'slow' one step and 'fast' two steps.
 * 3. When the loop terminates, 'slow' will be pointing to the middle node (or the second 
 *    middle node in an even-sized list).
 *
 * Time Complexity: O(n), where n is the number of nodes in the linked list. We traverse 
 * the list at most once.
 * Space Complexity: O(1), as we only use two additional pointers regardless of list size.
 *
 * Edge Cases:
 * - Single node list: The loop condition (fast.next != null) fails immediately; returns head.
 * - Two-node list: Loop runs once; slow moves to 2nd node, fast moves to null; returns 2nd node.
 * - Even-length list: Returns the second of the two middle nodes.
 *
 * Dry Run:
 * List: 1 -> 2 -> 3 -> 4 -> 5 -> 6
 * Initial: slow = 1, fast = 1
 * Step 1: slow = 2, fast = 3
 * Step 2: slow = 3, fast = 5
 * Step 3: slow = 4, fast = null (fast.next was 6, then fast becomes 6.next which is null)
 * Loop ends because fast is null. Return slow (node 4).
 *
 * Correctness Check:
 * The solution is correct. The condition `fast != null && fast.next != null` correctly 
 * handles both even and odd length lists, ensuring we don't attempt to access `.next` 
 * on a null reference.
 */

/*
 * Problem Statement: Return the middle node of a singly linked list (for even length, return the second middle).
 * Intuition: Move one pointer twice as fast as another; when the fast pointer ends, the slow pointer is at middle.
 * Approach: Use fast/slow pointers starting at head; advance slow by 1 and fast by 2 until fast cannot continue.
 * Time Complexity: O(n), single traversal.
 * Space Complexity: O(1).
 * Edge Cases handled: Single-node list; even-length list (returns second middle); null head input.
 * Dry Run: 1->2->3->4->5->6 -> slow visits 1,2,3,4 while fast visits 1,3,5,null -> answer is node 4.
 * LeetCode matching/assumption: Matches LeetCode 876 (Middle of the Linked List), second middle for even length.
 * Correctness Check: Fast reaches end in roughly half the steps, guaranteeing slow has advanced exactly to middle position.
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

public class MiddleLinkedList {
    static ListNode middleNode(ListNode head) {
        // Initialize both pointers at the start of the list.
        ListNode slow = head;
        ListNode fast = head;
        // Fast moves two steps, slow moves one step.
        // We check fast.next to ensure we can safely move fast two steps ahead.
        while (fast != null && fast.next != null) {
            // Slow pointer moves at 1x speed.
            slow = slow.next;
            // Fast pointer moves at 2x speed.
            fast = (fast.next).next;
        }
        // When fast reaches the end, slow is at the middle.
        return slow;
    }
}
