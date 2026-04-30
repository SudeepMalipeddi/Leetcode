/*
 * Problem: LeetCode 124 - Binary Tree Maximum Path Sum
 *
 * Problem Statement:
 * Given a binary tree, find the maximum path sum. A path is defined as any sequence of nodes 
 * from some starting node to any node in the tree along the parent-child connections. 
 * The path must contain at least one node and does not need to go through the root.
 *
 * Intuition:
 * For any node to be part of the maximum path, it can either be the "highest" point (the peak) 
 * of the path, connecting its left and right subtrees, or it can be a single step in a 
 * path that continues up to its parent. Since we want the maximum sum, we only include 
 * subtrees if they contribute a positive value.
 *
 * Approach:
 * 1. Initialize a global variable `max` to the smallest possible integer to track the overall result.
 * 2. Implement a recursive helper function `maxGain` that performs a post-order traversal.
 * 3. For each node, calculate the maximum gain from its left and right children. If a child's 
 *    gain is negative, treat it as 0 (effectively ignoring that branch).
 * 4. At each node, calculate the "price" of a path that peaks at this node: `node.val + leftGain + rightGain`.
 * 5. Update the global `max` if this local path sum is larger.
 * 6. Return `node.val + max(leftGain, rightGain)` to the parent, as a path can only extend 
 *    through one child to maintain the path property.
 *
 * Time Complexity: O(N) where N is the number of nodes, as we visit each node exactly once.
 * Space Complexity: O(H) where H is the height of the tree, due to the recursion stack. 
 * In the worst case (skewed tree), this is O(N).
 *
 * Edge Cases:
 * - A tree with only one node (returns that node's value).
 * - A tree with all negative values (the global max will be the value of the single largest node).
 * - A tree where the max path is contained entirely within a subtree.
 *
 * Dry Run:
 * Input: [1, 2, 3]
 * 1. maxGain(2): left=0, right=0. max = max(-inf, 0+0+2) = 2. Returns 2.
 * 2. maxGain(3): left=0, right=0. max = max(2, 0+0+3) = 3. Returns 3.
 * 3. maxGain(1): left=2, right=3. max = max(3, 2+3+1) = 6. Returns 1+max(2,3) = 4.
 * Result: 6.
 *
 * Correctness Check:
 * The solution is correct. It properly handles negative nodes by using Math.max(..., 0) 
 * for child gains and initializes the global maximum to handle trees where all paths are negative.
 */
public class BinaryTreeMaximumPathSum124 {
    // Global variable to track the maximum path sum found during recursion
    int max = Integer.MIN_VALUE; 

    public int maxPathSum(TreeNode root) {
        maxGain(root); // Start the recursive DFS traversal
        return max;
    }

    public int maxGain(TreeNode root) {
        // Base case: an empty node contributes nothing to the path sum
        if (root == null)
            return 0;

        // Recursive call on children. 
        // We use Math.max(..., 0) because if a subtree sum is negative, 
        // it's better to not include it in our path at all.
        int left = Math.max(maxGain(root.left), 0); 
        int right = Math.max(maxGain(root.right), 0); 

        // Check if the path that "turns" at the current node is the new global maximum.
        // This path includes the node itself and the best gains from both subtrees.
        max = Math.max(max, left + right + root.val); 

        // Return the maximum gain this node can provide to its parent.
        // To be a valid path for the parent, we can only pick ONE child branch 
        // (either left or right) to add to the current node's value.
        return Math.max(left, right) + root.val;
    }
}
