/*
Problem Statement:
- Recover binary tree encoded by preorder with dash depth markers.

Intuition:
- Stack depth should match node depth; pop to parent depth before attaching child.

Approach:
- Parse dash count and value, pop until stack size equals depth, attach as left then right child.

Time Complexity:
- O(n) parse and stack operations.

Space Complexity:
- O(h) stack depth.

Edge Cases:
- Single node string returns that node as root.

Dry Run:
- "1-2--3": 2 attaches to 1.left, 3 attaches to 2.left.
*/
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

            
            while (i < n && str.charAt(i) == '-') {
                dashes++;
                i++;
            }

            
            int j = i;
            
            while (j < n && str.charAt(j) != '-') {
                j++;
            }
            int val = Integer.parseInt(str.substring(i, j));
            TreeNode node = new TreeNode(val);

            
            // Pop back to the parent depth of the current node.
            while (st.size() > dashes) {
                st.pop();
            }

            
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

        
        while (st.size() > 1) {
            st.pop();
        }
        return st.peek();
    }
}
