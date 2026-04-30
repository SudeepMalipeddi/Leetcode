/*
 * Problem: 572. Subtree of Another Tree
 *
 * Problem Statement:
 * Given the roots of two binary trees root (s) and subRoot (t), return true if there is a 
 * subtree of root with the same structure and node values as subRoot. A subtree of a 
 * binary tree is a tree that consists of a node in root and all of its descendants.
 *
 * Intuition:
 * A tree 't' is a subtree of 's' if:
 * 1. 't' is identical to 's' itself.
 * 2. 't' is a subtree of the left child of 's'.
 * 3. 't' is a subtree of the right child of 's'.
 * This naturally leads to a recursive solution where we traverse 's' and at every node, 
 * we perform a deep comparison with 't'.
 *
 * Approach:
 * 1. Use a main recursive function (isSubTree) to traverse every node in the main tree 's'.
 * 2. For every node encountered in 's', call a helper function (isSameTree) to check if 
 *     the tree rooted at that node is identical to 't'.
 * 3. The helper function checks for structural identity and value equality recursively.
 *
 * Time Complexity: O(N * M)
 * Where N is the number of nodes in tree 's' and M is the number of nodes in tree 't'. 
 * In the worst case (e.g., all nodes have the same value), we might call isSameTree 
 * for every node in 's'.
 *
 * Space Complexity: O(H)
 * Where H is the height of tree 's'. This is the space used by the recursion stack. 
 * In the worst case of a skewed tree, H = N.
 *
 * Edge Cases:
 * - s is null: Cannot contain any subtree, return false.
 * - t is null: Technically a subtree, but problem constraints usually imply non-null.
 * - s and t are identical: Handled by the first call to isSameTree.
 *
 * Dry Run:
 * s = [3,4,5,1,2], t = [4,1,2]
 * 1. isSubTree(node 3, t): isSameTree(3, 4) is false.
 * 2. Recurse: isSubTree(node 4, t) OR isSubTree(node 5, t).
 * 3. isSubTree(node 4, t): isSameTree(4, 4) checks children.
 *    - left: isSameTree(1, 1) -> true.
 *    - right: isSameTree(2, 2) -> true.
 *    - returns true.
 * 4. Result is true.
 *
 * Correctness Check:
 * The solution is correct. It correctly handles the recursive definition of a subtree.
 */
public class SubtreeOfAnotherTree572 {
    public boolean isSubTree(TreeNode s, TreeNode t){
        // If the main tree is empty, it cannot contain t as a subtree
        if(s == null) return false;
        
        // Check if the tree rooted at the current node s is identical to t
        if(isSameTree(s,t)) return true;
        
        // If not identical at current node, check the left and right subtrees of s
        // We use OR because t only needs to be found in one of the branches
        return isSubTree(s.left, t) || isSubTree(s.right, t);
    }

    public boolean isSameTree(TreeNode s, TreeNode t){
        // If both nodes are null, the trees are identical at this position
        if(s == null && t == null) return true;
        
        // If only one is null, or the values don't match, they are not identical
        if(s == null || t == null) return false;
        if(s.val != t.val) return false;
        
        // Recursively ensure both left and right children are identical
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
