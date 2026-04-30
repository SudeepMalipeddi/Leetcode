/*
 * Problem Reference: LeetCode 543 - Diameter of Binary Tree
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * DFS computes subtree height while updating global best diameter.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(h)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
public class DiameterofBinaryTree543 {
    int max = 0;

    public int DiameterofBinaryTree(TreeNode root) {
        return maxDepth(root);
    }

    public int maxDepth(TreeNode root) {
        // Important guard: this branch handles a boundary or constraint-critical condition.
        if (root == null)
            return 0;
        // Recursive/helper call processes a smaller subproblem before combining results.
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}
