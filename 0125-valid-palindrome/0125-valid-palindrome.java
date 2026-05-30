class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder res = new StringBuilder();

        String str = s.toLowerCase();
        for (char ch : str.toCharArray()) {
            if (ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9')
                res.append(ch);
        }
        int l = 0;
        int r = res.toString().length() - 1;

        while (l < r) {
            char lch = res.charAt(l);
            char rch = res.charAt(r);
            if (lch != rch) {
                return false;
            }

            l++;
            r--;
        }
        return true;
    }
}