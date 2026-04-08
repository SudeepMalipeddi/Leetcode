public class CountSymmetricIntegers2843 {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            String curr = String.valueOf(i);
            int n = curr.length();
            if (n % 2 != 0) {
                continue;
            }
            int res1 = 0;
            int res2 = 0;
            for (int j = 0; j < n / 2; j++) {
                res1 += (curr.charAt(j) - '0');
                res2 += (curr.charAt(n / 2 + j) - '0');
            }
            if (res1 == res2) {
                count++;
            }
        }
        return count;
    }
}
