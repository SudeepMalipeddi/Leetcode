/*
Problem Statement:
- Remove exactly one occurrence of digit from numeric string to maximize resulting number.

Intuition:
- Try each removable position and keep maximum, or greedily remove first position improving next digit.

Approach:
- removeDigit enumerates candidates with StringBuilder and selects max via BigInteger; removeDigit1 uses greedy break rule.
- Improvement idea: use removeDigit1 as primary to avoid BigInteger conversions and candidate list allocation.

Time Complexity:
- Candidate method O(m^2); greedy method O(m).

Space Complexity:
- O(m) candidate storage.

Edge Cases:
- If multiple identical best removals exist, either max string is acceptable.

Dry Run:
- num="1231", digit='1' -> candidates "231" and "123", choose "231".
*/
import java.math.BigInteger;
import java.util.*;

public class RemoveDigitFromNumbertoMaximizeResult2259 {

    
    public String removeDigit(String num, char digit) {

        ArrayList<String> a1 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(num);
        
        for (int i = 0; i < sb.length(); i++) {
            
            // Generate a candidate by deleting this occurrence of digit.
            if (sb.charAt(i) == digit) {
                sb.deleteCharAt(i);
                a1.add(sb.toString());
                sb = new StringBuilder(num);
            }
        }
        BigInteger res = BigInteger.ZERO;
        
        for (int i = 0; i < a1.size(); i++) {
            BigInteger no = new BigInteger(a1.get(i));
            
            if (no.compareTo(res) > 0) {
                res = no;
            }
        }
        return res.toString();
    }

    
    public String removeDigit1(String number, char digit) {
        int n = number.length(), index = 0;
        
        for (int i = 0; i < n; i++) {
            
            if (number.charAt(i) == digit) {
                index = i;
                
                // Greedy choice: removing here improves the next digit immediately.
                if (i < n - 1 && number.charAt(i + 1) > digit) {
                    break;
                }
            }
        }
        return number.substring(0, index) + number.substring(index + 1);
    }

}
