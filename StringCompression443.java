public class StringCompression443 {
    public int compress(char[] chars) {
        int n = chars.length;
        if (n == 1) {
            return 1;
        }
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                sb.append(chars[i - 1]);
                if (count > 1) {
                    sb.append(count);
                }
                count = 1;
            }
        }
        sb.append(chars[n - 1]);
        if (count > 1) {
            sb.append(count);
        }
        int len = sb.length();
        for (int i = 0; i < len; i++) {
            chars[i] = sb.charAt(i);
        }
        return len;
    }
}
