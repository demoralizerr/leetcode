class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;   // total slots after the duplication trick
        int R = 2 * k;       // we choose 2k strictly increasing values
        if (R > N) return 0;

        long[] fact = new long[N + 1];
        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        long[] invFact = new long[N + 1];
        invFact[N] = power(fact[N], MOD - 2);
        for (int i = N; i > 0; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }

        return (int) (fact[N] * invFact[R] % MOD * invFact[N - R] % MOD);
    }

    private long power(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }
}