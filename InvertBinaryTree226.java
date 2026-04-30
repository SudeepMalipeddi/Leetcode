/*
 * Problem Statement: Given the root of a binary tree, swap every node's left and right child and return the new root.
 * Intuition: If each subtree is inverted, then swapping children at the current node completes inversion for that subtree.
 * Approach: Use DFS recursion; recurse into left and right subtrees, then swap root.left and root.right at the current node.
 * Time Complexity: O(n) because each node is visited once by the two recursive calls made per node.
 * Space Complexity: O(h) recursion stack, where h is tree height (O(log n) balanced, O(n) skewed).
 * Edge Cases handled: root == null returns null immediately; leaf nodes naturally swap null children.
 * Dry Run: root=4 with children (2,7). Recurse on 2 and 7 first, invert their subtrees, then swap at 4 => children become (7,2).
 * LeetCode matching: Matches LeetCode 226 (Invert Binary Tree).
 * Correctness Check: Recursion + swap order is valid; no correctness issue observed in this implementation.
 */

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){};
    TreeNode(int val){
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class InvertBinaryTree226 {
    public TreeNode invertTree(TreeNode root){
        // Empty subtree is already inverted.
        if(root == null) return null;
        // Invert left subtree in place.
        invertTree(root.left);
        // Invert right subtree in place.
        invertTree(root.right);

        // Swap child pointers at current node after children are processed.
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Return original root reference; tree is modified in place.
        return root;
    }    
}
