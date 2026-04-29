/*
 * Problem: LeetCode 102 - Binary Tree Level Order Traversal
 * Problem Statement: Return the level order traversal of a binary tree's nodes'
 *   values (from left to right, level by level).
 * Intuition: Breadth-first search naturally visits nodes level by level using a queue.
 * Approach:
 *   1) Push the root into a queue.
 *   2) For each level, process exactly the current queue size nodes.
 *   3) Append their values to a list and enqueue their children.
 * Time Complexity: O(n) for n nodes.
 * Space Complexity: O(n) for the queue and output.
 * Edge Cases: Empty tree, single node, skewed tree.
 * Dry Run: root=3, left=9, right=20 -> levels [[3], [9,20]].
 * Correctness Check: Each node is enqueued once and grouped by level size,
 *   ensuring correct per-level ordering.
 */
import java.util.*;

public class BinaryTreeLevelOrderTraversal102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // start BFS from root
        while (!q.isEmpty()) {
            int size = q.size(); // number of nodes in current level
            List<Integer> lvl = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                lvl.add(cur.val); // collect current level values
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            res.add(lvl); // finished one level
        }
        return res;
    }
}
