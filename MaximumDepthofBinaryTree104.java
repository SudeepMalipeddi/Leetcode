/*
 * Problem Statement: Compute the maximum depth (root-to-leaf node count) of a binary tree.
 * Intuition: Depth at each node equals 1 plus the deeper depth of its two children.
 * Approach: Recursive DFS: return 0 for null, recursively compute left/right depths, then return max(left, right) + 1.
 * Time Complexity: O(n), each node is visited once.
 * Space Complexity: O(h) recursion stack, where h is tree height.
 * Edge Cases handled: Empty tree (depth 0), skewed trees, and balanced trees.
 * Dry Run: For root 3 with children 9 and 20, and 20 having 15 and 7, leaf depth is 3, so answer is 3.
 * LeetCode matching/assumption: Matches LeetCode 104 depth definition.
 * Correctness Check: Base case and recursive relation together satisfy depth definition for all subtrees.
 */

class MaximumDepthofBinaryTree104 {
    public int maxDepth(TreeNode root) {
        // Base case: empty subtree contributes depth 0.
        if (root == null) {
            return 0;
        }
        // Recursively compute subtree depths.
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        // Current node adds one level above deeper subtree.
        return Math.max(left, right) + 1;
    }

    public static void main(String args[]) {
        MaximumDepthofBinaryTree104 obj = new MaximumDepthofBinaryTree104();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        // root.left.left = new TreeNode(4);
        // root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(obj.maxDepth(root));
    }
}
