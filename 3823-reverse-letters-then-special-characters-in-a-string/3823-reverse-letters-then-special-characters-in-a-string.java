class Solution {
    public String reverseByType(String s) {
        return reverseSpecialChars(reverseLetters(s));
    }

    private String reverseLetters(String s) {
        StringBuilder sb = new StringBuilder(s);
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            char lch = s.charAt(l);
            char rch = s.charAt(r);
            if (Character.isLetter(lch) && Character.isLetter(rch)) {
                //reverse
                sb.setCharAt(l, rch);
                sb.setCharAt(r, lch);
                l++;
                r--;
            } else if (!Character.isLetter(lch))
                l++;
            else if (!Character.isLetter(rch))
                r--;
        }
        return sb.toString();

    }

    private String reverseSpecialChars(String s) {
        StringBuilder sb = new StringBuilder(s);
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            char lch = s.charAt(l);
            char rch = s.charAt(r);
            if (isSpecialChar(lch) && isSpecialChar(rch)) {
                //reverse
                sb.setCharAt(l, rch);
                sb.setCharAt(r, lch);
                l++;
                r--;
            } else if (!isSpecialChar(lch))
                l++;
            else if (!isSpecialChar(rch))
                r--;
        }
        return sb.toString();
    }

    private boolean isSpecialChar(char ch) {
        return ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&' || ch == '*'
                || ch == '(' || ch == ')';
    }

}