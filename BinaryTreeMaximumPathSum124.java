/*
 * Problem: LeetCode 124. Binary Tree Maximum Path Sum
 *
 * Problem Statement:
 * Given a non-empty binary tree, find the maximum sum of any path. A path can start
 * and end at any node, but must go downwards (parent-child connections).
 *
 * Intuition:
 * For each node, the best path "through" it uses the best gain from the left and
 * right subtrees if they are positive. A global maximum tracks the best across all nodes.
 *
 * Approach:
 * 1. Use a recursive helper that returns the maximum gain from the current node to
 *    any leaf (single-branch path).
 * 2. At each node, compute left and right gains, clamping at 0 to ignore negative paths.
 * 3. Update global max with left + right + node.val (path through the node).
 * 4. Return node.val + max(left, right) to parent.
 *
 * Time Complexity: O(n) because each node is visited once.
 * Space Complexity: O(h) for recursion stack.
 *
 * Edge Cases handled:
 * - All negative values (global max initialized to Integer.MIN_VALUE).
 * - Single node tree.
 *
 * Dry Run:
 * Tree: [-10,9,20,null,null,15,7]
 * Node 15 -> gain 15, node 7 -> gain 7
 * Node 20 -> update max with 15+7+20=42, return 20+max(15,7)=35
 * Node -10 -> update max with 9+35-10=34; global max remains 42
 *
 * Correctness Check:
 * The helper ensures all candidate paths are considered, and global max captures
 * the best path that can split at any node.
 *
 * LeetCode Match:
 * LeetCode 124 - "Binary Tree Maximum Path Sum".
 */
public class BinaryTreeMaximumPathSum124 {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // Populate global max via postorder traversal
        maxGain(root);
        return max;
    }

    public int maxGain(TreeNode root) {
        if (root == null)
            return 0;

        // Ignore negative contributions by clamping to 0
        int left = Math.max(maxGain(root.left), 0);
        int right = Math.max(maxGain(root.right), 0);

        // Best path that passes through this node (may include both children)
        max = Math.max(max, left + right + root.val);

        // Return the best single-branch gain to parent
        return Math.max(left, right) + root.val;
    }
}
