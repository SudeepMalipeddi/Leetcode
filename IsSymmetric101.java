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
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode l, TreeNode r) {
        // Both missing nodes: this mirror position matches.
        if (l == null && r == null) {
            return true;
        }
        // Exactly one missing node breaks symmetry.
        if (l == null || r == null) {
            return false;
        }
        // Values must match, then recurse on mirrored child pairs.
        if (l.val == r.val) {
            return dfs(l.left, r.right) && dfs(l.right, r.left);
        }
        // Value mismatch at mirrored positions.
        return false;
    }
}
