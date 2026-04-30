/*
Problem Statement:
- Count passengers older than 60 from encoded detail strings.

Intuition:
- Age is fixed at substring indices 11..12, so parsing is direct.

Approach:
- Extract age substring for each entry and increment count if age>60.

Time Complexity:
- O(n).

Space Complexity:
- O(1).

Edge Cases:
- Age exactly 60 is excluded by strict > 60 check.

Dry Run:
- "...75.." contributes, "...60.." does not.
*/
public class NumberofSeniorCitizens2678 {
    public static int countSeniors(String[] details) {
        int count = 0;
        
        for (String s : details) {
            // Input format fixes age at indices 11 and 12.
            int age = Integer.parseInt(s.substring(11, 13));
            
            if (age > 60)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        String[] deets = { "7868190130M7522", "5303914400F9211", "9273338290F4010" };
        System.out.println(countSeniors(deets));
    }
}
