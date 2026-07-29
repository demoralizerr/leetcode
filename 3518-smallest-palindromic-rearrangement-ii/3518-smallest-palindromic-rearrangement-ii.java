import java.math.BigInteger;

class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int odd = 0;
        char mid = 0;
        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                mid = (char) ('a' + i);
            }
            freq[i] /= 2;
        }

        if (odd > 1) return "";

        int halfLen = n / 2;
        
        BigInteger totalPerms = countInitialPermutations(freq, halfLen);
        BigInteger bigK = BigInteger.valueOf(k);

        if (totalPerms.compareTo(bigK) < 0) return "";

        StringBuilder left = new StringBuilder();
        int currentTotal = halfLen;

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (freq[c] == 0) continue;

                BigInteger nextPerms = totalPerms
                    .multiply(BigInteger.valueOf(freq[c]))
                    .divide(BigInteger.valueOf(currentTotal));

                if (nextPerms.compareTo(bigK) < 0) {
                    bigK = bigK.subtract(nextPerms);
                } else {
                    left.append((char) ('a' + c));
                    freq[c]--;
                    currentTotal--;
                    totalPerms = nextPerms;
                    break;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);
        if ((n & 1) == 1) {
            ans.append(mid);
        }
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private BigInteger countInitialPermutations(int[] freq, int total) {
        BigInteger[] fact = new BigInteger[total + 1];
        fact[0] = BigInteger.ONE;
        for (int i = 1; i <= total; i++) {
            fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
        }

        BigInteger ways = fact[total];
        for (int f : freq) {
            if (f > 1) {
                ways = ways.divide(fact[f]);
            }
        }
        return ways;
    }
}
