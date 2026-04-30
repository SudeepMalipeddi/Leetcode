/*
 * Problem Statement: Return the minimum depth of a binary tree (shortest path from root to any leaf).
 * Intuition: Depth is 1 + depth of the relevant child subtree; null child must be handled carefully.
 * Approach: Recursively compute left and right depths; if one side is missing, use the other side only.
 * Time Complexity: O(n), visiting each node once.
 * Space Complexity: O(h) recursion stack, where h is tree height.
 * Edge Cases handled: Empty tree (0); single-node tree (1); nodes with exactly one child.
 * Dry Run: root(1) with only right child(2) -> left=0,right=1 => result=2 (not 1), which is correct.
 * LeetCode matching/assumption: Matches LeetCode 111 (Minimum Depth of Binary Tree).
 * Correctness Check: The `(left==0 || right==0)` branch avoids wrongly choosing depth 0 from a missing child.
 */

class MinimumDepthofBinaryTree111{
    public int minDepth(TreeNode root){
        // Empty tree has depth 0.
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // If one child is missing, depth must come from the non-null child.
        // Otherwise take smaller leaf depth from both children.
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
}
