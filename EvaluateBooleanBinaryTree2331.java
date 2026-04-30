/*
 * Problem: LeetCode 2331 - Evaluate Boolean Binary Tree
 *
 * Problem Statement:
 * You are given the root of a full binary tree where leaf nodes represent boolean values (0 for False, 1 for True) 
 * and non-leaf nodes represent boolean operators (2 for OR, 3 for AND). Evaluate the tree and return the 
 * boolean result of the root node.
 *
 * Intuition:
 * A full binary tree structure naturally represents a nested boolean expression. To evaluate the root, 
 * we must first know the values of its children. This "bottom-up" requirement makes recursion (Post-order Traversal) 
 * the ideal strategy: evaluate the leaves first, then propagate the results upward through the operators.
 *
 * Approach:
 * 1. Base Case: If the current node is a leaf (no children), return its value as a boolean (1 is true, 0 is false).
 * 2. Recursive Step: Recursively evaluate the left and right subtrees to get their boolean results.
 * 3. Evaluation: If the current node's value is 2, apply the OR operator to the results of the children.
 * 4. Evaluation: If the current node's value is 3, apply the AND operator to the results of the children.
 *
 * Time Complexity: O(n) where n is the number of nodes in the tree, as we visit every node exactly once.
 * Space Complexity: O(h) where h is the height of the tree, representing the maximum depth of the recursion stack.
 *
 * Edge Cases:
 * - A single-node tree: The root is a leaf, so it returns its boolean value immediately.
 * - Deeply nested trees: Handled by the recursive stack up to the memory limit.
 *
 * Dry Run:
 * Input: [2, 1, 3, null, null, 0, 1] (Root is OR, Left is True, Right is AND(False, True))
 * 1. evaluate(Root): Node is 2 (OR). Calls evaluate(Left) and evaluate(Right).
 * 2. evaluate(Left): Node is 1 (Leaf). Returns true.
 * 3. evaluate(Right): Node is 3 (AND). Calls evaluate(Right.Left) and evaluate(Right.Right).
 * 4. evaluate(Right.Left): Node is 0 (Leaf). Returns false.
 * 5. evaluate(Right.Right): Node is 1 (Leaf). Returns true.
 * 6. Back in evaluate(Right): Returns (false AND true) = false.
 * 7. Back in evaluate(Root): Returns (true OR false) = true.
 *
 * Correctness Check:
 * The solution correctly implements the logic for a full binary tree where non-leaf nodes are guaranteed 
 * to have two children and valid operator values (2 or 3).
 */
public class EvaluateBooleanBinaryTree2331 {
    public boolean evaluateTree(TreeNode root) {
        // Base Case: In a full binary tree, if one child is null, the node is a leaf.
        // Leaf nodes store 0 (false) or 1 (true).
        if (root.left == null) {
            return root.val == 1;
        }

        // Recursive Step: Evaluate the operator nodes.
        // Value 2 represents the boolean OR operation.
        if (root.val == 2) {
            return evaluateTree(root.left) || evaluateTree(root.right);
        } else {
            // Value 3 represents the boolean AND operation.
            // We use short-circuiting operators (|| and &&) which matches standard boolean logic.
            return evaluateTree(root.left) && evaluateTree(root.right);
        }
    }
}
