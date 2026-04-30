/*
 * Problem Statement:
 * Given the root of an N-ary tree, return its maximum depth.
 * Maximum depth is the number of nodes on the longest path from root to any leaf.
 *
 * Intuition:
 * The depth at any node is 1 + (maximum depth among its children).
 * So recursively solve each child subtree and keep the largest answer.
 *
 * Approach:
 * 1) If root is null, depth is 0.
 * 2) Recursively compute depth of every child.
 * 3) Return max child depth + 1 for the current root.
 *
 * Time Complexity:
 * O(n), where n is the number of nodes (each node is visited once).
 *
 * Space Complexity:
 * O(h) recursion stack, where h is tree height (worst-case O(n)).
 *
 * Edge Cases handled:
 * - root == null -> returns 0.
 * - Leaf node (no children) -> returns 1.
 * - Assumes children list is usable per typical LeetCode Node definition.
 *
 * Dry Run:
 * root(1) with children [2,3], and 3 has child [4]
 * - depth(2)=1, depth(4)=1, depth(3)=2
 * - depth(1)=max(1,2)+1=3
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 559 recursive DFS solution shape.
 *
 * Correctness Check:
 * Recurrence depth(node)=1+max(depth(child)) with base depth(null)=0 is standard and correct.
 */

import java.util.List;

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}

public class MaximumDepthofNaryTree559 {
    public int maxDepth(Node root) {
        // Empty tree has depth 0.
        if (root == null) {
            return 0;
        }
        int res = 0;
        // Explore every child subtree and keep the deepest depth seen so far.
        for (Node x : root.children) {
            res = Math.max(res, maxDepth(x));
        }
        // Include current node itself.
        return res + 1;
    }
}
