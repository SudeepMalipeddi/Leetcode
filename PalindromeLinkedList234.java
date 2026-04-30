/*
Problem Statement:
- Check if linked list values form a palindrome.

Intuition:
- Middle split + reverse second half enables linear-time symmetric comparison.

Approach:
- Use fast/slow to find midpoint, reverse from midpoint, compare first half with reversed second half.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- Single-node list is palindrome.

Dry Run:
- 1->2->2->1: reverse second half to 1->2, compare with first half 1->2.
*/
public class PalindromeLinkedList234 {

    public boolean isPallindrome(ListNode head) {
        
        if (head.next == null) {
            return true;
        }
        ListNode ptr = head, cptr = head;
        
        // Fast/slow pointers locate middle of the list.
        while (ptr != null && ptr.next != null) {
            ptr = ptr.next.next;
            cptr = cptr.next;
        }

        ListNode prev = null;
        
        // Reverse second half in-place for direct value comparison.
        while (cptr != null) {
            ListNode temp = cptr.next;
            cptr.next = prev;
            prev = cptr;
            cptr = temp;
        }

        ptr = head;
        cptr = prev;
        
        while (cptr != null) {
            
            if (ptr.val != cptr.val) {
                return false;
            }
            ptr = ptr.next;
            cptr = cptr.next;
        }
        return true;
    }
}
