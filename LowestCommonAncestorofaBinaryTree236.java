/*
 * Problem: 236. Lowest Common Ancestor of a Binary Tree
 *
 * Problem Statement:
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes, p and q.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined 
 * between two nodes p and q as the lowest node in T that has both p and q as descendants 
 * (where we allow a node to be a descendant of itself).”
 *
 * Intuition:
 * This is a bottom-up search problem. We traverse the tree using recursion. If a node 
 * is either p or q, it might be the LCA or a part of the path to it. If a node receives 
 * a non-null signal from both its left and right subtrees, it means p is in one subtree 
 * and q is in the other, making the current node the "lowest" split point (the LCA).
 *
 * Approach:
 * 1. Base Case: If the current node is null, or if it matches p or q, return the current node.
 * 2. Recursive Step: Search for p and q in the left and right subtrees.
 * 3. Result Aggregation:
 *    - If both left and right recursive calls return non-null, the current node is the LCA.
 *    - If only one call returns non-null, return that non-null value (it's either p, q, or the LCA found deeper).
 *    - If both are null, return null.
 *
 * Time Complexity: O(N), where N is the number of nodes in the binary tree. In the worst case, we visit every node.
 * Space Complexity: O(H), where H is the height of the tree. This is the space used by the recursion stack. 
 * In a skewed tree, this could be O(N); in a balanced tree, it is O(log N).
 *
 * Edge Cases:
 * - p is the ancestor of q (or vice versa): The ancestor will be returned as soon as it is found.
 * - p or q is the root: The root will be returned immediately.
 * - p and q are in different subtrees: The first common parent will be identified as the LCA.
 *
 * Dry Run:
 * Tree: [3, 5, 1, 6, 2, 0, 8], p=5, q=1
 * 1. LCA(3, 5, 1) calls LCA(5, 5, 1) and LCA(1, 5, 1).
 * 2. LCA(5, 5, 1) hits base case (root == p), returns 5.
 * 3. LCA(1, 5, 1) hits base case (root == q), returns 1.
 * 4. At node 3, left=5 and right=1. Since both are non-null, return 3.
 *
 * Correctness Check:
 * The solution is correct. It leverages the post-order traversal property to find the 
 * lowest node where the two targets converge.
 */

public class LowestCommonAncestorofaBinaryTree236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: If we reach a leaf (null) or find one of the target nodes (p or q),
        // we return the current node. This signals to the parent that a target was found.
        if (root == null || root == p || root == q) {
            return root;
        }

        // Post-order traversal: Explore the subtrees first.
        // Look for p and q in the left subtree.
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // Look for p and q in the right subtree.
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // After exploring subtrees, we decide what to return to the parent:
        
        // If the left subtree search returned null, it means neither p nor q 
        // exists in the left branch. Therefore, whatever the right branch 
        // found (either a target node, the LCA, or null) is our result.
        if (left == null)
            return right;
        
        // Similarly, if the right subtree search returned null, it means 
        // neither p nor q exists in the right branch. Return the left result.
        else if (right == null)
            return left;
        
        // If both left and right are non-null, it means one target (p or q) 
        // was found in the left subtree and the other was found in the right.
        // This makes the current 'root' the Lowest Common Ancestor.
        else
            return root;
    }
}
