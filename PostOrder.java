/*
 * Problem: LeetCode 145 - Binary Tree Postorder Traversal
 *
 * Problem Statement:
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * Postorder traversal visits nodes in the order: Left Subtree -> Right Subtree -> Root.
 *
 * Intuition:
 * 1. Recursive: The definition of postorder is recursive by nature. We visit the left child, 
 *    then the right child, then the current node.
 * 2. Iterative (Two Stacks): A standard Preorder is Root -> Left -> Right. If we modify 
 *    it to Root -> Right -> Left and store the results in a stack, popping that stack 
 *    reverses the order to Left -> Right -> Root, which is exactly Postorder.
 *
 * Approach:
 * 1. Recursive: Use a helper function that calls itself on the left child, then the right 
 *    child, and finally adds the current node's value to the result list.
 * 2. Iterative: Use 'stack1' to perform a modified preorder (Root-Right-Left). Every time 
 *    a node is popped from 'stack1', push it into 'stack2'. Because 'stack2' is Last-In-First-Out, 
 *    the final output will be the reverse: Left-Right-Root.
 *
 * Time Complexity: O(N)
 * Both approaches visit every node exactly once.
 *
 * Space Complexity: O(H) to O(N)
 * Recursive: O(H) where H is the height of the tree (stack frames). In the worst case 
 * (skewed tree), this is O(N).
 * Iterative: O(N) to store nodes in the stacks.
 *
 * Edge Cases:
 * - Empty tree (root is null): Should return an empty list.
 * - Single node tree: Should return a list with one element.
 *
 * Dry Run:
 * Tree: [1, 2, 3] (1 is root, 2 is left, 3 is right)
 * 1. Push 1 to stack1.
 * 2. Pop 1 from stack1, push 1 to stack2. Push 1.left (2) then 1.right (3) to stack1.
 * 3. Pop 3 from stack1, push 3 to stack2. (3 has no children).
 * 4. Pop 2 from stack1, push 2 to stack2. (2 has no children).
 * 5. Stack2 now contains [1, 3, 2] (bottom to top).
 * 6. Pop stack2 into result: [2, 3, 1].
 *
 * Correctness Check:
 * The solution is correct. Note that there are two methods with nearly identical names 
 * (postorderTraversal vs postOrderTraversal); one is a recursive wrapper and the other 
 * is the iterative implementation.
 */
import java.util.*;

public class PostOrder {
    public void traversal(TreeNode root, List<Integer> res) {
        
        // Base case: if we reach a null node, stop recursion
        if (root == null)
            return;
        // Recurse all the way to the left
        traversal(root.left, res);
        // Recurse all the way to the right
        traversal(root.right, res);
        // Visit the root after both subtrees are processed
        res.add(root.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traversal(root, res);
        return res;
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        
        if (root == null)
            return res;
        // stack1 is used for the modified preorder traversal (Root -> Right -> Left)
        Stack<TreeNode> stack1 = new Stack<>();
        // stack2 acts as a buffer to reverse the modified preorder into postorder
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        
        while (!stack1.isEmpty()) {
            TreeNode curr = stack1.pop();
            // stack2 reverses root-right-left into postorder left-right-root.
            stack2.push(curr);
            
            // We push left then right so that right is on top of stack1.
            // This ensures right is processed before left for the "reverse" stack.
            if (curr.left != null) {
                stack1.push(curr.left);
            }
            
            if (curr.right != null) {
                stack1.push(curr.right);
            }
        }
        
        // Pop everything from stack2 to get the final Left-Right-Root order
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().val);
        }
        return res;
    }
}
