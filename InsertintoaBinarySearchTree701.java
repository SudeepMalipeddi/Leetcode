/*
 * Problem: 701. Insert into a Binary Search Tree
 *
 * Problem Statement:
 * Given the root node of a Binary Search Tree (BST) and a value to insert into the tree,
 * insert the value into the BST such that the tree remains a BST. Return the root node 
 * of the BST after the insertion.
 *
 * Intuition:
 * A BST is defined by the property that for any node, all values in its left subtree are 
 * smaller and all values in its right subtree are larger. To insert a new value, we 
 * simply follow this logic from the root down to a leaf position where the value 
 * naturally "belongs."
 *
 * Approach:
 * 1. Handle the base case: if the tree is empty, the new node becomes the root.
 * 2. Use a pointer to traverse the tree starting from the root.
 * 3. Compare the target value with the current node's value.
 * 4. If the target is larger, move to the right child. If the right child is null, insert there.
 * 5. If the target is smaller, move to the left child. If the left child is null, insert there.
 *
 * Time Complexity: O(H), where H is the height of the tree. In a balanced tree, this is O(log N), 
 * but in a skewed tree, it can be O(N).
 * Space Complexity: O(1) because this implementation uses an iterative approach, 
 * requiring no extra space beyond a single pointer.
 *
 * Edge Cases:
 * - Empty tree: The root is null, so we return a new TreeNode.
 * - Single node tree: We attach the new node to either the left or right.
 *
 * Dry Run:
 * Input: root = [4,2,7], val = 5
 * 1. root (4) is not null. cur = 4.
 * 2. 5 > 4: cur.right (7) is not null. Move cur to 7.
 * 3. 5 < 7: cur.left is null. Create new TreeNode(5) and set cur.left = 5.
 * 4. Break and return original root.
 *
 * Correctness Check:
 * The solution correctly navigates the BST properties. Since the problem guarantees 
 * the value does not already exist, the <= check correctly directs the flow to the right.
 */
public class InsertintoaBinarySearchTree701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // Important guard: this branch handles a boundary or constraint-critical condition.
        // If the tree is empty, the new value becomes the root of the tree.
        if (root == null)
            return new TreeNode(val);
        
        // Use a 'cur' pointer to traverse the tree so we don't lose the reference to the original root.
        TreeNode cur = root;
        // Iterate through the active search space while maintaining the intended invariant.
        // We use an infinite loop because we are guaranteed to find a null spot to insert.
        while (true) {
            // BST Property: If the value to insert is greater than the current node's value,
            // it must be placed in the right subtree.
            if (cur.val <= val) {
                if (cur.right != null) {
                    // Continue traversing down the right side.
                    cur = cur.right;
                } else {
                    // Recursive/helper call processes a smaller subproblem before combining results.
                    // Found the insertion point: the right child is null.
                    cur.right = new TreeNode(val);
                    break;
                }
            } else {
                // BST Property: If the value to insert is smaller than the current node's value,
                // it must be placed in the left subtree.
                if (cur.left != null) {
                    // Continue traversing down the left side.
                    cur = cur.left;
                } else {
                    // Found the insertion point: the left child is null.
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }
        // Return the original root of the tree.
        return root;
    }
}
