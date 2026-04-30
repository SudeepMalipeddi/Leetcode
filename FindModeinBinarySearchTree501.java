/*
 * Problem: LeetCode 501 - Find Mode in Binary Search Tree
 *
 * Problem Statement:
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) 
 * (i.e., the most frequently occurred element) in it.
 *
 * Intuition:
 * An in-order traversal of a Binary Search Tree visits nodes in non-decreasing order. 
 * In a sorted sequence, all identical values are adjacent. This allows us to count 
 * frequencies of elements in a single pass without using a Hash Map for storage.
 *
 * Approach:
 * 1. Perform a recursive in-order traversal (Left -> Root -> Right).
 * 2. Maintain a 'currentCount' for the value currently being processed and a 'maxCount' for the highest frequency seen.
 * 3. If the current node's value matches the previous node's value, increment 'currentCount'. Otherwise, reset it to 1.
 * 4. If 'currentCount' equals 'maxCount', add the value to the results list.
 * 5. If 'currentCount' exceeds 'maxCount', clear the results list, update 'maxCount', and add the new mode.
 *
 * Time Complexity: O(N) where N is the number of nodes, as we visit each node exactly once.
 * Space Complexity: O(H) for the recursion stack, where H is the tree height (O(N) in the worst case of a skewed tree).
 *
 * Edge Cases:
 * - Empty tree: Handled by the null check in the traversal.
 * - Tree with all unique values: Every value will be added to the modes list with maxCount = 1.
 * - Tree where all values are the same: Only one value will be in the modes list with maxCount = N.
 *
 * Dry Run:
 * Input: [1, null, 2, 2] (In-order sequence: 1, 2, 2)
 * 1. Visit 1: currentCount=1, maxCount=1, modes=[1], currentVal=1.
 * 2. Visit 2: currentCount=1 (2 != 1), maxCount=1, modes=[1, 2], currentVal=2.
 * 3. Visit 2: currentCount=2 (2 == 2), currentCount > maxCount, maxCount=2, modes.clear(), modes=[2], currentVal=2.
 * Result: [2]
 *
 * Correctness Check:
 * The solution is correct. The initialization of 'currentVal' to 0 is safe because even if the first node 
 * is 0, 'currentCount' will correctly increment to 1.
 */
import java.util.ArrayList;
import java.util.List;

public class FindModeinBinarySearchTree501 {
    // State variables to track frequencies across recursive calls
    private int currentVal;
    private int currentCount = 0;
    private int maxCount = 0;
    private List<Integer> modes = new ArrayList<>();

    public int[] findMode(TreeNode root){
        // Initiate the in-order traversal to process nodes in sorted order
        InOrderTraversal(root);
        
        // Convert the dynamic list of modes into the required primitive array format
        int[] result = new int[modes.size()];
        for(int i = 0; i < modes.size(); i++){
            result[i] = modes.get(i);
        }
        return result;
    }


    private void InOrderTraversal(TreeNode node){
        // Base case: stop recursion when reaching a null child
        if(node == null){
            return;
        }
        
        // Step 1: Traverse the left subtree
        InOrderTraversal((node.left));

        // Step 2: Process the current node
        // Since it's an in-order traversal, identical values appear consecutively.
        // If the current value matches the previous one, increment the streak; otherwise, start a new streak.
        currentCount = (node.val == currentVal) ? currentCount + 1 : 1;

        if(currentCount == maxCount){
            // If this value is as frequent as the current modes, add it to the list
            modes.add(node.val);
        }
        else if(currentCount > maxCount){
            // If we found a new highest frequency, discard previous modes and update the record
            maxCount = currentCount;
            modes.clear();
            modes.add(node.val);
        }
        
        // Update currentVal so the next node in the traversal can compare against this one
        currentVal = node.val;

        // Step 3: Traverse the right subtree
        InOrderTraversal(node.right);
    }
}
