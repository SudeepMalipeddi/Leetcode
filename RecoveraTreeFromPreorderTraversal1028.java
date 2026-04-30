/*
 * Problem: 1028. Recover a Tree From Preorder Traversal
 *
 * Problem Statement:
 * We are given a string 'traversal' representing the preorder traversal of a binary tree, 
 * where the depth of each node is indicated by the number of dashes '-' preceding its value.
 * We need to reconstruct the original binary tree from this string.
 *
 * Intuition:
 * In a preorder traversal (Root-Left-Right), we explore the tree depth-first. The number of dashes 
 * tells us the exact level of the node. By using a stack to maintain the current path from the root, 
 * we can identify a node's parent: the parent is the node currently in the stack whose depth 
 * is exactly one less than the current node's depth.
 *
 * Approach:
 * 1. Iterate through the string using a pointer.
 * 2. For each node, count the number of dashes to determine its depth.
 * 3. Parse the integer value following the dashes.
 * 4. Maintain a stack of nodes representing the current path. The stack's size represents the 
 *    current depth level we are working on.
 * 5. If the stack size is greater than the current node's depth, it means we have finished 
 *    processing a subtree and need to backtrack. Pop from the stack until its size equals the depth.
 * 6. The node at the top of the stack is the parent. Attach the new node as the left child 
 *    if it's null; otherwise, attach it as the right child.
 * 7. Push the new node onto the stack.
 * 8. After the loop, the root is the bottom-most element in the stack.
 *
 * Time Complexity: O(N), where N is the length of the input string. Each character is processed once.
 * Space Complexity: O(H), where H is the height of the tree, representing the maximum stack size.
 *
 * Edge Cases:
 * - Single node tree (e.g., "1"): No dashes, stack size 0 matches depth 0, returns node 1.
 * - Skewed trees: The stack logic naturally handles both left-skewed and right-skewed structures.
 *
 * Dry Run:
 * Input: "1-2--3--4-5"
 * 1. "1": depth 0, val 1. Stack empty. Push 1. Stack: [1]
 * 2. "-2": depth 1, val 2. Stack size 1 == depth 1. Parent is 1. 1.left = 2. Push 2. Stack: [1, 2]
 * 3. "--3": depth 2, val 3. Stack size 2 == depth 2. Parent is 2. 2.left = 3. Push 3. Stack: [1, 2, 3]
 * 4. "--4": depth 2, val 4. Stack size 3 > depth 2. Pop 3. Stack size 2 == depth 2. Parent is 2. 2.right = 4. Push 4. Stack: [1, 2, 4]
 * 5. "-5": depth 1, val 5. Stack size 3 > depth 1. Pop 4, Pop 2. Stack size 1 == depth 1. Parent is 1. 1.right = 5. Push 5. Stack: [1, 5]
 * Result: Root is 1.
 *
 * Correctness Check:
 * The solution correctly handles multi-digit numbers and correctly identifies parent-child 
 * relationships based on the dash count. The stack-based approach effectively simulates 
 * the recursion of a preorder traversal.
 */
import java.util.Stack;

public class RecoveraTreeFromPreorderTraversal1028 {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode recoverFromPreorder(String str) {
        // The stack stores the current path from the root to the most recently processed node.
        Stack<TreeNode> st = new Stack<>();
        int i = 0, n = str.length();

        
        // Iterate through the entire string to reconstruct the tree node by node.
        while (i < n) {
            int dashes = 0;

            
            // Count the number of dashes to determine the depth of the current node.
            while (i < n && str.charAt(i) == '-') {
                dashes++;
                i++;
            }

            
            int j = i;
            
            // Find the end of the numeric value for the current node.
            while (j < n && str.charAt(j) != '-') {
                j++;
            }
            int val = Integer.parseInt(str.substring(i, j));
            TreeNode node = new TreeNode(val);

            
            // If the stack size is greater than the current depth, we are no longer in the 
            // subtree of the stack's top node. Backtrack to find the correct parent.
            while (st.size() > dashes) {
                st.pop();
            }

            
            // If the stack is not empty, the top node is the parent of the current node.
            if (!st.isEmpty()) {
                TreeNode parent = st.peek();
                
                // In a preorder traversal, the left child is always encountered before the right child.
                if (parent.left == null) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }

            // Push the current node onto the stack to potentially become a parent for future nodes.
            st.push(node);
            i = j;
        }

        
        // The root of the tree is the first node we pushed. Pop until only the root remains.
        while (st.size() > 1) {
            st.pop();
        }
        return st.peek();
    }
}
