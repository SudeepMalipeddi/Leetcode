/*
 * Problem: LeetCode 1325 - Delete Leaves With a Given Value
 *
 * Problem Statement:
 * Given a binary tree and an integer target, delete all leaf nodes that have the value target. 
 * If deleting a leaf node results in its parent becoming a leaf node with the target value, 
 * that parent must also be deleted (cascading deletion).
 *
 * Intuition:
 * This problem requires a bottom-up approach. We cannot decide whether to delete a node 
 * until we know the status of its children. If a node's children are deleted, it might 
 * become a new leaf. Therefore, Post-order Traversal (Left -> Right -> Root) is the 
 * key insight, as it allows us to process children before the parent.
 *
 * Approach:
 * 1. Use recursion to reach the bottom of the tree (post-order).
 * 2. Update the current node's left and right pointers with the results of the recursive calls.
 * 3. After the recursive calls return, check if the current node has become a leaf 
 *    (both left and right are null) and if its value matches the target.
 * 4. If it is a target leaf, return null to the parent to effectively "delete" it.
 * 5. Otherwise, return the current node.
 *
 * Time Complexity: O(n) where n is the number of nodes in the tree, as we visit each node exactly once.
 * Space Complexity: O(h) where h is the height of the tree, representing the maximum depth of the recursion stack.
 *
 * Edge Cases:
 * - The root itself is a target leaf (entire tree becomes null).
 * - The tree is empty (root is null).
 * - Deleting a leaf creates a new leaf that also needs to be deleted (handled by post-order).
 *
 * Dry Run:
 * Tree: [1, 2, 2], Target: 2
 * 1. Call remove(1).
 * 2. Call remove(1.left) -> remove(2).
 * 3. Node 2 has no children. 2 == target? Yes. Return null to 1.left.
 * 4. Call remove(1.right) -> remove(2).
 * 5. Node 2 has no children. 2 == target? Yes. Return null to 1.right.
 * 6. Back at Node 1. Both children are now null. 1 == target? No. Return Node 1.
 * Result: [1]
 *
 * Correctness Check:
 * The solution correctly implements post-order traversal and handles cascading deletions 
 * by re-assigning child pointers based on the recursive return values.
 */
public class DeleteLeavesWithaGivenValue1325 {

    // Time Complexity: O(n)
    // Space Complexity: O(h)
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // Base case: If we reach a null pointer, there's nothing to delete.
        if (root == null)
            return null;

        // Post-order traversal: We must process the children first.
        // We re-assign the left and right pointers because the recursive call 
        // might return null if the child node was a target leaf and got deleted.
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        // After processing children, the current node might have become a leaf.
        // We check three conditions:
        // 1. Is the left child null?
        // 2. Is the right child null?
        // 3. Does the current node's value match the target?
        if (root.left == null && root.right == null && root.val == target) {
            // Returning null tells the parent caller to set its corresponding child pointer to null.
            return null;
        }

        // If the node is not a target leaf, we return the node itself to keep it in the tree.
        return root;
    }
}
