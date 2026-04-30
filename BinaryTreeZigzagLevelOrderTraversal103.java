/*
 * Problem: 103. Binary Tree Zigzag Level Order Traversal
 *
 * Problem Statement:
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. 
 * This means the first level is read left-to-right, the second level right-to-left, 
 * the third level left-to-right, and so on.
 *
 * Intuition:
 * A standard Breadth-First Search (BFS) explores a tree level by level from left to right. 
 * To achieve the zigzag effect, we don't need to change the order in which we visit nodes 
 * or add their children to the queue. Instead, we simply change the order in which we 
 * insert the node values into the list for the current level.
 *
 * Approach:
 * 1. Initialize a Queue for standard BFS and a boolean flag to track the current direction.
 * 2. While the queue is not empty, determine the number of nodes at the current level (size).
 * 3. Use a LinkedList for the current level's result. If the flag is true (L-to-R), 
 *    append values to the end. If false (R-to-L), prepend values to the front (addFirst).
 * 4. Add the children of the current node to the queue for the next level.
 * 5. Toggle the flag after each level is fully processed.
 *
 * Time Complexity: O(N), where N is the number of nodes in the tree. Each node is visited and processed exactly once.
 * Space Complexity: O(N). In the worst case (a complete binary tree), the queue will hold up to N/2 nodes 
 * at the widest level, and the final result list stores all N nodes.
 *
 * Edge Cases:
 * - Empty tree (root == null): Should return an empty list.
 * - Single node: Should return a list containing one list with that node's value.
 * - Skewed trees: The logic holds as the queue naturally handles depth.
 *
 * Dry Run:
 * Input: [3, 9, 20, null, null, 15, 7]
 * 1. Queue: [3], flag: true. Process 3. row: [3]. res: [[3]]. flag: false. Queue: [9, 20].
 * 2. Queue: [9, 20], flag: false. 
 *    - Process 9: row.addFirst(9) -> [9].
 *    - Process 20: row.addFirst(20) -> [20, 9].
 *    res: [[3], [20, 9]]. flag: true. Queue: [15, 7].
 * 3. Queue: [15, 7], flag: true.
 *    - Process 15: row.add(15) -> [15].
 *    - Process 7: row.add(7) -> [15, 7].
 *    res: [[3], [20, 9], [15, 7]].
 *
 * Correctness Check:
 * The solution is correct. Using `addFirst` on a LinkedList effectively reverses the 
 * insertion order in O(1) time per element, satisfying the zigzag requirement without 
 * needing a full list reversal at each step.
 */
import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal103 {
    // State variable to track direction: true for Left-to-Right, false for Right-to-Left
    boolean flag = true; // flasg = true means left to right ,flag = false means right to left

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // Base case: if the tree is empty, return the empty result list
        if (root == null)
            return res;
            
        // Queue for Breadth-First Search (BFS) to process nodes level by level
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // BFS queue
        
        while (!q.isEmpty()) {
            // Capture the number of nodes at the current level before processing
            int size = q.size(); // number of nodes in current level
            
            // Using LinkedList specifically because it provides addFirst() for O(1) prepending
            LinkedList<Integer> row = new LinkedList<>(); // supports addFirst
            
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                
                // Zigzag logic: decide whether to append or prepend based on the flag
                if (flag) {
                    row.add(curr.val); // left-to-right: standard append
                } else {
                    row.addFirst(curr.val); // right-to-left by front insertion: reverses the order
                }
                
                // Standard BFS: always add children to the queue in Left-to-Right order
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }
            
            // Add the completed level to the final result
            res.add(row);
            
            // Flip the direction flag for the next level of the tree
            flag = !flag; // alternate direction for next level
        }
        return res;
    }
}
