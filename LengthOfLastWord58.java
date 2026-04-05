class Length_of_Last_Word58{
    public int lengthOfLastWord(String s) {
        // trim() removes leading and trailing spaces
        // lastIndexOf(" ") returns the index of the last space
        s = s.trim();
        // return the length of the string minus the index of the last space minus 1
        // this is because we want the length of the last word
        // and we can't have the space in the count
        // we subtract 1 because the index starts at 0
        // and the length starts at 1
        // so we need to subtract 1 to get the correct length
        return s.length()-s.lastIndexOf(" ")-1;
    }
    public int lastwordlength(String s){
        int length = 0;
        for(int i = s.length()-1; i>=0; i--){
            if(s.charAt(i)!=' '){
                length++;
            }
            else{
                if(length > 0){
                    return length;
                }
            }
        }
        return length;
    }
}