/*
 * Problem: LeetCode 113 - Path Sum II
 *
 * Problem Statement:
 * Given the root of a binary tree and an integer targetSum, return all
 * root-to-leaf paths where the sum of node values equals targetSum.
 * Each path must end at a leaf node (no children).
 *
 * Intuition:
 * A DFS naturally traces every root-to-leaf path. By carrying a running
 * "remaining sum" down the tree (subtracting each node's value), we can
 * check at every leaf whether the accumulated path hits the target.
 * Backtracking lets us reuse a single list for all paths without extra copies.
 *
 * Approach:
 * 1. Add the current node's value to the path list.
 * 2. If at a leaf and root.val == sum (remaining), the full path sums to target — copy it into results.
 * 3. Recurse left and right, passing sum - root.val as the new remaining sum.
 * 4. Remove the last element from path (backtrack) before returning to the caller.
 *
 * Time Complexity: O(n * h) — n nodes visited, each valid path copied takes O(h).
 * Space Complexity: O(h) for the recursion stack, where h is tree height (O(log n) balanced, O(n) skewed).
 *
 * Edge Cases:
 * - null root: dfs returns immediately, result stays empty.
 * - Internal node whose value equals targetSum: not added (leaf check prevents it).
 * - Multiple valid paths: each is independently copied into the result list.
 *
 * Dry Run (tree: 5->4->11->2 and 5->4->11->7, targetSum=22):
 * dfs(5, 22): path=[5], recurse left with sum=17
 *   dfs(4, 17): path=[5,4], recurse left with sum=13
 *     dfs(11, 13): path=[5,4,11], recurse left with sum=2
 *       dfs(2, 2): leaf, val==sum → add [5,4,11,2] to results, backtrack
 *     recurse right with sum=2
 *       dfs(7, 2): leaf, val!=sum → backtrack
 *     backtrack path=[5,4]
 *   backtrack path=[5]
 * Result: [[5,4,11,2]]
 *
 * Correctness Check:
 * Looks correct. The new ArrayList<>(path) copy at the leaf is necessary —
 * without it, all result entries would point to the same (eventually empty) list.
 */
import java.util.*;

public class PathSumII113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        dfs(root, sum, path, res);
        return res;
    }

    public void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> res) {
        
        if (root == null) {
            return;
        }
        path.add(root.val);
        
        // Leaf and exact remaining sum means current path is a valid answer.
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList<Integer>(path));
        }
        dfs(root.left, sum - root.val, path, res);
        dfs(root.right, sum - root.val, path, res);
        // Backtrack before exploring sibling branches.
        path.remove(path.size() - 1);
    }
}
