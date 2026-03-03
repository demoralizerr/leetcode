class Solution {
    public String trimTrailingVowels(String s) {
        StringBuilder res = new StringBuilder(s);
        int n = res.length();
        for (int r = n - 1; r >= 0; r--) {
            if (isVowel(res.charAt(r))) {
                res.deleteCharAt(r);
            } else
                break;
        }
        return res.toString();
    }

    private boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
            return true;
        else
            return false;
    }
}