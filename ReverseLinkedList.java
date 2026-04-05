public class reverseLinkedList {
    public ListNode reverseList(ListNode head){
        // Iterative solution
        // Initialize prev to null
        ListNode prev = null;
        // Initialize curr to head
        ListNode curr = head;
        // While loop to iterate through the linked list
        while(curr!=null){
            // Initialize nextTemp to curr.next
            ListNode nextTemp = curr.next;
            // Set curr.next to prev
            curr.next=prev;
            // Set prev to curr
            prev=curr;
            // Set curr to nextTemp
            curr=nextTemp;
        }
        // Return prev
        return prev;
    }
}
