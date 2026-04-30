/*
 * Problem: LeetCode 545 - Boundary of Binary Tree
 *
 * Problem Statement:
 * The boundary of a binary tree is the concatenation of the root, the left boundary, 
 * the leaves from left to right, and the right boundary (in reverse order) without duplicates.
 *
 * Intuition:
 * A direct traversal is difficult because the boundary isn't a standard DFS/BFS order. 
 * By decomposing the problem into three distinct sub-problems (Left Boundary, Leaves, 
 * and Right Boundary), we can ensure each node is visited in the correct anti-clockwise 
 * sequence while naturally avoiding duplicates at the "corners" (root and leaves).
 *
 * Approach:
 * 1. Handle the root separately: Add it to the result if it's not a leaf.
 * 2. Left Boundary: Traverse from the root's left child. Prefer the left child; 
 *    if it doesn't exist, go right. Stop before reaching a leaf.
 * 3. Leaves: Perform a standard DFS (Pre-order/In-order) to collect all leaf nodes 
 *    from left to right across the entire tree.
 * 4. Right Boundary: Traverse from the root's right child. Prefer the right child; 
 *    if it doesn't exist, go left. Stop before reaching a leaf. Use a stack or 
 *    temporary list to reverse these nodes for the anti-clockwise requirement.
 *
 * Time Complexity: O(N) where N is the number of nodes. We visit each node at most 
 * a constant number of times across the three phases.
 * Space Complexity: O(H) where H is the height of the tree. This accounts for the 
 * recursion stack in addLeaves and the temporary list used to reverse the right boundary.
 *
 * Edge Cases:
 * - Null tree: Handled by the initial null check.
 * - Single node tree: Handled by checking if the root is a leaf (it will be added by addLeaves).
 * - Skewed trees: The logic correctly follows the only available path.
 *
 * Dry Run:
 * Tree: [1, 2, 3, 4, 5, null, null]
 * 1. Root (1) is not leaf -> res = [1]
 * 2. Left Boundary: Start at 2. 2 is not leaf. Add 2. 2 has children (4, 5). 
 *    Next is 4. 4 is leaf, loop ends. res = [1, 2]
 * 3. Leaves: DFS finds 4 and 5. res = [1, 2, 4, 5]
 * 4. Right Boundary: Start at 3. 3 is leaf, loop doesn't run.
 * Result: [1, 2, 4, 5, 3] (Note: if 3 had children, it would be added here).
 *
 * Correctness Check:
 * The solution is correct. It carefully excludes leaf nodes from the boundary 
 * methods to prevent them from being added twice (once as a boundary node and 
 * once as a leaf node).
 */
import java.util.*;

public class BoundaryTreeTraversal {
    // Helper to identify leaf nodes to prevent duplication in boundary vs leaf logic
    public boolean isLeaf(TreeNode node) {
        if (node.left == null && node.right == null)
            return true;
        return false;
    }

    public void addLeftBoundary(TreeNode root, List<Integer> res) {
        TreeNode cur = root.left; // start from left child to avoid duplicating root
        while (cur != null) {
            if (isLeaf(cur) == false)
                res.add(cur.val); // left boundary excludes leaves
            
            // Standard boundary logic: prioritize left, fallback to right
            if (cur.left != null)
                cur = cur.left;
            else
                cur = cur.right;
        }
    }

    public void addLeaves(TreeNode root, List<Integer> res) {
        // Base case: if leaf is found, add to result in left-to-right order
        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }
        // Standard DFS to ensure we visit leaves from left to right
        if (root.left != null)
            addLeaves(root.left, res); // collect left subtree leaves
        if (root.right != null)
            addLeaves(root.right, res); // collect right subtree leaves
    }

    public void addRightBoundary(TreeNode root, List<Integer> res) {
        List<Integer> temp = new ArrayList<>();
        TreeNode cur = root.right; // start from right child to avoid duplicating root
        while (cur != null) {
            if (isLeaf(cur) == false)
                temp.add(cur.val);
            
            // Standard boundary logic: prioritize right, fallback to left
            if (cur.right != null)
                cur = cur.right;
            else
                cur = cur.left;
        }
        // Right boundary must be added in bottom-up order (reverse of traversal)
        for (int i = temp.size() - 1; i >= 0; i--)
            res.add(temp.get(i));
    }

    public List<Integer> printBounday(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        
        // Root is added first. If it's a leaf, addLeaves will handle it to avoid double counting.
        if (isLeaf(root) == false)
            res.add(root.val);
            
        addLeftBoundary(root, res); // Phase 1: Top-down left boundary
        addLeaves(root, res);       // Phase 2: Left-to-right leaves
        addRightBoundary(root, res); // Phase 3: Bottom-up right boundary
        
        return res;
    }

}
