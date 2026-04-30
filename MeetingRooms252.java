/*
 * Problem: 252. Meeting Rooms
 *
 * Problem Statement:
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...],
 * determine if a person could attend all meetings. This is equivalent to checking if any
 * two intervals overlap.
 *
 * Intuition:
 * If we sort the meetings by their start times, we only need to check if a meeting starts
 * before the previous one ends. Sorting turns a potential O(N^2) comparison of all pairs
 * into a linear O(N) scan of adjacent pairs.
 *
 * Approach:
 * 1. Sort the list of intervals based on the start time of each meeting.
 * 2. Iterate through the sorted intervals starting from the second meeting (index 1).
 * 3. Compare the current meeting's start time with the previous meeting's end time.
 * 4. If current.start < previous.end, an overlap exists; return false.
 * 5. If the loop completes without finding an overlap, return true.
 *
 * Time Complexity: O(n log n) where n is the number of intervals. The sorting step dominates the O(n) linear scan.
 * Space Complexity: O(1) or O(log n) depending on the sorting algorithm's internal stack space (e.g., Collections.sort uses Timsort).
 *
 * Edge Cases:
 * - Empty list: Returns true (no conflicts possible).
 * - Single meeting: Returns true (no conflicts possible).
 * - Meetings that "touch" (e.g., [1, 5] and [5, 10]): Returns true because the problem implies [start, end) intervals.
 *
 * Dry Run:
 * Input: [(0,30), (5,10), (15,20)]
 * 1. Sort by start: [(0,30), (5,10), (15,20)] (already sorted by start)
 * 2. i = 1: intervals.get(1).start (5) < intervals.get(0).end (30) -> True.
 * 3. Return false.
 *
 * Correctness Check:
 * The logic is correct for detecting overlaps in a set of intervals.
 * Note: The comparator `a.start - b.start` is used. While common, it can technically overflow if start times
 * are large and have opposite signs. `Integer.compare(a.start, b.start)` is the robust alternative.
 */

/*
 * Problem Statement:
 * Given meeting intervals [start, end), determine whether one person can attend all meetings
 * (i.e., no overlap between any two intervals).
 *
 * Intuition:
 * After sorting by start time, overlaps can only happen between neighboring intervals.
 *
 * Approach:
 * 1) Sort intervals by start.
 * 2) Scan from left to right.
 * 3) If current.start < previous.end, overlap exists -> return false.
 * 4) If no overlap found, return true.
 *
 * Time Complexity:
 * O(n log n) for sorting + O(n) scan.
 *
 * Space Complexity:
 * O(1) extra (ignoring sort internals).
 *
 * Edge Cases handled:
 * - Empty/single interval list -> trivially true.
 * - Touching meetings like [1,3] and [3,5] -> no overlap (allowed).
 *
 * Dry Run:
 * [(0,30),(5,10),(15,20)] -> sorted same order
 * compare (5,10) with (0,30): 5 < 30 => overlap => false
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 252 sort-then-scan strategy.
 *
 * Correctness Check:
 * Neighbor-only overlap check is sufficient after sorting by start.
 * Comparator note: current code uses (a.start - b.start), which can overflow;
 * Integer.compare(a.start, b.start) is safer (improvement note only).
 */

/* Given an array of meeting time interval objects consisting
 * of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), 
 * determine if a person could add all meetings to their schedule without any conflicts.
 * 
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: false
 * (0,30) and (5,10) will conflict
 * (0,30) and (15,20) will conflict
 * 
 * Input: intervals = [(5,8),(9,15)]
 * Output: true
 */

import java.util.*;

class Interval {
    public int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MeetingRooms252 {
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Sort intervals by start time. This ensures that if any overlap exists, 
        // the overlapping intervals must be adjacent in the sorted list.
        intervals.sort((a, b) -> a.start - b.start); // Improvement note: Integer.compare is overflow-safe.

        // Only adjacent intervals need checking once sorted.
        // We start the loop at index 1 to compare the current meeting with the previous one.
        for (int i = 1; i < intervals.size(); i++) {
            // If the current meeting starts before the previous meeting ends, there is an overlap.
            if (intervals.get(i).start < intervals.get(i - 1).end) {
                return false; // Found overlap, cannot attend all meetings.
            }
        }
        // If we iterate through the entire list without finding an overlap, all meetings are attendable.
        return true;
    }
}
