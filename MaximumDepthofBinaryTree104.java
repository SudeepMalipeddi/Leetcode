/*
 * Problem: LeetCode 104 - Maximum Depth of Binary Tree
 *
 * Problem Statement:
 * Given the root of a binary tree, return its maximum depth. The maximum depth is the number of nodes
 * along the longest path from the root node down to the farthest leaf node.
 *
 * Intuition:
 * The depth of any node is 1 plus the maximum depth of its children. A null node contributes depth 0.
 * This recursive definition directly maps to a post-order DFS traversal. The pattern is divide-and-conquer:
 * compute depths of left and right subtrees independently, then combine.
 *
 * Approach:
 *   1. Base case: if root == null, return 0 (empty tree has depth 0).
 *   2. Recursively compute left = maxDepth(root.left) — the depth of the left subtree.
 *   3. Recursively compute right = maxDepth(root.right) — the depth of the right subtree.
 *   4. Return Math.max(left, right) + 1 — the deeper subtree plus the current node.
 *
 * Time Complexity: O(n) — each node in the tree is visited exactly once.
 * Space Complexity: O(h) — recursion stack depth equals tree height h (worst-case O(n) for skewed,
 *   O(log n) for balanced).
 *
 * Edge Cases:
 * - Empty tree (root == null): base case returns 0 immediately.
 * - Single-node tree: left=0, right=0, returns 1. Correct.
 * - Skewed tree (all left children): recursion goes n levels deep, returns n. Correct.
 * - Balanced tree: depth computed correctly via max of subtrees.
 *
 * Dry Run:
 * Tree: root=3, left=9, right=20, 20.left=15, 20.right=7
 * maxDepth(3):
 *   left = maxDepth(9):
 *     left = maxDepth(null) = 0
 *     right = maxDepth(null) = 0
 *     return max(0,0)+1 = 1
 *   right = maxDepth(20):
 *     left = maxDepth(15):
 *       left = maxDepth(null) = 0, right = maxDepth(null) = 0, return 1
 *     right = maxDepth(7):
 *       left = maxDepth(null) = 0, right = maxDepth(null) = 0, return 1
 *     return max(1,1)+1 = 2
 *   return max(1,2)+1 = 3
 * Output: 3.
 *
 * Correctness Check:
 * The recursive formula depth(node) = 1 + max(depth(node.left), depth(node.right)) with base case
 * depth(null)=0 correctly computes the longest root-to-leaf path length for any binary tree by
 * structural induction on the tree height.
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
