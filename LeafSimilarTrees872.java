/*
 * Problem: 872. Leaf-Similar Trees
 *
 * Problem Statement:
 * Two binary trees are considered leaf-similar if their leaf value sequence (the values of all leaves 
 * read from left to right) is the same. Given the roots of two binary trees, return true if 
 * they are leaf-similar, and false otherwise.
 *
 * Intuition:
 * The problem asks us to ignore the internal nodes and focus solely on the "fringe" or leaves of the tree.
 * A standard Depth-First Search (DFS) visits nodes in a predictable order. If we traverse left-to-right,
 * we will encounter the leaves in the exact sequence required.
 *
 * Approach:
 * 1. Traverse the first tree using DFS to collect all leaf values into a list.
 * 2. Traverse the second tree using DFS to collect all leaf values into another list.
 * 3. Compare the two lists. If they have the same elements in the same order, the trees are leaf-similar.
 *
 * Time Complexity: O(N + M), where N and M are the total number of nodes in the two trees. 
 * Every node is visited exactly once during the traversal.
 *
 * Space Complexity: O(H1 + H2 + L1 + L2), where H is the height of the trees (representing the 
 * recursion stack depth) and L is the number of leaves stored in the lists. In the worst case 
 * of a skewed tree, H can be O(N).
 *
 * Edge Cases:
 * - Single-node trees: The root itself is the only leaf.
 * - Trees with different heights/structures but identical leaf sequences: Should return true.
 * - Trees with different numbers of leaves: The list comparison will correctly return false.
 *
 * Dry Run:
 * Tree 1: Root 3, Left 5, Right 1. 5 has leaves 6, 2. 1 has leaves 9, 8. -> Sequence: [6, 2, 9, 8]
 * Tree 2: Root 3, Left 5, Right 1. 5 has leaves 6, 7. 1 has leaves 4, 2. -> Sequence: [6, 7, 4, 2]
 * leaves1.equals(leaves2) -> [6, 2, 9, 8].equals([6, 7, 4, 2]) -> false.
 *
 * Correctness Check:
 * The solution is correct. The leaf condition (left == null && right == null) correctly identifies 
 * terminal nodes, and the order of recursive calls (left then right) ensures the left-to-right sequence.
 */

import java.util.*;

public class LeafSimilarTrees872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // Use lists to store the sequence of leaf values for comparison.
        // ArrayList is ideal here as we only perform appends and a final equality check.
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        
        // Build the exact left-to-right leaf sequence for tree 1.
        dfs(root1, leaves1);
        
        // Build the exact left-to-right leaf sequence for tree 2.
        dfs(root2, leaves2);
        
        // The equals() method for List checks if both lists have the same size 
        // and identical elements in the exact same order.
        return leaves1.equals(leaves2);
    }

    public void dfs(TreeNode root, List<Integer> leaves) {
        // Null node contributes nothing to the leaf sequence; base case for recursion.
        if (root == null) {
            return;
        }
        
        // A node with no children is a leaf; record its value in encounter order.
        // This check must happen before the recursive calls.
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
        }
        
        // Preorder-style traversal (Left then Right) ensures that leaves in the 
        // left subtree are recorded before leaves in the right subtree.
        dfs(root.left, leaves);
        dfs(root.right, leaves);
    }
}
