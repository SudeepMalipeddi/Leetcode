/*
 * Problem: 226. Invert Binary Tree
 *
 * Problem Statement:
 * Given the root of a binary tree, swap every node's left and right child and return the new root.
 *
 * Intuition:
 * A binary tree is a recursive structure. To invert the entire tree, we can invert the 
 * left and right subtrees independently and then swap them at the current node. 
 * This "divide and conquer" approach ensures that every node's children are 
 * eventually swapped, resulting in a mirrored version of the original tree.
 *
 * Approach:
 * 1. Base Case: If the current node is null, we've reached the end of a branch; return null.
 * 2. Recursive Step: Call the function on the left and right children. This processes 
 *    the subtrees deeper in the tree first (post-order style).
 * 3. Swap Step: Swap the left and right child pointers of the current node using a temporary variable.
 * 4. Return: Return the current node as the root of the now-inverted subtree.
 *
 * Time Complexity: O(n)
 * We visit every node in the tree exactly once to perform the swap operation.
 *
 * Space Complexity: O(h)
 * The space is determined by the recursion stack. In the worst case (a skewed tree), 
 * the height h is n. In the best case (a balanced tree), h is log(n).
 *
 * Edge Cases:
 * - Empty tree (root is null): Handled by the base case.
 * - Single node (leaf): Children are null; swapping them has no effect, which is correct.
 *
 * Dry Run:
 * Input: root = [4, 2, 7]
 * 1. invertTree(4) is called.
 * 2. invertTree(4) calls invertTree(2).
 * 3. invertTree(2) calls its null children, swaps them (no change), and returns node 2.
 * 4. invertTree(4) calls invertTree(7).
 * 5. invertTree(7) returns node 7.
 * 6. Back in invertTree(4), root.left is 2 and root.right is 7.
 * 7. Swap: root.left becomes 7, root.right becomes 2.
 * 8. Return node 4. Result: [4, 7, 2].
 *
 * Correctness Check:
 * The solution is correct. It correctly identifies that an in-place swap of child 
 * pointers at every node results in a mirrored tree. The post-order traversal 
 * (processing children before the parent swap) is a valid way to achieve this.
 */

class TreeNode{
    // Standard Binary Tree Node definition
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){};
    TreeNode(int val){
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class InvertBinaryTree226 {
    /**
     * Inverts a binary tree by swapping left and right children recursively.
     * @param root The root of the binary tree.
     * @return The root of the inverted binary tree.
     */
    public TreeNode invertTree(TreeNode root){
        // Base Case: If the current subtree is empty, it is already "inverted".
        // This also prevents NullPointerExceptions when accessing root.left/root.right.
        if(root == null) return null;

        // Recursive Step: Traverse down to the bottom of the tree.
        // We process the subtrees first. Note: The return values are ignored here 
        // because the function modifies the existing nodes in-place.
        invertTree(root.left);
        invertTree(root.right);

        // The "Work" Phase: Swap the left and right child pointers of the current node.
        // We use a temporary variable to avoid losing the reference to the left child.
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Return the current node. As the recursion unwinds, this node becomes 
        // the child of the caller, maintaining the tree structure.
        return root;
    }    
}
