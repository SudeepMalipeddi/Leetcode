/*
 * Problem: 1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree
 *
 * Problem Statement:
 * Given two binary trees, 'original' and 'cloned', where 'cloned' is a copy of 'original', 
 * and a reference to a node 'target' in the original tree, find and return the 
 * corresponding node in the cloned tree.
 *
 * Intuition:
 * Since the cloned tree is a structural mirror of the original, any path taken in the 
 * original tree to reach the target node will lead to the identical position in the 
 * cloned tree. By traversing both trees simultaneously using the same logic, we can 
 * identify the target in the original and return the node at the same "coordinates" in the clone.
 *
 * Approach:
 * 1. Perform a Depth First Search (DFS) traversal (In-order, Pre-order, or Post-order all work).
 * 2. Move through both trees in parallel (e.g., go left in both, then right in both).
 * 3. Compare the current node in the 'original' tree with the 'target' node reference.
 * 4. When a match is found (original == target), the current node in the 'cloned' tree is the result.
 *
 * Time Complexity: O(N) where N is the number of nodes in the tree. In the worst case, 
 * we may need to visit every node if the target is the last one visited or not present.
 * Space Complexity: O(H) where H is the height of the tree. This space is consumed by 
 * the implicit recursion stack. In a balanced tree, H = log N; in a skewed tree, H = N.
 *
 * Edge Cases:
 * - The tree is empty (handled by null checks).
 * - The target node is the root of the tree.
 * - The target node is a leaf node.
 *
 * Dry Run:
 * Original: [7,4,3,null,null,6,19], Target: Node(3)
 * 1. Start at root: original=7, cloned=7. Not target.
 * 2. Recurse Left: original=4, cloned=4. Not target.
 * 3. Recurse Left/Right of 4: Both null. Return.
 * 4. Back to root, Recurse Right: original=3, cloned=3. Match!
 * 5. Store cloned Node(3) in result and return.
 *
 * Correctness Check:
 * The solution is correct. Note that the first implementation uses a static variable 'res', 
 * which is generally discouraged in multi-threaded environments or competitive programming 
 * platforms unless reset between test cases. The second method (getTargetCopy1) is more robust.
 */
public class FindaCorrespondingNodeofaBinaryTreeinaCloneofThatTree1379 {
    // Static variable to store the result across recursive calls in the first approach.
    public static TreeNode res;

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // Base case: if the tree is empty or target doesn't exist, we can't find anything.
        if (original == null || target == null) {
            return null;
        }
        // Initiate the recursive traversal.
        helper(original, cloned, target);
        return res;
    }

    public static void helper(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // Standard DFS base case for recursion.
        if (original == null || target == null) {
            return;
        }
        // Traverse the left subtree of both trees simultaneously.
        helper(original.left, cloned.left, target);
        
        // Check if the current node in the original tree is the target reference.
        // We use '==' for reference equality as per problem constraints.
        if (original == target) {
            res = cloned;
        }
        
        // Traverse the right subtree of both trees simultaneously.
        helper(original.right, cloned.right, target);
    }

    // better method: This approach avoids global state and uses "early exit" to improve performance.
    public final TreeNode getTargetCopy1(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // Base case: reached a leaf's child or tree is empty.
        if (original == null || target == null) {
            return null;
        }
        // If we found the target in the original tree, return the current cloned node immediately.
        if (original == target) {
            return cloned;
        }
        
        // Search in the left subtree.
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        
        // If the target was found in the left subtree (non-null), propagate it up.
        if (left != null) {
            return left;
        }
        
        // Otherwise, the result must be in the right subtree (or null if not found at all).
        return getTargetCopy(original.right, cloned.right, target);
    }
}
