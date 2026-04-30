/*
 * Problem Statement:
 * Perform binary tree level-order traversal (BFS):
 * - levelOrder returns values grouped per level.
 * - levelOrder1 returns a flattened BFS order.
 *
 * Intuition:
 * Queue keeps frontier nodes in FIFO order. Capturing queue size at loop start isolates one depth level.
 *
 * Approach:
 * - Guard null root.
 * - Push root into queue.
 * - While queue not empty:
 *   - size = queue size (nodes currently in this level)
 *   - pop exactly size nodes, append values, enqueue their children.
 *   - store current level list (for levelOrder).
 *
 * Time Complexity (with concrete justification):
 * O(n): every node is enqueued once and dequeued once in both methods.
 *
 * Space Complexity (with concrete justification):
 * O(w) auxiliary queue space, where w is max tree width (worst-case O(n)).
 * levelOrder additionally stores O(n) output across nested lists.
 *
 * Edge Cases handled:
 * - root == null -> empty result.
 * - Single-node tree -> one level containing root value.
 * - Skewed tree -> one node per level.
 *
 * Dry Run (concrete example with state):
 * Tree: 1 / \ 2 3 / \ 4 5
 * q=[1]
 * level 0 size=1 -> pop 1, push 2,3 => level=[1]
 * level 1 size=2 -> pop 2(push4,5), pop3 => level=[2,3]
 * level 2 size=2 -> pop 4,5 => level=[4,5]
 * result=[[1],[2,3],[4,5]]
 *
 * LeetCode matching/assumption:
 * levelOrder matches LeetCode 102 semantics. Assumes TreeNode structure is available in project.
 *
 * Correctness Check:
 * Taking size before inner loop is critical; it prevents newly enqueued children from mixing into current level.
 * levelOrder1 intentionally returns flat BFS order, not grouped levels.
 */

import java.util.*;

public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        q.add(root);
        // Each outer iteration consumes exactly one tree depth.
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            // Process only nodes that were already in queue for this level.
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr.left != null)
                    q.add(curr.left);
                if (curr.right != null)
                    q.add(curr.right);
                level.add(curr.val);
            }
            res.add(level);
        }
        return res;
    }

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
                if (q.peek().left != null)
                    q.offer(q.peek().left);
                if (q.peek().right != null)
                    q.offer(q.peek().right);
                res.add(q.poll().val);
            }
        }
        return res;
    }
}
