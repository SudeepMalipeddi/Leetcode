/*
 * Problem: LeetCode 545 - Boundary of Binary Tree
 *
 * Problem Statement:
 * Given a binary tree, return the values of its boundary in anti-clockwise order starting from the root. 
 * The boundary includes the root, the left boundary (excluding leaf nodes), all leaves from left to right, 
 * and the right boundary (excluding leaf nodes) in reverse order.
 *
 * Intuition:
 * The boundary traversal can be decomposed into four distinct parts: the root, the left boundary, 
 * the leaves, and the right boundary. By handling these separately, we avoid complex conditional 
 * logic inside a single traversal and ensure the correct anti-clockwise order.
 *
 * Approach:
 * 1. Add the root to the result (if it's not a leaf).
 * 2. Traverse the left boundary: Start from root.left. Move left if possible, otherwise move right. 
 *    Add nodes to the list only if they are not leaves.
 * 3. Traverse all leaves: Use a standard Depth First Search (DFS) to find all leaf nodes from left to right.
 * 4. Traverse the right boundary: Start from root.right. Move right if possible, otherwise move left. 
 *    Use a stack to store nodes (excluding leaves) and then pop them to achieve the reverse (bottom-up) order.
 *
 * Time Complexity: O(N) where N is the number of nodes in the tree. Each node is visited at most a constant number of times.
 * Space Complexity: O(H) where H is the height of the tree. This is for the recursion stack in leavesHelper 
 * and the explicit stack used in rightHelper. In the worst case (skewed tree), H = N.
 *
 * Edge Cases:
 * - Empty tree (root == null): Handled by the initial null check.
 * - Single node tree: Handled by the isLeaf check; the root is added and helpers do nothing.
 * - Tree with only left or only right children: The logic correctly navigates the available paths.
 *
 * Dry Run:
 * Input: [1, 2, 3, 4, 5, 6, 7]
 * 1. Root (1) is not leaf -> res = [1]
 * 2. leftHelper: curr starts at 2. 2 is not leaf -> res = [1, 2]. curr moves to 4. 4 is leaf -> loop ends.
 * 3. leavesHelper: DFS finds 4, 5, 6, 7 -> res = [1, 2, 4, 5, 6, 7]
 * 4. rightHelper: curr starts at 3. 3 is not leaf -> stack = [3]. curr moves to 7. 7 is leaf -> loop ends.
 *    Pop stack -> res = [1, 2, 4, 5, 6, 7, 3]
 *
 * Correctness Check:
 * The solution correctly avoids duplicate nodes (like the root or leaves being added multiple times) 
 * by using the isLeaf check in the boundary helpers. The order is strictly anti-clockwise.
 */

import java.util.*;

class TreeBoundaryTraversal {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // Utility to identify leaf nodes to prevent duplication in boundary helpers
    public boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    // Traverses the left-most path from the root
    public void leftHelper(TreeNode root, ArrayList<Integer> res) {
        TreeNode curr = root.left;
        while (curr != null) {
            // Only add if it's not a leaf; leaves are handled by leavesHelper
            if (isLeaf(curr) == false)
                res.add(curr.val);
            
            // Priority: Go left if possible, otherwise go right
            if (curr.left != null)
                curr = curr.left;
            else
                curr = curr.right;
        }
    }

    // Traverses the right-most path and uses a stack to reverse the order
    public void rightHelper(TreeNode root, ArrayList<Integer> res) {
        TreeNode curr = root.right;
        Stack<Integer> st = new Stack<>();
        while (curr != null) {
            // Only add if it's not a leaf
            if (isLeaf(curr) == false)
                st.push(curr.val);
            
            // Priority: Go right if possible, otherwise go left
            if (curr.right != null)
                curr = curr.right;
            else
                curr = curr.left;
        }
        // Pop from stack to get bottom-up order for the right boundary
        while (!st.isEmpty())
            res.add(st.pop());
    }

    // Standard DFS to collect all leaf nodes in left-to-right order
    public void leavesHelper(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return;
        if (isLeaf(root))
            res.add(root.val);
        
        // Recurse left then right to maintain left-to-right leaf order
        leavesHelper(root.left, res);
        leavesHelper(root.right, res);
    }

    public ArrayList<Integer> boundaryTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        // Step 1: Add root if it's not a leaf (leavesHelper will catch it if it is)
        if (isLeaf(root) == false)
            res.add(root.val);

        // Step 2: Get Left Boundary (Top-Down)
        leftHelper(root, res);
        
        // Step 3: Get all Leaves (Left-to-Right)
        leavesHelper(root, res);
        
        // Step 4: Get Right Boundary (Bottom-Up)
        rightHelper(root, res);

        return res;
    }

    public static void main(String[] args) {
        // Constructing a sample tree:
        //        1
        //       / \
        //      2   3
        //     / \ / \
        //    4  5 6  7
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeBoundaryTraversal tree = new TreeBoundaryTraversal();
        ArrayList<Integer> result = tree.boundaryTraversal(root);
        
        // Expected Output: [1, 2, 4, 5, 6, 7, 3]
        // 1 (Root) -> 2 (Left Boundary) -> 4, 5, 6, 7 (Leaves) -> 3 (Right Boundary)
        System.out.println(result); 
    }
}
