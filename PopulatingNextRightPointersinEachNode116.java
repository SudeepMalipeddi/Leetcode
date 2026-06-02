/*
 * Problem: LeetCode 116 - Populating Next Right Pointers in Each Node
 *
 * Problem Statement:
 * Given a perfect binary tree where all leaves are at the same level and every parent has two children,
 * populate each node's next pointer to point to its right neighbor on the same level. If there is no
 * right neighbor, the next pointer should be set to null. The root's next is always null.
 *
 * Intuition:
 * A level-order (BFS) traversal naturally processes nodes left-to-right within each level. By tracking
 * the current level's size, we can distinguish the last node of a level (next = null) from others
 * (next = the upcoming node at the front of the queue). The queue preserves the left-to-right ordering
 * within each level.
 *
 * Approach:
 *   1. If root == null, return root immediately (empty tree edge case).
 *   2. Create a Queue<Node> q (using LinkedList) and offer the root.
 *   3. While q is not empty:
 *      a. Get the current level's size = q.size().
 *      b. For i from 0 to size-1:
 *         - curr = q.poll() (remove the front node).
 *         - If i != size-1 (not the last node of the level): curr.next = q.peek() (the next node
 *           in the queue is its immediate right neighbor).
 *         - Else (i == size-1, this is the level's rightmost node): curr.next = null.
 *         - If curr.left != null, offer curr.left to q.
 *         - If curr.right != null, offer curr.right to q.
 *   4. Return root with all next pointers populated.
 *
 * Time Complexity: O(n) — each node is enqueued, dequeued, and processed exactly once.
 * Space Complexity: O(w) — the queue holds at most the width of the tree's widest level, which is
 *   up to n/2 for a perfect binary tree (the leaf level), so O(n) in the worst case.
 *
 * Edge Cases:
 * - Empty tree (root == null): returns null immediately.
 * - Single-node tree: size=1, i=0 equals size-1, so next=null. Correct.
 * - The algorithm works for any binary tree, not just perfect ones, since it uses BFS level-by-level.
 *
 * Dry Run:
 * Perfect tree: root=1, left=2, right=3, 2.left=4, 2.right=5, 3.left=6, 3.right=7
 * Queue initially: [1]
 * Level 1: size=1, i=0: curr=1, i==size-1 so next=null; enqueue 2,3 → q=[2,3]
 * Level 2: size=2
 *   i=0: curr=2, i!=size-1 so next=q.peek()=3; enqueue 4,5 → q=[3,4,5]
 *   i=1: curr=3, i==size-1 so next=null; enqueue 6,7 → q=[4,5,6,7]
 * Level 3: size=4
 *   i=0: curr=4, next=q.peek()=5; no children
 *   i=1: curr=5, next=q.peek()=6; no children
 *   i=2: curr=6, next=q.peek()=7; no children
 *   i=3: curr=7, next=null; no children
 * q empty, return root.
 * Result: level 1: 1→null; level 2: 2→3→null; level 3: 4→5→6→7→null. Correct.
 *
 * Correctness Check:
 * BFS processes nodes level by level left-to-right, so q.peek() after polling always points to the
 * immediate right neighbor within the same level. The size variable correctly delineates level
 * boundaries, ensuring the last node of each level gets next=null. By structural induction on tree
 * levels, all next pointers are correctly assigned.
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
