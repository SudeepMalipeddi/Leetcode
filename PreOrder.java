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

public class preOrder {
    public void traversal(TreeNode root, List<Integer> res) {
        // checking if root node is null
        if (root == null)
            return;
        // adding the value of the root node to the result list
        res.add(root.val);
        // traversing the left subtree
        traversal(root.left, res);
        // traversing the right subtree
        traversal(root.right, res);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        // creating a new list to store the result
        List<Integer> res = new ArrayList<>();
        // calling the traversal function
        traversal(root, res);
        // returning the result
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
            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);
        }
        return res;
    }
}
