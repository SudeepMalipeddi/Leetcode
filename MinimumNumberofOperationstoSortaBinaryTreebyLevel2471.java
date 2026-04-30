/*
Problem Statement:
- For each tree level, count minimum swaps required to sort level values; sum across levels.

Intuition:
- BFS isolates levels, then each level becomes an array minimum-swap problem.

Approach:
- Collect values level by level, sort a clone, and swap misplaced values using a value->index map.
- Correctness concern: getMinSwaps maps a value to one index, so duplicate values on a level can break index tracking.

Time Complexity:
- O(n log n) total due to per-level sorting across all nodes.

Space Complexity:
- O(w) queue + O(level) arrays/maps, where w is max width.

Edge Cases:
- Single-node trees return 0 operations.

Dry Run:
- Level [7,6,8] sorted is [6,7,8]; one swap fixes level, contributing 1.
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
        
        // Process tree level by level; each level can be sorted independently.
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int[] levelValues = new int[levelSize];
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelValues[i] = node.val;

                
                if (node.left != null)
                    queue.add(node.left);
                
                if (node.right != null)
                    queue.add(node.right);
            }
            totalSwaps += getMinSwaps(levelValues);
        }
        return totalSwaps;
    }

    private int getMinSwaps(int[] original) {
        int swaps = 0;
        int[] target = original.clone();
        Arrays.sort(target);

        Map<Integer, Integer> pos = new HashMap<>();
        
        for (int i = 0; i < original.length; i++) {
            pos.put(original[i], i);
            
        }
        
        for (int i = 0; i < original.length; i++) {
            
            // Swap current value into its sorted position using tracked indices.
            if (original[i] != target[i]) {
                swaps++;
                int curPos = pos.get(target[i]);
                pos.put(original[i], curPos);
                original[curPos] = original[i];
            }
        }
        return swaps;
    }

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
