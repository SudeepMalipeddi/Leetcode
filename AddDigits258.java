/**
 * LeetCode 258. Add Digits
 * Given an integer num, repeatedly add all its digits until the result has only
 * one digit, and return it.
 */
public class AddDigits258 {
    /**
     * Time Complexity: O(1) - uses mathematical digital root logic.
     * Space Complexity: O(1)
     */
    public int addDigits(int num) {
        // 0 returns 0
        if (num == 0)
            return 0;
        // Multiples of 9 have a digital root of 9
        else if (num % 9 == 0)
            return 9;
        // Otherwise, the digital root is num % 9
        return num % 9;
    }
}
