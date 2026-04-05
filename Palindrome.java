public class palindrome {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        long var = x;
        long temp = 0;
        long reverse=0;
        while(x>0){
            temp = x%10;
            reverse = ((reverse*10) + temp);
            x = x/10;
        }
        return (reverse == var);
    }
}
