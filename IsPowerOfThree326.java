public class ispowerofthree326 {
    public boolean isPowerOfThree(int n){
        // Non recursive approach
        if( n == 0)
            return false;
        while( n % 3 == 0){
            n /= 3;
        }
        return n == 1;
    }
    public boolean isPowerOfThree1(int n){
        // Recursive approach
        if(n==1){
            return true;
        }
        if(n <=0 || n%3!=0){
            return false;
        }
        return isPowerOfThree(n/3);
    }   
}
