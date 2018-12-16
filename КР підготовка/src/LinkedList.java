public class LinkedList {

    private Node head;
    private int  size;

    public LinkedList() {
        head = new Node(0);
        this.head.next = this.head;
        this.head.prev = this.head;
    }

    public void add(int item) {
        Node x = new Node(item);
        x.next = head;
        head.prev.next = x;
        x.prev = head.prev;
        head.prev = x;
        x.item = item;
        ++size;
    }

    void push(Node c) {
        Node b = c.prev;
        c.next.prev = b;
        b.prev.next = c;
        b.next = c.next;
        c.next = b;
        c.prev = b.prev;
        b.prev = c;
    }

    void insertion_sort() {
        Node curr = head.next;
        Node next;
        while (curr != head) {
            next = curr.next;
            while (curr.prev != head) {
                if (curr.item > curr.prev.item) {
                    push(curr);
                } else {
                    break;
                }
            }
            curr = next;
        }
    }

    void sort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[i] > arr[j]) {
                    //swap

                } else break;
            }
        }
    }

    void print() {
        Node curr = head.prev;
        while (curr != head) {
            System.out.println(curr.item);
            curr = curr.prev;
        }
    }


    private class Node {
        Node prev;
        int  item;
        Node next;

        Node(int item) {
            this.item = item;
        }
    }

    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.add(2);
        l.add(1);
        l.add(4);
        l.add(3);
        l.insertion_sort();
        l.print();
    }
}
