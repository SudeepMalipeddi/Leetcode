/*
 * Problem: 2471. Minimum Number of Operations to Sort a Binary Tree by Level
 *
 * Problem Statement:
 * Given the root of a binary tree with unique values, return the minimum number of operations 
 * to make every level of the tree sorted in strictly increasing order. In one operation, 
 * you can choose any two nodes at the same level and swap their values.
 *
 * Intuition:
 * Each level of the tree is independent of the others. The problem reduces to finding the 
 * minimum number of swaps to sort an array for each level. This is a classic problem that 
 * can be solved by identifying cycles in the permutation or by using a hash map to track 
 * the current positions of elements and swapping them into their correct sorted positions.
 *
 * Approach:
 * 1. Use Breadth-First Search (BFS) to traverse the tree level by level.
 * 2. For each level, store the node values in an array.
 * 3. To find the minimum swaps for a level:
 *    a. Create a sorted version of the level array to know the target state.
 *    b. Use a Map to store the current index of each value in the original array.
 *    c. Iterate through the array. If the current element is not the target element, 
 *       swap it with the element currently at the target's position and update the map.
 * 4. Accumulate the swap counts from all levels.
 *
 * Time Complexity: O(N log N), where N is the number of nodes. BFS visits every node once O(N). 
 * Sorting each level of size W takes O(W log W). Summing across all levels gives O(N log N).
 *
 * Space Complexity: O(N) to store the queue for BFS and the arrays/maps for each level.
 *
 * Edge Cases:
 * - Single node tree: 0 operations needed.
 * - Tree already sorted by level: 0 operations needed.
 * - Tree with levels in reverse order: Maximum swaps needed.
 *
 * Dry Run:
 * Level values: [7, 6, 8, 5]
 * Target sorted: [5, 6, 7, 8]
 * Map: {7:0, 6:1, 8:2, 5:3}
 * i=0: target[0]=5, original[0]=7. Swap 7 and 5. original: [5, 6, 8, 7]. Map: {5:0, 6:1, 8:2, 7:3}. Swaps=1.
 * i=1: target[1]=6, original[1]=6. No swap.
 * i=2: target[2]=7, original[2]=8. Swap 8 and 7. original: [5, 6, 7, 8]. Map: {5:0, 6:1, 7:2, 8:3}. Swaps=2.
 * i=3: target[3]=8, original[3]=8. No swap.
 * Total swaps for level = 2.
 *
 * Correctness Check:
 * The logic correctly identifies the minimum swaps for unique values. Note that the problem 
 * statement guarantees unique values, which is critical for the Map-based index tracking.
 */
import java.util.*;

public class MinimumNumberofOperationstoSortaBinaryTreebyLevel2471 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;
        }
    }

    public int minimumOperations(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int totalSwaps = 0;
        
        // Standard BFS traversal to process the tree level by level.
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int[] levelValues = new int[levelSize];
            
            // Extract all values at the current level.
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelValues[i] = node.val;

                
                if (node.left != null)
                    queue.add(node.left);
                
                if (node.right != null)
                    queue.add(node.right);
            }
            // Calculate minimum swaps needed to sort the current level's values.
            totalSwaps += getMinSwaps(levelValues);
        }
        return totalSwaps;
    }

    private int getMinSwaps(int[] original) {
        int swaps = 0;
        int[] target = original.clone();
        // Sort the clone to determine the final desired position of each element.
        Arrays.sort(target);

        // Map each value to its current index in the 'original' array for O(1) lookups.
        Map<Integer, Integer> pos = new HashMap<>();
        
        for (int i = 0; i < original.length; i++) {
            pos.put(original[i], i);
            
        }
        
        for (int i = 0; i < original.length; i++) {
            
            // If the current element is not what it should be in a sorted array...
            if (original[i] != target[i]) {
                swaps++;
                // Find where the correct element (target[i]) is currently located.
                int curPos = pos.get(target[i]);
                // Update the map: the element currently at original[i] is moving to curPos.
                pos.put(original[i], curPos);
                // Perform the swap in the original array.
                original[curPos] = original[i];
                // Note: We don't need to update original[i] because we move to i+1 next.
            }
        }
        return swaps;
    }

    // Alternative O(N^2) swap calculation using selection sort logic.
    // This is less efficient than the Map-based approach for large levels.
    public static int findSwaps(List<Integer> list) {
        int res = 0;
        
        for (int i = 0; i < list.size(); i++) {
            int min = i;
            
            for (int j = i + 1; j < list.size(); j++) {
                
                if (list.get(j) < list.get(min)) {
                    min = j;
                }
            }
            
            if (min != i) {
                res++;
                swap(list, min, i);
            }
        }
        return res;
    }

    public static void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
