class test{
int[] plusOne(int[] digits){
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
public class plus_one66 {
    // int[] plusOne(int[] digits){
    //     int n = digits.length;
    //     digits[n-1]=digits[n-1] + 1;
    //     for(int i = n; i >= 0; --i){
    //         if(digits[i]<9){
    //             digits[i]++;
    //             return digits;
    //         }
    //         digits[i]=0;
    //     }
    //     int[] newNumber = new int[n+1];
    //     newNumber[0] = 1;
    //     return newNumber;
    // }
    public static void main(String[] args) {
        int[] digit = {9};
        int n = digit.length;
        for(int i = 0; i < n; i++){
            System.out.println(digit[i]);
        }
        test t = new test();
        t.plusOne(digit);
        // 
        System.out.println();
        for(int i = 0; i < digit.length; i++){
            System.out.println(digit[i]);
        }
    }
}
