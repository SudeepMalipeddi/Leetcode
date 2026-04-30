/*
 * Problem: 100. Same Tree
 *
 * Problem Statement:
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 * Intuition:
 * A tree is a recursive data structure. Two trees are identical if and only if:
 * 1. The current nodes are both null (base case) OR both non-null with equal values.
 * 2. Their left subtrees are identical.
 * 3. Their right subtrees are identical.
 *
 * Approach:
 * 1. Handle the base case: If both nodes are null, we've reached the end of both branches simultaneously, so they are identical.
 * 2. Handle the failure cases: If one node is null while the other isn't, or if the values stored in the nodes differ, the trees are not identical.
 * 3. Recursively call the function for the left children and the right children.
 * 4. Use the logical AND operator to ensure both subtrees satisfy the "same tree" property.
 *
 * Time Complexity: O(N), where N is the number of nodes in the trees. We visit each node exactly once.
 * Space Complexity: O(H), where H is the height of the tree. This is the space used by the recursion stack. 
 * In the worst case (a completely unbalanced/skewed tree), H = N. In the best case (balanced tree), H = log(N).
 *
 * Edge Cases:
 * - Both trees are empty (null): Should return true.
 * - One tree is empty, the other is not: Should return false.
 * - Trees have the same values but different structures: Should return false.
 * - Trees have the same structure but different values: Should return false.
 *
 * Dry Run:
 * p = [1, 2], q = [1, 2]
 * 1. isSameTree(p(1), q(1)): p and q not null, values match (1==1).
 * 2. Call isSameTree(p.left(2), q.left(2)): values match (2==2).
 * 3. Call isSameTree(p.left.left(null), q.left.left(null)): returns true.
 * 4. Call isSameTree(p.left.right(null), q.left.right(null)): returns true.
 * 5. Result for node 2 is (true && true) = true.
 * 6. Call isSameTree(p.right(null), q.right(null)): returns true.
 * 7. Final result for node 1 is (true && true) = true.
 *
 * Correctness Check:
 * The solution correctly implements the recursive definition of tree equality. 
 * Note: This code assumes a standard TreeNode class exists with 'val', 'left', and 'right' fields.
 */
public class SameTree100 {
    boolean isSameTree(TreeNode p,TreeNode q){
        // Base Case: If both nodes are null, we have reached the end of both branches.
        // This means the structure matches up to this point.
        if(p==null && q==null){
            return true;
        }
        
        // If one node is null and the other is not, the structures are different.
        // If the values at the current nodes are different, the trees are not the same.
        if(p == null || q == null || p.val!=q.val){
            return false;
        }
        
        // Recursive Step: The trees are the same only if both the left subtrees 
        // AND the right subtrees are identical.
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    public static void main(String[] args) {
        SameTree100 st = new SameTree100();
        
        // Constructing Tree p: 1 -> (2, 3)
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        
        // Constructing Tree q: 1 -> (2, 3)
        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);
        
        // Expected output: true
        System.out.println(st.isSameTree(p, q));
    }
}
