/*
 * Problem Reference: Inorder Successor in BST
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * BST search tracks lowest ancestor greater than target.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(h)
 *
 * Space Complexity:
 * O(1)
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
public class InOrderSuccessor {
    // Naive solution
    public TreeNode inorderSuccesor(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        TreeNode curr = root;
        // Iterate through the active search space while maintaining the intended invariant.
        while (curr != null) {
            // Important guard: this branch handles a boundary or constraint-critical condition.
            if (curr.val <= p.val) {
                curr = curr.right;
            } else {
                succ = curr;
                curr = curr.left;
            }
        }
        return succ;
    }
}
