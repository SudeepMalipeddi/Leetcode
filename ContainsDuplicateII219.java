import java.util.HashMap;

public class ContainsDuplicateII219 {
    /*
     * Problem: LeetCode 219 - Contains Duplicate II
     * Problem Statement: Return true if there are two equal elements whose
     *   indices differ by at most k.
     * Intuition: Track the most recent index of each value; if the distance to
     *   the current index is <= k, a valid pair exists.
     * Approach:
     *   1) Use a HashMap from value to last seen index.
     *   2) For each index i, if the value was seen and i - lastIndex <= k, return true.
     *   3) Update the last seen index.
     * Time Complexity: O(n) average with hashing.
     * Space Complexity: O(n) for the map.
     * Edge Cases: k=0, all unique elements, repeated elements far apart.
     * Dry Run: nums=[1,2,3,1], k=3 -> 1 seen at 0, i=3 diff=3 => true.
     * Correctness Check: The map always stores the closest previous index, so
     *   any qualifying pair is detected when it appears.
     */
    public boolean containsNearByDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // value -> last index

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i); // update most recent index
        }
        return false;
    }
}
