/*
 * Problem: Path to Given Node (Binary Tree)
 *
 * Problem Statement:
 * Given a binary tree and a target value 'b', find the path from the root node 
 * to the node containing the value 'b'. Return the path as a list of integers.
 *
 * Intuition:
 * This is a classic backtracking problem on a tree. We explore a path using 
 * Depth First Search (DFS). If the path leads to our target, we preserve the 
 * list; if we hit a dead end (a leaf or a subtree without the target), we 
 * "backtrack" by removing the current node from our path list and returning false.
 *
 * Approach:
 * 1. Initialize an empty list to store the path.
 * 2. Use a recursive helper function (getPath) to traverse the tree.
 * 3. At each node, add the node's value to the list.
 * 4. If the current node is the target, return true to signal success up the call stack.
 * 5. Recursively search the left and right subtrees.
 * 6. If the target is found in either subtree (indicated by a true return), keep the list as is.
 * 7. If the target is not found in either subtree, remove the current node (backtrack) and return false.
 *
 * Time Complexity: O(N), where N is the number of nodes in the tree. In the worst case, 
 * we visit every node once.
 * Space Complexity: O(H), where H is the height of the tree. This space is used by 
 * the recursion stack and the result list. In a skewed tree, H = N.
 *
 * Edge Cases:
 * - Root is null: Handled by returning an empty list.
 * - Target is the root: Handled by the first value check.
 * - Target does not exist: The list will be empty after all backtracking.
 *
 * Dry Run:
 * Tree: [1, 2, 3], Target: 3
 * 1. solve(root, 3) calls getPath(1, [], 3).
 * 2. getPath(1): res=[1]. 1 != 3. Call getPath(2).
 * 3. getPath(2): res=[1, 2]. 2 != 3. Call getPath(null) -> false.
 * 4. getPath(2): Backtrack! res.remove(2). res=[1]. Return false.
 * 5. getPath(1): Call getPath(3).
 * 6. getPath(3): res=[1, 3]. 3 == 3. Return true.
 * 7. getPath(1): Receives true from right child. Returns true.
 * Result: [1, 3]
 *
 * Correctness Check:
 * The solution is correct. It uses the standard backtracking pattern for tree paths.
 * Note: If the tree contains duplicate values, this returns the path to the first 
 * instance of 'b' encountered in a pre-order traversal.
 */

import java.util.*;

public class RootToNode {
    public static List<Integer> solve(TreeNode root, int b) {
        List<Integer> res = new ArrayList<>();
        // Basic safety check for an empty tree
        if (root == null)
            return res;
        getPath(root, res, b);
        return res;
    }

    public static boolean getPath(TreeNode root, List<Integer> res, int b) {
        // Base case: reached the end of a branch without finding the target
        if (root == null)
            return false;

        // Tentatively add the current node to the path
        res.add(root.val);

        // Success case: current node is the target
        if (root.val == b)
            return true;

        // Recursive step: check if the target exists in the left or right subtrees.
        // Short-circuiting: if getPath(left) is true, we don't even evaluate getPath(right).
        if (getPath(root.left, res, b) || getPath(root.right, res, b))
            return true;

        // Backtracking step: if we reach here, the target was not found in this 
        // node's subtrees. Remove the current node from the path list.
        res.remove(res.size() - 1);
        return false;
    }
}
