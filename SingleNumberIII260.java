import java.util.HashMap;

public class SingleNumberIII260 {
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        int i = 0;
        for (int x : map.keySet()) {
            if (map.get(x) == 1) {
                res[i++] = x;
            }
        }
        return res;
    }
}
