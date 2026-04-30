/*
Problem Statement:
- Connect each node's next pointer to its right neighbor on the same level.

Intuition:
- Level-order traversal reveals exact neighbor order within a level.

Approach:
- For each BFS level, set next of each node to queue front except the last node, whose next is null.

Time Complexity:
- O(n).

Space Complexity:
- O(w) queue.

Edge Cases:
- Null root returns null.

Dry Run:
- For level [4,5,6], set 4.next=5, 5.next=6, 6.next=null.
*/
import java.util.*;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

public class PopulatingNextRightPointersinEachNode116 {
    public Node connect(Node root) {
        
        if (root == null) {
            return root;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        
        while (!q.isEmpty()) {
            int size = q.size();
            
            // Nodes processed in queue order are exactly left-to-right within this level.
            for (int i = 0; i < size; i++) {
                Node curr = q.poll();
                
                if (i != size - 1) {
                    curr.next = q.peek();
                } else {
                    curr.next = null;
                }
                
                if (curr.left != null)
                    q.offer(curr.left);
                
                if (curr.right != null)
                    q.offer(curr.right);
            }
        }

        return root;
    }
}
