import java.util.Stack;

public class RecoveraTreeFromPreorderTraversal1028 {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode recoverFromPreorder(String str) {
        Stack<TreeNode> st = new Stack<>();
        int i = 0, n = str.length();

        while (i < n) {
            int dashes = 0;

            // Count dashes to determine depth
            while (i < n && str.charAt(i) == '-') {
                dashes++;
                i++;
            }

            // Extract the number
            int j = i;
            while (j < n && str.charAt(j) != '-') {
                j++;
            }
            int val = Integer.parseInt(str.substring(i, j));
            TreeNode node = new TreeNode(val);

            // Pop nodes until stack size matches the current depth
            while (st.size() > dashes) {
                st.pop();
            }

            // Attach node to parent
            if (!st.isEmpty()) {
                TreeNode parent = st.peek();
                if (parent.left == null) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }

            st.push(node);
            i = j;
        }

        // Return root (bottom of stack)
        while (st.size() > 1) {
            st.pop();
        }
        return st.peek();
    }
}
