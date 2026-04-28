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
        int res = -1;
        while (root != null) {
            if (root.val == key)
                return root.val;
            else if (root.val < key)
                root = root.right; // need larger values
            else {
                res = root.val;
                root = root.left; // try to find a smaller ceiling
            }
        }
        return res;
    }
}
