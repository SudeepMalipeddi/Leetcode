/*
 * Problem: 559. Maximum Depth of N-ary Tree
 *
 * Problem Statement:
 * Given the root of an N-ary tree, find its maximum depth. The maximum depth is 
 * defined as the number of nodes along the longest path from the root node 
 * down to the farthest leaf node.
 *
 * Intuition:
 * The depth of a tree rooted at a specific node is 1 plus the maximum depth 
 * of its children. This recursive relationship allows us to solve the problem 
 * by breaking it down into subproblems: find the depth of every subtree and 
 * pick the largest one.
 *
 * Approach:
 * 1. Handle the base case: if the root is null, the depth is 0.
 * 2. Initialize a variable to track the maximum depth of children (starting at 0).
 * 3. Iterate through each child in the current node's children list.
 * 4. Recursively call maxDepth on each child and update the maximum depth found.
 * 5. Return the maximum child depth plus 1 (to account for the current node).
 *
 * Time Complexity: O(N)
 * We visit every node exactly once to calculate its contribution to the depth.
 *
 * Space Complexity: O(H)
 * The space used is the implicit recursion stack. In the worst case (a skewed tree), 
 * this is O(N). In a balanced tree, it is O(log N).
 *
 * Edge Cases:
 * - The tree is empty (root is null): returns 0.
 * - The tree has only one node (root is a leaf): returns 1.
 * - A node has many children but only one path is deep.
 *
 * Dry Run:
 * Input: Root(1) -> Children: [Node(3), Node(2), Node(4)], Node(3) -> Children: [Node(5), Node(6)]
 * 1. maxDepth(1) calls maxDepth(3), maxDepth(2), maxDepth(4).
 * 2. maxDepth(3) calls maxDepth(5) and maxDepth(6).
 * 3. maxDepth(5) and maxDepth(6) are leaves, they return 1.
 * 4. maxDepth(3) takes max(1, 1) + 1 = 2.
 * 5. maxDepth(2) and maxDepth(4) are leaves, they return 1.
 * 6. maxDepth(1) takes max(2, 1, 1) + 1 = 3. Result is 3.
 *
 * Correctness Check:
 * The solution correctly implements a Depth First Search (DFS) approach. 
 * The base case and the recursive step properly handle N-ary structures.
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
        // Base Case: If the current node is null, it contributes 0 to the depth.
        if (root == null) {
            return 0;
        }
        
        // Initialize the maximum depth of subtrees to 0.
        // If the node is a leaf (has no children), the loop won't execute and res remains 0.
        int res = 0;
        
        // Recursive Step: Iterate through all children of the current node.
        // This is a post-order traversal logic where we process children before the parent.
        for (Node x : root.children) {
            // Find the depth of the current child's subtree and update our maximum.
            res = Math.max(res, maxDepth(x));
        }
        
        // The depth of the current node is 1 (itself) plus the maximum depth of its subtrees.
        return res + 1;
    }
}
