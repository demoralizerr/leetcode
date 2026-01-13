class Solution {
    public String decodeString(String s) {
        Stack<String> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == ']') {
                String substr = "";
                while (!stk.isEmpty() && !stk.peek().equals("[")) {
                    substr = stk.pop() + substr;
                }
                stk.pop(); //to pop '['

                String numstr = "";
                //collecting number k
                while (!stk.isEmpty() && Character.isDigit(stk.peek().charAt(0))) {
                    numstr = stk.pop() + numstr;
                }
                int k = Integer.parseInt(numstr);
                //add substr into stack k times
                String repeatedStr = substr.toString().repeat(k);
                stk.push(repeatedStr);

            } else
                stk.push(String.valueOf(s.charAt(i)));

        }

        StringBuilder res = new StringBuilder();
        while (!stk.isEmpty()) {
            res.insert(0, stk.pop());
        }
        return res.toString();

    }
}