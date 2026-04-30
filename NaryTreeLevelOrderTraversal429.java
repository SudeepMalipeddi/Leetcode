/*
 * Problem: 429. N-ary Tree Level Order Traversal
 *
 * Problem Statement:
 * Given an n-ary tree, return the level order traversal of its nodes' values. 
 * Each level should be returned as a separate list within the final result list.
 *
 * Intuition:
 * Level order traversal is synonymous with Breadth-First Search (BFS). By using a queue, 
 * we can process nodes in the order they appear horizontally. To group them by level, 
 * we capture the queue size at the beginning of each level's iteration.
 *
 * Approach:
 * 1. Initialize a result list of lists.
 * 2. Handle the base case: if the root is null, return the empty result.
 * 3. Use a Queue to manage nodes. (Note: The provided code below is missing the initial q.offer(root) step).
 * 4. While the queue is not empty, determine the number of nodes at the current level (size).
 * 5. Iterate 'size' times to process only the nodes belonging to the current level.
 * 6. For each node, add its value to a temporary level list and add all its children to the queue for the next level.
 * 7. Append the level list to the final result.
 *
 * Time Complexity: O(N) where N is the total number of nodes in the tree. Each node is visited exactly once.
 * Space Complexity: O(W) where W is the maximum width of the tree. In the worst case, the queue stores one full level.
 *
 * Edge Cases:
 * - Empty tree (root == null): Should return an empty list.
 * - Tree with only a root: Should return [[root.val]].
 * - Tree with varying number of children per node.
 *
 * Dry Run:
 * Input: Root(1) -> Children[3, 2, 4]; 3 -> Children[5, 6]
 * 1. res = [], q = [1] (Assuming root was added)
 * 2. Level 1: size=1. Poll 1. lvl=[1]. Add children [3, 2, 4] to q. res=[[1]].
 * 3. Level 2: size=3. Poll 3, lvl=[3], add [5, 6] to q. Poll 2, lvl=[3, 2]. Poll 4, lvl=[3, 2, 4]. res=[[1], [3, 2, 4]].
 * 4. Level 3: size=2. Poll 5, lvl=[5]. Poll 6, lvl=[5, 6]. res=[[1], [3, 2, 4], [5, 6]].
 *
 * Correctness Check:
 * BUG ALERT: The code below initializes the queue but never adds the 'root' node to it. 
 * Consequently, q.isEmpty() will be true immediately, and the method will return an empty list 
 * even if the tree is not empty. To fix this, 'q.offer(root);' must be called before the while loop.
 */
import java.util.*;

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

public class NaryTreeLevelOrderTraversal429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (root == null)
            return res;

        Queue<Node> q = new LinkedList<>();
        
        
        // BFS level traversal; q should contain root before this loop starts.
        // Logic Bug: q.offer(root) is missing here, so the loop below will not execute.
        while (!q.isEmpty()) {
            // Capture the number of nodes at the current level before processing.
            int size = q.size();
            List<Integer> lvl = new ArrayList<>();
            
            // Process exactly 'size' nodes to ensure we stay within the current level.
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                lvl.add(cur.val);
                
                // N-ary trees have a list of children instead of just left/right pointers.
                for (Node child : cur.children) {
                    q.offer(child);
                }
            }
            // After processing all nodes at this depth, add the level list to the result.
            res.add(lvl);
        }
        return res;
    }
}
