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
        intervals.sort((a, b) -> a.start - b.start);
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start < intervals.get(i - 1).end) {
                return false;
            }
        }
        return true;
    }
}
