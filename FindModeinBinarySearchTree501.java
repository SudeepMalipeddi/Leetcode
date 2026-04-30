/*
 * Problem Reference: LeetCode 501 - Find Mode in Binary Search Tree
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Traverse tree, count frequencies, track maximum count.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n) map or O(h) inorder variant
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
import java.util.ArrayList;
import java.util.List;

public class FindModeinBinarySearchTree501 {
    private int currentVal;
    private int currentCount = 0;
    private int maxCount = 0;
    private List<Integer> modes = new ArrayList<>();

    public int[] findMode(TreeNode root){
        InOrderTraversal(root);
        int[] result = new int[modes.size()];
        for(int i = 0; i < modes.size(); i++){
            result[i] = modes.get(i);
        }
        return result;
    }


    private void InOrderTraversal(TreeNode node){
        if(node == null){
            return;
        }
        // Inorder traversal on BST visits equal values consecutively, enabling frequency counting in one pass.
        InOrderTraversal((node.left));

        currentCount = (node.val == currentVal) ? currentCount + 1 : 1;

        if(currentCount == maxCount){
            modes.add(node.val);
        }
        else if(currentCount > maxCount){
            maxCount = currentCount;
            modes.clear();
            modes.add(node.val);
        }
        currentVal = node.val;

        InOrderTraversal(node.right);
    }
}
