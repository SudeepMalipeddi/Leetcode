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
        intervals.sort((a, b) -> a.start - b.start); // Improvement note: Integer.compare is overflow-safe.

        // Only adjacent intervals need checking once sorted.
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start < intervals.get(i - 1).end) {
                return false; // Found overlap, cannot attend all meetings.
            }
        }
        return true;
    }
}
