/*
Problem Statement:
- Return all root-to-leaf paths whose sum equals target.

Intuition:
- DFS with backtracking explores each path while maintaining remaining sum.

Approach:
- Append node to path, check leaf+sum match, recurse children with reduced sum, then backtrack.

Time Complexity:
- O(n*h) worst-case including path copy cost.

Space Complexity:
- O(h) recursion stack excluding result storage.

Edge Cases:
- Null root yields empty path list.

Dry Run:
- On path 5->4->11->2 with target 22, remaining sum reaches 0 at leaf, so store path.
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
