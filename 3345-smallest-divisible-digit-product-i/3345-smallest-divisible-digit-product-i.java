class Solution {
    public int smallestNumber(int n, int t) {
        for (int num = n; num <= 100; num++) {
            if (product(num) % t == 0)
                return num;
        }
        return 0;
    }

    public int product(int num) {
        int ans = 1;
        while (num > 0) {
            ans *= num % 10;
            num = num / 10;
        }
        return ans;
    }
}