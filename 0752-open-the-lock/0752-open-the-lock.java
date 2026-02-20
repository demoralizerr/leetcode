class Solution {
    private Set<String> visit;

    class Pair {
        String lock;
        int turns;

        public Pair(String lock, int turns) {
            this.lock = lock;
            this.turns = turns;
        }
    }

    public int openLock(String[] deadends, String target) {
        this.visit = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        for (String end : deadends)
            visit.add(end);
        if (visit.contains("0000"))
            return -1;

        q.offer(new Pair("0000", 0));
        return bfs(q, target);
    }

    private int bfs(Queue<Pair> q, String target) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.lock.equals(target)) {
                //found target
                return p.turns;
            }
            for (String child : childrens(p.lock)) {
                if (!visit.contains(child)) {
                    visit.add(child);
                    q.offer(new Pair(child, p.turns + 1));
                }
            }
        }
        return -1;
    }

    private List<String> childrens(String lock) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int digit = lock.charAt(i) - '0';
            //turn up
            String up = lock.substring(0, i) + (digit + 1) % 10 + lock.substring(i + 1);
            res.add(up);

            //turn down
            String down = lock.substring(0, i) + (digit - 1 + 10) % 10 + lock.substring(i + 1);
            res.add(down);
        }
        return res;
    }
}