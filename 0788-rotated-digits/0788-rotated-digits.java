class Solution {
    public int rotatedDigits(int n) {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            String num = String.valueOf(i);

            // invalid check
            if (num.contains("3") || num.contains("4") || num.contains("7"))
                continue;

            // must have at least one changing digit
            if (num.contains("2") || num.contains("5") ||
                num.contains("6") || num.contains("9"))
                count++;
        }

        return count;
    }
}