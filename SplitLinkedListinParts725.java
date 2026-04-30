/*
 * Problem: 725. Split Linked List in Parts
 *
 * Problem Statement:
 * Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than one.
 * This may lead to some parts being null. The parts should be in the order of occurrence in the input list, 
 * and parts occurring earlier should always have a size greater than or equal to parts occurring later.
 *
 * Intuition:
 * To distribute N nodes into k buckets as evenly as possible, each bucket gets at least N/k nodes.
 * The remaining N%k nodes are distributed one-by-one to the first N%k buckets.
 * This ensures the "size difference of at most 1" and "earlier parts are larger" constraints.
 *
 * Approach:
 * 1. Traverse the list once to find the total length.
 * 2. Calculate the base size of each part (length / k) and the number of extra nodes (length % k).
 * 3. Iterate k times to create each part. For each part, determine its size (base size + 1 if remainder > 0).
 * 4. Traverse to the end of that part's size, sever the 'next' pointer to isolate the part, and store the head.
 *
 * Time Complexity: O(N + k), where N is the number of nodes in the list. We traverse the list to find the length (N) 
 * and then traverse it again to split it (N). We also initialize the result array of size k.
 * Space Complexity: O(k) if we count the output array, or O(1) auxiliary space if we don't, as we are 
 * modifying the existing list nodes' pointers in-place.
 *
 * Edge Cases:
 * - head is null: The result will be an array of k nulls.
 * - k > length: Each node becomes its own part, followed by empty (null) parts.
 * - k = 1: The entire list is returned as the single element in the array.
 *
 * Dry Run:
 * head = [1, 2, 3, 4], k = 3
 * 1. length = 4.
 * 2. partSize = 4 / 3 = 1. remainder = 4 % 3 = 1.
 * 3. i = 0: size = 1 + 1 = 2. Part is [1, 2]. Sever after 2. curr moves to 3.
 * 4. i = 1: size = 1 + 0 = 1. Part is [3]. Sever after 3. curr moves to 4.
 * 5. i = 2: size = 1 + 0 = 1. Part is [4]. Sever after 4. curr moves to null.
 * Result: [[1,2], [3], [4]]
 *
 * Correctness Check:
 * The solution correctly handles the distribution of the remainder and the breaking of links. 
 * The use of (remainder-- > 0) is a concise way to distribute extra nodes to the first few parts.
 */
public class SplitLinkedListinParts725 {
    public ListNode[] splitListToParts(ListNode head, int k){
        // First pass: Calculate the total number of nodes in the linked list.
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            length++;
        }

        // Determine the minimum number of nodes per part and how many parts get an extra node.
        int partSize = length / k;
        int remainder = length % k;
        
        ListNode[] result = new ListNode[k];
        curr = head;

        // Second pass: Split the list into k segments.
        for(int i = 0; i < k; i++){
            // The current node is the head of the i-th part.
            result[i] = curr;
            
            // Calculate the size of the current part. 
            // If remainder > 0, this part gets one of the extra nodes.
            int size = partSize + (remainder--> 0 ? 1 : 0);
            
            // Move to the last node of the current segment.
            // We loop (size - 1) times because 'curr' is already at the first node.
            for(int j = 0; j < size-1; j++){
                if (curr != null) curr = curr.next;
            }
            
            // Sever the connection to the next part of the list.
            if(curr != null){
                ListNode temp = curr.next;
                curr.next = null; // Break the link to isolate the current part.
                curr = temp;      // Move 'curr' to the start of the next part.
            }
        }
        return result;
    }
}
