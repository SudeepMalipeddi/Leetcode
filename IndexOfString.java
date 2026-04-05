public class index_of_string {
    public int strStr(String haystack, String needle){
        int hlen = haystack.length();
        int nlen = needle.length();
        int nindex = 0;
        for(int i = 0; i < hlen; i++){
            // as long as the characters are equal increment nindex
            if(haystack.charAt(i) == needle.charAt(nindex)){
                nindex++;
            }
            else{
                // start from the next index of the previous start index
                i = i - nindex;
                // needle should start from index 0
                nindex = 0;
            }
            // check if nindex reached needle length
            if(nindex == nlen){
                return i-nlen+1;
            }
        }
        return -1;
    }
}
