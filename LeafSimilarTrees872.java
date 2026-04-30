/*
 * Problem Statement:
 * Two binary trees are leaf-similar if their leaf value sequence from left to right is identical.
 * Return true if root1 and root2 are leaf-similar, else false.
 *
 * Intuition:
 * Compare only leaves, not full structure. A DFS that records leaf nodes in traversal order gives
 * exactly the sequence the problem asks for.
 *
 * Approach:
 * 1) DFS root1 and collect leaf values into leaves1.
 * 2) DFS root2 and collect leaf values into leaves2.
 * 3) Compare the two lists with equals().
 *
 * Time Complexity (with concrete justification):
 * O(n1 + n2): each node in both trees is visited once by DFS.
 *
 * Space Complexity (with concrete justification):
 * O(h1 + h2 + l1 + l2) worst-case, where h is recursion depth and l is number of leaves stored.
 * In skewed trees recursion can reach O(n).
 *
 * Edge Cases handled:
 * - Null subtree returns immediately in DFS.
 * - Single-node trees treated as one-leaf sequences.
 * - Different number of leaves -> list equality fails naturally.
 *
 * Dry Run (concrete example with state):
 * root1 leaves discovered: [6,7,4,9,8]
 * root2 leaves discovered: [6,7,4,9,8]
 * leaves1.equals(leaves2) -> true
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 872. Assumes TreeNode definition has val/left/right fields.
 *
 * Correctness Check:
 * DFS appends only when node is a leaf (left==null && right==null), preserving left-to-right order.
 * Structural differences above leaves do not affect output, which matches problem requirements.
 */

import java.util.*;

public class LeafSimilarTrees872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        // Build the exact left-to-right leaf sequence for tree 1.
        dfs(root1, leaves1);
        // Build the exact left-to-right leaf sequence for tree 2.
        dfs(root2, leaves2);
        return leaves1.equals(leaves2);
    }

    public void dfs(TreeNode root, List<Integer> leaves) {
        // Null node contributes nothing to the leaf sequence.
        if (root == null) {
            return;
        }
        // A node with no children is a leaf; record its value in encounter order.
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
        }
        // Preorder-style traversal ensures left subtree leaves are recorded before right subtree leaves.
        dfs(root.left, leaves);
        dfs(root.right, leaves);
    }
}
