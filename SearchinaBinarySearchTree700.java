/*
 * Problem: 700. Search in a Binary Search Tree
 *
 * Problem Statement:
 * Given the root of a Binary Search Tree (BST) and an integer 'val', find the node in the BST 
 * where the node's value equals 'val' and return the subtree rooted with that node. 
 * If the value does not exist, return null.
 *
 * Intuition:
 * The fundamental property of a BST is that for any given node, all values in its left subtree 
 * are smaller and all values in its right subtree are larger. This allows us to perform 
 * a binary search-like traversal, eliminating one entire subtree at every step.
 *
 * Approach:
 * 1. Start at the root.
 * 2. If the current node is null or its value matches the target, return the current node.
 * 3. If the target value is smaller than the current node's value, move to the left child.
 * 4. If the target value is larger, move to the right child.
 * 5. Repeat until the value is found or a null reference is reached.
 *
 * Time Complexity: O(H), where H is the height of the tree. In a balanced BST, this is O(log N). 
 * In the worst case (a skewed tree), this is O(N).
 * Space Complexity: 
 * - Recursive: O(H) due to the implicit call stack.
 * - Iterative: O(1) as we only update a single pointer.
 *
 * Edge Cases:
 * - The tree is empty (root is null).
 * - The value is not present in the tree.
 * - The value is located at the root or a leaf node.
 *
 * Dry Run:
 * Input: root = [4,2,7,1,3], val = 2
 * 1. Current node is 4. 2 < 4, so move to the left child.
 * 2. Current node is 2. 2 == 2, match found.
 * 3. Return the node with value 2 and its children [1,3].
 *
 * Correctness Check:
 * The logic correctly leverages BST properties to navigate the tree. Both implementations 
 * handle the "not found" case correctly by returning null.
 */
public class SearchinaBinarySearchTree700 {

    // Recursive Method
    public TreeNode searchBST1(TreeNode root, int val) {
        // Base Case: If we reach a null node, the value isn't here. 
        // If the current node's value matches, we've found our target.
        if (root == null || root.val == val)
            return root;

        // If the target value is greater than the current node, 
        // the BST property guarantees it can only exist in the right subtree.
        if (root.val < val)
            return searchBST1(root.right, val);
        // Otherwise, the target must be in the left subtree.
        else
            return searchBST1(root.left, val);
    }

    // Iterative Method
    public TreeNode searchBST2(TreeNode root, int val) {
        // Traverse the tree as long as we haven't hit a null path 
        // and haven't found the target value yet.
        while (root != null && root.val != val) {
            // Standard BST navigation: go right for larger values, left for smaller.
            if (root.val < val)
                root = root.right;
            else
                root = root.left;
        }
        // If the loop terminates because root == null, we return null (not found).
        // If it terminates because root.val == val, we return the matching node.
        return root;
    }

}
