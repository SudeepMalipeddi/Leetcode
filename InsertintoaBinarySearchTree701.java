/*
 * Problem Reference: LeetCode 701 - Insert into a Binary Search Tree
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Recurse/iterate down BST until null child and insert node.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(h)
 *
 * Space Complexity:
 * O(h) recursion or O(1) iterative
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
public class InsertintoaBinarySearchTree701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // Important guard: this branch handles a boundary or constraint-critical condition.
        if (root == null)
            return new TreeNode(val);
        TreeNode cur = root;
        // Iterate through the active search space while maintaining the intended invariant.
        while (true) {
            if (cur.val <= val) {
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    // Recursive/helper call processes a smaller subproblem before combining results.
                    cur.right = new TreeNode(val);
                    break;
                }
            } else {
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}
