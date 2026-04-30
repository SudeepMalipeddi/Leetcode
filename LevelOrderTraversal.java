/*
 * Problem: LeetCode 102 - Binary Tree Level Order Traversal
 *
 * Problem Statement:
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * The result should be a list of lists, where each inner list contains values from a single level.
 *
 * Intuition:
 * Breadth-First Search (BFS) is the natural choice for level-by-level processing. By using a 
 * Queue (FIFO), we can visit nodes in the order they appear. The key insight is to capture 
 * the queue's size at the start of each level to distinguish between nodes of the current 
 * depth and their children being added for the next depth.
 *
 * Approach:
 * 1. Initialize a Queue to manage the BFS frontier and a List of Lists for the result.
 * 2. Handle the null root edge case immediately.
 * 3. While the queue is not empty:
 *    a. Record the current queue size (this represents the number of nodes at the current level).
 *    b. Iterate 'size' times to process only the current level's nodes.
 *    c. For each node, add its value to a temporary list and enqueue its non-null children.
 *    d. Add the temporary level list to the final result.
 *
 * Time Complexity: O(n)
 * Every node in the tree is enqueued and dequeued exactly once.
 *
 * Space Complexity: O(w)
 * The queue stores at most the maximum width of the tree (w). In a full binary tree, 
 * this is approximately n/2. The output list also takes O(n) space.
 *
 * Edge Cases:
 * - Empty tree (root == null): Returns an empty list.
 * - Single node tree: Returns a list containing one list with the root value.
 * - Left/Right skewed tree: Processes one node per level.
 *
 * Dry Run:
 * Tree: [3, 9, 20, null, null, 15, 7]
 * 1. q=[3], res=[]
 * 2. Level 1: size=1. Poll 3. q=[9, 20]. level=[3]. res=[[3]]
 * 3. Level 2: size=2. Poll 9 (no children). Poll 20. q=[15, 7]. level=[9, 20]. res=[[3], [9, 20]]
 * 4. Level 3: size=2. Poll 15. Poll 7. q=[]. level=[15, 7]. res=[[3], [9, 20], [15, 7]]
 *
 * Correctness Check:
 * The solution correctly separates levels by using the fixed 'size' variable in the inner loop.
 * Without this, the loop would continue indefinitely as children are added to the same queue.
 */

import java.util.*;

public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // LinkedList is a common implementation of the Queue interface in Java.
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        
        // Standard BFS guard clause.
        if (root == null)
            return res;
            
        q.add(root);
        
        // Each outer iteration consumes exactly one tree depth.
        while (!q.isEmpty()) {
            // CRITICAL: Capture the size here. This tells us how many nodes are in the CURRENT level.
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            
            // Process only nodes that were already in queue for this level.
            // Any nodes added to the queue during this loop belong to the NEXT level.
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                
                // Standard BFS: check children before adding to avoid NullPointerExceptions.
                if (curr.left != null)
                    q.add(curr.left);
                if (curr.right != null)
                    q.add(curr.right);
                    
                level.add(curr.val);
            }
            // After the inner loop, we have finished one full horizontal slice of the tree.
            res.add(level);
        }
        return res;
    }

    /**
     * Alternative version that returns a flat list of all values in BFS order.
     */
    public List<Integer> levelOrder1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        
        if (root == null)
            return res;
            
        q.offer(root);
        
        // Same BFS layering logic, but append into one flat list.
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                // peek() allows us to look at the node without removing it yet.
                if (q.peek().left != null)
                    q.offer(q.peek().left);
                if (q.peek().right != null)
                    q.offer(q.peek().right);
                
                // poll() removes the head of the queue.
                res.add(q.poll().val);
            }
        }
        return res;
    }
}
