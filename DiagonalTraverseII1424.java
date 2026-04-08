import java.util.*;

public class DiagonalTraverseII1424 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> diagonals = new HashMap<>();
        int total = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                diagonals.computeIfAbsent(i + j, k -> new ArrayList<>()).add(nums.get(i).get(j));
                total++;
            }
        }
        int[] result = new int[total];
        int idx = 0, d = 0;
        while (diagonals.containsKey(d)) {
            List<Integer> diag = diagonals.get(d);
            for (int i = diag.size() - 1; i >= 0; i--) {
                result[idx++] = diag.get(i);
            }
            d++;
        }
        return result;
    }
}
