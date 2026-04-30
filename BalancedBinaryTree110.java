/*
 * Problem: LeetCode 110 - Balanced Binary Tree
 *
 * Problem Statement:
 * Given a binary tree, determine if it is height-balanced. A height-balanced binary tree 
 * is defined as a binary tree in which the left and right subtrees of every node 
 * differ in height by no more than 1.
 *
 * Intuition:
 * A naive top-down approach calculates height repeatedly for each node, leading to O(N^2).
 * The optimal approach uses a bottom-up DFS (post-order traversal). By calculating 
 * heights from the leaves up, we can check the balance condition at each node 
 * simultaneously. If any subtree is found to be unbalanced, we "short-circuit" the 
 * calculation by returning a sentinel value (-1).
 *
 * Approach:
 * 1. Use a recursive helper function `height(node)` that returns the height of the tree.
 * 2. Base case: An empty node has a height of 0.
 * 3. Recursive step: Get the height of the left and right subtrees.
 * 4. If the left subtree is unbalanced (-1), the right subtree is unbalanced (-1), 
 *    or the current node's subtrees differ by more than 1, return -1 immediately.
 * 5. Otherwise, return the actual height: 1 + max(leftHeight, rightHeight).
 * 6. In the main function, check if the result is not -1.
 *
 * Time Complexity: O(n) where n is the number of nodes, as we visit each node exactly once.
 * Space Complexity: O(h) where h is the height of the tree, representing the recursion 
 * stack depth. In the worst case (skewed tree), this is O(n).
 *
 * Edge Cases:
 * - Empty tree: Should return true (height 0 is not -1).
 * - Single node: Should return true.
 * - Highly skewed tree: Should return false if height difference > 1.
 *
 * Dry Run:
 * Tree: [1, 2, 2, 3, 3, null, null, 4, 4] (Left-heavy)
 * 1. height(4) returns 1.
 * 2. height(3) sees left child (4) is height 1, right child (4) is height 1. Returns 2.
 * 3. height(2) sees left child (3) is height 2, right child (3) is height 2. Returns 3.
 * 4. height(root=1) sees left child (2) is height 3, right child (2) is height 1.
 * 5. |3 - 1| = 2, which is > 1. Returns -1.
 * 6. isBalanced returns false.
 *
 * Correctness Check:
 * The solution is correct. The use of -1 as a sentinel value effectively bubbles up 
 * the "unbalanced" state from any depth to the root, ensuring O(n) efficiency.
 */
public class BalancedBinaryTree110 {
    public Boolean isBalanced(TreeNode root) {
        // If the helper returns -1, it means an imbalance was detected somewhere in the tree.
        return height(root) != -1;
    }

    public int height(TreeNode root) {
        // Base case: An empty tree has a height of 0 and is balanced.
        if (root == null) {
            return 0;
        }

        // Post-order traversal: Visit children before the current node.
        int leftHeight = height(root.left); // height of left subtree
        
        // Optimization: If the left subtree is already unbalanced, no need to check the right.
        int rightHeight = height(root.right); // height of right subtree

        /* 
         * Check three conditions for imbalance:
         * 1. Left subtree returned -1 (already unbalanced).
         * 2. Right subtree returned -1 (already unbalanced).
         * 3. Current node is unbalanced (height difference > 1).
         */
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // If balanced, return the standard height of this node.
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
