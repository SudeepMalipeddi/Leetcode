/*
 * Problem Reference: LeetCode 1325 - Delete Leaves With a Given Value
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Post-order recursion: clean children first, then decide current leaf deletion.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(h) recursion
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
public class DeleteLeavesWithaGivenValue1325 {

    // Time Complexity: O(n)
    // Space Complexity: O(h)
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // Important guard: this branch handles a boundary or constraint-critical condition.
        if (root == null)
            return null;
        // Recursive/helper call processes a smaller subproblem before combining results.
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }
}
