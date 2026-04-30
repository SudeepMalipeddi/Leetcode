/*
 * Problem: LeetCode 57 - Insert Interval
 *
 * Problem Statement:
 * Given a set of non-overlapping intervals sorted by their start time, insert a new interval 
 * into the intervals (merge if necessary). The resulting list must remain sorted and non-overlapping.
 *
 * Intuition:
 * Since the input is already sorted, we can process the intervals in a single linear pass. 
 * The problem naturally divides into three phases: intervals that end before the new one starts, 
 * intervals that overlap with the new one, and intervals that start after the new one ends.
 *
 * Approach:
 * 1. Add all intervals that completely precede the new interval (where interval.end < newInterval.start).
 * 2. For all intervals that overlap with the new interval (where interval.start <= newInterval.end), 
 *    merge them by updating the new interval's start to the minimum and its end to the maximum.
 * 3. Add the resulting merged new interval to the list.
 * 4. Add all remaining intervals that completely follow the merged interval.
 *
 * Time Complexity: O(n), where n is the number of intervals, as we iterate through the list exactly once.
 * Space Complexity: O(n) to store the result list before converting it back to an array.
 *
 * Edge Cases:
 * - Empty intervals array: The new interval is simply added.
 * - New interval at the very beginning or very end: Handled by the first or last while loops.
 * - New interval overlaps all existing intervals: Handled by the middle merging loop.
 *
 * Dry Run:
 * intervals = [[1,3], [6,9]], newInterval = [2,5]
 * 1. Phase 1: [1,3] ends at 3, newInterval starts at 2. 3 < 2 is false. Skip.
 * 2. Phase 2 (Overlap): [1,3] starts at 1, newInterval ends at 5. 1 <= 5 is true.
 *    Merge: newInterval becomes [min(1,2), max(3,5)] = [1,5]. i moves to 1.
 *    Next: [6,9] starts at 6, newInterval ends at 5. 6 <= 5 is false. Loop ends.
 * 3. Add [1,5] to result.
 * 4. Phase 3: Add remaining [6,9].
 * Result: [[1,5], [6,9]]
 *
 * Correctness Check:
 * The solution correctly handles the sorted property and merging logic. The use of 
 * Math.min/max ensures that the merged interval covers the full span of all overlapping segments.
 */
import java.util.ArrayList;
import java.util.List;

public class InsertInterval57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int l = 0, r = intervals.length - 1;
        List<int[]> res = new ArrayList<>();
        int i = 0, n = intervals.length;
        
        // Phase 1: Add all intervals that end before the new interval starts.
        // These intervals do not overlap and should remain unchanged.
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i++]);
        }
        
        // Phase 2: Merge all overlapping intervals.
        // An interval overlaps if its start time is less than or equal to the new interval's end time.
        // Note: We already know these intervals don't end before the new interval starts from Phase 1.
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // Expand the new interval to cover the current overlapping interval.
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        
        // Add the final merged version of the new interval.
        res.add(newInterval);
        
        // Phase 3: Add all remaining intervals that start after the merged interval ends.
        while (i < n) {
            res.add(intervals[i++]);
        }
        
        // Convert the dynamic list back to the required 2D array format.
        return res.toArray(new int[res.size()][]);
    }
}
