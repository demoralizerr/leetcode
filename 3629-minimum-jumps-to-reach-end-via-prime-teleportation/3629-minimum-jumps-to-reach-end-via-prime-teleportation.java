class Solution {

    public int minJumps(int[] nums) {
        int n = nums.length;

        if (n == 1)
            return 0;

        int max = 0;
        for (int x : nums)
            max = Math.max(max, x);

        int[] spf = smallestPrimeFactor(max);

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            Set<Integer> factors = getPrimeFactors(nums[i], spf);

            for (int f : factors) {
                map.computeIfAbsent(f, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.offer(0);
        visited[0] = true;

        Set<Integer> usedPrime = new HashSet<>();

        int steps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int i = q.poll();

                if (i == n - 1)
                    return steps;

                // adjacent left
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    q.offer(i - 1);
                }

                // adjacent right
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    q.offer(i + 1);
                }

                int val = nums[i];

                // teleport only if current value itself is prime
                if (isPrime(val, spf) && !usedPrime.contains(val)) {

                    usedPrime.add(val);

                    List<Integer> next = map.getOrDefault(val, new ArrayList<>());

                    for (int idx : next) {

                        if (!visited[idx]) {
                            visited[idx] = true;
                            q.offer(idx);
                        }
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    private int[] smallestPrimeFactor(int n) {

        int[] spf = new int[n + 1];

        for (int i = 0; i <= n; i++)
            spf[i] = i;

        for (int i = 2; i * i <= n; i++) {

            if (spf[i] == i) {

                for (int j = i * i; j <= n; j += i) {

                    if (spf[j] == j)
                        spf[j] = i;
                }
            }
        }

        return spf;
    }

    private boolean isPrime(int x, int[] spf) {
        return x > 1 && spf[x] == x;
    }

    private Set<Integer> getPrimeFactors(int x, int[] spf) {

        Set<Integer> set = new HashSet<>();

        while (x > 1) {
            set.add(spf[x]);
            x /= spf[x];
        }

        return set;
    }
}