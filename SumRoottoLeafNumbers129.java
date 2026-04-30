/*
 * Problem: 129. Sum Root to Leaf Numbers
 *
 * Problem Statement:
 * Given a binary tree where each node contains a single digit from 0-9, each root-to-leaf path 
 * represents a number. Find the total sum of all numbers formed by these paths.
 *
 * Intuition:
 * As we move from a parent to a child, the parent's value becomes the "tens" (or higher) place. 
 * We can build the number dynamically by multiplying the current running sum by 10 and 
 * adding the current node's value. Once we reach a leaf, we have a complete number.
 *
 * Approach:
 * 1. Use a recursive Depth First Search (DFS) to traverse from root to leaves.
 * 2. Maintain a 'sum' variable that represents the number formed from the root to the current node.
 * 3. At each node, update: current_sum = (parent_sum * 10) + current_node_value.
 * 4. If the node is a leaf, return the current_sum.
 * 5. If not a leaf, return the sum of the results from the left and right subtrees.
 *
 * Time Complexity: O(N), where N is the number of nodes in the tree, as we visit each node exactly once.
 * Space Complexity: O(H), where H is the height of the tree, representing the maximum depth of the recursion stack.
 *
 * Edge Cases:
 * - Empty tree: Handled by the null check (returns 0).
 * - Single node tree: The leaf check will trigger immediately and return the node's value.
 *
 * Dry Run:
 * Tree: [4, 9, 0, 5, 1]
 * - dfs(4, 0) -> sum = 4.
 *   - dfs(9, 4) -> sum = 49.
 *     - dfs(5, 49) -> sum = 495. Leaf! Return 495.
 *     - dfs(1, 49) -> sum = 491. Leaf! Return 491.
 *     - 9's call returns 495 + 491 = 986.
 *   - dfs(0, 4) -> sum = 40. Leaf! Return 40.
 * - Root call returns 986 + 40 = 1026.
 *
 * Correctness Check:
 * The solution correctly implements the path-to-number logic and aggregates leaf values.
 */
public class SumRoottoLeafNumbers129 {
    public static int sumNumbers(TreeNode root) {
        // Start DFS with an initial accumulated sum of 0
        return dfs(root, 0);
    }

    public static int dfs(TreeNode root, int sum) {
        // Base case: if we hit a null child of a non-leaf node, it contributes 0 to the sum
        if (root == null) {
            return 0;
        }

        // Standard digit-building logic: shift existing digits left (base 10) and add current
        sum = sum * 10 + root.val;

        // If we reach a leaf node (no children), the number for this path is complete
        if (root.left == null && root.right == null) {
            return sum;
        }

        // Recursively calculate the sum of numbers formed in the left and right subtrees
        // and return their combined total to the parent
        return dfs(root.left, sum) + dfs(root.right, sum);
    }
}
