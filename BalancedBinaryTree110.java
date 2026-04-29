/*
 * Problem: LeetCode 110 - Balanced Binary Tree
 * Problem Statement: Determine if a binary tree is height-balanced, meaning
 *   the depths of the two subtrees of every node never differ by more than one.
 * Intuition: Use a post-order traversal that returns height, but propagate a
 *   sentinel value (-1) when an imbalance is found.
 * Approach:
 *   1) Recursively compute left and right subtree heights.
 *   2) If any subtree is unbalanced or height difference > 1, return -1.
 *   3) Otherwise return max(left, right) + 1.
 *   4) The tree is balanced iff the root height is not -1.
 * Time Complexity: O(n) because each node is visited once.
 * Space Complexity: O(h) recursion stack, where h is tree height.
 * Edge Cases: Empty tree (balanced), single node, skewed trees.
 * Dry Run: root=1 with left=2, right=3 (both leaves) -> heights 1 and 1,
 *   diff=0 => balanced.
 * Correctness Check: The sentinel -1 ensures any imbalance bubbles up, so the
 *   final check captures all nodes' balance constraints.
 */
public class BalancedBinaryTree110 {
    public Boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left); // height of left subtree
        int rightHeight = height(root.right); // height of right subtree
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
