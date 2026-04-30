/*
 * Problem: LeetCode 257 - Binary Tree Paths
 *
 * Problem Statement:
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 * A leaf is a node with no children.
 *
 * Intuition:
 * To find all paths from root to leaves, we need to traverse the tree and keep track of the 
 * sequence of nodes visited. Depth-First Search (DFS) is a natural fit because it explores 
 * one branch fully before moving to the next, allowing us to build the path string as we go.
 *
 * Approach:
 * 1. Initialize an empty list to store the final path strings.
 * 2. Use a recursive DFS helper function that takes the current node, the path built so far, and the result list.
 * 3. If the current node is a leaf, append its value to the path and add the complete path to the result list.
 * 4. If the current node is not a leaf, append its value and "->" to the path, then recurse into its children.
 *
 * Time Complexity: O(N^2) in the worst case (skewed tree). 
 * For each node, we perform string concatenation which takes O(L) time where L is the path length. 
 * In a balanced tree, this is closer to O(N log N).
 *
 * Space Complexity: O(N). 
 * The recursion stack depth is equal to the height of the tree (H). In the worst case (skewed tree), H = N. 
 * Each stack frame also stores a string representing the path.
 *
 * Edge Cases:
 * - Root is null: Return an empty list.
 * - Single node tree: Return a list containing just the root's value as a string.
 *
 * Dry Run:
 * Input: root = [1, 2, 3, null, 5]
 * 1. dfs(node 1, path ""): Not leaf. Calls dfs(node 2, "1->") and dfs(node 3, "1->").
 * 2. dfs(node 2, path "1->"): Not leaf. Calls dfs(node 5, "1->2->").
 * 3. dfs(node 5, path "1->2->"): Leaf! Adds "1->2->5" to res. Returns.
 * 4. dfs(node 3, path "1->"): Leaf! Adds "1->3" to res. Returns.
 * Result: ["1->2->5", "1->3"]
 *
 * Correctness Check:
 * The solution correctly identifies leaf nodes and uses the immutable nature of Strings in Java 
 * to "backtrack" automatically (each recursive call gets its own copy of the path string).
 */
import java.util.*;

public class BinaryTreePaths257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        // Base case: if the tree is empty, return the empty result list
        if (root == null)
            return res;
        dfs(root, "", res); // build paths recursively
        return res;
    }

    public void dfs(TreeNode root, String path, List<String> res) {
        // Base case for recursion: if the current node is a leaf
        if (root.left == null && root.right == null) {
            // Append the final node value to the path and add to results
            res.add(path + root.val); // leaf completes a path
            return;
        }
        
        // If there is a left child, continue the DFS down the left branch
        if (root.left != null) {
            // Append current node value and arrow to the path string
            // Note: String concatenation creates a new String, which handles state for us
            dfs(root.left, path + root.val + "->", res); // extend path to left
        }
        
        // If there is a right child, continue the DFS down the right branch
        if (root.right != null) {
            // The 'path' variable here still holds the original value before the left call
            dfs(root.right, path + root.val + "->", res); // extend path to right
        }
    }
}
