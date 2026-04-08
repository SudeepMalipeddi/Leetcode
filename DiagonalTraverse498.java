import java.util.*;

public class DiagonalTraverse498 {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[m * n];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = i + j;
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(mat[i][j]);
            }
        }
        int i = 0;
        int count = 0;
        for (Integer x : map.keySet()) {
            if (count % 2 != 0) {
                Collections.reverse(map.get(x));
            }
            for (Integer y : map.get(x)) {
                res[i++] = y;
            }
            count++;
        }
        return res;
    }
}
