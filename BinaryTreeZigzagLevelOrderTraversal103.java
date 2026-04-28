/*
 * Problem: LeetCode 103. Binary Tree Zigzag Level Order Traversal
 *
 * Problem Statement:
 * Given a binary tree, return its level order traversal with alternating direction
 * at each level (left-to-right, then right-to-left, and so on).
 *
 * Intuition:
 * BFS processes the tree by levels. A direction flag and a deque-like list let us
 * insert values at the end or front depending on the level.
 *
 * Approach:
 * 1. Use a queue for BFS.
 * 2. For each level, use a LinkedList to allow addFirst or addLast.
 * 3. Flip the direction flag after each level.
 *
 * Time Complexity: O(n) because each node is processed once.
 * Space Complexity: O(w) for queue and current level list.
 *
 * Edge Cases handled:
 * - Empty tree.
 * - Single node.
 *
 * Dry Run:
 * Tree: [1,2,3,4,5]
 * Level 0 (L->R): [1]
 * Level 1 (R->L): [3,2]
 * Level 2 (L->R): [4,5]
 *
 * Correctness Check:
 * Alternating insertion order per level guarantees zigzag ordering.
 *
 * LeetCode Match:
 * LeetCode 103 - "Binary Tree Zigzag Level Order Traversal".
 */
import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal103 {
    boolean flag = true; // flasg = true means left to right ,flag = false means right to left

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        // Standard BFS queue
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            // Process one level at a time
            int size = q.size();
            LinkedList<Integer> row = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                // Insert based on current direction
                if (flag) {
                    row.add(curr.val);
                } else {
                    row.addFirst(curr.val);
                }
                // Enqueue children for the next level
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }
            res.add(row);
            // Flip direction for next level
            flag = !flag;
        }
        return res;
    }
}
