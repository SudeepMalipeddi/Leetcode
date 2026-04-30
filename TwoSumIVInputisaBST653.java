/*
 * Problem: 653. Two Sum IV - Input is a BST
 *
 * Problem Statement:
 * Given the root of a Binary Search Tree (BST) and a target number k, return true if there 
 * exist two elements in the BST such that their sum is equal to the given target.
 *
 * Intuition:
 * A BST provides an implicit sorted structure. In a sorted array, the Two Sum problem is 
 * solved using two pointers (left and right). We can apply this same logic to a BST by 
 * either converting the tree to a sorted list or by using two iterators that traverse 
 * the tree in inorder (ascending) and reverse-inorder (descending) directions.
 *
 * Approach:
 * 1. Naive (findTarget1): Perform an inorder traversal to extract all values into a 
 *    sorted ArrayList. Use the standard two-pointer technique on the list.
 * 2. Optimized (findTarget2): Use a custom BSTIterator to simulate the two pointers 
 *    directly on the tree. One iterator starts at the smallest value (leftmost), 
 *    and the other starts at the largest value (rightmost).
 *
 * Time Complexity: O(N) for both approaches, as we may visit every node once.
 * Space Complexity: 
 * - Naive: O(N) to store the list of all node values.
 * - Optimized: O(H) where H is the tree height, to store the stack for the iterators.
 *
 * Edge Cases:
 * - Empty tree: Handled by null check.
 * - Target sum not possible: Handled by the while loop condition (l < r).
 * - Single node tree: The two-pointer logic naturally handles this as l will not be less than r.
 *
 * Dry Run:
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * 1. Left iterator starts at 2, Right iterator starts at 7.
 * 2. l=2, r=7. Sum = 9. 9 == 9? Yes. Return true.
 *
 * Correctness Check:
 * The solution is correct. The use of two iterators effectively implements the 
 * "Next" and "Before" functionality required for a two-pointer approach on a BST.
 */

import java.util.*;

class BSTIterator {
    // Stack stores the path to the current node to allow backtracking
    Stack<TreeNode> stack = new Stack<>();
    // reverse = false: standard inorder (smallest to largest)
    // reverse = true: reverse inorder (largest to smallest)
    boolean reverse = true;

    public BSTIterator(TreeNode root, boolean isReverse) {
        this.reverse = isReverse;
        pushAll(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Returns the next value in the traversal and updates the stack.
     */
    public int next() {
        TreeNode node = stack.pop();
        // If we are going forward, we need to explore the right subtree next.
        // If we are going backward, we need to explore the left subtree next.
        if (reverse == false) {
            pushAll(node.right);
        } else {
            pushAll(node.left);
        }
        return node.val;
    }

    /**
     * Helper to push all nodes along one edge (left for forward, right for backward).
     */
    public void pushAll(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = reverse ? node.right : node.left;
        }
    }
}

public class TwoSumIVInputisaBST653 {
    /**
     * Naive approach: Flatten the BST into a sorted list.
     */
    public boolean findTarget1(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int left = 0;
        int right = list.size() - 1;
        // Standard two-pointer approach on a sorted list
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * Optimized approach: Use two iterators to save space.
     */
    public boolean findTarget2(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        // Initialize two iterators: one from the start, one from the end
        BSTIterator left = new BSTIterator(root, false);
        BSTIterator right = new BSTIterator(root, true);
        
        int l = left.next();
        int r = right.next();

        // Move pointers inward until they meet or the target is found
        while (l < r) {
            if (l + r == k) {
                return true;
            } else if (l + r < k) {
                // Sum too small, move the left pointer forward
                l = left.next();
            } else {
                // Sum too large, move the right pointer backward
                r = right.next();
            }
        }
        return false;
    }
}
