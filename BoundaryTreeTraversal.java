/*
 * Problem: Boundary Traversal of a Binary Tree (closest to "Boundary of Binary Tree")
 * Problem Statement: Return the boundary of a binary tree in anti-clockwise
 *   order starting from the root: left boundary, leaves, then right boundary.
 * Intuition: Collect the three boundary parts separately to avoid duplicates.
 * Approach:
 *   1) Add root if it is not a leaf.
 *   2) Walk down the left boundary, excluding leaves.
 *   3) Add all leaves via DFS.
 *   4) Walk down the right boundary, excluding leaves, and add in reverse.
 * Time Complexity: O(n) because every node is visited at most a few times.
 * Space Complexity: O(h) for recursion stack in addLeaves plus output storage.
 * Edge Cases: Single-node tree, skewed trees, trees with only one subtree.
 * Dry Run: root=1, left=2, right=3, leaves 4,5 -> boundary [1,2,4,5,3].
 * Correctness Check: Separating boundaries prevents duplicates (e.g., leaves
 *   that are also on boundaries), matching the standard boundary traversal.
 */
import java.util.*;

public class BoundaryTreeTraversal {
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
            if (cur.left != null)
                cur = cur.left;
            else
                cur = cur.right;
        }
    }

    public void addLeaves(TreeNode root, List<Integer> res) {
        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }
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
            if (cur.right != null)
                cur = cur.right;
            else
                cur = cur.left;
        }
        for (int i = temp.size() - 1; i >= 0; i--)
            res.add(temp.get(i));
    }

    public List<Integer> printBounday(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        if (isLeaf(root) == false)
            res.add(root.val);
        addLeftBoundary(root, res); // left boundary (excluding leaves)
        addLeaves(root, res); // all leaf nodes
        addRightBoundary(root, res); // right boundary in reverse
        return res;
    }

}
