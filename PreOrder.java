/*
 * Problem: LeetCode 144 - Binary Tree Preorder Traversal
 *
 * Problem Statement:
 * Given the root of a binary tree, return the preorder traversal of its nodes'
 * values (root, left, right). Both recursive and iterative solutions are provided.
 *
 * Intuition:
 * Preorder traversal follows the natural DFS order: visit the current node first,
 * then recursively traverse the left subtree, then the right subtree. For the
 * iterative version, a stack mimics recursion: push right child first so left
 * child is popped and processed next (LIFO property).
 *
 * Approach:
 *   Recursive (preorderTraversal + traversal helper):
 *   1. Create an ArrayList `res` to collect node values.
 *   2. Call helper `traversal(root, res)` which:
 *      - Returns immediately if `root == null`.
 *      - Adds `root.val` to `res`.
 *      - Recursively calls `traversal(root.left, res)`.
 *      - Recursively calls `traversal(root.right, res)`.
 *   3. Return `res`.
 *
 *   Iterative (preOrderTraversal):
 *   1. Create an ArrayList `res` and an empty Stack<TreeNode> `stack`.
 *   2. If `root == null`, return empty `res`.
 *   3. Push `root` onto `stack`.
 *   4. While `stack` is not empty:
 *      - Pop `curr` from stack, add `curr.val` to `res`.
 *      - If `curr.right != null`, push `curr.right` (pushed first so popped last).
 *      - If `curr.left != null`, push `curr.left`.
 *   5. Return `res`.
 *
 * Time Complexity: O(n) — each node is visited exactly once in both approaches.
 * Space Complexity: O(h) recursive (call stack depth = tree height) or O(n)
 *                   iterative worst case (skewed tree fills stack with all nodes).
 *
 * Edge Cases:
 * - Null root: both methods return an empty list immediately.
 * - Single node tree: returns a list with that single value.
 * - Left-skewed or right-skewed trees handled correctly by both approaches.
 *
 * Dry Run:
 * Input: root = [1, null, 2, 3] (1 has right child 2, 2 has left child 3)
 *
 * Recursive:
 *   traversal(1): add 1, call traversal(null), call traversal(2)
 *   traversal(2): add 2, call traversal(3), call traversal(null)
 *   traversal(3): add 3
 *   res = [1, 2, 3]
 *
 * Iterative:
 *   stack = [1]
 *   pop 1 -> res=[1], push right(2)
 *   stack = [2]
 *   pop 2 -> res=[1,2], push left(3)
 *   stack = [3]
 *   pop 3 -> res=[1,2,3], no children
 *   Output: [1, 2, 3]
 *
 * Correctness Check:
 * Preorder definition (root->left->right) is directly encoded. The recursive
 * method follows the definition literally. The iterative method simulates the
 * same order by pushing right before left onto the LIFO stack, guaranteeing
 * left is processed before right.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class PreOrder {
    public void traversal(TreeNode root, List<Integer> res) {
        
        if (root == null)
            return;
        
        res.add(root.val);
        
        traversal(root.left, res);
        
        traversal(root.right, res);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        
        List<Integer> res = new ArrayList<>();
        
        traversal(root, res);
        
        return res;
    }

    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            
            // Push right first so left is processed next (LIFO stack).
            if (curr.right != null)
                stack.push(curr.right);
            
            if (curr.left != null)
                stack.push(curr.left);
        }
        return res;
    }
}
