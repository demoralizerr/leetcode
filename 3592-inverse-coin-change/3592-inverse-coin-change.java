class Solution {
    public List<Integer> findCoins(int[] numWays) {
        List<Integer> result = new ArrayList<>();
        // We use a long array to prevent overflow during calculations
        long[] currentWays = new long[numWays.length + 1];
        currentWays[0] = 1; // Base case: 1 way to make 0

        if (backtrack(1, numWays, currentWays, result)) {
            return result;
        }
        return new ArrayList<>(); // Return empty if no valid set found
    }

    private boolean backtrack(int amount, int[] numWays, long[] currentWays, List<Integer> result) {
        int n = numWays.length;
        if (amount > n) {
            // Final Validation: Check if every single index matches perfectly
            for (int i = 1; i <= n; i++) {
                if (currentWays[i] != numWays[i - 1]) return false;
            }
            return true;
        }

        // If currentWays is already higher than numWays, this path is dead
        if (currentWays[amount] > numWays[amount - 1]) return false;

        // Path A: The current amount IS a new coin
        if (currentWays[amount] < numWays[amount - 1]) {
            result.add(amount);
            long[] nextWays = updateWays(currentWays, amount, n);
            if (backtrack(amount + 1, numWays, nextWays, result)) return true;
            result.remove(result.size() - 1); // Undo
        } 
        
        // Path B: The current amount is NOT a new coin
        // Only possible if current ways already match target ways
        if (currentWays[amount] == numWays[amount - 1]) {
            if (backtrack(amount + 1, numWays, currentWays, result)) return true;
        }

        return false;
    }

    private long[] updateWays(long[] current, int coin, int n) {
        long[] next = current.clone();
        for (int j = coin; j <= n; j++) {
            next[j] += next[j - coin];
        }
        return next;
    }
}
