/*
 * Problem: LeetCode 173 - Binary Search Tree Iterator
 * Problem Statement: Implement an iterator over a BST that returns the next
 *   smallest number in average O(1) time and O(h) space.
 * Intuition: An in-order traversal of a BST yields values in sorted order; a
 *   stack can simulate the recursion.
 * Approach:
 *   1) Push all left nodes from the root to the stack.
 *   2) next(): pop the top node (next smallest), then push all left nodes of its
 *      right child.
 *   3) hasNext(): check if the stack is non-empty.
 * Time Complexity: Amortized O(1) per next/hasNext; each node is pushed/popped once.
 * Space Complexity: O(h) for the stack, where h is tree height.
 * Edge Cases: Empty tree, skewed tree (stack size up to n).
 * Dry Run: BST 2 with left=1, right=3 -> stack [2,1], next=1, then next=2, then 3.
 * Correctness Check: The stack always holds the path to the next unvisited node
 *   in in-order, so iteration returns values in ascending order.
 */
import java.util.*;

public class BinarySearchTreeIterator173 {
    Stack<TreeNode> stack = new Stack<>(); // stack of left spine nodes

    public BinarySearchTreeIterator173(TreeNode root) {
        pushAll(root); // preload with the smallest path
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        TreeNode node = stack.pop(); // next smallest in in-order
        pushAll(node.right); // explore right subtree's left spine
        return node.val;
    }

    public void pushAll(TreeNode node) {
        while (node != null) {
            stack.push(node); // defer visiting node until its left side is done
            node = node.left;
        }
    }

}
