import java.util.*;

class TreeBoundaryTraversal {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }


    public boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public void leftHelper(TreeNode root, ArrayList<Integer> res) {
        TreeNode curr = root.left;
        while (curr != null) {
            if (isLeaf(curr) == false)
                res.add(curr.val);
            if (curr.left != null)
                curr = curr.left;
            else
                curr = curr.right;
        }
    }

    public void rightHelper(TreeNode root, ArrayList<Integer> res) {
        TreeNode curr = root.right;
        Stack<Integer> st = new Stack<>();
        while (curr != null) {
            if (isLeaf(curr) == false)
                st.push(curr.val);
            if (curr.right != null)
                curr = curr.right;
            else
                curr = curr.left;
        }
        while (!st.isEmpty())
            res.add(st.pop());
    }

    public void leavesHelper(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return;
        if (isLeaf(root))
            res.add(root.val);
        leavesHelper(root.left, res);
        leavesHelper(root.right, res);
    }

    public ArrayList<Integer> boundaryTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        if (isLeaf(root) == false)
            res.add(root.val);

        leftHelper(root, res);
        leavesHelper(root, res);
        rightHelper(root, res);

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeBoundaryTraversal tree = new TreeBoundaryTraversal();
        ArrayList<Integer> result = tree.boundaryTraversal(root);
        System.out.println(result); // Output: [1, 2, 4, 5, 6, 7, 3]

    }
}