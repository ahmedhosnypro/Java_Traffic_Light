package traffic;

class CircularQueue {
    private final int size;
    private final String[] elements;
    private int front;
    private int rear;
    private int count;

    public CircularQueue(int size) {
        this.size = size;
        elements = new String[size];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }

    public boolean enqueue(String element) {
        if (isFull()) {
            return false;
        }
        elements[rear] = element;
        rear = (rear + 1) % size;
        count++;
        return true;
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }
        String element = elements[front];
        elements[front] = null;
        front = (front + 1) % size;
        count--;
        return element;
    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[front];
    }


    public int size() {
        return count;
    }

    public int capacity() {
        return size;
    }

    public String toString() {
        if (isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (elements[i] != null) {
                sb.append(elements[i]);
                if (i != size - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
}
