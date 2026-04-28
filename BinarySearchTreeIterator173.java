/*
 * Problem: LeetCode 173. Binary Search Tree Iterator
 *
 * Problem Statement:
 * Implement an iterator over a BST that returns the next smallest element on each call.
 *
 * Intuition:
 * Inorder traversal of a BST yields sorted order. A stack can simulate the traversal
 * lazily, pushing left paths and popping the next smallest node each time.
 *
 * Approach:
 * 1. On construction, push all nodes on the left spine of the root.
 * 2. next(): pop the top (smallest), then push all nodes on its right child's left spine.
 * 3. hasNext(): stack non-empty.
 *
 * Time Complexity:
 * - next()/hasNext() are amortized O(1) because each node is pushed/popped once.
 * Space Complexity: O(h) where h is the tree height for the stack.
 *
 * Edge Cases handled:
 * - Empty tree (stack empty).
 * - Skewed trees.
 *
 * Dry Run:
 * BST:    2
 *       /   \
 *      1     3
 * Init stack: [2,1] (top=1)
 * next()->1, pushAll(null), next()->2 (pushAll(3)), next()->3
 *
 * Correctness Check:
 * Inorder simulation via stack always yields the next smallest element.
 *
 * LeetCode Match:
 * LeetCode 173 - "Binary Search Tree Iterator".
 */
import java.util.*;

public class BinarySearchTreeIterator173 {
    Stack<TreeNode> stack = new Stack<>();

    public BinarySearchTreeIterator173(TreeNode root) {
        // Preload the left spine so the smallest element is on top
        pushAll(root);
    }

    public boolean hasNext() {
        // If stack is non-empty, there are still nodes to visit
        return !stack.isEmpty();
    }

    public int next() {
        // Pop the current smallest node
        TreeNode node = stack.pop();
        // After visiting node, visit its right subtree next (left spine first)
        pushAll(node.right);
        return node.val;
    }

    public void pushAll(TreeNode node) {
        // Push all left children to reach the next smallest element
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

}
