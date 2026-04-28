/*
 * Problem: LeetCode 102. Binary Tree Level Order Traversal
 *
 * Problem Statement:
 * Given a binary tree, return the level order traversal of its nodes' values
 * (from left to right, level by level).
 *
 * Intuition:
 * A queue enables breadth-first search (BFS), which naturally processes nodes
 * level by level.
 *
 * Approach:
 * 1. Use a queue starting with the root.
 * 2. For each level, measure current queue size.
 * 3. Pop exactly that many nodes, record their values, and enqueue their children.
 *
 * Time Complexity: O(n) because each node is enqueued and dequeued once.
 * Space Complexity: O(w) where w is the maximum width of the tree.
 *
 * Edge Cases handled:
 * - Empty tree.
 * - Single node.
 *
 * Dry Run:
 * Tree: [3,9,20,null,null,15,7]
 * Level 0: [3]
 * Level 1: [9,20]
 * Level 2: [15,7]
 *
 * Correctness Check:
 * BFS with level size boundaries guarantees correct grouping by depth.
 *
 * LeetCode Match:
 * LeetCode 102 - "Binary Tree Level Order Traversal".
 */
import java.util.*;

public class BinaryTreeLevelOrderTraversal102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        // Queue for BFS traversal
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            // Number of nodes in the current level
            int size = q.size();
            List<Integer> lvl = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                // Process one node from this level
                TreeNode cur = q.poll();
                lvl.add(cur.val);
                // Enqueue children for next level
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            // Add the completed level to the result
            res.add(lvl);
        }
        return res;
    }
}
