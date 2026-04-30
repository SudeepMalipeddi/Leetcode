/*
 * Problem: Binary Tree Preorder, Inorder, and Postorder Traversal in One Pass
 *
 * Problem Statement:
 * Given the root of a binary tree, return the preorder, inorder, and postorder 
 * traversals of its nodes' values using a single iterative approach.
 *
 * Intuition:
 * In a depth-first traversal, every node is conceptually visited three times:
 * 1. When we first arrive at the node (Pre-order).
 * 2. After we return from the left subtree (In-order).
 * 3. After we return from the right subtree (Post-order).
 * By maintaining a "state" for each node on the stack, we can track which 
 * visit we are currently on.
 *
 * Approach:
 * 1. Use a stack to store pairs of (Node, State). State 1 = Pre, 2 = In, 3 = Post.
 * 2. If state is 1: Record value in Pre-order list, increment state to 2, and move to the left child.
 * 3. If state is 2: Record value in In-order list, increment state to 3, and move to the right child.
 * 4. If state is 3: Record value in Post-order list and pop the node from the stack.
 *
 * Time Complexity: O(N), where N is the number of nodes in the tree. Each node is visited exactly 3 times.
 * Space Complexity: O(H), where H is the height of the tree, representing the maximum depth of the stack.
 *
 * Edge Cases:
 * - Empty tree: The code should handle a null root (though the current implementation might throw a NullPointerException).
 * - Skewed tree: Stack depth will be O(N).
 *
 * Dry Run:
 * Tree: [1, null, 2]
 * 1. Push (1, state 1). Pre: [1]. State becomes 2. Left is null.
 * 2. State is 2. In: [1]. State becomes 3. Right is 2. Push (2, state 1).
 * 3. Process node 2: Pre: [1, 2]. State 2. In: [1, 2]. State 3. Post: [2]. Pop 2.
 * 4. Back at node 1: State is 3. Post: [2, 1]. Pop 1. Stack empty.
 *
 * Correctness Check:
 * The provided code has several logic issues:
 * 1. It uses `root.val` as a key in a HashMap, which fails if node values are not unique.
 * 2. It reassigns the `root` variable to a `new TreeNode(entry.getKey())` when backtracking, 
 *    which loses the original tree structure and references.
 * 3. It does not handle the case where `root` is initially null.
 * 4. The loop to find the key in the entry set is inefficient and assumes only one entry exists.
 */

import java.util.*;

public class Threetraversals {
    public void preInPostTraversal(TreeNode root) {
        // Stack stores a map representing the node value and its current traversal state (1, 2, or 3)
        Stack<HashMap<Integer, Integer>> st = new Stack<>();
        HashMap<Integer, Integer> pair = new HashMap<>();
        
        // Initialize the stack with the root node in state 1 (Pre-order)
        st.push(new HashMap<>());
        st.peek().put(root.val, 1);
        
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        while (!st.isEmpty()) {
            // State 1: Pre-order processing
            if (st.peek().get(root.val) == 1) {
                pre.add(root.val);
                st.peek().put(root.val, 2); // Move to state 2 (In-order)
                if (root.left != null) {
                    pair = new HashMap<>();
                    pair.put(root.left.val, 1);
                    st.push(pair);
                    root = root.left; // Move pointer to left child
                }
            } 
            // State 2: In-order processing
            else if (st.peek().get(root.val) == 2) {
                in.add(root.val);
                st.peek().put(root.val, 3); // Move to state 3 (Post-order)
                if (root.right != null) {
                    pair = new HashMap<>();
                    pair.put(root.right.val, 1);
                    st.push(pair);
                    root = root.right; // Move pointer to right child
                }
            } 
            // State 3: Post-order processing
            else if (st.peek().get(root.val) == 3) {
                post.add(root.val);
                st.pop(); // Node fully processed, remove from stack
                
                // Backtrack: Update the 'root' pointer to the parent node currently at the top of the stack
                if (!st.isEmpty()) {
                    for (Map.Entry<Integer, Integer> entry : st.peek().entrySet()) {
                        // Note: Creating a new TreeNode here is problematic as it loses child references
                        if (entry.getValue() == 1 || entry.getValue() == 2 || entry.getValue() == 3) {
                            root = new TreeNode(entry.getKey());
                        }
                    }
                }
            }
        }

    }
}
