/*
 * Problem Reference: LeetCode 1379 - Find a Corresponding Node of a Binary Tree in a Clone
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Traverse original and clone together until target node is reached.
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
public class FindaCorrespondingNodeofaBinaryTreeinaCloneofThatTree1379 {
    public static TreeNode res;

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // Important guard: this branch handles a boundary or constraint-critical condition.
        if (original == null || target == null) {
            return null;
        }
        // Recursive/helper call processes a smaller subproblem before combining results.
        helper(original, cloned, target);
        return res;
    }

    public static void helper(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || target == null) {
            return;
        }
        helper(original.left, cloned.left, target);
        if (original == target) {
            res = cloned;
        }
        helper(original.right, cloned.right, target);
    }

    // better method
    public final TreeNode getTargetCopy1(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || target == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }
}
