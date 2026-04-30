/*
 * Problem: 111. Minimum Depth of Binary Tree
 *
 * Problem Statement:
 * Find the minimum depth of a binary tree, which is defined as the number of nodes 
 * along the shortest path from the root node down to the nearest leaf node.
 * A leaf is a node with no children.
 *
 * Intuition:
 * The "minimum depth" is tricky because a path must end at a leaf. If we simply 
 * took Math.min(left, right) + 1, a node with only one child would return a 
 * depth of 1 (treating the null child as a path of length 0), which is incorrect 
 * because the null side is not a leaf. We must ensure we only consider paths 
 * that actually reach a leaf.
 *
 * Approach:
 * 1. Base Case: If the current node is null, its depth is 0.
 * 2. Recursive Step: Calculate the minimum depth of the left and right subtrees.
 * 3. Logic for Leaf Paths:
 *    - If one child is null (depth 0), we cannot use that path. We must take 
 *       the depth of the existing child and add 1.
 *    - If both children exist, we take the minimum of the two depths and add 1.
 *    - If both are null (leaf node), the formula (left + right + 1) correctly returns 1.
 *
 * Time Complexity: O(N) where N is the number of nodes in the tree, as we must visit each node once.
 * Space Complexity: O(H) where H is the height of the tree, representing the maximum stack depth. 
 *                  In the worst case (skewed tree), this is O(N).
 *
 * Edge Cases:
 * - Empty tree: Returns 0.
 * - Skewed tree (linked list style): Correctly ignores the null side and traverses the full branch.
 * - Single node tree: Returns 1.
 *
 * Dry Run:
 * Tree: [1, 2, null] (1 is root, 2 is left child, right is null)
 * 1. minDepth(1): calls minDepth(2) and minDepth(null).
 * 2. minDepth(null) returns 0.
 * 3. minDepth(2): calls minDepth(null) [left] and minDepth(null) [right].
 * 4. Both return 0. (left == 0 || right == 0) is true. Returns 0 + 0 + 1 = 1.
 * 5. Back at minDepth(1): left = 1, right = 0.
 * 6. (left == 0 || right == 0) is true. Returns 1 + 0 + 1 = 2.
 * Result: 2 (Correct, path is 1->2).
 *
 * Correctness Check:
 * The solution is correct. The ternary operator handles the "one-child" vs "two-child" 
 * logic efficiently without needing multiple if-else blocks.
 */

class MinimumDepthofBinaryTree111{
    public int minDepth(TreeNode root){
        // Base case: An empty tree or reaching beyond a leaf node.
        if(root == null) return 0;

        // Post-order traversal: Get results from subtrees first.
        int left = minDepth(root.left);
        int right = minDepth(root.right);

        /* 
         * Logic breakdown:
         * 1. If left == 0, it means the left child is null. We must take the right path.
         * 2. If right == 0, it means the right child is null. We must take the left path.
         * 3. If both are 0, the node is a leaf; left + right + 1 = 1.
         * 4. If both are > 0, the node has two children; we take the minimum of the two.
         */
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
}
