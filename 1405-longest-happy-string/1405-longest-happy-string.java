class Solution {
    class Pair {
        int count;
        char ch;

        public Pair(int count, char ch) {
            this.count = count;
            this.ch = ch;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        Queue<Pair> maxheap = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.count, p1.count));
        StringBuilder res = new StringBuilder();
        if (a > 0)
            maxheap.offer(new Pair(a, 'a'));
        if (b > 0)
            maxheap.offer(new Pair(b, 'b'));
        if (c > 0)
            maxheap.offer(new Pair(c, 'c'));

        while (!maxheap.isEmpty()) {
            Pair p1 = maxheap.poll();

            // check if last two chars are same as p1
            if (res.length() >= 2 && res.charAt(res.length() - 1) == p1.ch && res.charAt(res.length() - 2) == p1.ch) {
                // can't use p1, must use next
                if (maxheap.isEmpty())
                    break;
                Pair p2 = maxheap.poll();
                res.append(p2.ch);
                p2.count--;
                if (p2.count > 0)
                    maxheap.offer(p2);
                maxheap.offer(p1); // put p1 back
            } else {
                // append p1 twice if count >= 2, once if count == 1
                if (p1.count >= 2) {
                    res.append(p1.ch).append(p1.ch);
                    p1.count -= 2;
                } else {
                    res.append(p1.ch);
                    p1.count--;
                }
                if (p1.count > 0)
                    maxheap.offer(p1);
            }
        }

        return res.toString();
    }
}