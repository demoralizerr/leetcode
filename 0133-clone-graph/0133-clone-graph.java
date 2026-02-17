/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        Queue<Node> q = new LinkedList<>();
        Map<Node,Node> oldToNew = new HashMap<>();
        if(node==null) return null;

        oldToNew.put(node, new Node(node.val));
        q.offer(node);

        //BFS
         while(!q.isEmpty()){
            Node curr = q.poll();
            for(Node nei : curr.neighbors){
                //check if already cloned or not
                if(!oldToNew.containsKey(nei)){
                    oldToNew.put(nei, new Node(nei.val));
                    q.offer(nei);
                }
                //add current popped neighbours into its cloned node neighbours
                oldToNew.get(curr).neighbors.add(oldToNew.get(nei));
            }
         }
         return oldToNew.get(node);
    }
}