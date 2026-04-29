/*
 * Problem: LeetCode 257 - Binary Tree Paths
 * Problem Statement: Return all root-to-leaf paths in a binary tree as strings
 *   in the format "a->b->c".
 * Intuition: A depth-first traversal accumulates the path string until a leaf.
 * Approach:
 *   1) Start DFS from the root with an empty path.
 *   2) When a leaf is reached, append the built path to results.
 *   3) Recurse into left and right children, extending the path with "->".
 * Time Complexity: O(n * L) where L is average path length (string building).
 * Space Complexity: O(h) recursion stack plus output storage.
 * Edge Cases: Empty tree, single node tree.
 * Dry Run: root=1, left=2 (right=5), right=3 -> paths "1->2->5", "1->3".
 * Correctness Check: Every root-to-leaf path is visited once, and only leaves
 *   contribute to the output list.
 */
import java.util.*;

public class BinaryTreePaths257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        dfs(root, "", res); // build paths recursively
        return res;
    }

    public void dfs(TreeNode root, String path, List<String> res) {
        if (root.left == null && root.right == null) {
            res.add(path + root.val); // leaf completes a path
            return;
        }
        if (root.left != null) {
            dfs(root.left, path + root.val + "->", res); // extend path to left
        }
        if (root.right != null) {
            dfs(root.right, path + root.val + "->", res); // extend path to right
        }
    }
}
