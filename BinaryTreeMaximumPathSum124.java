/*
 * Problem: LeetCode 124 - Binary Tree Maximum Path Sum
 * Problem Statement: Find the maximum sum of any path in a binary tree, where
 *   a path can start and end at any nodes but must go downward via parent-child links.
 * Intuition: For each node, the best path that passes through it is leftGain +
 *   rightGain + node.val; gains below zero should be ignored.
 * Approach:
 *   1) Post-order traverse to compute max gain from each node to its parent.
 *   2) Update a global maximum with leftGain + rightGain + node.val at each node.
 *   3) Return node.val + max(leftGain, rightGain) as the gain to the parent.
 * Time Complexity: O(n) for n nodes.
 * Space Complexity: O(h) recursion stack, h is tree height.
 * Edge Cases: All negative values, single node, skewed trees.
 * Dry Run: root=1 with left=2, right=3 -> max at root = 2+3+1=6.
 * Correctness Check: Each node considers the best "through" path, while the
 *   returned gain ensures parent paths remain valid (single-branch).
 */
public class BinaryTreeMaximumPathSum124 {
    int max = Integer.MIN_VALUE; // global maximum path sum

    public int maxPathSum(TreeNode root) {
        maxGain(root); // fills global max via DFS
        return max;
    }

    public int maxGain(TreeNode root) {
        if (root == null)
            return 0;

        int left = Math.max(maxGain(root.left), 0); // best gain from left subtree
        int right = Math.max(maxGain(root.right), 0); // best gain from right subtree

        max = Math.max(max, left + right + root.val); // path through current node

        return Math.max(left, right) + root.val;
    }
}
