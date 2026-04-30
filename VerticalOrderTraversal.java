/*
 * Problem: 987. Vertical Order Traversal of a Binary Tree
 *
 * Problem Statement:
 * Given the root of a binary tree, calculate the vertical order traversal. For each node at 
 * position (row, col), its left child is at (row + 1, col - 1) and its right child is at 
 * (row + 1, col + 1). The result should be grouped by column index from left to right, 
 * then by row index from top to bottom, and finally by value in ascending order for 
 * nodes at the same (row, col) position.
 *
 * Intuition:
 * To solve this, we need a way to track the coordinates (row, column) of every node. 
 * Since the output requires specific ordering (column -> row -> value), using sorted 
 * data structures like TreeMap and PriorityQueue allows us to maintain this order 
 * automatically during traversal or collection.
 *
 * Approach:
 * 1. Use a nested data structure: TreeMap<Column, TreeMap<Row, PriorityQueue<Value>>>.
 *    - The outer TreeMap sorts columns from left to right.
 *    - The inner TreeMap sorts rows from top to bottom.
 *    - The PriorityQueue sorts values at the exact same coordinate.
 * 2. Perform a Depth First Search (DFS) to traverse the tree, passing and updating 
 *    coordinates (row, col) as we go.
 * 3. After the traversal, iterate through the nested maps to extract values into 
 *    the final list of lists.
 *
 * Time Complexity: O(N log N), where N is the number of nodes. Each node is visited 
 * once (O(N)), but each insertion into the TreeMaps and PriorityQueue takes logarithmic 
 * time relative to the number of elements.
 * Space Complexity: O(N) to store all node values and their coordinates in the map structure.
 *
 * Edge Cases:
 * - Empty tree: Handled by the null check at the start.
 * - Nodes at the same (row, col): Handled by the PriorityQueue.
 * - Skewed trees: Handled by the coordinate system (col - 1 or col + 1).
 *
 * Dry Run:
 * Input: [3,9,20,null,null,15,7]
 * 1. DFS(3, 0, 0) -> hmap[0][0] = {3}
 * 2. DFS(9, -1, 1) -> hmap[-1][1] = {9}
 * 3. DFS(20, 1, 1) -> hmap[1][1] = {20}
 * 4. DFS(15, 0, 2) -> hmap[0][2] = {15}
 * 5. DFS(7, 2, 2) -> hmap[2][2] = {7}
 * Result extraction: Col -1: [9], Col 0: [3, 15], Col 1: [20], Col 2: [7]
 *
 * Correctness Check:
 * The solution correctly implements the sorting requirements. Using TreeMap ensures 
 * columns and rows are processed in order, and PriorityQueue handles value ties.
 */

// Source: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
// Didnt solve it yet. I will solve it later.
import java.util.*;

class VerticalOrderTraversal {
    // Nested map structure to maintain sorting:
    // Outer Key: Column index (sorted naturally)
    // Inner Key: Row index (sorted naturally)
    // Value: PriorityQueue to handle multiple nodes at the same (col, row) in ascending order
    TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> hmap;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        hmap = new TreeMap<>();
        // Start DFS from the root at coordinate (0, 0)
        dfs(root, 0, 0);

        // Iterate through the columns in ascending order (left to right)
        for (int i : hmap.keySet()) {
            List<Integer> temp = new ArrayList<>();
            TreeMap<Integer, PriorityQueue<Integer>> t = hmap.get(i);
            // Iterate through the rows in ascending order (top to bottom)
            for (int j : t.keySet()) {
                PriorityQueue<Integer> pq = t.get(j);
                // Extract all values at this specific (col, row) coordinate
                while (!pq.isEmpty()) {
                    temp.add(pq.poll());
                }
            }
            res.add(temp);
        }
        return res;
    }

    public void dfs(TreeNode root, int col, int row) {
        if (root == null) {
            return;
        }
        
        // Retrieve or initialize the map for the current column
        TreeMap<Integer, PriorityQueue<Integer>> t = hmap.getOrDefault(col, new TreeMap<>());
        // Retrieve or initialize the priority queue for the current row within that column
        PriorityQueue<Integer> pq = t.getOrDefault(row, new PriorityQueue<>());
        
        // Add the current node's value to the PQ (automatically sorts if multiple values exist)
        pq.add(root.val);
        
        // Put the updated structures back into the maps
        t.put(row, pq);
        hmap.put(col, t);

        // Standard DFS: Left child moves left (col-1) and down (row+1)
        dfs(root.left, col - 1, row + 1);
        // Right child moves right (col+1) and down (row+1)
        dfs(root.right, col + 1, row + 1);
    }
}
