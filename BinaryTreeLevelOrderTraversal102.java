/*
 * Problem: 102. Binary Tree Level Order Traversal
 *
 * Problem Statement:
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 *
 * Intuition:
 * Breadth-First Search (BFS) is the standard algorithm for visiting nodes level-by-level. 
 * By using a queue and tracking the size of the queue at the start of each level, 
 * we can group nodes belonging to the same depth together.
 *
 * Approach:
 * 1. Initialize a result list and a queue. Add the root to the queue if it is not null.
 * 2. While the queue is not empty, determine the current level's size (number of nodes currently in the queue).
 * 3. Iterate 'size' times: poll a node, add its value to the current level's list, and add its children to the queue.
 * 4. Add the level list to the result list and repeat until the queue is empty.
 *
 * Time Complexity: O(N) where N is the number of nodes in the tree. Each node is visited exactly once.
 * Space Complexity: O(N) to store the result and the queue. In a perfect binary tree, the queue 
 * stores at most N/2 nodes at the leaf level.
 *
 * Edge Cases:
 * - Empty tree (root is null): Returns an empty list.
 * - Single node tree: Returns a list containing one list with the root value.
 * - Skewed tree: Works correctly, processing one node per level.
 *
 * Dry Run:
 * Input: root = [3, 9, 20, null, null, 15, 7]
 * 1. Queue: [3]. res: []
 * 2. Level 1: size=1. Poll 3. lvl=[3]. Queue: [9, 20]. res: [[3]]
 * 3. Level 2: size=2. Poll 9, then 20. lvl=[9, 20]. Queue: [15, 7]. res: [[3], [9, 20]]
 * 4. Level 3: size=2. Poll 15, then 7. lvl=[15, 7]. Queue: []. res: [[3], [9, 20], [15, 7]]
 *
 * Correctness Check:
 * The solution is correct. It uses a standard BFS template with a nested loop to handle level separation.
 */
import java.util.*;

public class BinaryTreeLevelOrderTraversal102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // Initialize the outer list to store the values of each level
        List<List<Integer>> res = new ArrayList<>();
        
        // Handle the edge case where the tree is empty
        if (root == null)
            return res;
            
        // Use a Queue to facilitate Breadth-First Search (FIFO)
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // start BFS from root
        
        while (!q.isEmpty()) {
            // The current size of the queue represents exactly how many nodes are at this specific depth
            int size = q.size(); // number of nodes in current level
            List<Integer> lvl = new ArrayList<>();
            
            // Process all nodes belonging to the current level before moving to the next depth
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                lvl.add(cur.val); // collect current level values
                
                // Enqueue children for the next level; they will be processed in the next iteration of the while loop
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            // Once the for-loop finishes, we have collected all values for the current level
            res.add(lvl); // finished one level
        }
        return res;
    }
}
