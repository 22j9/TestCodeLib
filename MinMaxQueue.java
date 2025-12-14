import java.util.*;
import java.math.*;

class MinMaxQueue {
    private Deque<Integer> queue;
    private Deque<Integer> minQueue;
    private Deque<Integer> maxQueue;

    public MinMaxQueue() {
        queue = new ArrayDeque<>();
        minQueue = new ArrayDeque<>();
        maxQueue = new ArrayDeque<>();
    }

    public void enqueue(int element) {
        if (queue.size() == 0) {
            queue.addLast(element);
            minQueue.addLast(element);
            maxQueue.addLast(element);
        } else {
            queue.addLast(element);
            
            // For minimum deque: pop elements from back until the element at back is greater than current
            while (!minQueue.isEmpty() && minQueue.peekLast() > element) {
                minQueue.removeLast();
            }
            minQueue.addLast(element);
            
            // For maximum deque: pop elements from back until the element at back is smaller than current
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < element) {
                maxQueue.removeLast();
            }
            maxQueue.addLast(element);
        }
    }

    public int dequeue() {
        int removed = queue.peekFirst();
        
        queue.removeFirst();
        
        if (!minQueue.isEmpty() && removed == minQueue.peekFirst()) {
            minQueue.removeFirst();
        }
        
        if (!maxQueue.isEmpty() && removed == maxQueue.peekFirst()) {
            maxQueue.removeFirst();
        }
        
        return removed;
    }

    public int getMin() {
        return minQueue.peekFirst();
    }

    public int getMax() {
        return maxQueue.peekFirst();
    }
}
