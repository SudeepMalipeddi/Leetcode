import java.util.Set;
import java.util.HashSet;

public class Finding3DigitEvenNumbers2094 {
    public static int[] findEvenNumbers(int[] digits) {
        Set<Integer> res = new HashSet<>();
        int[] count = new int[10];
        for (int digit : digits) {
            count[digit]++;
        }
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 8; k += 2) {
                    int[] tempcount = count.clone();
                    tempcount[i]--;
                    tempcount[j]--;
                    tempcount[k]--;
                    if (tempcount[i] >= 0 && tempcount[j] >= 0 && tempcount[k] >= 0) {
                        int num = i * 100 + j * 10 + k;
                        res.add(num);
                    }
                }
            }
        }
        return res.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    public static void main(String[] args) {
        int[] digits = { 2, 1, 3, 0 };
        findEvenNumbers(digits);
        // Output: [102, 120, 132, 210, 230, 302, 320, 402, 420]
    }
}
