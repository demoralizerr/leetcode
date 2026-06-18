class Solution {
    public char processStr(String s, long k) {
        long L = 0;

        for (char ch : s.toCharArray()) {
            if (ch >= 'a' && ch <= 'z')
                L++;
            else if (ch == '*')
                L = Math.max(0, L - 1);
            else if (ch == '#')
                L = 2 * L;
            else
                continue;
        }

        if (k >= L)
            return '.';

        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '*')
                L++;
            else if (ch == '#') {
                L = L / 2;
                if (k >= L)
                    k = k - L;

            } else if (ch == '%') {
                k = L - 1 - k;
            } else {
                L--;
                if (k == L)
                    return ch;
            }
        }

        return '.';
    }
}