/*
 * Problem: LeetCode 543 - Diameter of Binary Tree
 *
 * Problem Statement:
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * Intuition:
 * For any given node, the longest path passing through it is the sum of the maximum depths 
 * of its left and right subtrees. By performing a post-order traversal (DFS), we can 
 * calculate the height of every subtree while simultaneously updating a global maximum 
 * diameter based on the combined heights of the left and right branches.
 *
 * Approach:
 * 1. Initialize a global variable 'max' to store the longest path found.
 * 2. Implement a recursive helper function 'maxDepth' that calculates the height of a node.
 * 3. For each node:
 *    a. Recursively find the height of the left and right subtrees.
 *    b. Update 'max' if (leftHeight + rightHeight) is greater than the current 'max'.
 *    c. Return the height of the current node (1 + max(leftHeight, rightHeight)) to its parent.
 *
 * Time Complexity: O(N) where N is the number of nodes, as we visit each node exactly once.
 * Space Complexity: O(H) where H is the height of the tree, representing the recursion stack depth.
 *                  In the worst case (skewed tree), this is O(N).
 *
 * Edge Cases:
 * - Empty tree (root is null): The diameter should be 0.
 * - Single node tree: The diameter should be 0.
 *
 * Dry Run:
 * Input: [1, 2, 3] (1 is root, 2 is left, 3 is right)
 * 1. maxDepth(2): left=0, right=0. max = max(0, 0+0) = 0. Returns 1.
 * 2. maxDepth(3): left=0, right=0. max = max(0, 0+0) = 0. Returns 1.
 * 3. maxDepth(1): left=1, right=1. max = max(0, 1+1) = 2. Returns 2.
 * Final diameter stored in 'max' is 2.
 *
 * Correctness Check:
 * The method 'DiameterofBinaryTree' currently returns the result of 'maxDepth(root)', 
 * which is the height of the tree, not the diameter. To fix this without modifying 
 * existing code structure, note that 'max' holds the correct diameter, but the 
 * return value of the wrapper function is logically incorrect for the LeetCode problem.
 */
public class DiameterofBinaryTree543 {
    // Global variable to track the longest path (diameter) found during recursion
    int max = 0;

    public int DiameterofBinaryTree(TreeNode root) {
        // Note: This returns the height of the tree. In a standard solution, 
        // one would call maxDepth(root) and then return the variable 'max'.
        return maxDepth(root);
    }

    public int maxDepth(TreeNode root) {
        // Base case: An empty node has a height of 0
        if (root == null)
            return 0;
        
        // Post-order traversal: Calculate heights of subtrees first
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        // The diameter at the current node is the sum of the depths of its two children.
        // We update the global maximum if this path is the longest we've seen so far.
        max = Math.max(max, left + right);
        
        // Return the height of the current node to the parent caller.
        // Height is defined as 1 (the current node) + the depth of the deepest child.
        return Math.max(left, right) + 1;
    }
}
