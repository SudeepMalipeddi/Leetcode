/*
 * Problem Statement: In a binary tree, return the lowest node that has both p and q in its subtree (allowing a node to be its own ancestor).
 * Intuition: Recursively search left and right; when both sides report a hit, current root is the split point (LCA).
 * Approach: Base-case on null/p/q, recurse into children, and merge results: return non-null child if only one side found, else return root.
 * Time Complexity: O(n), each node is visited once.
 * Space Complexity: O(h) recursion stack, where h is tree height.
 * Edge Cases handled: Empty tree, p==root, q==root, and when one target is ancestor of the other.
 * Dry Run: If p is in left subtree and q in right subtree of node X, left!=null and right!=null at X, so X is returned.
 * LeetCode matching/assumption: Matches LeetCode 236 recursive LCA for general binary trees (not BST-specific).
 * Correctness Check: Returned node is lowest common ancestor because recursion only bubbles up found targets or first split point.
 */

public class LowestCommonAncestorofaBinaryTree236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Stop if subtree is empty or current node matches one target.
        if (root == null || root == p || root == q) {
            return root;
        }
        // Search both subtrees for p and q.
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // If only right subtree has a target, bubble it up.
        if (left == null)
            return right;
        // If only left subtree has a target, bubble it up.
        else if (right == null)
            return left;
        // If both sides found a target, current root is LCA.
        else
            return root;
    }
}
