/*
Problem Statement:
- Return binary tree preorder traversal.

Intuition:
- Preorder visits root before left and right subtrees.

Approach:
- Provide both recursive traversal and iterative stack-based traversal.

Time Complexity:
- O(n).

Space Complexity:
- O(h) recursive or O(n) iterative stack worst case.

Edge Cases:
- Null root returns empty list.

Dry Run:
- Node 1 with children 2,3 yields [1,2,3].
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class PreOrder {
    public void traversal(TreeNode root, List<Integer> res) {
        
        if (root == null)
            return;
        
        res.add(root.val);
        
        traversal(root.left, res);
        
        traversal(root.right, res);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        
        List<Integer> res = new ArrayList<>();
        
        traversal(root, res);
        
        return res;
    }

    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            
            // Push right first so left is processed next (LIFO stack).
            if (curr.right != null)
                stack.push(curr.right);
            
            if (curr.left != null)
                stack.push(curr.left);
        }
        return res;
    }
}
