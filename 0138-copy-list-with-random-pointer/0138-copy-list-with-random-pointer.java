/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> oldToCopy = new HashMap<>();
        Node dummy = new Node(0);
        Node copy = dummy;
        Node curr = head;
        while (curr != null) {
            copy.next = new Node(curr.val);
            copy = copy.next;
            oldToCopy.put(curr, copy); // saving old node to copy node 
            curr = curr.next;
        }

        copy = dummy.next;
        curr = head;
        while (curr != null) {
            copy.random = oldToCopy.get(curr.random); //curr.random will point to an copy node in map
            copy = copy.next;
            curr = curr.next;
        }
        return dummy.next;
    }
}