public class delete_Node_in_aLinkedList {
    public void deleteNode(ListNode node){
        // checking if the node is null or if it is the last element
        if(node == null || node.next == null){
            // making its next node null and the node itself null
            node.next = null;
            node = null;
        }
        // storing the value of the next node in it 
        node.val = node.next.val;
        // making the node's next element as the next element's next element
        node.next = node.next.next;

        // Time Complexity : O(1)
        // Space Complexity : O(1)

   }
}
