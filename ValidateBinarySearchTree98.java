/*
 * Problem: 98. Validate Binary Search Tree
 *
 * Problem Statement:
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined such that the left subtree of a node contains only nodes with keys less than the node's key,
 * and the right subtree contains only nodes with keys greater than the node's key.
 *
 * Intuition:
 * A common mistake is only checking if a node's value is greater than its left child and smaller than its right child.
 * However, all nodes in the left subtree must be smaller than the root, and all nodes in the right subtree must be larger.
 * To enforce this, we must pass down a valid range (min, max) that each node's value must fall within.
 *
 * Approach:
 * 1. Use a recursive helper function that accepts the current node and its allowed range [min, max].
 * 2. For the root, the range is (-infinity, +infinity). We use Integer objects (null) to represent infinity.
 * 3. For every left child, the maximum allowed value becomes the current node's value.
 * 4. For every right child, the minimum allowed value becomes the current node's value.
 * 5. If any node violates its range, the tree is not a valid BST.
 *
 * Time Complexity: O(N), where N is the number of nodes in the tree, as we visit each node exactly once.
 * Space Complexity: O(H), where H is the height of the tree, representing the maximum depth of the recursion stack.
 *
 * Edge Cases:
 * - Empty tree (root is null): Valid BST.
 * - Single node tree: Valid BST.
 * - Nodes with Integer.MIN_VALUE or Integer.MAX_VALUE: Handled by using Integer objects/null instead of primitives.
 *
 * Dry Run:
 * Tree: [5, 1, 4, null, null, 3, 6]
 * 1. helper(5, null, null) -> Valid.
 * 2. helper(1, null, 5) -> Valid (1 < 5).
 * 3. helper(4, 5, null) -> Invalid (4 is not > 5). Returns false.
 *
 * Correctness Check:
 * The solution is correct. It properly handles the strict inequality requirements of a BST and uses 
 * null-safe comparisons to avoid issues with integer overflow or boundary values.
 */

public class ValidateBinarySearchTree98 {
    public boolean isValidBST(TreeNode root) {
        // Start recursion with no initial constraints (null represents infinity)
        return helper(root, null, null);
    }

    /**
     * Recursive helper to validate the BST property using a range.
     * @param root The current node being inspected.
     * @param min The exclusive lower bound for the current node's value.
     * @param max The exclusive upper bound for the current node's value.
     */
    boolean helper(TreeNode root, Integer min, Integer max) {
        // Base case: An empty tree or reaching a leaf's child is always a valid BST
        if (root == null) {
            return true;
        }

        // Check if the current node's value violates the min/max constraints.
        // Note: BST definition usually requires strictly less/greater than, so we check <= and >=.
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
            return false;
        }

        // Recurse to children:
        // For the left child, the value must be less than the current node's value (new max).
        // For the right child, the value must be greater than the current node's value (new min).
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}
