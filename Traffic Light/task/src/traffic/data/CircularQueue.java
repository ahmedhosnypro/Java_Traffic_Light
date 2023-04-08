package traffic.data;

import traffic.system.Road;

public class CircularQueue {
    private final int capacity;
    private final Road[] elements;
    private int front;
    private int rear;
    private int count;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        elements = new Road[capacity];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    public boolean enqueue(Road element) {
        if (isFull()) {
            return false;
        }
        elements[rear] = element;
        rear = (rear + 1) % capacity;
        count++;
        return true;
    }

    public Road dequeue() {
        if (isEmpty()) {
            return null;
        }
        Road element = elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
        count--;
        return element;
    }

    public int size() {
        return count;
    }

    public int capacity() {
        return capacity;
    }

    public Road[] toArray() {
        if (isEmpty()) {
            return new Road[0];
        }
        Road[] array = new Road[count];
        int index = 0;
        for (int i = front; i < front + count; i++) {
            array[index++] = elements[i % capacity];
        }
        return array;
    }

    public String toString() {
        if (isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        var arr = toArray();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != capacity - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
