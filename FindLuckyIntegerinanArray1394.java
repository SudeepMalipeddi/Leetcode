import java.util.HashMap;

public class FindLuckyIntegerinanArray1394 {
    public int findLucky(int[] arr) {
        int res = -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : arr) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (int x : map.keySet()) {
            if (map.get(x) == x) {
                res = Math.max(res, x);
            }
        }
        return res;
    }

    public int findLucky2(int[] arr) {
        int[] count = new int[501];
        for (int x : arr) {
            count[x]++;
        }
        for (int i = 500; i > 0; i--) {
            if (count[i] == i) {
                return i;
            }
        }
        return -1;
    }
}
