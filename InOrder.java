/*
 * Problem: 94. Binary Tree Inorder Traversal
 *
 * Problem Statement:
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * Inorder traversal visits nodes in the specific order: Left Subtree -> Root -> Right Subtree.
 *
 * Intuition:
 * To visit nodes in Left-Root-Right order, we must explore as far left as possible before processing 
 * any node. Once we hit a null, we "backtrack" to the last parent, record it, and then explore its 
 * right subtree. This naturally fits a stack-based approach (LIFO) or recursion.
 *
 * Approach:
 * 1. Recursive: Use the system call stack to visit the left child, then the current node, then the right child.
 * 2. Iterative: Use an explicit Stack to simulate recursion. Push nodes while moving left; when 
 *    stuck, pop to "visit" the node and move to its right child.
 *
 * Time Complexity: O(n) where n is the number of nodes, as we visit each node exactly once.
 * Space Complexity: O(h) where h is the height of the tree (O(log n) for balanced, O(n) for skewed), 
 * representing the maximum depth of the stack.
 *
 * Edge Cases:
 * - Empty tree (root is null): Returns an empty list.
 * - Single node tree: Returns a list with one value.
 * - Right-skewed or Left-skewed trees: Correctness maintained by the stack/recursion depth.
 *
 * Dry Run:
 * Tree: [1, null, 2, 3] (1 is root, 2 is right child of 1, 3 is left child of 2)
 * 1. curr=1: push(1), curr=null (1.left).
 * 2. curr is null: pop(1), res=[1], curr=2 (1.right).
 * 3. curr=2: push(2), curr=3 (2.left).
 * 4. curr=3: push(3), curr=null (3.left).
 * 5. curr is null: pop(3), res=[1, 3], curr=null (3.right).
 * 6. curr is null: pop(2), res=[1, 3, 2], curr=null (2.right).
 * 7. Stack empty and curr null: break.
 *
 * Correctness Check:
 * The solution correctly implements the inorder traversal logic for both recursive and iterative methods.
 */
import java.util.*;

public class InOrder {
    public void traversal(TreeNode root, List<Integer> res) {
        // Base case: If the current node is null, we've reached the end of a branch.
        if (root == null) {
            return;
        }
        // Step 1: Recursively visit the entire left subtree.
        traversal(root.left, res);
        // Step 2: Visit the current node (Root).
        res.add(root.val);
        // Step 3: Recursively visit the entire right subtree.
        traversal(root.right, res);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traversal(root, res);
        return res;
    }

    public List<Integer> inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        
        // The loop continues as long as there are nodes to explore or nodes in the stack to backtrack to.
        while (true) {
            if (curr != null) {
                // "Go Left": Push the current node and move to its left child.
                // We push it to the stack to remember to visit it AFTER its left subtree is done.
                stack.push(curr);
                curr = curr.left;
            } else {
                // If curr is null, we've reached the leftmost limit of the current path.
                if (stack.isEmpty()) {
                    break;
                }
                // "Process Root": Pop the last node we pushed (the parent of the null child).
                curr = stack.pop();
                res.add(curr.val);
                
                // "Go Right": Now that the left subtree and root are done, move to the right subtree.
                curr = curr.right;
            }
        }
        return res;
    }
}
