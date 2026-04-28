/*
 * Problem: LeetCode 110. Balanced Binary Tree
 *
 * Problem Statement:
 * Given a binary tree, determine if it is height-balanced: for every node, the
 * heights of left and right subtrees differ by at most 1.
 *
 * Intuition:
 * A postorder traversal can compute subtree heights. If any subtree is unbalanced,
 * propagate a sentinel value (-1) upward to stop further checks.
 *
 * Approach:
 * 1. Recursively compute height of left and right subtrees.
 * 2. If either subtree is unbalanced or height difference > 1, return -1.
 * 3. Otherwise, return the height of the current node.
 * 4. The tree is balanced if the root height is not -1.
 *
 * Time Complexity: O(n) because each node is visited once.
 * Space Complexity: O(h) for recursion stack, where h is tree height.
 *
 * Edge Cases handled:
 * - Empty tree.
 * - Single node.
 * - Deeply skewed trees.
 *
 * Dry Run:
 * root = [3,9,20,null,null,15,7]
 * heights of leaves = 1, subtrees balanced -> root height = 3, not -1 => true.
 *
 * Correctness Check:
 * The sentinel -1 correctly flags imbalance and prevents extra work; standard solution.
 *
 * LeetCode Match:
 * LeetCode 110 - "Balanced Binary Tree".
 */
public class BalancedBinaryTree110 {
    public Boolean isBalanced(TreeNode root) {
        // If height returns -1, the tree is unbalanced
        return height(root) != -1;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Postorder: compute left and right subtree heights first
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        // If any subtree is unbalanced or height diff exceeds 1, return sentinel -1
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        // Height of current node
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
