public class CounttheNumberofPowerfulIntegers2999 {
    public static long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        long count = 0;
        for (long i = start; i <= finish; i++) {
            String curr = String.valueOf(i);
            if (!curr.endsWith(s)) {
                continue;
            }
            boolean valid = true;
            for (char c : curr.toCharArray()) {
                if ((c - '0') > limit) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "20";
        long start = 20, finish = 1159;
        int limit = 5;
        // for (int i = 0; i <= limit; i++) {
        // // a = String.valueOf(i);
        // String prefix = String.format("", i);
        // String concatenated = String.valueOf(i).concat(s);
        // if (Integer.parseInt(concatenated) < finish && Integer.parseInt(concatenated)
        // > start) {
        // count++;
        // System.out.println(concatenated);
        // }
        // }

        // while()

        long n = numberOfPowerfulInt(start, finish, limit, s);
        System.out.println(n);
    }
}
