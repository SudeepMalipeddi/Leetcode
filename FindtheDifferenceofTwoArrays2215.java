/*
 * Problem: 2215. Find the Difference of Two Arrays
 *
 * Problem Statement:
 * Given two integer arrays nums1 and nums2, return a list of two lists:
 * 1. A list of all distinct integers in nums1 which are not present in nums2.
 * 2. A list of all distinct integers in nums2 which are not present in nums1.
 *
 * Intuition:
 * The problem requires finding elements that exist in one collection but not the other, while ensuring 
 * the output contains only unique values. HashSets are the ideal data structure here because they 
 * provide O(1) average time complexity for lookups (membership testing) and automatically handle 
 * deduplication of the input arrays.
 *
 * Approach:
 * 1. Convert nums1 into a HashSet (set1) and nums2 into a HashSet (set2). This removes duplicates 
 *    within each array and prepares them for fast searching.
 * 2. Iterate through the elements of set1. If an element is not present in set2, add it to the first result list.
 * 3. Iterate through the elements of set2. If an element is not present in set1, add it to the second result list.
 * 4. Return the two lists nested within a parent list.
 *
 * Time Complexity: O(N + M) where N is the length of nums1 and M is the length of nums2. 
 * We iterate through each array once to build the sets and each set once to find the differences.
 * Space Complexity: O(N + M) to store the unique elements of both arrays in HashSets.
 *
 * Edge Cases:
 * - Arrays with no common elements: Both sets will be returned in full (minus internal duplicates).
 * - Identical arrays: Both result lists will be empty.
 * - One array is a subset of the other: One of the result lists will be empty.
 *
 * Dry Run:
 * nums1 = [1,2,3,3], nums2 = [1,1,2,2]
 * set1 = {1, 2, 3}, set2 = {1, 2}
 * ans1: 1 (in set2? Yes), 2 (in set2? Yes), 3 (in set2? No -> add 3) -> [3]
 * ans2: 1 (in set1? Yes), 2 (in set1? Yes) -> []
 * Result: [[3], []]
 *
 * Correctness Check:
 * The solution is correct. It properly handles the "distinct" requirement by iterating over the Sets 
 * rather than the original arrays, and the logic for finding missing elements is sound.
 */
import java.util.*;

public class FindtheDifferenceofTwoArrays2215 {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        // ans1 and ans2 will store the unique elements found in one array but not the other
        List<Integer> ans1 = new ArrayList<>();
        List<Integer> ans2 = new ArrayList<>();
        
        // HashSets provide O(1) lookup and automatically handle deduplication of input values
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        
        // Convert the first array into a set to remove duplicates and enable fast membership checks
        for (int n : nums1) {
            set1.add(n);
        }
        
        // Convert the second array into a set
        for (int n : nums2) {
            set2.add(n);
        }
        
        // Check which elements of the first set are missing in the second set
        for (int n : set1) {
            if (set2.contains(n) == false) {
                ans1.add(n);
            }
        }
        
        // Check which elements of the second set are missing in the first set
        for (int n : set2) {
            if (set1.contains(n) == false) {
                ans2.add(n);
            }
        }
        
        // Construct the final list of lists as required by the method signature
        result.add(ans1);
        result.add(ans2);
        return result;
    }
}
