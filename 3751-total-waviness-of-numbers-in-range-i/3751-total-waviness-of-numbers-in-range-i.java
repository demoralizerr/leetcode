class Solution {
    public int totalWaviness(int num1, int num2) {
        int wavy = 0;

        for (int num = num1; num <= num2; num++) {
            String numstr = String.valueOf(num);
            int len = numstr.length();
            if (len < 3)
                continue;

            for (int idx = 1; idx <= len - 2; idx++) {
                int middigit = numstr.charAt(idx) - '0';
                int ldigit = numstr.charAt(idx - 1) - '0';
                int rdigit = numstr.charAt(idx + 1) - '0';

                if (middigit > ldigit && middigit > rdigit)
                    wavy++;

                if (middigit < ldigit && middigit < rdigit)
                    wavy++;

            }
        }
        return wavy;

    }
}