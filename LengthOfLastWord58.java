/*
 * Problem Statement:
 * Given a string s containing words and spaces, return the length of its last word.
 * A word is a maximal substring of non-space characters.
 *
 * Intuition:
 * Ignore trailing spaces first; then measure characters after the last remaining space.
 * Both provided methods implement this idea differently.
 *
 * Approach:
 * lengthOfLastWord:
 * - trim() removes trailing/leading spaces.
 * - length - lastIndexOf(" ") - 1 gives last word length.
 *
 * lastwordlength:
 * - Scan from right to left.
 * - Count non-space chars until first separating space after counting started.
 *
 * Time Complexity (with concrete justification):
 * O(n) for both methods: each character is visited at most a constant number of times.
 *
 * Space Complexity (with concrete justification):
 * O(1) auxiliary for reverse scan method.
 * trim()-based method may allocate a trimmed string object proportional to input length.
 *
 * Edge Cases handled:
 * - Trailing spaces (e.g., "hello world   ").
 * - Single word without spaces.
 * - Multiple spaces between words.
 *
 * Dry Run (concrete example with state):
 * s = "   fly me   to   the moon  "
 * trimmed = "fly me   to   the moon"
 * last space index = 17, trimmed length = 22
 * answer = 22 - 17 - 1 = 4 ("moon")
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 58. Assumes at least one word exists, consistent with the problem constraint.
 *
 * Correctness Check:
 * Reverse-scan method avoids extra parsing and stops immediately once the last word length is finalized.
 * trim() method depends on Java String APIs and remains correct under valid constraints.
 */

class Length_of_Last_Word58{
    public int lengthOfLastWord(String s) {
        // Remove boundary spaces so the last character belongs to the last word.
        s = s.trim();
        // Last word starts right after the final space in the trimmed string.
        return s.length()-s.lastIndexOf(" ")-1;
    }
    public int lastwordlength(String s){
        int length = 0;
        // Walk backwards: count letters in the suffix word.
        for(int i = s.length()-1; i>=0; i--){
            if(s.charAt(i)!=' '){
                length++;
            }
            else{
                // Once counting has started, the first space means last word ended.
                if(length > 0){
                    return length;
                }
            }
        }
        return length;
    }
}
