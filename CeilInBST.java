/*
 * Problem: Ceil in a Binary Search Tree
 *
 * Problem Statement:
 * Given a Binary Search Tree (BST) and a target key, find the "ceiling" of the key.
 * The ceiling is the smallest value in the BST that is greater than or equal to the key.
 * If no such value exists, return -1.
 *
 * Intuition:
 * In a BST, the left child is smaller and the right child is larger. If a node's value
 * is greater than the key, it is a potential ceiling. However, there might be an even
 * smaller value that is still >= key in that node's left subtree. If a node's value
 * is smaller than the key, the ceiling must reside in the right subtree.
 *
 * Approach:
 * 1. Initialize a variable 'res' to -1 to store the best candidate found so far.
 * 2. Traverse the tree iteratively from the root.
 * 3. If the current node's value equals the key, return it immediately (perfect match).
 * 4. If the current node's value is less than the key, discard the left subtree and move right.
 * 5. If the current node's value is greater than the key, update 'res' and move left to find a smaller ceiling.
 *
 * Time Complexity: O(H), where H is the height of the tree. This is O(log N) for balanced trees and O(N) for skewed trees.
 * Space Complexity: O(1) because we use an iterative approach and only a few pointers.
 *
 * Edge Cases:
 * - Empty tree: Returns -1.
 * - Key is larger than all nodes: Returns -1.
 * - Key is smaller than all nodes: Returns the minimum node value.
 *
 * Dry Run:
 * BST: [8, 5, 10], Key: 6
 * 1. root = 8: 8 > 6. res = 8. Move left to 5.
 * 2. root = 5: 5 < 6. Move right to null.
 * 3. Loop ends. Return res (8).
 *
 * Correctness Check:
 * The solution is correct. It correctly utilizes the BST property to prune the search space
 * and maintains the "smallest value >= key" invariant.
 */
/*
 * Problem: Ceil in a Binary Search Tree
 * Problem Statement: Given a BST and a key, find the smallest value in the BST
 *   that is greater than or equal to the key (the ceiling).
 * Intuition: In a BST, moving left finds smaller values and moving right finds
 *   larger values, so we can iterate while tracking the best candidate.
 * Approach:
 *   1) Start at the root with res = -1.
 *   2) If node value equals key, return it immediately.
 *   3) If node value < key, move right (ceil must be larger).
 *   4) If node value > key, update res and move left to search for a smaller ceil.
 * Time Complexity: O(h) where h is tree height.
 * Space Complexity: O(1) iterative.
 * Edge Cases: Key larger than all nodes (returns -1), empty tree.
 * Dry Run: BST [8,5,10], key=6 -> at 8 res=8 move left, at 5 move right,
 *   end => res=8.
 * Correctness Check: res always holds the smallest seen value >= key, and BST
 *   ordering ensures no smaller valid candidate is skipped.
 */
public class CeilInBST {
    public static int findCeilBST(TreeNode root, int key) {
        // Initialize result to -1; this will be returned if no value >= key exists in the tree
        int res = -1;
        // Iterate through the tree until we hit a null leaf or find an exact match
        while (root != null) {
            // If the current node's value is exactly the key, it is the smallest possible ceiling
            if (root.val == key)
                return root.val;
            // If the current node is smaller than the key, the ceiling must be in the right subtree
            else if (root.val < key)
                root = root.right; // need larger values
            // If the current node is larger than the key, it is a candidate for the ceiling
            else {
                // Record this node as the best candidate so far
                res = root.val;
                // Move left to see if there is a smaller value that is still >= key
                root = root.left; // try to find a smaller ceiling
            }
        }
        return res;
    }
}
