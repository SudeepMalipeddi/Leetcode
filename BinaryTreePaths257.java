/*
 * Problem: LeetCode 257. Binary Tree Paths
 *
 * Problem Statement:
 * Given a binary tree, return all root-to-leaf paths as strings with nodes separated
 * by "->".
 *
 * Intuition:
 * Each root-to-leaf path can be built by DFS, carrying the path string as we traverse.
 *
 * Approach:
 * 1. Perform DFS from the root.
 * 2. When a leaf is reached, append the accumulated path to the result list.
 * 3. Recurse on left and right children, extending the path with "->".
 *
 * Time Complexity: O(n) node visits, with extra string work proportional to path lengths.
 * Space Complexity: O(h) recursion stack, plus output storage.
 *
 * Edge Cases handled:
 * - Empty tree.
 * - Single node tree (path is just the root value).
 *
 * Dry Run:
 * Tree: [1,2,3,null,5]
 * Paths: "1->2->5", "1->3"
 *
 * Correctness Check:
 * DFS ensures every root-to-leaf path is constructed exactly once.
 *
 * LeetCode Match:
 * LeetCode 257 - "Binary Tree Paths".
 */
import java.util.*;

public class BinaryTreePaths257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        // Start DFS with an empty path prefix
        dfs(root, "", res);
        return res;
    }

    public void dfs(TreeNode root, String path, List<String> res) {
        // If we are at a leaf, finalize this path
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
            return;
        }
        // Extend path for left subtree
        if (root.left != null) {
            dfs(root.left, path + root.val + "->", res);
        }
        // Extend path for right subtree
        if (root.right != null) {
            dfs(root.right, path + root.val + "->", res);
        }
    }
}
