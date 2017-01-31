
public class LinkedStackOfString {
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
