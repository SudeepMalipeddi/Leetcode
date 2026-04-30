/*
 * Problem: 101. Symmetric Tree
 *
 * Problem Statement:
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * Intuition:
 * A tree is symmetric if its left subtree is a mirror image of its right subtree. This means 
 * that at every level, the values must match in a mirrored fashion: the left side's left 
 * child must match the right side's right child, and the left side's right child must 
 * match the right side's left child.
 *
 * Approach:
 * 1. Use a recursive helper function (DFS) that takes two nodes representing mirrored positions.
 * 2. Base Case 1: If both nodes are null, they are symmetric.
 * 3. Base Case 2: If only one node is null or the values don't match, they are not symmetric.
 * 4. Recursive Step: Check the "outer" children (left.left and right.right) and the "inner" 
 *    children (left.right and right.left) simultaneously.
 *
 * Time Complexity: O(N), where N is the number of nodes in the tree, because we visit every node once.
 * Space Complexity: O(H), where H is the height of the tree, representing the maximum depth of the recursion stack.
 *
 * Edge Cases:
 * - Empty tree: The current code will throw a NullPointerException if root is null.
 * - Single node tree: Returns true (correct).
 * - Tree with only left or only right children: Returns false (correct).
 *
 * Dry Run:
 * Input: [1, 2, 2, 3, 4, 4, 3]
 * 1. isSymmetric(1) calls dfs(left:2, right:2).
 * 2. dfs(2, 2): Values match. 
 *    - Calls dfs(left.left:3, right.right:3) -> returns true.
 *    - Calls dfs(left.right:4, right.left:4) -> returns true.
 * 3. Both return true, so the root call returns true.
 *
 * Correctness Check:
 * The logic for mirroring is correct. Note: The code assumes 'root' is not null. In a production 
 * environment or on LeetCode (depending on constraints), a `if (root == null) return true;` 
 * check should be added at the start of isSymmetric.
 */
public class SymmetricTree101 {
    public boolean isSymmetric(TreeNode root) {
        // Start the recursive comparison between the left and right subtrees of the root
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode left, TreeNode right) {
        // Base case: If both nodes are null, we've reached the end of both branches symmetrically
        if (left == null && right == null)
            return true;
        
        // If one is null and the other isn't, the tree structure is not mirrored
        if (left == null || right == null)
            return false;
        
        // If the current node values match, we must check their children
        if (left.val == right.val) {
            // To be a mirror:
            // 1. The left child of the left node must match the right child of the right node (outer)
            // 2. The right child of the left node must match the left child of the right node (inner)
            return dfs(left.left, right.right) && dfs(left.right, right.left);
        }
        
        // If values do not match, it's not symmetric
        return false;
    }
}
