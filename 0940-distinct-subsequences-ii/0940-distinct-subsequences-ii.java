class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        int n = s.length();
        
        // last[c] = value of dp right after the last time character c was processed
        long[] last = new long[26];
        Arrays.fill(last, -1);
        
        long dp = 1; // dp represents count of distinct subsequences so far, including empty subsequence
        
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            long newDp = (dp * 2) % MOD;
            
            if (last[c] != -1) {
                newDp = (newDp - last[c] + MOD) % MOD;
            }
            
            last[c] = dp;   // store dp value BEFORE this doubling, for next time we see c
            dp = newDp;
        }
        
        // subtract 1 to remove the empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}