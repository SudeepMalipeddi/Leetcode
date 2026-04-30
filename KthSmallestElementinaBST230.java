/*
 * Problem: 230. Kth Smallest Element in a BST
 *
 * Problem Statement:
 * Given the root of a binary search tree (BST) and an integer k, return the kth smallest value (1-indexed) 
 * of all the values of the nodes in the tree.
 *
 * Intuition:
 * A fundamental property of a Binary Search Tree is that an in-order traversal (Left -> Root -> Right) 
 * visits nodes in strictly increasing order. Therefore, the kth node visited during an in-order 
 * traversal is guaranteed to be the kth smallest element in the BST.
 *
 * Approach:
 * 1. Use a recursive in-order traversal to explore the tree.
 * 2. Maintain a global counter ('count') to keep track of how many nodes have been visited in sorted order.
 * 3. When 'count' reaches 'k', capture the current node's value into a 'result' variable.
 * 4. Return the captured result after the traversal completes.
 *
 * Time Complexity: O(H + k) where H is the height of the tree. We traverse down to the leftmost leaf (H) 
 * and then visit k nodes. In the worst case of a skewed tree, this is O(N).
 * Space Complexity: O(H) for the recursion stack. In a balanced tree, this is O(log N); in a skewed tree, it is O(N).
 *
 * Edge Cases:
 * - k = 1: The smallest element (leftmost node in the tree).
 * - k = N: The largest element (rightmost node in the tree).
 * - Skewed Tree: The tree acts like a linked list; recursion depth will be N.
 *
 * Dry Run:
 * BST: [3, 1, 4, null, 2], k = 2
 * 1. inorder(3) calls inorder(1).
 * 2. inorder(1) calls inorder(null) -> returns.
 * 3. Visit node 1: count = 1. (1 != 2).
 * 4. inorder(1) calls inorder(2).
 * 5. inorder(2) calls inorder(null) -> returns.
 * 6. Visit node 2: count = 2. (2 == 2). result = 2.
 * 7. Traversal continues/unwinds, eventually returning result 2.
 *
 * Correctness Check:
 * The solution correctly identifies the kth element. Note that the current implementation continues 
 * traversing the remaining nodes even after 'result' is found because there is no global "stop" 
 * mechanism (the 'return' only exits the current recursive frame). While correct, it could be 
 * optimized to short-circuit entirely once count == k.
 */

class KthSmallestElementinaBST230 {
    // Number of nodes visited in inorder so far.
    // We use a class member because primitives are passed by value in Java; 
    // this allows the count to persist across recursive stack frames.
    int count = 0;
    
    // Captured k-th smallest value.
    int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        // Inorder gives ascending sequence for BST.
        inorder(root, k);
        return result;
    }

    /**
     * Performs a standard recursive in-order traversal: Left, then Root, then Right.
     */
    public void inorder(TreeNode root, int k) {
        // Empty subtree contributes nothing. Base case for recursion.
        if (root == null)
            return;
            
        // Visit left subtree (smaller values).
        // We go as deep as possible to the left to find the smallest elements first.
        inorder(root.left, k);
        
        // Process current node in sorted order.
        // This section represents the "Root" visit in Left-Root-Right.
        count++;
        
        // Capture answer exactly at k-th visit.
        if (count == k) {
            result = root.val;
            // This return exits the current method call, but the recursion 
            // will continue to unwind and potentially visit right subtrees of parents.
            return;
        }
        
        // Visit right subtree (larger values).
        // Only explored after the current node and its entire left subtree are processed.
        inorder(root.right, k);
    }
}
