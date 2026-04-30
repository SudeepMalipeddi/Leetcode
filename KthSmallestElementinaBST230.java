/*
 * Problem Statement: Return the k-th smallest value in a Binary Search Tree.
 * Intuition: Inorder traversal of BST visits nodes in sorted ascending order.
 * Approach: Run recursive inorder, increment a global counter at each visit, capture node value when count==k.
 * Time Complexity: O(n) in worst case because traversal may visit all nodes before finding k-th.
 * Space Complexity: O(h) recursion stack where h is tree height.
 * Edge Cases handled: null subtree returns immediately; works when k points to root/min/max position in inorder order.
 * Dry Run: BST [3,1,4,null,2], k=1 -> inorder visits 1 first, count becomes 1, result=1.
 * LeetCode matching: Matches LeetCode 230 (Kth Smallest Element in a BST).
 * Correctness Check: Logic returns correct value; traversal does not fully short-circuit globally after finding result.
 */

class KthSmallestElementinaBST230 {
    // Number of nodes visited in inorder so far.
    int count = 0;
    // Captured k-th smallest value.
    int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        // Inorder gives ascending sequence for BST.
        inorder(root, k);
        return result;
    }

    public void inorder(TreeNode root, int k) {
        // Empty subtree contributes nothing.
        if (root == null)
            return;
        // Visit left subtree (smaller values).
        inorder(root.left, k);
        // Process current node in sorted order.
        count++;
        // Capture answer exactly at k-th visit.
        if (count == k) {
            result = root.val;
            return;
        }
        // Visit right subtree (larger values).
        inorder(root.right, k);
    }
}
