public class ArrayQueue {
    private int[] q;
    private int   head;
    private int   tail;

    public ArrayQueue() {
        this.q = new int[10];
        this.head = 0;
        this.tail = 0;
    }

    public void enqueue(int i) {
        if (tail == q.length - 1 && head == 0) {
            resize(q.length * 2);
        } else if (tail == q.length - 1 && head != 0) {
            shift();
        }
        q[tail++] = i;
    }

    public int dequeue() {
        if (!empty()) {
            int res = q[head++];
            int len = tail - head;
            if (len == q.length / 4 && len != 0 && len > 5) {
                resize(len * 2);
            }
            return res;
        } else throw new NullPointerException("no elements in queue");
    }

    private void resize(int capacity) {
        int[] clone = new int[capacity];
        int   ind   = 0;
        for (int i = head; i < tail; i++) {
            clone[ind++] = q[i];
        }
        q = clone;
    }

    private void shift() {
        int ind = 0;
        for (int i = head; i < tail; i++) {
            q[ind++] = q[i];
        }
        head = 0;
        tail = ind;
    }

    public boolean empty() {
        return head == tail;
    }

    public static void main(String[] args) {
        ArrayQueue q = new ArrayQueue();
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
            if (i % 3 == 0) {
                q.dequeue();
            }
        }
        for (; !q.empty(); ) {
            System.out.println(q.dequeue());
        }
    }
}
