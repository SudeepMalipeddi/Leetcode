/*
 * Problem: Floor in BST
 *
 * Problem Statement:
 * Given a Binary Search Tree (BST) and a target key, find the floor of the key.
 * The floor is defined as the largest value in the BST that is less than or equal to the key.
 *
 * Intuition:
 * In a BST, for any node, all values in the left subtree are smaller and all values in the right 
 * subtree are larger. To find the floor, we look for the largest value that doesn't exceed the key. 
 * If the current node's value is less than the key, it is a potential floor, but a better (larger) 
 * one might exist in its right subtree. If the current node is greater than the key, the floor 
 * must reside in the left subtree.
 *
 * Approach:
 * 1. Initialize a result variable `res` to -1 to handle cases where no floor exists.
 * 2. Traverse the tree starting from the root using a while loop.
 * 3. If the current node's value matches the key exactly, return it as the floor.
 * 4. If the current node's value is greater than the key, move to the left child (current is too big).
 * 5. If the current node's value is less than the key, update `res` to the current value and move 
 *    to the right child to search for a value closer to the key.
 *
 * Time Complexity: O(H), where H is the height of the BST. In a balanced BST, this is O(log N); 
 * in a skewed tree, it is O(N).
 * Space Complexity: O(1) because the implementation is iterative and does not use the call stack.
 *
 * Edge Cases:
 * - Key is smaller than the minimum value in the BST (returns -1).
 * - Key is larger than the maximum value in the BST (returns the maximum value).
 * - The tree is empty (returns -1).
 *
 * Dry Run:
 * Tree: [10, 5, 15, 2, 8], Key: 7
 * 1. root = 10: 10 > 7, move left.
 * 2. root = 5: 5 < 7, res = 5, move right.
 * 3. root = 8: 8 > 7, move left.
 * 4. root = null: loop terminates. Return res (5).
 *
 * Correctness Check:
 * The solution correctly implements the BST search property to find the floor. No bugs identified.
 */
public class FloorInBST {
    public static int floorinBST(TreeNode root, int key) {
        // Initialize result to -1 to handle cases where no value in the BST is <= key.
        int res = -1;
        // Iterate through the active search space while maintaining the intended invariant.
        while (root != null) {
            // If we find an exact match, it is the floor by definition.
            if (root.val == key)
                return root.val;
            else if (root.val > key) {
                // Current node is too large; the floor must be in the left subtree.
                root = root.left;
            } else {
                // Current node is a candidate for the floor (val < key).
                // Store it and move right to see if a larger value still <= key exists.
                res = root.val;
                root = root.right;
            }
        }
        return res;
    }
}
