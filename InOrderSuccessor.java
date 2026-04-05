public class inOrderSuccessor {
    // Naive solution
    public TreeNode inorderSuccesor(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val <= p.val) {
                curr = curr.right;
            } else {
                succ = curr;
                curr = curr.left;
            }
        }
        return succ;
    }
}
