/*
 * Problem: LeetCode 285. Inorder Successor in BST
 *
 * Problem Statement:
 * Given a binary search tree (BST) and a node 'p' in it, find the in-order successor 
 * of that node in the BST. The successor of a node p is the node with the smallest 
 * key greater than p.val.
 *
 * Intuition:
 * In a BST, the inorder successor is either the leftmost node in the right subtree 
 * (if it exists) or the lowest ancestor that has 'p' in its left subtree. By 
 * leveraging the BST property (left < root < right), we can eliminate half the 
 * search space at each step.
 *
 * Approach:
 * 1. Initialize a 'succ' pointer to null to track the potential successor.
 * 2. Traverse the tree starting from the root using a 'curr' pointer.
 * 3. If the current node's value is less than or equal to p's value, the successor 
 *    cannot be in the current node or its left subtree; move to the right child.
 * 4. If the current node's value is greater than p's value, the current node is a 
 *    candidate for the successor. We record it and move to the left child to see 
 *    if a smaller value (closer to p) exists.
 *
 * Time Complexity: O(h), where h is the height of the tree. This is O(log n) for 
 * a balanced tree and O(n) for a skewed tree.
 * Space Complexity: O(1), as the iterative approach uses no extra space or recursion stack.
 *
 * Edge Cases:
 * - p is the largest node in the tree: The loop will finish with 'succ' remaining null.
 * - Tree has only one node: If p is that node, returns null.
 * - p has a right child: The algorithm will correctly find the leftmost node in that right subtree.
 *
 * Dry Run:
 * Tree: [5, 3, 6, 2, 4], p = 3
 * 1. curr = 5: 5 > 3, so succ = 5, curr moves to 3 (left).
 * 2. curr = 3: 3 <= 3, so curr moves to 4 (right).
 * 3. curr = 4: 4 > 3, so succ = 4, curr moves to null (left).
 * 4. Loop terminates. Return succ = 4.
 *
 * Correctness Check:
 * The solution is correct. Note that the method name 'inorderSuccesor' is misspelled 
 * in the original code (missing an 's'), which is preserved here as per instructions.
 */
/*
 * Problem Reference: Inorder Successor in BST
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * BST search tracks lowest ancestor greater than target.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(h)
 *
 * Space Complexity:
 * O(1)
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
public class InOrderSuccessor {
    // Naive solution
    public TreeNode inorderSuccesor(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        TreeNode curr = root;
        // Iterate through the active search space while maintaining the intended invariant.
        // We traverse from root to a leaf, narrowing down the potential successor.
        while (curr != null) {
            // Important guard: this branch handles a boundary or constraint-critical condition.
            // If current node is less than or equal to p, the successor must be in the right subtree.
            if (curr.val <= p.val) {
                curr = curr.right;
            } else {
                // If current node is greater than p, it is a potential successor.
                // We store it in 'succ' and move left to find if there's a smaller value 
                // that is still greater than p.val.
                succ = curr;
                curr = curr.left;
            }
        }
        // After the loop, 'succ' will hold the smallest node value greater than p.val.
        return succ;
    }
}
