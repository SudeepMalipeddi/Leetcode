/*
Problem Statement:
- Return N-ary tree level-order traversal as list of levels.

Intuition:
- BFS naturally groups nodes by distance from root.

Approach:
- Use queue, process current queue size as one level, enqueue every child.
- Correctness concern: queue is never seeded with root, so current implementation always returns empty for non-null root.

Time Complexity:
- O(n) visiting each node once.

Space Complexity:
- O(w) queue space.

Edge Cases:
- Null root returns empty list.

Dry Run:
- Root level added first, then its children form the second level.
*/
import java.util.*;

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

public class NaryTreeLevelOrderTraversal429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (root == null)
            return res;

        Queue<Node> q = new LinkedList<>();
        
        
        // BFS level traversal; q should contain root before this loop starts.
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> lvl = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                lvl.add(cur.val);
                
                for (Node child : cur.children) {
                    q.offer(child);
                }
            }
            res.add(lvl);
        }
        return res;
    }
}
