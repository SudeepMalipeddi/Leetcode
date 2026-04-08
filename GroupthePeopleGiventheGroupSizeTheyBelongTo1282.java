import java.util.*;

public class GroupthePeopleGiventheGroupSizeTheyBelongTo1282 {
    public List<List<Integer>> groupThePeople(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int groupSize = nums[i];
            if (!map.containsKey(groupSize)) {
                map.put(groupSize, new ArrayList<>());
            }
            map.get(groupSize).add(i);
            if (map.get(groupSize).size() == groupSize) {
                res.add(new ArrayList<>(map.get(groupSize)));
                map.remove(groupSize);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GroupthePeopleGiventheGroupSizeTheyBelongTo1282 solution = new GroupthePeopleGiventheGroupSizeTheyBelongTo1282();
        int[] nums = { 3, 3, 3, 3, 3, 1, 3 };
        List<List<Integer>> result = solution.groupThePeople(nums);
        for (List<Integer> group : result) {
            System.out.println(group);
        }
    }
}
