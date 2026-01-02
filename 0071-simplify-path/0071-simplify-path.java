public class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");

        for (String cur : paths) {
            if (cur.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!cur.equals("") && !cur.equals(".")) {
                stack.push(cur);
            }
        }

        return "/" + String.join("/", stack);
    }
}