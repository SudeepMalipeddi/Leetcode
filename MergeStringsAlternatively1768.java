public class mergeStringsAlternatively1768 {
    // This is the solution to the question of mergeStringsAlternatively1768
    public String mergeAlternately(String word1, String word2) {
        // Creating a StringBuilder object
        StringBuilder res = new StringBuilder();
        // Creating a while loop
        int i = 0;
        while(i < word1.length() || i<word2.length()){

            if( i < word1.length()){
                res.append(word1.charAt(i));
            }
            if( i < word2.length()){
                res.append(word2.charAt(i));
            }
            i++;
        }
        return res.toString();
    }
    public static void main(String[] args){
        String word1 = "abc";
        String word2 = "pqrstuz";
        mergeStringsAlternatively1768 test = new mergeStringsAlternatively1768();
        System.out.println(test.mergeAlternately(word1, word2));
    }
}
