/*
 * Problem: 101. Symmetric Tree
 *
 * Problem Statement:
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * Intuition:
 * A tree is symmetric if its left and right subtrees are mirror images of each other. This means 
 * that for any two nodes being compared, their values must be equal, and their children must 
 * be mirrors: the left child of the left node must match the right child of the right node, 
 * and the right child of the left node must match the left child of the right node.
 *
 * Approach:
 * 1. Handle the base case: An empty tree is symmetric.
 * 2. Use a helper function `dfs` to compare two nodes (left and right) simultaneously.
 * 3. In the helper:
 *    - If both nodes are null, they are symmetric (base case for leaves).
 *    - If only one is null, they are not symmetric.
 *    - If values differ, they are not symmetric.
 *    - Recursively check the "outer" pair (l.left, r.right) and the "inner" pair (l.right, r.left).
 *
 * Time Complexity: O(n), where n is the number of nodes in the tree. We visit every node exactly once.
 * Space Complexity: O(h), where h is the height of the tree. This is the maximum depth of the recursion stack.
 *
 * Edge Cases:
 * - Empty tree: Returns true.
 * - Single node: Returns true.
 * - Asymmetric structure (e.g., left child exists but right doesn't): Returns false.
 * - Symmetric structure but asymmetric values: Returns false.
 *
 * Dry Run:
 * Input: root = [1, 2, 2, 3, 4, 4, 3]
 * 1. isSymmetric(1) -> calls dfs(2L, 2R)
 * 2. dfs(2L, 2R): values match (2==2).
 *    - Calls dfs(2L.left, 2R.right) -> dfs(3, 3)
 *    - Calls dfs(2L.right, 2R.left) -> dfs(4, 4)
 * 3. dfs(3, 3): values match. Calls dfs(null, null) -> returns true.
 * 4. dfs(4, 4): values match. Calls dfs(null, null) -> returns true.
 * 5. Both recursive calls return true, so the whole tree is symmetric.
 *
 * Correctness Check:
 * The solution correctly uses a recursive DFS approach to validate mirror symmetry. 
 * The logic covers both structural identity and value equality.
 */

/*
 * Problem Statement: Return true if a binary tree is symmetric around its center (mirror image left vs right).
 * Intuition: Two nodes are mirror-equal when values match and their outer/inner children are mirror-equal recursively.
 * Approach: Compare pairs (left,right) using DFS: (l.left,r.right) and (l.right,r.left).
 * Time Complexity: O(n) because each node is compared once with its mirror counterpart.
 * Space Complexity: O(h) recursion stack where h is tree height.
 * Edge Cases handled: null root is symmetric; one null and one non-null child pair is asymmetric.
 * Dry Run: root=1, children (2,2), grandchildren (3,4,4,3). Pair checks succeed in mirror order, so return true.
 * LeetCode matching: Matches LeetCode 101 (Symmetric Tree).
 * Correctness Check: Mirror recursion conditions are complete; no correctness issue observed.
 */


public class IsSymmetric101 {
    public boolean isSymmetric(TreeNode root) {
        // Empty tree is symmetric by definition.
        if (root == null) {
            return true;
        }
        // Start mirror comparison from the two root children.
        // We treat the left and right subtrees as two separate trees to compare for mirror properties.
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode l, TreeNode r) {
        // Both missing nodes: this mirror position matches.
        // This is the successful base case for reaching the end of a branch.
        if (l == null && r == null) {
            return true;
        }
        // Exactly one missing node breaks symmetry.
        // This handles structural mismatches where one side is deeper than the other.
        if (l == null || r == null) {
            return false;
        }
        // Values must match, then recurse on mirrored child pairs.
        if (l.val == r.val) {
            // Symmetry requires the "outer" children to match AND the "inner" children to match.
            // l.left (outer left) vs r.right (outer right)
            // l.right (inner left) vs r.left (inner right)
            return dfs(l.left, r.right) && dfs(l.right, r.left);
        }
        // Value mismatch at mirrored positions.
        return false;
    }
}
