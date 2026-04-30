/*
Problem Statement:
- Return binary tree postorder traversal.

Intuition:
- Postorder is left-right-root; recursion expresses it directly, two-stack method simulates it iteratively.

Approach:
- traversal handles recursive solution; postOrderTraversal uses stack1 for root-right-left order and stack2 to reverse.

Time Complexity:
- O(n) for both methods.

Space Complexity:
- O(h) recursive or O(n) iterative stacks.

Edge Cases:
- Null root returns empty list.

Dry Run:
- Node 1 with children 2,3 yields [2,3,1].
*/
import java.util.*;

public class PostOrder {
    public void traversal(TreeNode root, List<Integer> res) {
        
        if (root == null)
            return;
        traversal(root.left, res);
        traversal(root.right, res);
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
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        
        while (!stack1.isEmpty()) {
            TreeNode curr = stack1.pop();
            // stack2 reverses root-right-left into postorder left-right-root.
            stack2.push(curr);
            
            if (curr.left != null) {
                stack1.push(curr.left);
            }
            
            if (curr.right != null) {
                stack1.push(curr.right);
            }
        }
        
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().val);
        }
        return res;
    }
}
