class Solution {

    public int calPoints(String[] operations) {
        Stack<Integer> s = new Stack<>();
        for (String op : operations) {
            if (op.equals("C")) {
                if (!s.isEmpty())
                    s.pop();
            } else if (op.equals("D")) {
                if (!s.isEmpty())
                    s.push(2 * s.peek());
            } else if (op.equals("+") && s.size() >= 2) {
                int top1 = s.pop();
                int top2 = s.peek();
                s.push(top1);
                s.push(top1 + top2);
            } else {
                s.push(Integer.valueOf(op));
            }
        }

        int sum = 0;
        while (!s.isEmpty())
            sum += s.pop();
        return sum;
    }

}