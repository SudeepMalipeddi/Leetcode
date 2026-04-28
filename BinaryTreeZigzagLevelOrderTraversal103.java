/*
 * Problem: LeetCode 103 - Binary Tree Zigzag Level Order Traversal
 * Problem Statement: Return the level order traversal of a binary tree's values,
 *   alternating between left-to-right and right-to-left on each level.
 * Intuition: Standard BFS gives levels; reversing order can be simulated by
 *   inserting values at the front on alternate levels.
 * Approach:
 *   1) Perform BFS with a queue.
 *   2) For each level, add values to a LinkedList either at the end (L->R)
 *      or front (R->L) depending on a boolean flag.
 *   3) Toggle the flag after each level.
 * Time Complexity: O(n) for n nodes.
 * Space Complexity: O(n) for queue and output.
 * Edge Cases: Empty tree, single node, skewed trees.
 * Dry Run: root=1, children 2 and 3 -> level1 [1], level2 [3,2].
 * Correctness Check: Each node is processed once, and per-level ordering is
 *   determined solely by the alternating flag.
 */
import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal103 {
    boolean flag = true; // flasg = true means left to right ,flag = false means right to left

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // BFS queue
        while (!q.isEmpty()) {
            int size = q.size(); // number of nodes in current level
            LinkedList<Integer> row = new LinkedList<>(); // supports addFirst
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (flag) {
                    row.add(curr.val); // left-to-right
                } else {
                    row.addFirst(curr.val); // right-to-left by front insertion
                }
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }
            res.add(row);
            flag = !flag; // alternate direction for next level
        }
        return res;
    }
}
