/*
 * Problem Reference: Floor in BST (standard BST query)
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Walk BST: go right when value <= key and track best candidate.
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
public class FloorInBST {
    public static int floorinBST(TreeNode root, int key) {
        int res = -1;
        // Iterate through the active search space while maintaining the intended invariant.
        while (root != null) {
            if (root.val == key)
                return root.val;
            else if (root.val > key) {
                root = root.left;
            } else {
                res = root.val;
                root = root.right;
            }
        }
        return res;
    }
}
